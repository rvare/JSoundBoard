package org.simplesoundboard.model;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
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
import org.simplesoundboard.controller.Controller;

/**
 * The Model of the MVC architecture.
 * @author Richard Varela
 * @since 1.0
 */
public final class Model {
	private Controller controller;
	private File presetFile; // If the user uses a preset file, store it here to easily save changes they make.
	private HashMap<String, SoundClip> subscribers;

	public static final short MAX_SOUNDS = 9;

	/**
	 * Main contructor of the Model class.
 	 * @since 1.0
	 */
	public Model() {
		this.subscribers = new HashMap<String, SoundClip>(Model.MAX_SOUNDS);
	}

	// Getters
	/**
	 * Returns the count of how many subscribers are currently in the Model.
	 * @return Returns the number of current subscribers.
 	 * @since 1.0
	 */
	public int getSoundCount() {
		return this.subscribers.size();
	}

	/**
	 * Gets a specific sound clip by using a string.
	 * @param name String that represents the name of the sound.
	 * @return Returns a SoundClip object that corresponds to the String in name.
	 * @throws NoSoundException When no sound corresponding to name is found, this exception is thrown.
 	 * @since 1.0
	 */
	public SoundClip getSelectedSoundClip(final String soundName) throws NoSoundException {
		assert soundName != null : "getSelectedSoundClip: name is null";

		if (this.subscribers.size() == 0)
			// TODO: Throw an error when there are no sounds.
			return null;

		SoundClip sc = this.subscribers.get(soundName);
		if (sc == null)
			throw new NoSoundException("Sound does not exist.");

		return sc;
	}

	// Setters
	public void setController(Controller controller) {
		this.controller = controller;
	}

	// Observer operations
	/**
	 * Notifies current subscribers in the Model.
	 * @param subscriber A SoundClip object that is a new subscriber to be added to the Model.
	 * @throws SoundNameConflictException When there is a sound with the same name already in the model, this is thrown.
 	 * @since 1.0
	 */
	public void subscribe(final SoundClip subscriber) throws SoundNameConflictException {
		assert subscriber != null : "New subscriber is null";

		if (this.subscribers.size() == Model.MAX_SOUNDS)
			// TODO: Throw an error when max number of sounds has been met.
			return;

		if (this.subscribers.containsKey(subscriber.getSoundName()))
			throw new SoundNameConflictException("Sound already exists");

		this.subscribers.put(subscriber.getSoundName(), subscriber);
	}

	/**
	 * Removes a subscriber in the model.
	 * @param subscriber A SoundClip object that represents the subscriber to remove.
	 * @throws NoSoundException When there is no sound in the Model, this is thrown.
 	 * @since 1.0
	 */
	public void unsubscribe(final SoundClip subscriber) throws NoSoundException {
		assert subscriber != null : "subscriber null";
		assert this.subscribers.size() >= 0 : "Number of subscribers is negative";

		if (this.subscribers.size() == 0)
			// TODO: Throw and let user know there are no sounds loaded.
			return;

		if (this.subscribers.containsKey(subscriber.getSoundName()))
			throw new NoSoundException("No sound found");

		this.subscribers.remove(subscriber.getSoundName());
	}

	/**
	 * Notifies the subscriber that has the sound we want to play.
	 * @param soundName A String object that has the name of the sound we want to play.
 	 * @since 1.0
	 */
	public void notifySubscribers(final String soundName) {
		assert soundName != null : "soundName is null";

		this.subscribers.get(soundName).update(soundName);
	}

	// File operations
	/**
	 * Opens a text file that contains several directory paths to several audio files and loads them in for the user.
	 * @param filePreset A String object that contains the path to the file that will be used to create a File object.
 	 * @since 1.0
	 */
	public void loadPreset(final File filePreset) 
		throws UnsupportedAudioFileException, IOException, LineUnavailableException, SoundNameConflictException
	{
		assert filePreset != null : "filePreset is null";
		BufferedReader reader = new BufferedReader(new FileReader(filePreset));
		String line;

		while ((line = reader.readLine()) != null) {
			String[] split_line = line.split("\t");
			File soundClipFile = new File(split_line[1]);
			this.addSound(soundClipFile, split_line[0]);
			this.controller.createSoundButton(split_line[0]); // Throws SoundNameConflictException
		}
	}

	/**
	 * Saves the directory path of the currently loaded sounds, allowing the user to easily load all audio files without doing it manually.
	 * @param filePreset A String object that contains the path to the file that will be used to create a File object.
 	 * @since 1.0
	 */
	public void savePreset(final File filePreset) throws IOException {
		assert filePreset != null : "filePreset is null";
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePreset));

		for (Map.Entry<String, SoundClip> sc : this.subscribers.entrySet()) {
			writer.write(String.format("%s\t%s\n", sc.getValue().getSoundName(), sc.getValue().getSoundFile().getAbsolutePath()));
		}

		writer.close();
	}

	/**
	 * Creates a new SoundClip and adds it as a subscriber to the model.
	 * @param soundFile String object that contains the path to the audio file that is used to open the file.
	 * @param soundName The name of the sound the user created to identify the sound and name its corresponding button in the View class.
	 * @throws UnsupportedAudioFileException Thrown when user tries to load an audio file that's not compitable with the Clip class.
	 * @throws IOException Thrown when there is an issue with the file.
	 * @throws LineUnavailableException Thrown when the JVM is unable to create an InputLineStream.
	 * @throws SoundNameConflictException Thrown when the new name of the sound is already in use by another sound.
 	 * @since 1.0
	 */
	public void addSound(final File soundFile, final String soundName)
		throws UnsupportedAudioFileException, IOException, LineUnavailableException, SoundNameConflictException
	{
		assert soundFile != null : "soundFile is null";
		assert soundName != null : "soundName is null";

		if (this.subscribers.size() == (int)Model.MAX_SOUNDS)
			// TODO: Throw error that the user has hit the max number of sounds they can load.
			return;

		SoundClip newSoundClip = new SoundClip(soundFile, soundName);
		this.subscribe(newSoundClip);
	}
}

