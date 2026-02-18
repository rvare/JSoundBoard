package org.simplesoundboard.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Displays brief documentation on how to use the application.
 * @author Richard Varela
 */
public final class DocumentationDialog extends JDialog {
	/**
	 * Default constructor that creates the entire window.
	 */
	public DocumentationDialog() {
		this.setTitle("Documentation");

		String docContent = """
							<html>
							<body>
							<p>Here is some doc content.</p>
							</body>
							</html>
							""";
		JLabel docContentContainer = new JLabel(docContent);
		JPanel panel = new JPanel();
		panel.add(docContentContainer);
		this.getContentPane().add(BorderLayout.CENTER, panel);

		this.pack();
		this.setSize(500, 400);
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
	}
}
