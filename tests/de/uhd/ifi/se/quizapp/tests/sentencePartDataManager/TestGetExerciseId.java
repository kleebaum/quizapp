package de.uhd.ifi.se.quizapp.tests.sentencePartDataManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Test;
import org.sqlite.SQLiteException;

import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise;

public class TestGetExerciseId extends TestGetExerciseSuper{
	/*
	// AQ1
	@Test
	public void testNoExerciseToSmall() throws ClassNotFoundException, SQLException {
		this.removeExerciseData();

		SentencePartDataManager newDataManager = new SentencePartDataManager();
		SentencePartExercise exercise = newDataManager.getExercise(-1);

		assertNull(exercise);
	}


	// AQ2
	@Test
	public void testNoDatabaseToSmall() throws ClassNotFoundException, SQLException {
		this.removeDataBase();
		SentencePartDataManager newDataManager = new SentencePartDataManager();
		SentencePartExercise exercise = newDataManager.getExercise(-1);
		assertNull(exerciseList);
	}

	// AQ3
	@Test
	public void testExerciseInDatabaseToSmall() throws ClassNotFoundException, SQLException {

		SentencePartDataManager newDataManager = new SentencePartDataManager();
		SentencePartExercise exercise = newDataManager.getExercise(-1);

		assertNull(exercise);

	}

	// AQ4
	@Test
	public void testNoExerciseToBig() throws ClassNotFoundException, SQLException {
		this.removeExerciseData();

		SentencePartDataManager newDataManager = new SentencePartDataManager();
		SentencePartExercise exercise = newDataManager.getExercise(1000);

		assertNull(exercise);
	}

	// AQ5
	@Test(expected = SQLiteException.class)
	public void testNoDatabaseToBig() throws ClassNotFoundException, SQLException {
		this.removeDataBase();
		SentencePartDataManager newDataManager = new SentencePartDataManager();
		SentencePartExercise exercise = newDataManager.getExercise(1000);

	}

	
	// AQ6
	@Test
	public void testExerciseInDatabaseToBig() throws ClassNotFoundException, SQLException {

		SentencePartDataManager newDataManager = new SentencePartDataManager();
		SentencePartExercise exercise = newDataManager.getExercise(1000);

		assertNull(exercise);

	}

	// AQ7
	@Test
	public void testNoExercise() throws ClassNotFoundException, SQLException {
		this.removeExerciseData();

		SentencePartDataManager newDataManager = new SentencePartDataManager();
		SentencePartExercise exercise = newDataManager.getExercise(1);

		assertNull(exercise);
	}

	// AQ8
	@Test(expected = SQLiteException.class)
	public void testNoDatabase() throws ClassNotFoundException, SQLException {
		this.removeDataBase();
		SentencePartDataManager newDataManager = new SentencePartDataManager();
		SentencePartExercise exercise = newDataManager.getExercise(1);
	}

	
	// AQ9
	@Test
	public void testExerciseInDatabase() throws ClassNotFoundException, SQLException {

		SentencePartDataManager newDataManager = new SentencePartDataManager();
		SentencePartExercise exercise = newDataManager.getExercise(1);

		assertNotNull(exercise);
	}
	*/
}
