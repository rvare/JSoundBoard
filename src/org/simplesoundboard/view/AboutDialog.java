package org.simplesoundboard.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Displays a dialog window with information about the application, author, and license information.
 * @author Richard Varela
 * @since 1.0
 */
public final class AboutDialog extends JDialog {
	private static short DEAFULT_WIDTH = 250;
	private static short DEAFULT_HEIGHT = 150;
	private static String applicationTitle = "Simple Sound Board";
	private static String authorAndCopyright = "Copyright 2026 Richard Varela (rvare)";
	private static String versionNumber = "v1.0";

	/**
	 * Default constructor that creates the entire dialog.
	 */
	public AboutDialog() {
		this.setTitle("About");

		JLabel appTitleLabel = new JLabel(AboutDialog.applicationTitle);
		appTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel authorCopyrightLabel = new JLabel(AboutDialog.authorAndCopyright);
		authorCopyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel versionNumberLabel = new JLabel(AboutDialog.versionNumber);
		versionNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(appTitleLabel);
		panel.add(authorCopyrightLabel);
		panel.add(versionNumberLabel);

		this.getContentPane().add(BorderLayout.CENTER, panel);

		this.pack();
		this.setSize(AboutDialog.DEAFULT_WIDTH, AboutDialog.DEAFULT_HEIGHT);
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
	}
}
