package com.examples.Services.MusicCommon;

interface MusicPlayer {
	void playMusic(int songIndex);
	void pauseMusic();
	void resumeMusic();
	void stopMusic();
	void stopMusicPlayer();
	boolean isPlaying();
	void removeLog();
	List<String> logTransacations();
}