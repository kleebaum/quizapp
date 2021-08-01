package de.uhd.ifi.se.quizapp.tests.datamanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.Administrator;
import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Student;

public class TestDataManager {

	private DataManager dataManager;

	@Before
	public void setUp() {
		dataManager = new DataManager();
	}

	@Test
	public void testGetStudentByUsernameWithUsernameEmpty() {
		Student student = dataManager.getStudent("");
		assertNull(student);
	}

	@Test
	public void testGetStudentByUsernameWithUsernameNull() {
		Student student = dataManager.getStudent(null);
		assertNull(student);
	}

	@Test
	public void testGetAdminByUsernameOfDataManagerWithUsernameInTheDatabase() {
		String username = "admin";
		Administrator administrator = dataManager.getAdministrator(username);
		assertNotNull(administrator);
		assertEquals(administrator.getUsername(), username);
	}

	@Test
	public void testGetStudents() {
		List<Student> studentList = dataManager.getStudents();
		assertNotNull(studentList);
		assertTrue(studentList.size() > 0);
	}

	@Test
	public void testInsertStudentOfDataManagerWithStudentNotNull() {
		dataManager.deleteUser("username");
		Student student = new Student("username", "userfirstname", "userlastname", "userpassword");
		assertTrue(dataManager.insertStudent(student));
	}

	@Test
	public void testInsertStudentOfDataManagerWithUninitializedStudent() {
		Student student = new Student(null, null, null, null);
		assertFalse(dataManager.insertStudent(student));
	}
}