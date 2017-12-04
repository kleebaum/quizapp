package de.uhd.ifi.se.quizapp.model;

public class Student extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Student() {
		super();
	}

	/**
	 * 
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param password
	 */
	public Student(String username, String firstname, String lastname, String password) {
		super(username, firstname, lastname, password);
	}

}
