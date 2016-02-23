package io.ckl.depencyinjectionsampleapp.helpers;

import java.util.Iterator;
import java.util.List;

/**
 * Created by edsonmenegatti on 2/23/16.
 */
public class ValidationHelper {

	public static void validate(Validation... args) {
		for (Validation validation : args) {
			validation.validate();
		}
	}

	public static void pruneInvalid(List<? extends Validation> validations) {
		Iterator<? extends Validation> itr = validations.iterator();
		while (itr.hasNext()) {
			try {
				Validation next = itr.next();
				next.validate();
			} catch (ValidationFailedException ex) {
				itr.remove();
			}
		}
	}
}