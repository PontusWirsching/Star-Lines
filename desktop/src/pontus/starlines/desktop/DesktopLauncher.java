package pontus.starlines.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pontus.starlines.JustDont;
import pontus.starlines.JustDont.OS;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1080 / 3;
		config.height = 1920 / 3;
//		config.width = 1920;
//		config.height = 1080;
		config.resizable = true;
//		config.fullscreen = true;
		JustDont.currentOS = OS.WINDOWS;
		new LwjglApplication(new JustDont(), config);
	}
}
