package de.uhd.ifi.se.quizapp.tests.sentencePartDataManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.sqlite.SQLiteException;

import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise;

public class TestGetExerciseNoParameter extends TestGetExerciseSuper{
	/*
	
	// AQ1
	@Test
	public void testNoExercise() throws ClassNotFoundException, SQLException{
		this.removeExerciseData();
		
		exerciseList = new ArrayList<SentencePartExercise>();
		exerciseList=this.dataManager.getExercises();
		assertTrue(exerciseList.size()==0);
	}
	
	// AQ2
	@Test (expected = SQLiteException.class)
	public void testNoDatabase() throws ClassNotFoundException, SQLException{
		this.removeDataBase();
		SentencePartDataManager newDataManager=new SentencePartDataManager();
		
		exerciseList = new ArrayList<SentencePartExercise>();
		exerciseList=newDataManager.getExercises();
	}
	
	// AQ3
	@Test
	public void testExerciseInDatabase() throws ClassNotFoundException, SQLException{
		SentencePartDataManager newDataManager = new SentencePartDataManager();
		this.dataManager=newDataManager;
		
		exerciseList = new ArrayList<SentencePartExercise>();
		exerciseList=this.dataManager.getExercises();
		
		assertNotNull(exerciseList);
		
	}
	*/
}
