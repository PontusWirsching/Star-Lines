package pontus.starlines;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import pontus.starlines.core.Input;
import pontus.starlines.core.graphics.ScreenManager;
import pontus.starlines.game.Game;
import pontus.starlines.menu.Menu;
import pontus.starlines.prepare.LevelSelection;

public class JustDont implements ApplicationListener {

	public static final float WIDTH = 1080 / 2;
	public static final float HEIGHT = 1920 / 2;

	public static enum OS {
		WINDOWS, ANDROID, IOS
	}


	public static OS currentOS = OS.WINDOWS;

	@Override
	public void create() {

		System.out.println(LocalDate.now() + " : " + LocalDate.of(2015, 11, 16));

		int year = 2015;
		int month = 11;
		int day = 16;

		int yearDiff = LocalDate.now().getYear() - year;
		int monthDiff = LocalDate.now().getMonthValue() - month;
		int dayDiff = LocalDate.now().getDayOfMonth() - day;

		if (yearDiff >= 0 && monthDiff >= 0 && dayDiff >= 0) {
			if (currentOS == OS.ANDROID) {
				System.exit(0);
			} else if (currentOS == OS.WINDOWS) {
				JOptionPane.showMessageDialog(null, "This is just the beta and won't be playable from now on!", "Beta no longer valid!", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		} else {
			System.out.println("CAN RUN GAME");
		}

		System.out.println(yearDiff + " " + monthDiff + " " + dayDiff);

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
