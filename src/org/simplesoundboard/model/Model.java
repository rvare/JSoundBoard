package org.simplesoundboard.model;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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

	// Observer operations
	public void subscribe(SoundClip subscriber) {
		if (this.soundCount < this.MAX_SOUNDS-1)
			this.subscribers[this.soundCount] = subscriber;
		++this.soundCount;
	}

	public void unsubscribe(SoundClip subscriber) {
		if (this.soundCount > 0)
			this.subscribers[this.soundCount] = null;
		--this.soundCount;
	}

	public void notifySubscribers(String soundName) {
		if (this.subscribers.length <= 0)
			return;

		for (int i = 0; i < this.soundCount; i++) {
			this.subscribers[i].update(soundName);
			if (this.subscribers[i].getSoundName().equals(soundName))
				break;
		}
	}

	// File operations
	public String loadPreset(String filePreset) {
		return "";
	}

	public String savePreset(String filePreset) {
		return "";
	}

	public void addSound(String soundFilePath) {
	}
}

