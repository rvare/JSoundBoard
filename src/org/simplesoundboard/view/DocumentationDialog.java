package org.simplesoundboard.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public final class DocumentationDialog extends JDialog {
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
