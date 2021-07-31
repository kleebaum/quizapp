package de.uhd.ifi.se.quizapp.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.Administrator;
import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Information;
import de.uhd.ifi.se.quizapp.model.Student;

public class TestDataManager {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// T64
	@Test
	public void TestGetInformation() throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		ArrayList<Information> informationList = new ArrayList<Information>();
		informationList = dataManager.getInformation();
		assertTrue(informationList.size() != 0);
		assertNotNull(informationList);
	}

	// T65
	@Test
	public void TestGetInformationbByID() throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		Information information = null;
		information = dataManager.getInformation(18);
		assertNotNull(information);
		assertEquals(information.getInformationId(), 18);
	}

	// T77
	@Test
	public void TestGetStudentByUsername() throws ClassNotFoundException, SQLException {
		Student student;
		DataManager dataManager = new DataManager();
		String username = "";
		student = dataManager.getStudent(username);
		assertNull(student);
	}

	// T76
	@Test
	public void TestGetStudentByUsernameOfDataManagerWithUninitializedUsernameNegativeTest()
			throws ClassNotFoundException, SQLException {
		Student student;
		DataManager dataManager = new DataManager();
		String username = null;
		student = dataManager.getStudent(username);
		assertNull(student);
	}

	// T75
	@Test
	public void TestGetAdminByUsernameOfDataManagerWithUsernameInTheDatabase()
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
	public void TestGetStudents() throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		ArrayList<Student> studentList = null;
		studentList = dataManager.getStudents();

		assertNotNull(studentList);
		assertTrue(studentList.size() != 0);
	}

	// T54
	@Test
	public void TestInsertInformationOfDataManagerWithInformationNotNull() throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		Information information = new Information("Obst", "Obst Obst Obst");

		assertTrue(dataManager.insertInformation(information));
	}

	// T55
	@Test
	public void TestInsertInformationOfDataManagerWithUninitializedInformation()
			throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		Information information = null;

		assertFalse(dataManager.insertInformation(information));
	}

	// T71
	@Test
	public void TestInsertStudentOfDataManagerWithStudentNotNull() throws ClassNotFoundException, SQLException {
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
	public void TestUpdateInformationOfDataManagerWithInformationNotNull() throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		Information information = new Information(20, "Obst", "Obst Obst Obst");
		assertTrue(dataManager.updateInformation(information));
	}

	// T59
	@Test
	public void TestUpdateInformationOfDataManagerWithUninitializedInformation()
			throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		Information information = new Information(0, null, null);
		assertFalse(dataManager.updateInformation(information));
	}

	// T70
	@Test
	public void TestInsertStudentOfDataManagerWithUninitializedStudent() throws ClassNotFoundException, SQLException {
		Student student = new Student(null, null, null, null);
		DataManager dataManager = new DataManager();

		assertFalse(dataManager.insertStudent(student));

	}

}
