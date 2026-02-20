package org.simplesoundboard.exception;

/**
 * Thrown when user attempts to reuse a sound name more than once.
 * @author Richard Varela
 */
public class SoundNameConflictException extends Exception {
	/**
	 * Default constuctor.
	 * @param errorMessage A String object that represents the error message to be displayed.
	 */
	public SoundNameConflictException(String errorMessage) {
		super(errorMessage);
	}
}
