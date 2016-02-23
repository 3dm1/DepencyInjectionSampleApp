package io.ckl.depencyinjectionsampleapp.helpers;

/**
 * Created by edsonmenegatti on 2/23/16.
 */
public class ValidationFailedException extends RuntimeException {

	public ValidationFailedException() {
	}

	public ValidationFailedException(String detailMessage) {
		super(detailMessage);
	}
}
