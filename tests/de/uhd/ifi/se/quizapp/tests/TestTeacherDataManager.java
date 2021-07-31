package de.uhd.ifi.se.quizapp.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Student;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageDataManager;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageResult;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartResult;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceDataManager;

public class TestTeacherDataManager {

	private static DataManager dataManager;
	private static SentencePartDataManager sentencePartDataManager;
	private static TwoChoiceDataManager twoChoiceDataManager;
	private static LabelImageDataManager lableImageDataManager;
	private static Student student;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataManager = new DataManager();
		sentencePartDataManager = new SentencePartDataManager();
		twoChoiceDataManager = new TwoChoiceDataManager();
		lableImageDataManager = new LabelImageDataManager();
		student = new Student();		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dataManager = null;
		sentencePartDataManager = null;
		twoChoiceDataManager = null;
		lableImageDataManager = null;
		student = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetResultByStudentofTeacherDataManagerWithInitializedStudentWithRxistingResult() throws ClassNotFoundException, SQLException {
		student = dataManager.getStudent("t");
		assertNotNull(student);
		
		ArrayList<SentencePartResult> sentencePartResults =  sentencePartDataManager.getResultByStudent(student);
	
		assertTrue(sentencePartResults.size() > 0);
		
	}
	
	@Test
	public void testGetResultByStudentofTeacherDataManagerWithInitializedStudentWithNoResult() throws ClassNotFoundException, SQLException {
		student = dataManager.getStudent("Test");
		assertNotNull(student);
		
		ArrayList<LabelImageResult> sentencePartResults =  lableImageDataManager.getResultByStudent(student);

		assertTrue(sentencePartResults.size() == 0);
		
	}
	
	@Test
	public void testGetResultByStudentofTeacherDataManagerWithNotInitializedStudent() throws ClassNotFoundException, SQLException {
		student = null;
		assertNull(student);
		
		ArrayList<LabelImageResult> sentencePartResults =  lableImageDataManager.getResultByStudent(student);
	
		assertTrue(sentencePartResults.size() == 0);
		
	}

}
