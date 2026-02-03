package org.simplesoundboard.view;

import java.io.*;

import org.simplesoundboard.exception.*;

public interface IView {
	public static final short DEFAULT_WIDTH = 400;
	public static final short DEFAULT_HEIGHT = 400;
	public static final short DEFAULT_SOUND_COUNT = 9;
	public static final short DEFAULT_BUTTON_GRID_HEIGHT = 3;
	public static final short DEFAULT_BUTTON_GRID_WIDTH = 3;
	public static final String WINDOW_TITLE = "Simple Sound Board";

	// Operations
	public void showMainFrame();
	public void addSoundButton(Object buttonListener, String soundName);
	public void deleteSoundButton(String soundButtonName) throws NoSoundException;
	public void showAboutDialog();
	public void showDocumentationDialog();
	public String nameSoundButtonDialog();
	public File newSoundFilePath();
	public void showErrorDialog(String errorMessage);

	// Add listeners
	public void addNewSoundListener(Object newSoundListener);
	public void addDeleteSoundListener(Object deleteSoundListener);
	public void addAboutDialogListener(Object aboutOptionListener);
	public void addDocumentationListener(Object docOptionListener);
	public void addSavePresetListener(Object saveOptionListener);
	public void addLoadPresetListener(Object loadOptionListener);
}
