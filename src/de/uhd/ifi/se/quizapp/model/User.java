package de.uhd.ifi.se.quizapp.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

//@Decision User is the super class of student, administrator and teacher class
//@Rationale Same attributes, just different role
//@Argument easy to manage access to certain areas

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String role;
	private boolean isValid = false;

	public User() {
		// this("MaxM", "Max", "Mustermann", "1234");
	}

	/**
	 * 
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param password
	 */
	public User(String username, String firstname, String lastname, String password) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}

	public User(String username, String firstname, String lastname, String password, String role) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * 
	 * @param password
	 * @throws NoSuchAlgorithmException
	 */
	public void hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(password.getBytes());
		byte[] digest = messageDigest.digest();
		this.password = DatatypeConverter.printHexBinary(digest).toUpperCase();
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isValid() {
		return this.isValid;
	}

	public String getPasswordHash() {
		return this.password;
	}
}
