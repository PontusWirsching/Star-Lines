package pontus.starlines.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Point;
import pontus.starlines.game.level.Level;
import pontus.starlines.game.level.LevelHandler;

public class Parser {

	public static void parse(String path) {
		try {
			String data = path + ".xml";

			XmlReader reader = new XmlReader();
			Element root = reader.parse(Gdx.files.internal(data));

			
			Array<Element> items = root.getChildrenByName("level");

			for (Element e : items) {
				Point position = new Point();
				int page = 0;
				String name = e.get("name");
				String start = e.get("start");
				String finnish = e.get("finnish");
				String[] start2 = start.split(" ");
				Point startPos = new Point(Integer.parseInt(start2[0]), Integer.parseInt(start2[1]));
				Point startSize = new Point(Integer.parseInt(start2[2]), Integer.parseInt(start2[3]));
				String[] finnish2 = finnish.split(" ");
				Point finnishPos = new Point(Integer.parseInt(finnish2[0]), Integer.parseInt(finnish2[1]));
				float finnishRad = Integer.parseInt(finnish2[2]);
				
				Level l = new Level(new Rectangle(startPos.x, startPos.y, startSize.x, startSize.y), new Circle(finnishPos.x, finnishPos.y, finnishRad));
				l.name = name;
				
				
				
				
				Array<Element> walls = e.getChildrenByName("wall");
				
				for (Element wall : walls) {
					String[] p1 = wall.get("p1").split(" ");
					String[] p2 = wall.get("p2").split(" ");
					l.addWall(new Line(Float.parseFloat(p1[0]), Float.parseFloat(p1[1]), Float.parseFloat(p2[0]), Float.parseFloat(p2[1])));
				}
				Array<Element> stars = e.getChildrenByName("star");
				
				for (Element star : stars) {
					String[] p = star.get("p").split(" ");
					l.actualStars.add(new Point(Float.parseFloat(p[0]), Float.parseFloat(p[1])));
				}
				l.reset();
				
				
				LevelHandler.addLevel(l, position, page);
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
