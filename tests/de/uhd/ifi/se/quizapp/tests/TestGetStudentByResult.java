package de.uhd.ifi.se.quizapp.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Student;

public class TestGetStudentByResult {

	// T119
	@Test
	public void TestGetStudentByResultOfTeacherDataManagerWithEmptyExerciseId()
			throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		ArrayList<Student> students = dataManager.getStudentByResult(0);
		assertNotNull(students);
		assertEquals(students.size(), 0);
	}

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
