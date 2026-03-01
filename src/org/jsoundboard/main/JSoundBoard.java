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

package org.simplesoundboard.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.simplesoundboard.view.View;
import org.simplesoundboard.model.Model;
import org.simplesoundboard.controller.Controller;

/**
 * The Main entry point of the application.
 * The View, Model, and Controller are instantiated for use, and ends with the main frame being shown.
 * @author Richard Varela
 */
public final class JSoundBoard {
	/**
	 * The main entry point of the application. No arguements are passed in for args.
	 */
	public static void main(String[] args) {
		View view = new View();
		Model model = new Model();

		Controller controller = new Controller(model, view);

		view.showMainFrame();
	}
}
