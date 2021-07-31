package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

/**
 * 
 * More Test are to complex for the useability and the the complexity
 *
 */
public class TestGetExercise extends LabelImageDataTestingSuper {

	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	@Test
	public void testGetExercise() throws ClassNotFoundException, SQLException{
		assertNotNull(this.dataManager.getExercises());
	}

}
