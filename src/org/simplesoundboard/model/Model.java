package org.simplesoundboard.model;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.simplesoundboard.model.SoundClip;

public class Model {
	private File presetFile;
	private String filePresetPath;
	private SoundClip[] subscribers;
	private short soundCount;

	public static short MAX_SOUNDS = 9;

	public Model() {
		this.subscribers = new SoundClip[this.MAX_SOUNDS];
		this.soundCount = 0;
	}

	// Getters
	public short getSoundCount() {
		return this.soundCount;
	}

	public SoundClip getSelectedSoundClip(String name) {
		assert name != null : "getSelectedSoundClip: name is null";
		assert this.soundCount > 0 : "getSelectedSoundClip: soundCount is < 0";
		if (this.soundCount == 0)
			return null;

		for (SoundClip sc : subscribers) {
			if (sc.getSoundName().equals(name)) {
				return sc;
			}
		}

		return null;
	}

	// Observer operations
	public void subscribe(SoundClip subscriber) {
		for (int i = 0; i < this.MAX_SOUNDS-1; i++) {
			if (this.subscribers[i] == null) {
				this.subscribers[this.soundCount] = subscriber;
				++this.soundCount;
				break;
			}
		}
	}

	public void unsubscribe(SoundClip subscriber) {
		assert subscriber != null : "subscriber null";
		assert this.soundCount >= 0 : "soundCount is less than zero";
		if (this.soundCount == 0)
			return;

		for (int i = 0; i < this.MAX_SOUNDS; i++) {
			if (this.subscribers[i] == subscriber) {
				System.out.println(this.subscribers[i].getSoundName());
				this.subscribers[i] = null;
				--this.soundCount;
			}
		}
	}

	public void notifySubscribers(String soundName) {
		if (this.subscribers.length == 0)
			return;

		for (int i = 0; i < this.MAX_SOUNDS; i++) {
			if (this.subscribers[i] != null && this.subscribers[i].getSoundName().equals(soundName)) {
				System.out.println("Play sound");
				this.subscribers[i].update(soundName);
				break;
			}
		}
	}

	// File operations
	public String loadPreset(String filePreset) {
		return "";
	}

	public String savePreset(String filePreset) {
		return "";
	}

	public void addSound(File soundFile, String soundName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (this.soundCount == this.MAX_SOUNDS) return;
		SoundClip newSoundClip = new SoundClip(soundFile, soundName);
		this.subscribe(newSoundClip);
	}
}

