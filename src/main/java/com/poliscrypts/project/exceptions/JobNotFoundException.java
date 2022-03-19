package com.poliscrypts.project.exceptions;

public class JobNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1061481266447436544L;

	public JobNotFoundException(String exception) {
		super(exception);
	}
}
