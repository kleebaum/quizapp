package de.uhd.ifi.se.quizapp.tests;

import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.Student;

public class TestStudent {

	@Test
	public void testStudent() {
		Student student = new Student();

		assertTrue(student instanceof Student);
		assertNotNull(student);

		assertNull(student.getFirstname());
		assertNull(student.getLastname());
		assertNull(student.getUsername());
		assertNull(student.getPasswordHash());
	}

	// T5
	@Test
	public void testConstructorOfStudentWithUninitializedUsernameFirstnameLastnamePassword() {
		Student student = new Student(null, null, null, null);

		assertTrue(student instanceof Student);
		assertNotNull(student);

		assertNull(student.getFirstname());
		assertNull(student.getLastname());
		assertNull(student.getUsername());
		assertNull(student.getPasswordHash());
	}

	// T7
	@Test
	public void testConstructorOfStudentWithEmptyFirstnameUninitializedUsernameLastnamePassword() {
		Student student = new Student(null, "", null, null);

		assertTrue(student instanceof Student);
		assertNotNull(student);

		assertNotNull(student.getFirstname());
		assertNull(student.getLastname());
		assertNull(student.getUsername());
		assertNull(student.getPasswordHash());

		assertTrue(student.getFirstname().isEmpty());
	}

	// T9
	@Test
	public void testConstructorOfStudentWithEmptyLastnameUninitializedUsernameFirstnamePassword() {
		Student student = new Student(null, null, "", null);

		assertTrue(student instanceof Student);
		assertNotNull(student);

		assertNull(student.getFirstname());
		assertNotNull(student.getLastname());
		assertNull(student.getUsername());
		assertNull(student.getPasswordHash());

		assertTrue(student.getLastname().isEmpty());
	}

	// T11
	@Test
	public void testConstructorOfStudentWithEmptyPasswordAndUninitializedUsernameFirstnameLastname()
			throws NoSuchAlgorithmException {
		Student student = new Student(null, null, null, "");

		assertTrue(student instanceof Student);
		assertNotNull(student);

		assertNull(student.getFirstname());
		assertNull(student.getLastname());
		assertNull(student.getUsername());
		assertNotNull(student.getPasswordHash());

		assertTrue(student.getPasswordHash().isEmpty());
	}

	// T4
	@Test
	public void testConstructorOfStudentWithEmptyUsernameUninitializedFirstnameLastnamePassword() {
		Student student = new Student("", null, null, null);

		assertTrue(student instanceof Student);
		assertNotNull(student);

		assertNull(student.getFirstname());
		assertNull(student.getLastname());
		assertNotNull(student.getUsername());
		assertNull(student.getPasswordHash());

		assertTrue(student.getUsername().isEmpty());
	}

	// T6
	@Test
	public void testConstructorOfStudentWithNonEmptyFirstnameUninitializedUsernameLastnamePassword() {
		Student student = new Student(null, "TestVorname", null, null);

		assertTrue(student instanceof Student);
		assertNotNull(student);

		assertNotNull(student.getFirstname());
		assertNull(student.getLastname());
		assertNull(student.getUsername());
		assertNull(student.getPasswordHash());

		assertTrue(student.getFirstname() == "TestVorname");
	}

	// T8
	@Test
	public void testConstructorOfStudentWithNonEmptyLastnameUninitializedUsernameFirstnamePassword() {
		Student student = new Student(null, null, "TestNachname", null);

		assertTrue(student instanceof Student);
		assertNotNull(student);

		assertNull(student.getFirstname());
		assertNotNull(student.getLastname());
		assertNull(student.getUsername());
		assertNull(student.getPasswordHash());

		assertTrue(student.getLastname() == "TestNachname");
	}

	// T10
	@Test
	public void testConstructorOfStudentWithNonEmptyPasswordUninitializedUsernameFirstnameLastname() {
		Student student = new Student(null, null, null, "password");

		assertTrue(student instanceof Student);
		assertNotNull(student);

		assertNull(student.getFirstname());
		assertNull(student.getLastname());
		assertNull(student.getUsername());
		assertNotNull(student.getPasswordHash());

		assertTrue(student.getPasswordHash() == "password");
	}

	// T3
	@Test
	public void testConstructorOfStudentWithNonEmptyUsernameUninitializedFirstnameLastnamePassword() {
		Student student = new Student("TestUsername", null, null, null);

		assertTrue(student instanceof Student);
		assertNotNull(student);

		assertNull(student.getFirstname());
		assertNull(student.getLastname());
		assertNotNull(student.getUsername());
		assertNull(student.getPasswordHash());

		assertTrue(student.getUsername() == "TestUsername");
	}
	// toDo: more tests:
	// http://jira-se.ifi.uni-heidelberg.de/secure/Tests.jspa#/design?projectId=11209

}
