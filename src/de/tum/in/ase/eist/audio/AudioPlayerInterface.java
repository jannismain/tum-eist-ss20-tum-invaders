package de.tum.in.ase.eist.audio;

public interface AudioPlayerInterface {

	
    public static final String BACKGROUND_MUSIC_FILE = "Music.wav";
    public static final String CRASH_MUSIC_FILE = "Crash.wav";
 
    // all the sounds are taken from https://www.classicgaming.cc/classics/space-invaders/sounds
	
    
    public static final String EXPOSION_MUSIC_FILE = "explosion.wav";
    public static final String INVADER_KILLED_MUSIC_FILE = "invaderkilled.wav";
    public static final String SHOOT_MUSIC_FILE = "shoot.wav";
    public static final String INVADER_PITCH_MUSIC_FILE = "ufo_lowpitch.wav";
    
    String getCrashSoundFilePath();
    String getBackgroundMusicFilePath();
    void playBackgroundMusic();
    void stopBackgroundMusic();
    boolean isPlayingBackgroundMusic();
    void playCrashSound();
    boolean getCrashSoundPlayed();

}
