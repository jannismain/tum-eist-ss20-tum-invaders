/**
 * This class was taken from Bumpers and adapted for SpaceInvaders
 */

package de.tum.in.ase.eist.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

/**
 * Play sound effects and background music
 */
public class AudioPlayer {

	private MediaPlayer mediaPlayer;

	// all the sounds are taken from
	// https://www.classicgaming.cc/classics/space-invaders/sounds
	public static final String BACKGROUND_MUSIC_FILE = "background.wav";
	public static final String EXPLOSION_MUSIC_FILE = "explosion.wav";
	public static final String INVADER_KILLED_MUSIC_FILE = "invaderkilled.wav";
	public static final String INVADER_SHOT_MUSIC_FILE = "invadershot.wav";
	public static final String SHOOT_MUSIC_FILE = "shoot.wav";

	boolean shootSoundPlayed;

	public AudioPlayer() {
		shootSoundPlayed = false;
	}

	private Media loadAudioFile(String fileName) {
		URL musicSourceUrl = getClass().getClassLoader().getResource(fileName);
		if (musicSourceUrl == null) {
			throw new RuntimeException(
					"Please ensure that your resources folder contains the appropriate files for this exercise.");
		}
		String musicSource = musicSourceUrl.toString();
		return new Media(musicSource);
	}

	public void playBackgroundMusic() {
		this.mediaPlayer = new MediaPlayer(loadAudioFile(BACKGROUND_MUSIC_FILE));
		this.mediaPlayer.setAutoPlay(true);
		// Loop for the main music sound:
		this.mediaPlayer.setOnEndOfMedia(() -> AudioPlayer.this.mediaPlayer.seek(Duration.ZERO));
		this.mediaPlayer.play();
	}

	public void stopBackgroundMusic() {
		this.mediaPlayer.stop();
	}

	public void playShootSound() {
		this.shootSoundPlayed = true;
		new MediaPlayer(loadAudioFile(SHOOT_MUSIC_FILE)).play();
	}

	public void playExplosionSound() {
		new MediaPlayer(loadAudioFile(EXPLOSION_MUSIC_FILE)).play();
	}

	public void playInvaderKilledSound() {
		new MediaPlayer(loadAudioFile(INVADER_KILLED_MUSIC_FILE)).play();
	}

	public void playInvaderShotSound() {
		new MediaPlayer(loadAudioFile(INVADER_SHOT_MUSIC_FILE)).play();
	}

}
