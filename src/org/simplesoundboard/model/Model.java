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
import org.simplesoundboard.exception.*;

public final class Model {
	private File presetFile;
	private String filePresetPath;
	private HashMap<String, SoundClip> subscribers;

	public static final short MAX_SOUNDS = 9;

	public Model() {
		this.subscribers = new HashMap<String, SoundClip>(Model.MAX_SOUNDS);
	}

	// Getters
	public int getSoundCount() {
		return this.subscribers.size();
	}

	public SoundClip getSelectedSoundClip(String name) throws NoSoundException {
		assert name != null : "getSelectedSoundClip: name is null";
		if (this.subscribers.size() == 0)
			return null;

		SoundClip sc = this.subscribers.get(name);
		if (sc == null)
			throw new NoSoundException("Sound does not exist.");

		return sc;
	}

	// Observer operations
	public void subscribe(SoundClip subscriber) throws SoundNameConflictException {
		assert subscriber != null : "New subscriber is null";
		if (this.subscribers.size() == Model.MAX_SOUNDS)
			return;

		if (this.subscribers.containsKey(subscriber.getSoundName()))
			throw new SoundNameConflictException("Sound already exists");

		this.subscribers.put(subscriber.getSoundName(), subscriber);
	}

	public void unsubscribe(SoundClip subscriber) throws NoSoundException {
		assert subscriber != null : "subscriber null";
		if (this.subscribers.size() == 0)
			return;

		if (this.subscribers.containsKey(subscriber.getSoundName()))
			throw new NoSoundException("No sound found");

		this.subscribers.remove(subscriber.getSoundName());
	}

	public void notifySubscribers(String soundName) {
		if (this.subscribers.size() == 0)
			return;

		System.out.println("Play sound");
		this.subscribers.get(soundName).update(soundName);
	}

	// File operations
	public String loadPreset(String filePreset) {
		return "";
	}

	public String savePreset(String filePreset) {
		return "";
	}

	public void addSound(File soundFile, String soundName) throws UnsupportedAudioFileException, IOException, LineUnavailableException, SoundNameConflictException {
		if (this.subscribers.size() == (int)Model.MAX_SOUNDS)
			return;
		SoundClip newSoundClip = new SoundClip(soundFile, soundName);
		this.subscribe(newSoundClip);
	}
}

