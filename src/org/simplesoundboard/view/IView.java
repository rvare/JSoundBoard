package org.simplesoundboard.view;

public interface IView {
	public static short DEFAULT_WIDTH = 400;
	public static short DEFAULT_HEIGHT = 400;
	public static String WINDOW_TITLE = "Simple Sound Board";

	public void showMainFrame();
	public void addNewSoundListener(Object newSoundButtonListener);
	public void addDeleteSoundListener(Object deleteSoundButtonListener);
	public void addAboutDialogListener(Object aboutOptionListener);
	public void addDocumentationListener(Object docOptionListener);
	public void addSavePresetListener(Object saveOptionListener);
	public void addLoadPresetListener(Object loadOptionListener);
}
