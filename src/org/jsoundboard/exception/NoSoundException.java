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
