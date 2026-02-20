package org.simplesoundboard.view;

import java.io.*;

import org.simplesoundboard.exception.*;

/**
 * Interface that makes it easy for any user to alter View but forces to commit the "contract" that the Controller needs to function.
 * @author Richard Varela
 */
public interface IView {
	public static final short DEFAULT_WIDTH = 400;
	public static final short DEFAULT_HEIGHT = 400;
	public static final short DEFAULT_SOUND_COUNT = 9;
	public static final short DEFAULT_BUTTON_GRID_HEIGHT = 3;
	public static final short DEFAULT_BUTTON_GRID_WIDTH = 3;
	public static final String WINDOW_TITLE = "Simple Sound Board";

	// Operations
	/**
	 * Method that is called by the main method to display the GUI.
	 * An abstract way of displaying the GUI without need to know the specific method of the graphics library.
	 */
	public void showMainFrame();

	/**
	 * Add a new sound button to the GUI.
	 * @param buttListener An object that should act as a listener, whose implementation is based on the GUI library.
	 * @param soundName String object that represents the sound name and is used to label the button.
	 * @throws SoundNameConflict If the name the user gives already exists, this is thrown.
	 */
	public void addSoundButton(Object buttonListener, String soundName) throws SoundNameConflictException;

	/**
	 * Deletes a sound based on the name.
	 * @param soundButtonName String object that contains the name of the sound to be deleted.
	 * @throws NoSoundException Thrown when the sound with the given name does not exist.
	 */
	public void deleteSoundButton(String soundButtonName) throws NoSoundException;

	/**
	 * Used to display the about dialog. Callers do not need to know the specific implementation details.
	 */
	public void showAboutDialog();

	/**
	 * Used to display the documentation dialog. Callers do not need to know the specific implementation details.
	 */
	public void showDocumentationDialog();

	/**
	 * Used to display the dialog that allows a user to name a new sound. Callers do not need to know the specific implementation details.
	 */
	public String nameSoundButtonDialog();

	/**
	 * Used to get the directory path a sound file that will be added to the application.
	 * @return A File object that represents the sound file.
	 */
	public File newSoundFilePath();

	/**
	 * Used to display an error message to the screen.
	 * The message is based on what the exception is thrown.
	 * See all the exceptions classes used to see what they are.
	 */
	public void showErrorDialog(String errorMessage);

	// Add listeners
	/**
	 * Method used to add a listener for when the user wants to add a new sound to application.
	 * @param newSoundListener Represents the object that will act as the listener for this action. You must cast in the implementation.
	 */
	public void addNewSoundListener(Object newSoundListener);

	/**
	 * Method used to add a listener for when the user wants to delete a sound.
	 * @param deleteSoundListener Represents the object that will act as the listener for this action. You must cast in the implementation.
	 */
	public void addDeleteSoundListener(Object deleteSoundListener);

	/**
	 * Used to add a listener for when the user opens the about dialog.
	 * @param aboutOptionListener Represents the object that will act as the listener for this action. You must cast in the implementation.
	 */
	public void addAboutDialogListener(Object aboutOptionListener);

	/**
	 * Used to add a listener for when the user opens the documentation dialog.
	 * @param docOptionListener Represents the object that will act as the listener for this action. You must cast in the implementation.
	 */
	public void addDocumentationListener(Object docOptionListener);

	/**
	 * Used to add a listener for when the user wants to save their own preset.
	 * @param saveOptionListener Represents the object that will act as the listener for this action. You must cast in the implementation.
	 */
	public void addSavePresetListener(Object saveOptionListener);

	/**
	 * Used to add a listener for when the user wants to load in thri own preset.
	 * @param loadOptionListener Represents the object that will act as the listener for this action. You must cast in the implementation.
	 */
	public void addLoadPresetListener(Object loadOptionListener);
}
