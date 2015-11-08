package pontus.starlines.core.graphics;

import java.util.ArrayList;

public class ScreenManager {

	public static ArrayList<Screen> screens = new ArrayList<Screen>();
	
	public static int selected = 0;
	
	public static void add(Screen screen) {
		screens.add(screen);
	}
	
	public static void setSelected(String name) {
		for (Screen s : screens) {
			if (name.equals(s.name)) {
				selected = screens.indexOf(s);
				resize(0, 0);
			}
		}
	}
	
	public static void resize(int width, int height) {
		getSelected().resize(width, height);
	}
	
	public static Screen get(int index) {
		return screens.get(index);
	}
	
	public static Screen getSelected() {
		return get(selected);
	}
	
	public static void render(float delta) {
		getSelected().render(delta);
	}
	
}
