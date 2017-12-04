package de.uhd.ifi.se.quizapp.model;

public class Administrator extends User {

	private static final long serialVersionUID = 1L;

	public Administrator() {
		super();
	}

	/**
	 * 
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param password
	 */
	public Administrator(String username, String firstname, String lastname, String password) {
		super(username, firstname, lastname, password);
	}

}
