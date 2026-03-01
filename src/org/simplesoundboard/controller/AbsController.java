/*
Copyright 2026 Richard Varela
This file is part of JSoundBoard.

JSoundBoard is free software: you can redistribute it and/or modify it under the
terms of the GNU General Public License as published by the Free Software Foundation,
either version 3 of the License, or (at your option) any later version.

JSoundBoard is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with SimpleJotts.
If not, see <https://www.gnu.org/licenses/>.
*/

package org.simplesoundboard.controller;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.simplesoundboard.model.*;
import org.simplesoundboard.view.*;
import org.simplesoundboard.exception.*;

/**
 * Abstract Controller class that is extended by a contrete Controller class.
 * @author Richard Varela
 * @since 1.0
 */
abstract public class AbsController {
	protected final Model model;
	protected final IView iView;

	/**
	 * Default constructor used to instantiate the controller. Preconditions: a valid Model and iView object must be instantiated first.
	 * @param model A reference to the Model object.
	 * @param iView A reference to an object that implements the IView interface.
	 * @since 1.0
	 */
	public AbsController(final Model model, final IView iView) {
		assert model != null : "Model is null.";
		assert iView != null : "iView is null.";

		this.model = model;
		this.iView = iView;

		this.model.setController(this);
	}

	// Getters
	/**
	 * Used to retrieve a reference to the Model class.
	 * @return Returns a reference to the model class.
	 * @since 1.0
	 */
	public Model getModel() {
		return this.model;
	}

	/**
	 * Used to retrieve a reference to the View class.
	 * @return Returns a reference to the View class.
	 * @since 1.0
	 */
	public IView getIView() {
		return this.iView;
	}

	// Operations
	// Abstract methods
	abstract public void saveSoundPresetFile();
	abstract public void loadSoundPresetFile();

	/**
	 * Used to call the showAboutDialog method of the IView interface.
	 * @since 1.0
	 */
	abstract public void showAboutDialog();

	/**
	 * Used to call the showDocumentationDialog method of the IView interface.
	 * @since 1.0
	 */
	abstract public void showDocumentationDialog();

	/**
	 * Used to call the View class to create a new button for the sound when the user uses a preset file.
	 * @since 1.0
	 */
	abstract public void createSoundButton(String soundName);

	// Concrete methods
	// File operations
	/**
	 * Calls the Model's load preset method when the user opens a preset file.
	 * @param filePresetPath A String object that represents the path to the preset file.
	 * @since 1.0
	 */
	public void loadPresetToModel(final File filePresetPath)
		throws UnsupportedAudioFileException, IOException, LineUnavailableException, SoundNameConflictException
	{
		assert !filePresetPath.equals("") && filePresetPath != null : "soundName is empty string or null.";
		this.model.loadPreset(filePresetPath);
	}

	/**
	 * Calls the Model's save preset method when the user opens a preset file.
	 * @param filePresetPath A String object that represents the path that the preset file will be saved to.
	 * @since 1.0
	 */
	public void savePresetFromModel(final File filePresetPath) throws IOException {
		assert filePresetPath != null : "filePreset is null.";
		this.model.savePreset(filePresetPath);
	}

	// Sound operations
	/**
	 * Calls the Model's subscribe method to add a new sound to the model.
	 * @param soundFilePath A String object that represent the path to the audio to be used.
	 * @since 1.0
	 */
	public void addSoundToModel(final String soundFilePath) {
		assert soundFilePath != null : "soundFilePath is null";
		try {
			this.model.subscribe(new SoundClip(soundFilePath, ""));
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Calls the Model's removeSoundFromModel method to remove a sound from the model.
	 * @param soundClip A reference to the SoundClip object that is to be removed.
	 * @since 1.0
	 */
	public void removeSoundFromModel(final SoundClip soundClip) throws NoSoundException {
		assert soundClip != null : "soundClip is null.";
		this.model.unsubscribe(soundClip);
	}

	/**
	 * Calls the Model's notifySubscribers to play the sound that corresponds to the button the user pressed.
	 * @param soundName A String object that represents the name of the sound to be deleted.
	 * @since 1.0
	 */
	public void playSoundClip(final String soundName) {
		assert !soundName.equals("") && soundName != null : "soundName is empty string or null.";
		this.model.notifySubscribers(soundName);
	}
}
