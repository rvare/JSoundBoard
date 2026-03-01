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

package org.simplesoundboard.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Represents the GUI dialog to name a sound when a new one is loaded.
 * @author Richard Varela
 * @since 1.0
 */
public final class NameSoundDialog extends JDialog {
	private static final short DEFAULT_WIDTH = 250;
	private static final short DEFAULT_HEIGHT = 150;
	private static final short TEXT_FIELD_COLUMNS = 20;
	private JTextField textField;
	private String soundName;
	private boolean confirmation;

	/**
	 * Default constructor.
	 * @param mainFrame A JFrame that is the main frame of the application, as in, the View class.
	 */
	public NameSoundDialog(final JFrame mainFrame) {
		super(mainFrame);

		this.confirmation = false;

		JLabel promptLabel = new JLabel("Enter name for sound");

		this.textField = new JTextField(NameSoundDialog.TEXT_FIELD_COLUMNS);

		JPanel namePanel = new JPanel();
		namePanel.add(promptLabel);
		namePanel.add(this.textField);
		this.getContentPane().add(BorderLayout.CENTER, namePanel);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new okButtonListener());
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new cancelButtonListener());

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		this.getContentPane().add(BorderLayout.SOUTH, buttonPanel);

		this.pack();
		this.setSize(NameSoundDialog.DEFAULT_WIDTH, NameSoundDialog.DEFAULT_HEIGHT);
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		this.setVisible(true);
	}

	// Getters
	/**
	 * Getter that retrieves the name the user typed.
	 * @return A String object that contains the name the user inputed in the dialog.
	 * @since 1.0
	 */
	public String getTextFieldContent() {
		return this.textField.getText();
	}

	/**
	 * Getter that retrieves the user's confirmation.
	 * @return Returns a boolean. It'll return true if the user clicks 'OK', false for 'Cancel'.
	 * @since 1.0
	 */
	public boolean getConfirmation() {
		return this.confirmation;
	}

	// Internal classes
	/**
	 * Private class that acts as a listener for the 'OK' button.
	 * @author Richard Varela
	 * @since 1.0
	 */
	private class okButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			confirmation = true;
			dispose();
		}
	}

	/**
	 * Private class that acts as a listener for the 'Cancel' button.
	 * @author Richard Varela
	 * @since 1.0
	 */
	private class cancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			confirmation = false;
			dispose();
		}
	}
}
