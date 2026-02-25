package org.simplesoundboard.exception;

/**
 * Thrown when there is no sound.
 * @author Richard Varela
 * @since 1.0
 */
public class NoSoundException extends Exception {
	/**
	 * Default constuctor.
	 * @param errorMessage A String object that represents the error message to be displayed.
	 */
	public NoSoundException(String errorMessage) {
		super(errorMessage);
	}
}
