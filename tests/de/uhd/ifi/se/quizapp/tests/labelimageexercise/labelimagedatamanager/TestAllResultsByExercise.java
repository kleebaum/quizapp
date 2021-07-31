package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class TestAllResultsByExercise extends LabelImageDataTestingSuper{
	
	@Test
	public void testGetAllResultsByExerciseNull() throws ClassNotFoundException, SQLException{
		assertNull(this.dataManager.getAllResultsByExercise(null));
	}
	
	@Test
	public void testGetAllResultsByExerciseEmpty() throws ClassNotFoundException, SQLException{
		assertEquals(0,this.dataManager.getAllResultsByExercise("").size(),0);
		
	}
	
	@Test
	public void testGetAllResultsByExercisewithId() throws ClassNotFoundException, SQLException{
		assertNotEquals(0,this.dataManager.getAllResultsByExercise("11").size(),0);
	}

}
