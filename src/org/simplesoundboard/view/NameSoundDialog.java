package org.simplesoundboard.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class NameSoundDialog extends JDialog {
	private static final short DEFAULT_WIDTH = 250;
	private static final short DEFAULT_HEIGHT = 150;
	private static final short TEXT_FIELD_COLUMNS = 20;
	private JTextField textField;
	private String soundName;
	private boolean confirmation;

	public NameSoundDialog(final JFrame mainFrame) {
		super(mainFrame);

		this.confirmation = false;

		JLabel promptLabel = new JLabel("Enter name for sound");

		this.textField = new JTextField(this.TEXT_FIELD_COLUMNS);

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
		this.setSize(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		this.setVisible(true);
	}

	// Getters
	public String getTextFieldContent() {
		return this.textField.getText();
	}

	public boolean getConfirmation() {
		return this.confirmation;
	}

	// Internal classes
	private class okButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			confirmation = true;
			dispose();
		}
	}

	private class cancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			confirmation = false;
			dispose();
		}
	}
}
