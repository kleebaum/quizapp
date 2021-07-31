package de.uhd.ifi.se.quizapp.tests.sentencePartDataManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.sqlite.SQLiteException;

import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise;

public class TestGetExerciseDiff extends TestGetExerciseSuper {
	/*
	// AQ1
	@Test
	public void testNoExerciseToSmall() throws ClassNotFoundException, SQLException {
		this.removeExerciseData();

		SentencePartDataManager newDataManager = new SentencePartDataManager();
		exerciseList = new ArrayList<SentencePartExercise>();
		exerciseList = newDataManager.getExercises(-1);

		assertNull(exerciseList);
	}
	// AQ2
	@Test // (expected = SQLiteException.class)
	public void testNoDatabaseToSmall() throws ClassNotFoundException, SQLException {
		this.removeDataBase();
		SentencePartDataManager newDataManager = new SentencePartDataManager();

		exerciseList = new ArrayList<SentencePartExercise>();
		exerciseList = newDataManager.getExercises(-1);

		assertNull(exerciseList);

	}

	// AQ3
	@Test
	public void testExerciseInDatabaseToSmall() throws ClassNotFoundException, SQLException {

		SentencePartDataManager newDataManager = new SentencePartDataManager();
		exerciseList = new ArrayList<SentencePartExercise>();
		exerciseList = newDataManager.getExercises(-1);

		assertNull(exerciseList);

	}

	// AQ4
	@Test
	public void testNoExerciseToBig() throws ClassNotFoundException, SQLException {
		this.removeExerciseData();

		SentencePartDataManager newDataManager = new SentencePartDataManager();
		exerciseList = new ArrayList<SentencePartExercise>();
		exerciseList = newDataManager.getExercises(4);

		assertNull(exerciseList);
	}
	
	// AQ5
	@Test // (expected = SQLiteException.class)
	public void testNoDatabaseToBig() throws ClassNotFoundException, SQLException {
		this.removeDataBase();
		SentencePartDataManager newDataManager = new SentencePartDataManager();
		exerciseList = new ArrayList<SentencePartExercise>();
		exerciseList = newDataManager.getExercises(4);

		assertNull(exerciseList);

	}
	
	// AQ6
	@Test
	public void testExerciseInDatabaseToBig() throws ClassNotFoundException, SQLException {

		SentencePartDataManager newDataManager = new SentencePartDataManager();
		exerciseList = new ArrayList<SentencePartExercise>();
		exerciseList = newDataManager.getExercises(4);

		assertNull(exerciseList);

	}

	// AQ7
	@Test
	public void testNoExercise() throws ClassNotFoundException, SQLException {
		this.removeExerciseData();

		SentencePartDataManager newDataManager = new SentencePartDataManager();
		exerciseList = new ArrayList<SentencePartExercise>();
		exerciseList = newDataManager.getExercises(1);

		assertTrue(exerciseList.size() == 0);
	}

	// AQ8
	@Test(expected = SQLiteException.class)
	public void testNoDatabase() throws ClassNotFoundException, SQLException {
		this.removeDataBase();
		SentencePartDataManager newDataManager = new SentencePartDataManager();
		exerciseList = new ArrayList<SentencePartExercise>();
		exerciseList = newDataManager.getExercises(1);

		assertTrue(exerciseList.size() == 0);
	}
	
	// AQ9
	@Test
	public void testExerciseInDatabase() throws ClassNotFoundException, SQLException {

		SentencePartDataManager newDataManager = new SentencePartDataManager();
		exerciseList = new ArrayList<SentencePartExercise>();
		exerciseList = newDataManager.getExercises(1);

		assertNotNull(exerciseList);
	}
	*/
	
}
