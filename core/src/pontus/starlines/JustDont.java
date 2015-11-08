package pontus.starlines;

import pontus.starlines.core.Input;
import pontus.starlines.core.graphics.ScreenManager;
import pontus.starlines.game.Game;
import pontus.starlines.menu.Menu;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;


public class JustDont implements ApplicationListener {
	
	public static final float WIDTH = 1080 / 2;
	public static final float HEIGHT = 1920 / 2;
	
	
	

	@Override
	public void create() {
		
		ScreenManager.add(new Game());
		ScreenManager.add(new Menu());

		ScreenManager.setSelected("MENU");
		
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
