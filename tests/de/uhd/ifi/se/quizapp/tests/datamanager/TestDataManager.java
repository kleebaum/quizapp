package de.uhd.ifi.se.quizapp.tests.datamanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.Administrator;
import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Student;

public class TestDataManager {

	// T77
	@Test
	public void testGetStudentByUsername() throws ClassNotFoundException, SQLException {
		Student student;
		DataManager dataManager = new DataManager();
		String username = "";
		student = dataManager.getStudent(username);
		assertNull(student);
	}

	// T76
	@Test
	public void testGetStudentByUsernameOfDataManagerWithUninitializedUsernameNegative()
			throws ClassNotFoundException, SQLException {
		Student student;
		DataManager dataManager = new DataManager();
		String username = null;
		student = dataManager.getStudent(username);
		assertNull(student);
	}

	// T75
	@Test
	public void testGetAdminByUsernameOfDataManagerWithUsernameInTheDatabase()
			throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		String username = "admin";
		Administrator administrator = null;
		administrator = dataManager.getAdministrator(username);
		assertNotNull(administrator);
		assertEquals(administrator.getUsername(), username);
	}

	// T72
	@Test
	public void testGetStudents() throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		List<Student> studentList = null;
		studentList = dataManager.getStudents();

		assertNotNull(studentList);
		assertTrue(studentList.size() != 0);
	}

	// T71
	@Test
	public void testInsertStudentOfDataManagerWithStudentNotNull() throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		Student student = new Student("username", "userfirstname", "userlastname", "userpassword");
		assertTrue(dataManager.insertStudent(student));
	}

	// T70
	@Test
	public void testInsertStudentOfDataManagerWithUninitializedStudent() throws ClassNotFoundException, SQLException {
		Student student = new Student(null, null, null, null);
		DataManager dataManager = new DataManager();

		assertFalse(dataManager.insertStudent(student));
	}
}