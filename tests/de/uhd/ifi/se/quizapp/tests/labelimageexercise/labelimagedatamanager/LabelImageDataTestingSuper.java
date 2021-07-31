package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageDataManager;

public class LabelImageDataTestingSuper {

	protected LabelImageDataManager dataManager;
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException{
		this.dataManager= new LabelImageDataManager();
	}
	
	@After
	public void tearDown() throws ClassNotFoundException, SQLException{
	}
}
