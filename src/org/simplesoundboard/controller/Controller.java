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

		// Create menu bar listeners

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

	// Button listeners
}
