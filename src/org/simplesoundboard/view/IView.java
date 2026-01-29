package org.simplesoundboard.view;

public interface IView {
	public static short DEFAULT_WIDTH = 400;
	public static short DEFAULT_HEIGHT = 400;
	public static short DEFAULT_SOUND_COUNT = 9;
	public static short DEFAULT_BUTTON_GRID_HEIGHT = 3;
	public static short DEFAULT_BUTTON_GRID_WIDTH = 3;
	public static String WINDOW_TITLE = "Simple Sound Board";

	// Operations
	public void showMainFrame();
	public void addSoundButton(Object buttonListener);
	public void deleteSoundButton();
	public void showAboutDialog();
	public void showDocumentationDialog();

	// Add listeners
	public void addNewSoundListener(Object newSoundListener);
	public void addDeleteSoundListener(Object deleteSoundListener);
	public void addAboutDialogListener(Object aboutOptionListener);
	public void addDocumentationListener(Object docOptionListener);
	public void addSavePresetListener(Object saveOptionListener);
	public void addLoadPresetListener(Object loadOptionListener);
}
