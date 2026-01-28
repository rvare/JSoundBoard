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

abstract public class AbsController {
	protected final Model model;
	protected final IView iView;

	public AbsController(final Model model, final IView iView) {
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
		this.model.loadPreset(filePresetPath);
	}

	public void savePresetFromModel(String filePresetPath) {
		this.model.savePreset(filePresetPath);
	}

			// Sound operations
	public void addSoundToModel(String soundFilePath) {
		try {
			this.model.subscribe(new SoundClip(soundFilePath, ""));
		}
		catch(Exception ex) {

		}
	}

	public void removeSoundFromModel(SoundClip soundName) {
		this.model.unsubscribe(soundName);
	}

	public void playSoundClip(String soundName) {
		this.model.notifySubscribers(soundName);
	}
}
