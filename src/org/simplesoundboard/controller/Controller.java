package org.simplesoundboard.controller;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.simplesoundboard.model.*;
import org.simplesoundboard.view.*;
import org.simplesoundboard.controller.AbsController;
import org.simplesoundboard.exception.*;

/**
 * Concrete class of the abstract cotroller.
 */
public final class Controller extends AbsController {
	/**
	 * Default constructor that also calls the AbsController constructor.
	 * Preconditions: A valid Model and object that implements IView interface must be instantiated first.
	 * @param model A reference to the Model object.
	 * @param iView A reference to an object that implements the IView interface.
	 * @since 1.0
	 */
	public Controller(final Model model, final IView iView) {
		super(model, iView);

		// Create menu bar listeners
		this.iView.addNewSoundListener(new AddSoundListener());
		this.iView.addDeleteSoundListener(new DeleteSoundListener());
		this.iView.addAboutDialogListener(new AboutListener());
		this.iView.addDocumentationListener(new DocumentationListener());
		this.iView.addSavePresetListener(new SaveOptionListener());
		this.iView.addLoadPresetListener(new LoadOptionListener());
	}

	// Operations
	public void saveSoundPresetFile() {
		File saveFile = this.iView.savePresetFile();

		try {
			this.model.savePreset(saveFile);
		}
		catch(IOException ioEx) {
			// System.out.println(ioEx.getMessage());
			this.iView.showErrorDialog(ioEx.getMessage());
		}
		catch(Exception ex) {
			// System.out.println(ex.getMessage());
			this.iView.showErrorDialog(ex.getMessage());
		}
	}

	public void loadSoundPresetFile() {
		File loadFile = this.iView.loadPresetFile();

		try {
			this.model.loadPreset(loadFile);
		}
		catch(UnsupportedAudioFileException ex) {
			// System.out.println(ex.getMessage());
		}
		catch(LineUnavailableException ex) {
			// System.out.println(ex.getMessage());
		}
		catch(SoundNameConflictException ex) {
			// System.out.println(ex.getMessage());
		}
		catch(IOException ioEx) {
			// System.out.println(ioEx.getMessage());
		}
		catch(Exception ex) {
			// System.out.println(ex.getMessage());
		}
	}

	public void showAboutDialog() {

	}

	public void showDocumentationDialog() {

	}

	@Override
	public void createSoundButton(String soundName) {
		SoundButtonListener sbListener = new SoundButtonListener(soundName);
		try {
			this.iView.addSoundButton(sbListener, soundName);
		}
		catch(SoundNameConflictException ex) {
			this.iView.showErrorDialog(ex.getMessage());
		}
		catch(Exception ex) {
			this.iView.showErrorDialog(ex.getMessage());
		}
	}

	// Menu bar listeners
	/**
	 * Private internal class that implements the ActionListener interface that listens for when the user loads a preset file.
	 * @author Richard Varela
	 */
	private class LoadOptionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			loadSoundPresetFile();
		}
	}

	/**
	 * Private internal class that implements the ActionListener interface that listens for when the user saves a preset file.
	 * @author Richard Varela
	 * @since 1.0
	 */
	private class SaveOptionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			saveSoundPresetFile();
		}
	}

	/**
	 * Internal private class that implements the ActionListener interface that listens for when the users wants to add a new sound.
	 * @author Richard Varela
	 */
	private class AddSoundListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			File newSound = iView.newSoundFilePath();

			if (newSound == null)
				return;

			String soundName = iView.nameSoundButtonDialog();

			if (soundName.equals(""))
				return;

			try {
				model.addSound(newSound, soundName);
				SoundButtonListener sbListener = new SoundButtonListener(soundName);
				iView.addSoundButton(sbListener, soundName);
			}
			catch(UnsupportedAudioFileException ex) {
				// System.out.println(ex.getMessage());
				iView.showErrorDialog("Unsupported file format.\nMust be wav.");
			}
			catch(LineUnavailableException ex) {
				// System.out.println(ex.getMessage());
				iView.showErrorDialog(ex.getMessage());
			}
			catch(SoundNameConflictException ex) {
				// System.out.println(ex.getMessage());
				iView.showErrorDialog(ex.getMessage());
			}
			catch(IOException ex) {
				// System.out.println(ex.getMessage());
				iView.showErrorDialog(ex.getMessage());
			}
			catch(Exception ex) {
				// System.out.println(ex.getMessage());
				iView.showErrorDialog(ex.getMessage());
			}
		}
	}

	/**
	 * Internal private class that implements the ActionListener interface that listens for when the user wants to delete a sound.
	 * @author Richard Varela
	 */
	private class DeleteSoundListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			String soundName = iView.nameSoundButtonDialog();

			try {
				iView.deleteSoundButton(soundName);
				SoundClip soundClip = model.getSelectedSoundClip(soundName);
				model.unsubscribe(soundClip);
			}
			catch(NoSoundException noSoundEx) {
				// System.out.println(noSoundEx.getMessage());
				iView.showErrorDialog(noSoundEx.getMessage());
			}
			catch(Exception ex) {
				// System.out.println(ex.getMessage());
				iView.showErrorDialog(ex.getMessage());
			}
		}
	}

	/**
	 * Internal private class that implements the ActionListener interface that listens for when the user opens the About Dialog.
	 * @author Richard Varela
	 */
	private class AboutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			iView.showAboutDialog();
		}
	}

	/**
	 * Internal private class that implements the ActionListener interface that listens for when the user opens the Docs Dialog.
	 * @author Richard Varela
	 */
	private class DocumentationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			iView.showDocumentationDialog();
		}
	}

	// Button listener
	/**
	 * Internal private class that implements the ActionListener interface.
	 * This class is responsible for handling the case that the user clicks a JButton to play a sound.
	 * @author Richard Varela
	 */
	private class SoundButtonListener implements ActionListener {
		private String name;

		public SoundButtonListener() {} // Default constructor

		public SoundButtonListener(final String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public void setName(final String name) {
			this.name = name;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			model.notifySubscribers(this.name);
		}
	}
}
