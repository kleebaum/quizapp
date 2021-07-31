package de.uhd.ifi.se.quizapp.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Student;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageDataManager;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageResult;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartResult;

public class TestTeacherDataManager {

	private static DataManager dataManager;
	private static SentencePartDataManager sentencePartDataManager;
	private static LabelImageDataManager lableImageDataManager;
	private static Student student;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataManager = new DataManager();
		sentencePartDataManager = new SentencePartDataManager();
		lableImageDataManager = new LabelImageDataManager();
		student = new Student();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dataManager = null;
		sentencePartDataManager = null;
		lableImageDataManager = null;
		student = null;
	}

	@Test
	@Ignore
	public void testGetResultByStudentofTeacherDataManagerWithInitializedStudentWithRxistingResult()
			throws ClassNotFoundException, SQLException {
		student = dataManager.getStudent("t");
		assertNotNull(student);

		ArrayList<SentencePartResult> sentencePartResults = sentencePartDataManager.getResultByStudent(student);

		assertTrue(sentencePartResults.size() > 0);

	}

	@Test
	@Ignore
	public void testGetResultByStudentofTeacherDataManagerWithInitializedStudentWithNoResult()
			throws ClassNotFoundException, SQLException {
		student = dataManager.getStudent("Test");
		assertNotNull(student);

		ArrayList<LabelImageResult> sentencePartResults = lableImageDataManager.getResultByStudent(student);

		assertTrue(sentencePartResults.size() == 0);

	}

	@Test
	public void testGetResultByStudentofTeacherDataManagerWithNotInitializedStudent()
			throws ClassNotFoundException, SQLException {
		student = null;
		assertNull(student);

		ArrayList<LabelImageResult> sentencePartResults = lableImageDataManager.getResultByStudent(student);

		assertTrue(sentencePartResults.size() == 0);

	}

}
