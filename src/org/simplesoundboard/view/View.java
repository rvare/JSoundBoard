package org.simplesoundboard.view;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import org.simplesoundboard.view.*;

public class View extends JFrame implements IView {
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

	private JButton[] buttons;
	private static int soundButtonCounts = 0;

	public View() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.buttons = new JButton[this.DEFAULT_SOUND_COUNT];

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
		// this.addNewSoundListener(new NewSoundListener());

		this.deleteSoundOption = new JMenuItem("Delete Sound");
		editMenu.add(deleteSoundOption);
		// this.addDeleteSoundListener(new DeleteSoundListener());

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
		this.buttonLayout = new GridLayout(this.DEFAULT_BUTTON_GRID_HEIGHT,
											this.DEFAULT_BUTTON_GRID_WIDTH);
		this.buttonPanel.setLayout(this.buttonLayout);

		this.getContentPane().add(BorderLayout.CENTER, this.buttonPanel);

		// Create the frame
		this.setTitle(this.WINDOW_TITLE);
		this.setSize(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);

		// this.addAboutDialogListener(new AboutDialogListener());
		// this.addDocumentationListener(new DocumentationDialogListener());
	}

	// Operations
	@Override
	public void showMainFrame() {
		this.setVisible(true);
	}

	@Override
	public void showAboutDialog() {
		new AboutDialog().setVisible(true);
	}

	@Override
	public void showDocumentationDialog() {
		new DocumentationDialog().setVisible(true);
	}

	@Override
	public void addSoundButton(Object buttonListener) {
		if (this.soundButtonCounts == this.DEFAULT_SOUND_COUNT) return;
		JButton soundButton = new JButton(String.format("Button %d", soundButtonCounts));
		soundButton.addActionListener((ActionListener)(buttonListener));
		buttons[soundButtonCounts] = soundButton;
		buttonPanel.add(soundButton);
		++soundButtonCounts;
		revalidate();
		repaint();
	}

	@Override
	public void deleteSoundButton() {
		if (this.soundButtonCounts == 0) return;
		--soundButtonCounts;
		buttonPanel.remove(buttons[soundButtonCounts]);
		buttons[soundButtonCounts] = null;
		revalidate();
		repaint();
	}

	// Add listeners
	@Override
	public void addNewSoundListener(Object newSoundListener) {
		this.addSoundOption.addActionListener((ActionListener)(newSoundListener));
	}

	@Override
	public void addDeleteSoundListener(Object deleteSoundListener) {
		this.deleteSoundOption.addActionListener((ActionListener)(deleteSoundListener));
	}

	@Override
	public void addAboutDialogListener(Object aboutOptionListener) {
		this.aboutOption.addActionListener((ActionListener)(aboutOptionListener));
	}

	@Override
	public void addDocumentationListener(Object docOptionListener) {
		this.docOption.addActionListener((ActionListener)(docOptionListener));
	}

	@Override
	public void addSavePresetListener(Object saveOptionListener) {
		this.savePresetOption.addActionListener((ActionListener)(saveOptionListener));
	}

	@Override
	public void addLoadPresetListener(Object loadOptionListener) {
		this.openPresetOption.addActionListener((ActionListener)(loadOptionListener));
	}
}
