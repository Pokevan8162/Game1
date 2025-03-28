package main;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30]; // stores file path of sound files
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/In_Space.wav");
		soundURL[1] = getClass().getResource("/sound/Player_Bounce.wav");
		soundURL[2] = getClass().getResource("/sound/Player_Death.wav");
		
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
//			return clip;
		} catch (Exception e) {
			
		}
//		return null;
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
//	public void stop(Clip clip) {
//		clip.stop();
//	}
	
	public void stop() {
		clip.stop();
	}
}
