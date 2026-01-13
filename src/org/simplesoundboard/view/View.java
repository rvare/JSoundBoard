package org.simplesoundboard.view;

import org.simplesoundboard.view.IView;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

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

	public View() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		this.buttonLayout = new GridLayout(3, 3);
		this.buttonPanel.setLayout(this.buttonLayout);
		this.buttonPanel.add(new JButton("Button 1"));
		this.buttonPanel.add(new JButton("Button 2"));
		this.buttonPanel.add(new JButton("Button 3"));
		this.buttonPanel.add(new JButton("Button 4"));
		this.buttonPanel.add(new JButton("Button 5"));
		this.buttonPanel.add(new JButton("Button 6"));
		this.buttonPanel.add(new JButton("Button 7"));
		this.buttonPanel.add(new JButton("Button 8"));
		this.buttonPanel.add(new JButton("Button 9"));

		this.getContentPane().add(BorderLayout.CENTER, this.buttonPanel);

		// Create the frame
		this.setTitle(this.WINDOW_TITLE);
		this.setSize(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
	}

	public void showMainFrame() {
		this.setVisible(true);
	}

	public void addNewSoundListener(Object newSoundButtonListener) {
	}

	public void addDeleteSoundListener(Object deleteSoundButtonListener) {
	}

	public void addAboutDialogListener(Object aboutOptionListener) {
	}

	public void addDocumentationListener(Object docOptionListener) {
	}

	public void addSavePresetListener(Object saveOptionListener) {
	}

	public void addLoadPresetListener(Object loadOptionListener) {
	}

}
