package org.simplesoundboard.controller;

import java.util.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.simplesoundboard.model.*;
import org.simplesoundboard.view.*;
import org.simplesoundboard.exception.*;

abstract public class AbsController {
	protected final Model model;
	protected final IView iView;

	public AbsController(final Model model, final IView iView) {
		assert mode != null : "Model is null.";
		assert iView != null : "iView is null.";
		System.out.println("AbsController");
		this.model = model;
		this.iView = iView;
	}

	// Getters
	public Model getModel() {
		return this.model;
	}

	public IView getIView() {
		return this.iView;
	}

	// Operations
		// Abstract methods
	abstract public void saveSoundPresetFile();
	abstract public void loadSoundPresetFile();
	abstract public void showAboutDialog();
	abstract public void showDocumentationDialog();

		// Concrete methods
			// File operations
	public void loadPresetToModel(String filePresetPath) {
		assert !filePresetPath.equals("") && filePresetPath != null : "soundName is empty string or null.";
		this.model.loadPreset(filePresetPath);
	}

	public void savePresetFromModel(String filePresetPath) {
		assert !filePresetPath.equals("") && filePresetPath != null : "soundName is empty string or null.";
		this.model.savePreset(filePresetPath);
	}

			// Sound operations
	public void addSoundToModel(String soundFilePath) {
		assert soundFilePath != null : "soundFilePath is null";
		try {
			this.model.subscribe(new SoundClip(soundFilePath, ""));
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void removeSoundFromModel(SoundClip soundClip) throws NoSoundException {
		assert soundClip != null : "soundClip is null.";
		this.model.unsubscribe(soundClip);
	}

	public void playSoundClip(String soundName) {
		assert !soundName.equals("") && soundName != null : "soundName is empty string or null.";
		this.model.notifySubscribers(soundName);
	}
}
