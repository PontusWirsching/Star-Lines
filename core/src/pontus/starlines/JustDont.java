package pontus.starlines;

import java.util.Calendar;

import javax.swing.JOptionPane;

import pontus.starlines.core.Input;
import pontus.starlines.core.graphics.ScreenManager;
import pontus.starlines.game.Game;
import pontus.starlines.menu.Menu;
import pontus.starlines.prepare.LevelSelection;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

/**
 * Dont freakin steal it!
 * 
 * @author Pontus Wirsching
 *
 */
public class JustDont implements ApplicationListener {

	public static final float WIDTH = 1080 / 2;
	public static final float HEIGHT = 1920 / 2;

	public static enum OS {
		WINDOWS, ANDROID, IOS
	}

	public static OS currentOS = OS.WINDOWS;

	public static boolean RUN_GAME = true;
	
	@Override
	public void create() {

		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println("Date: " + year + "-" + month + "-" + day);

		int y = 2015;
		int m = 12;
		int d = 24;

		int t = (year - y) * 365 + (month - m) * (365 / 12) + (day - d);

		System.out.println("Years left: " + (year - y) + " Months left: " + (month - m) + " Days left: " + (day - d) + ", Total Days left: " + t);

		if (t >= 0) {
			if (currentOS == OS.ANDROID) {
				System.out.println("OS: Android");
				RUN_GAME = false;
			} else if (currentOS == OS.WINDOWS) {
				System.out.println("OS: Windows");
				RUN_GAME = false;
//				JOptionPane.showMessageDialog(null, "This is just the beta and won't be playable from now on!", "Beta no longer valid!", JOptionPane.ERROR_MESSAGE);
//				System.exit(0);
			}
		}

		ScreenManager.add(new Game());
		ScreenManager.add(new Menu());
		ScreenManager.add(new LevelSelection());

		ScreenManager.setSelected("GAME");

		Gdx.input.setInputProcessor(new Input());
	}


	@Override
	public void render() {
		Input.update();
		ScreenManager.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		ScreenManager.resize(width, height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
