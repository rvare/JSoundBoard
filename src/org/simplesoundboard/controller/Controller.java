package org.simplesoundboard.controller;

import java.util.*;
import java.io.*;
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

public class Controller extends AbsController {
	public Controller(final Model model, final IView iView) {
		super(model, iView);

		System.out.println("Controller");
		// Create menu bar listeners
		this.iView.addNewSoundListener(new AddSoundListener());
		this.iView.addDeleteSoundListener(new DeleteSoundListener());
		this.iView.addAboutDialogListener(new AboutListener());
		this.iView.addDocumentationListener(new DocumentationListener());
		this.iView.addSavePresetListener(new SaveOptionListener());
		this.iView.addLoadPresetListener(new LoadOptionListener());

		// Create button listeners

	}

	// Operations
	public void saveSoundPresetFile() {

	}

	public void loadSoundPresetFile() {

	}

	public void showAboutDialog() {

	}

	public void showDocumentationDialog() {

	}

	// Menu bar listeners
	private class LoadOptionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Open preset");
		}
	}

	private class SaveOptionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Save preset");
		}
	}

	private class AddSoundListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Add sound listener");
			SoundButtonListener sbListener = new SoundButtonListener();
			iView.addSoundButton(sbListener);
		}
	}

	private class DeleteSoundListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Delete sound listener");
			iView.deleteSoundButton();
		}
	}

	private class AboutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("About listener");
			iView.showAboutDialog();
		}
	}

	private class DocumentationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Documentation listener");
			iView.showDocumentationDialog();
		}
	}

	// Button listeners
	private class SoundButtonListener implements ActionListener {
		private String name;

		public SoundButtonListener() { }

		public SoundButtonListener(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Button pressed");
		}
	}
}
