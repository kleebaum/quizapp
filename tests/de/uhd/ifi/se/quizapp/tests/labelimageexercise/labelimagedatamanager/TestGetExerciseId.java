package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

public class TestGetExerciseId extends LabelImageDataTestingSuper {

	@Test
	public void testGetExerciseIdLess() throws ClassNotFoundException, SQLException {
		assertNull(this.dataManager.getExercise(0));
	}

	@Test
	@Ignore
	public void testGetExerciseIdInData() throws ClassNotFoundException, SQLException {
		assertNotNull(this.dataManager.getExercise(11));
	}

	@Test
	public void testGetExerciseIdNotInData() throws ClassNotFoundException, SQLException {
		assertNull(this.dataManager.getExercise(1000));
	}
}
