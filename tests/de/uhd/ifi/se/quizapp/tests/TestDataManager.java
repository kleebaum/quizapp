package de.uhd.ifi.se.quizapp.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.Administrator;
import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Information;
import de.uhd.ifi.se.quizapp.model.Student;

public class TestDataManager {

	// T64
	@Test
	public void testGetInformation() throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		List<Information> informationList = new ArrayList<Information>();
		informationList = dataManager.getInformation();
		assertTrue(informationList.size() != 0);
		assertNotNull(informationList);
	}

	// T65
	@Test
	@Ignore
	public void testGetInformationbByID() throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		Information information = null;
		information = dataManager.getInformation(18);
		assertNotNull(information);
		assertEquals(information.getInformationId(), 18);
	}

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
		ArrayList<Student> studentList = null;
		studentList = dataManager.getStudents();

		assertNotNull(studentList);
		assertTrue(studentList.size() != 0);
	}

	// T54
	@Test
	public void testInsertInformationOfDataManagerWithInformationNotNull() throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		Information information = new Information("Obst", "Obst Obst Obst");

		assertTrue(dataManager.insertInformation(information));
	}

	// T55
	@Test
	public void testInsertInformationOfDataManagerWithUninitializedInformation()
			throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		Information information = null;

		assertFalse(dataManager.insertInformation(information));
	}

	// T71
	@Test
	public void testInsertStudentOfDataManagerWithStudentNotNull() throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		Student student = new Student("username", "userfirstname", "userlastname", "userpassword");
		try {
			assertTrue(dataManager.insertStudent(student));
		} catch (SQLException e) {
			System.out.println("Primary Key already exists");
		}
	}

	// T58
	@Test
	@Ignore
	public void testUpdateInformationOfDataManagerWithInformationNotNull() throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		Information information = new Information(20, "Obst", "Obst Obst Obst");
		assertTrue(dataManager.updateInformation(information));
	}

	// T59
	@Test
	public void testUpdateInformationOfDataManagerWithUninitializedInformation()
			throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		Information information = new Information(0, null, null);
		assertFalse(dataManager.updateInformation(information));
	}

	// T70
	@Test
	public void testInsertStudentOfDataManagerWithUninitializedStudent() throws ClassNotFoundException, SQLException {
		Student student = new Student(null, null, null, null);
		DataManager dataManager = new DataManager();

		assertFalse(dataManager.insertStudent(student));
	}
}