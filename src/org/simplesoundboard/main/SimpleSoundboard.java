package org.simplesoundboard.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.simplesoundboard.view.View;
import org.simplesoundboard.model.Model;
import org.simplesoundboard.model.SoundClip;

public class SimpleSoundBoard {
	public static void main(String[] args) {
		// View view = new View();
		// view.showMainFrame();
		Model model = new Model();
		try {
			SoundClip sc = new SoundClip("../MegaFart.wav", "MegaFart");
			model.subscribe(sc);
			SoundClip sc2 = new SoundClip("../OhYeahFilthy.wav", "OhYeahFilthy");
			model.subscribe(sc2);
			Scanner scanner = new Scanner(System.in);
			int i = 1;

			model.notifySubscribers("MegaFart");
			model.notifySubscribers("OhYeahFilthy");

			model.unsubscribe(sc);
			model.unsubscribe(sc2);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
