package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageDataManager;

public class LabelImageDataTestingSuper {

	protected LabelImageDataManager dataManager;

	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		this.dataManager = new LabelImageDataManager();
	}

	@After
	public void tearDown() throws ClassNotFoundException, SQLException {
	}
}
