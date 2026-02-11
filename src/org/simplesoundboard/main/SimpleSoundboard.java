package org.simplesoundboard.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.simplesoundboard.view.*;
import org.simplesoundboard.model.*;
import org.simplesoundboard.controller.*;

public final class SimpleSoundBoard {
	public static void main(String[] args) {
		View view = new View();
		Model model = new Model();

		Controller controller = new Controller(model, view);

		view.showMainFrame();
	}
}
