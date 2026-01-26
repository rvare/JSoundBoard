package org.simplesoundboard.model;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundClip implements ISubscriber {
	private String soundName;
	private AudioInputStream audioInputStream;
	private Clip soundClip;

	public SoundClip(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

		this.soundClip = AudioSystem.getClip();
		this.soundClip.open(audioInputStream);
		this.soundClip.stop();
	}

	public SoundClip(String filePath, String soundName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this(filePath);
		this.soundName = soundName;
	}

	// Getters
	@Override
	public String getSoundName() {
		return this.soundName;
	}

	public AudioInputStream getAudioInputStream() {
		return this.audioInputStream;
	}

	public Clip getSoundClip() {
		return this.soundClip;
	}

	// Setters
	public void setSoundClipName(String soundName) {
		this.soundName = soundName;
	}

	// Operations
	@Override
	public void update(String subscriberName) {
		if (!subscriberName.equals(this.soundName))
			return;
		this.soundClip.setFramePosition(0);
		this.soundClip.start();
	}

	public void stopSound() {
		this.soundClip.stop();
	}
}
