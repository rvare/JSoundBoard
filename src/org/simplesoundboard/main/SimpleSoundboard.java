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
public final class SimpleSoundBoard {
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
