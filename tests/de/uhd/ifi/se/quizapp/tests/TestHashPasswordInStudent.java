package de.uhd.ifi.se.quizapp.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.Student;

public class TestHashPasswordInStudent {

	@Test
	public void testHash() throws NoSuchAlgorithmException {
		String password = "password";
		Student student = new Student();
		student.hashPassword(password);
		String hashedPassword = student.getPasswordHash();
		assertNotEquals(password, hashedPassword);
	}

	@Test
	public void testHashLength() throws NoSuchAlgorithmException {
		String password = "password";
		Student student = new Student();
		student.hashPassword(password);
		String hashedPassword = student.getPasswordHash();
		assertTrue(hashedPassword.length() == 64);
	}
	
	@Test
	public void testHashPasswordOfStudentWithInitializedNonEmptyPassword() throws NoSuchAlgorithmException {
		String password = "";
		Student student = new Student();
		student.hashPassword(password);
		String hashedPassword = student.getPasswordHash();
		assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855".toUpperCase(), hashedPassword);
	}
	
	/*
	 * ???
	 */
	@Test (expected = NoSuchAlgorithmException.class)
	public void testHashPasswordOfStudentWithMD5AlgorithmNotAvailable() throws NoSuchAlgorithmException {
		String password = "password";
		Student student = new Student();
		student.hashPassword(password);
		throw new NoSuchAlgorithmException();
	}
	
	@Test (expected = NullPointerException.class)
	public void testHashPasswordOfStudentWithUninitializedPassword() throws NoSuchAlgorithmException {
		String password = null;
		Student student = new Student();
		student.hashPassword(password);
		String hashedPassword = student.getPasswordHash();
		assertNotEquals(password, hashedPassword);
	}
	
	// toDo tests http://jira-se.ifi.uni-heidelberg.de/secure/Tests.jspa#/design?projectId=11209

}
