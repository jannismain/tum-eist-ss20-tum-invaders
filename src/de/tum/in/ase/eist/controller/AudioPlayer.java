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

	enum Sound {
		SHOOT, EXPLOSION, INVADER_HIT, INVADER_KILLED
	}

	private MediaPlayer mediaPlayer;
	private boolean shootSoundPlayed;

	// all the sounds are taken from
	// https://www.classicgaming.cc/classics/space-invaders/sounds
	public static final String SOUND_BACKGROUND_MUSIC = "background.wav";
	public static final String SOUND_EXPLOSION = "explosion.wav";
	public static final String SOUND_INVADER_KILLED = "invaderkilled.wav";
	public static final String SOUND_INVADER_HIT = "invadershot.wav";
	public static final String SOUND_SHOOT = "shoot.wav";

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
		this.mediaPlayer = new MediaPlayer(loadAudioFile(SOUND_BACKGROUND_MUSIC));
		this.mediaPlayer.setAutoPlay(true);
		// Loop for the main music sound:
		this.mediaPlayer.setOnEndOfMedia(() -> AudioPlayer.this.mediaPlayer.seek(Duration.ZERO));
		this.mediaPlayer.play();
	}

	public void stopBackgroundMusic() {
		this.mediaPlayer.stop();
	}

	public void playSound(Sound s) {
		switch (s) {
			case SHOOT:
				this.shootSoundPlayed = true;
				new MediaPlayer(loadAudioFile(SOUND_SHOOT)).play();
				break;
			case EXPLOSION:
				new MediaPlayer(loadAudioFile(SOUND_EXPLOSION)).play();
				break;
			case INVADER_KILLED:
				new MediaPlayer(loadAudioFile(SOUND_INVADER_KILLED)).play();
				break;
			case INVADER_HIT:
				new MediaPlayer(loadAudioFile(SOUND_INVADER_HIT)).play();
			default:
				break;
		}
	}

}
