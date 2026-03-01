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
 * Displays brief documentation on how to use the application.
 * @author Richard Varela
 * @since 1.0
 */
public final class DocumentationDialog extends JDialog {
	/**
	 * Default constructor that creates the entire window.
	 */
	public DocumentationDialog() {
		this.setTitle("Documentation");

		String docContent = """
							<html>
							<body style='width: 300px'>
							<p>
								To add a sound, go to Edit and click Add Sound.
								There a file explorer will open allowing to find the sound file you want.
								Once you've picked a sound file, give it a unique name and it will be ready to use.
							</p>
							<br>
							<p>
								To delete a sound, go to Edit and click Delete Sound.
								Enter the name of the sound in the dialog and it should be deleted.
							</p>
							<br>
							<p>
								To save your loaded sounds, you can create a preset file by going to File and clicking Save.
								You can use this file to load all your sounds without doing it manually.
								To load a preset, go to File and click Open.
							</p>
							</body>
							</html>
							""";
		JLabel docContentContainer = new JLabel(docContent);
		JPanel panel = new JPanel();
		panel.add(docContentContainer);
		this.getContentPane().add(BorderLayout.CENTER, panel);

		this.pack();
		this.setSize(500, 300);
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
	}
}
