package pontus.starlines.game.level;

import java.util.ArrayList;

public class LevelHandler {

	public static ArrayList<Level> levels = new ArrayList<Level>();
	
	public static int selected = 0;
	
	public static void addLevel(Level l) {
		levels.add(l);
	}
	
	public static Level get(int index) {
		return levels.get(index);
	}
	
	public static Level get(String name) {
		for (int i = 0; i < levels.size(); i++) {
			if (i >= levels.size()) break;
			if (get(i) != null && get(i).name == name) return get(i);
		}
		return null;
	}
	
	public static Level getSelected() {
		return get(selected);
	}
	
}
