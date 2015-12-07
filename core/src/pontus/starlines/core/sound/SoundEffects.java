package pontus.starlines.core.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Contains different sound effects.
 * @author Pontus Wirsching
 * @since 2015-11-14
 */
public enum SoundEffects {

	HIT_WALL("sounds/Hitting a Wall.wav"),
	PICKUP_STAR("sounds/Picking up a Star.wav"),
	NO_STARS("sounds/No Stars!.wav"),
	ONE_TWO_STARS("sounds/One-Two Stars!.wav"),
	THREE_STARS("sounds/Three Stars!.wav");
	
	
	
	
	
	
	
	
	
	
	/**
	 * Sound instance.
	 */
	private Sound sound;
	
	/**
	 * Play volume for this sound.
	 */
	private float volume = 0.25f;
	
	/**
	 * 
	 * @param path - String path relative to assets folder
	 */
	SoundEffects(String path) {
		sound = Gdx.audio.newSound(Gdx.files.internal(path));
	}
	
	/**
	 * 
	 * @param path - String path relative to assets folder
	 * @param volume - Volume of this sound effect
	 */
	SoundEffects(String path, float volume) {
		sound = Gdx.audio.newSound(Gdx.files.internal(path));
		setVolume(volume);
	}
	
	/**
	 * @return The sound instance.
	 */
	public Sound getSound() {
		return sound;
	}
	
	/**
	 * Sets volume variable.
	 * @param v - New volume
	 */
	public void setVolume(float v) {
		volume = v;
	}
	
	/**
	 * Plays the sound once.
	 * @return A long variable that contains the sound id.
	 */
	public long play() {
		return sound.play(volume);
	}
	
	/**
	 * Loops the sound until stopped.
	 * @return A long variable that contains the sound id.
	 */
	public long loop() {
		return sound.loop(volume);
	}
	
	/**
	 * Stops the sound if looped.
	 */
	public void stop() {
		sound.stop();
	}
}
