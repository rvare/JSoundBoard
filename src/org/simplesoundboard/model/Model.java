package org.simplesoundboard.model;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.simplesoundboard.model.ISubscriber;

public class Model {
	private File presetFile;
	private String filePresetPath;
	private ISubscriber[] subscribers;
	private short soundCount;

	private static short MAX_SOUNDS = 9;

	public Model() {
		this.soundCount = 0;
		this.subscribers = new ISubscriber[9];
	}

	public void subscribe(ISubscriber subscriber) {
		if (this.soundCount < this.MAX_SOUNDS-1) {
			this.subscribers[this.soundCount] = subscriber;
			++this.soundCount;
		}
	}

	public void unsubscribe(ISubscriber subscriber) {
		if (this.soundCount > 0) {
			this.subscribers[this.soundCount] = null;
			--this.soundCount;
		}
	}

	public void notifySubscribers() {
	}

	public String loadPreset(String filePreset) {
		return "";
	}

	public String savePreset(String filePreset) {
		return "";
	}
}

