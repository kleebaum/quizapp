package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class TestGetExercisesDiff extends LabelImageDataTestingSuper{
	
	@Test
	public void testGetExercisesDiffLess() throws ClassNotFoundException, SQLException{
		assertNull(this.dataManager.getExercises(0));
	}
	
	@Test
	public void testGetExercisesDiffOk() throws ClassNotFoundException, SQLException{
		assertNotNull(this.dataManager.getExercises(1));
	}

	@Test
	public void testGetExercisesDiffToBig() throws ClassNotFoundException, SQLException{
		assertNull(this.dataManager.getExercises(4));
	}
}
