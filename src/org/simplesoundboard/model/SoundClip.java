package org.simplesoundboard.model;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
* Class that represents the audio we want to play.
* @author Richard Varela
* @since 1.0
*/
public class SoundClip {
	private String soundName;
	private AudioInputStream audioInputStream;
	private Clip soundClip;

	/**
	 * Default constructor to use.
	 * @param filePath Object that represents the directory path to the audio file we want to play.
	 * @param soundName String object that represents the name we want to give the audio.
	 * @throws UnsupportedAudioFileException Thrown when the user tries to use an audio file that's not compitable.
	 * @throws IOException Thrown when there is an issue with using the file.
	 * @throws LineUnavailableException Thrown when unable to create an InputLineStream.
 	 * @since 1.0
	 */
	public SoundClip(File filePath, String soundName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.audioInputStream = AudioSystem.getAudioInputStream(filePath.getAbsoluteFile());

		this.soundClip = AudioSystem.getClip();
		this.soundClip.open(audioInputStream);

		this.soundName = soundName;
	}

	/**
	 * Alternate constructor for when a String object is passed in for the file path instead of a file object.
	 * @param filePath String object that represents the directory path to the audio file we want to play.
	 * @param soundName String object that represents the name we want to give the audio.
	 * @throws UnsupportedAudioFileException Thrown when the user tries to use an audio file that's not compitable.
	 * @throws IOException Thrown when there is an issue with using the file.
	 * @throws LineUnavailableException Thrown when unable to create an InputLineStream.
 	 * @since 1.0
	 */
	public SoundClip(String filePath, String soundName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this(new File(filePath), soundName);
	}

	// Getters
	/**
	 * Returns the name of the sound the user gave.
	 * @return String object that represents the name of the sound given by the user when loaded.
 	 * @since 1.0
	 */
	public String getSoundName() {
		return this.soundName;
	}

	/**
	 * Returns an AudioInputStream object associated with the audio clip.
	 * @return AudioInputStream object.
 	 * @since 1.0
	 */
	public AudioInputStream getAudioInputStream() {
		return this.audioInputStream;
	}

	/**
	 * Returns the actual Clip object associated with the sound.
	 * @return Clip object associated with the sound.
 	 * @since 1.0
	 */
	public Clip getSoundClip() {
		return this.soundClip;
	}

	// Setters
	/**
	 * Sets/changes the name of the SoundClip.
	 * @param soundName A String object that contains the new name of the SoundClip.
 	 * @since 1.0
	 */
	public void setSoundClipName(String soundName) {
		this.soundName = soundName;
	}

	// Operations
	/**
	 * Called when the user clicks a button in the window, and plays the sound corresponding sound with the same name as the button.
	 * @param subscriberName A String object that contains the name of the sound to be played.
 	 * @since 1.0
	 */
	public void update(String subscriberName) {
		if (!subscriberName.equals(this.soundName))
			return;

		this.soundClip.flush();
		this.soundClip.setFramePosition(0);
		this.soundClip.start();
	}

	/**
	 * Stops the current sound playing.
 	 * @since 1.0
	 */
	public void stopSound() {
		this.soundClip.stop();
	}

	/**
	 * Overriden toString method.
	 * @return A String object that contains the name of the SoundClip.
 	 * @since 1.0
	 */
	@Override
	public String toString() {
		return this.soundName;
	}
}
