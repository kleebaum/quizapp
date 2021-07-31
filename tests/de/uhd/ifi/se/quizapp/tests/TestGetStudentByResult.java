package de.uhd.ifi.se.quizapp.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Student;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartResult;

public class TestGetStudentByResult {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		DataManager dataManager = new DataManager();
		Student student = dataManager.getStudent("admin");
		
		SentencePartDataManager sentenceDataManager = new SentencePartDataManager();
		SentencePartExercise exercise = sentenceDataManager.getExercise(1);
	}

	@After
	public void tearDown() throws Exception {
	}

	// T119
	@Test
	public void TestGetStudentByResultOfTeacherDataManagerWithEmptyExerciseId()
			throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		ArrayList<Student> students = dataManager.getStudentByResult(0);
		assertNotNull(students);
		assertEquals(students.size(), 0);
	}

	/* Solte nicht ausgeführt werden da hier aufgaben erstellt werden müssen und dies die Datenbank 
	 * verunreinigen würde
	
	// T117
	@Test
	public void TestGetStudentByResultOfTeacherDataManagerWithNonEmptyExerciseIdExerciseAndItsResultExist()
			throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();

		// Student muss Aufgabe bearbeitet haben
		SentencePartResult sentenceResult = new SentencePartResult();
		SentencePartDataManager sentenceDataManager = new SentencePartDataManager();

		SentencePartExercise exercise = sentenceDataManager.getExercise(1);
		sentenceResult.setExercise(exercise);

		Student student = dataManager.getStudent("admin");
		sentenceResult.setStudent(student);

		sentenceDataManager.insertResult(sentenceResult);

		ArrayList<Student> students = dataManager.getStudentByResult(1);
		assertNotNull(students);
		assertTrue(students.size() > 0);
	}
	 */
	// T118
	@Test
	public void TestGetStudentByResultOfTeacherDataManagerWithNonEmptyExerciseIdExerciseNotInDatabase()
			throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		ArrayList<Student> students = dataManager.getStudentByResult(4353674);
		assertNotNull(students);
		assertEquals(students.size(), 0);
	}

	// T120
	@Test
	public void TestGetStudentByResultOfTeacherDataManagerWithNotInitializedExerciseId()
			throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		ArrayList<Student> students = dataManager.getStudentByResult(-1);
		assertNotNull(students);
		assertEquals(students.size(), 0);
	}

}
