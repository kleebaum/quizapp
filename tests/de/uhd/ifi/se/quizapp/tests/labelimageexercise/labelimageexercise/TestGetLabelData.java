package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimageexercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise;

public class TestGetLabelData {
	
	private LabelImageExercise imageExercise;
	
	@Before
	public void setUp(){
		this.imageExercise=new LabelImageExercise();
	}
	
	@After
	public void tearDown(){
	}

	@Test 
	public void testGetLabelDataVariableNull(){
		this.imageExercise.setLabelData(null);
		assertNull(this.imageExercise.getLabelData());
	}
	
	@Test 
	public void testGetLabelsDataVariableEmpty(){
		this.imageExercise.setLabelData("");
		assertEquals(this.imageExercise.getLabelData(),"");
	}
	
	@Test
	public void testGetLabelsDataVariableFilled(){
		this.imageExercise.setLabelData("Test");
		assertEquals(this.imageExercise.getLabelData(), "Test");
	}
}
