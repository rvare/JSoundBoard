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

package org.jsoundboard.view;

import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jsoundboard.view.*;
import org.jsoundboard.exception.*;

/**
 * Class that implements the IView interface and should extend from whatever graphics library is being used.
 * @author Richard Varela
 * @since 1.0
 */
public final class View extends JFrame implements IView {
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem openPresetOption;
	private JMenuItem savePresetOption;
	private JMenuItem addSoundOption;
	private JMenuItem deleteSoundOption;
	private JMenuItem docOption;
	private JMenuItem aboutOption;
	private JPanel buttonPanel;
	private GridLayout buttonLayout;

	private HashMap<String, JButton> buttons;

	/**
	 * Default constructor.
	 */
	public View() {
		this.buttons = new HashMap<String, JButton>(IView.DEFAULT_SOUND_COUNT);

		// Create menu bar
		this.menuBar = new JMenuBar();

		// Create file menu and its items
		JMenu fileMenu = new JMenu("File");

		this.openPresetOption = new JMenuItem("Open");
		fileMenu.add(this.openPresetOption);

		this.savePresetOption = new JMenuItem("Save");
		fileMenu.add(this.savePresetOption);

		// Create edit menu and its items
		JMenu editMenu = new JMenu("Edit");

		this.addSoundOption = new JMenuItem("Add Sound");
		editMenu.add(this.addSoundOption);

		this.deleteSoundOption = new JMenuItem("Delete Sound");
		editMenu.add(deleteSoundOption);

		// Create help menu and its items
		JMenu helpMenu = new JMenu("Help");

		this.docOption = new JMenuItem("Documentation");
		helpMenu.add(this.docOption);

		this.aboutOption = new JMenuItem("About");
		helpMenu.add(this.aboutOption);

		// Add all menu items to the menu bar
		this.menuBar.add(fileMenu);
		this.menuBar.add(editMenu);
		this.menuBar.add(helpMenu);
		this.setJMenuBar(this.menuBar);

		// Create button layout
		this.buttonPanel = new JPanel();
		this.buttonLayout = new GridLayout(IView.DEFAULT_BUTTON_GRID_HEIGHT,
											IView.DEFAULT_BUTTON_GRID_WIDTH);
		this.buttonPanel.setLayout(this.buttonLayout);

		this.getContentPane().add(BorderLayout.CENTER, this.buttonPanel);

		// Create the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(IView.WINDOW_TITLE);
		this.setSize(IView.DEFAULT_WIDTH, IView.DEFAULT_HEIGHT);
	}

	// Operations
	/**
	 * @see IView.
	 */
	@Override
	public void showMainFrame() {
		this.setVisible(true);
	}

	/**
	 * @see IView.
	 */
	@Override
	public void showAboutDialog() {
		new AboutDialog().setVisible(true);
	}

	/**
	 * @see IView.
	 */
	@Override
	public void showDocumentationDialog() {
		new DocumentationDialog().setVisible(true);
	}

	/**
	 * @see IView.
	 */
	@Override
	public void addSoundButton(Object buttonListener, String soundName) throws SoundNameConflictException {
		assert buttonListener != null : "buttonListener is null";
		assert soundName != null && !soundName.equals("") : "soundName is null or has no name";
		if (this.buttons.size() == IView.DEFAULT_SOUND_COUNT) return;

		if (this.buttons.containsKey(soundName))
			throw new SoundNameConflictException("Name already used for button.");

		JButton soundButton = new JButton(soundName);
		soundButton.addActionListener((ActionListener)(buttonListener));

		this.buttons.put(soundName, soundButton);

		buttonPanel.add(soundButton);
		revalidate();
		repaint();
	}

	/**
	 * @see IView.
	 */
	@Override
	public void deleteSoundButton(String soundButtonName) throws NoSoundException {
		assert soundButtonName != null && !soundButtonName.equals("") : "soundButtonName is null or empty";
		if (this.buttons.size() == 0)
			return;

		JButton button = this.buttons.get(soundButtonName);
		if (button == null)
			throw new NoSoundException("Sound not found");
		buttonPanel.remove(button);
		this.buttons.remove(soundButtonName);
		revalidate();
		repaint();
	}

	/**
	 * @see IView.
	 */
	@Override
	public String nameSoundButtonDialog() {
		NameSoundDialog nameSoundDialog = new NameSoundDialog(this);
		if (nameSoundDialog.getConfirmation() == true)
			return nameSoundDialog.getTextFieldContent();
		else
			return "";
	}

	/**
	 * @see IView.
	 */
	@Override
	public File loadPresetFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(this);

		return fileChooser.getSelectedFile();
	}

	/**
	 * @see IView.
	 */
	@Override
	public File savePresetFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showSaveDialog(this);

		return fileChooser.getSelectedFile();
	}

	/**
	 * @see IView.
	 */
	@Override
	public File newSoundFilePath() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);

		FileNameExtensionFilter wavFilter = new FileNameExtensionFilter("WAV", "wav");
		fileChooser.addChoosableFileFilter(wavFilter);
		int fileChooserStatus = fileChooser.showOpenDialog(this);

		if (fileChooserStatus == JFileChooser.CANCEL_OPTION)
			return null;

		if (fileChooserStatus == JFileChooser.ERROR_OPTION)
			return null;

		return fileChooser.getSelectedFile();
	}

	/**
	 * @see IView.
	 */
	@Override
	public void showErrorDialog(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}

	// Add listeners
	/**
	 * @see IView.
	 */
	@Override
	public void addNewSoundListener(Object newSoundListener) {
		this.addSoundOption.addActionListener((ActionListener)(newSoundListener));
	}

	/**
	 * @see IView.
	 */
	@Override
	public void addDeleteSoundListener(Object deleteSoundListener) {
		this.deleteSoundOption.addActionListener((ActionListener)(deleteSoundListener));
	}

	/**
	 * @see IView.
	 */
	@Override
	public void addAboutDialogListener(Object aboutOptionListener) {
		this.aboutOption.addActionListener((ActionListener)(aboutOptionListener));
	}

	/**
	 * @see IView.
	 */
	@Override
	public void addDocumentationListener(Object docOptionListener) {
		this.docOption.addActionListener((ActionListener)(docOptionListener));
	}

	/**
	 * @see IView.
	 */
	@Override
	public void addSavePresetListener(Object saveOptionListener) {
		this.savePresetOption.addActionListener((ActionListener)(saveOptionListener));
	}

	/**
	 * @see IView.
	 */
	@Override
	public void addLoadPresetListener(Object loadOptionListener) {
		this.openPresetOption.addActionListener((ActionListener)(loadOptionListener));
	}
}
