package de.uhd.ifi.se.quizapp.model;

/**
 * Models admins who are allowed to access the admin space.
 */
public class Administrator extends User {

	private static final long serialVersionUID = 1L;

	public Administrator() {
		super();
	}

	public Administrator(String username, String firstname, String lastname, String password) {
		super(username, firstname, lastname, password);
	}
}