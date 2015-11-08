package pontus.starlines.game;

import com.badlogic.gdx.graphics.Color;

public class Colors {
	
	public static final Color BACKGROUND = hex("BFCCFF");
	public static final Color OBJECTS = hex("969DFF");
	public static final Color OBJECTS_SHADOW = hex("5B719B");
	public static final Color TRAIL = hex("99FF96");
	public static final Color WALL = hex("FF6866");
	public static final Color WALL_SHADOW = hex("CE5452");
	public static final Color OUT_OF_SCREEN = hex("939DC4");
	public static final Color STAR = hex("FFF24D");
	public static final Color STAR_SHADOW = hex("A39B31");
	public static final Color STAR_GRAY = hex("E3E3E3");
	public static final Color STAR_GRAY_SHADOW = hex("919191");
	
	
	
	
	
	public static Color create(int r, int g, int b, int a) {
		return new Color(r / 255.0f, g / 255.0f, b / 255.0f, a / 255.0f);
	}

	public static Color hex(String colorStr) {
		return create(Integer.valueOf(colorStr.substring(0, 2), 16), Integer.valueOf(colorStr.substring(2, 4), 16), Integer.valueOf(colorStr.substring(4, 6), 16), 255);
	}

}
