package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimageexercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise;

public class TestSetLabelData {
	
	private LabelImageExercise imageExercise;
	
	@Before
	public void setUp(){
		this.imageExercise=new LabelImageExercise();
	}
	
	@After
	public void tearDown(){
	}
	
	@Test
	public void testSetLabelDataVariableNullParameterNull(){
		this.imageExercise.setLabelData(null);
		this.imageExercise.setLabelData(null);
		assertNull(this.imageExercise.getLabelData());		
	}
	
	@Test
	public void testSetLabelDataVariableEmptyParameterNull(){
		this.imageExercise.setLabelData("");
		this.imageExercise.setLabelData(null);
		assertNull(this.imageExercise.getLabelData());		
	}
	
	@Test
	public void testSetLabelDataVariableFilledParameterNull(){
		this.imageExercise.setLabelData("Test");
		this.imageExercise.setLabelData(null);
		assertNull(this.imageExercise.getLabelData());		
	}
	
	@Test
	public void testSetLabelDataVariableNullParameterEmpty(){
		this.imageExercise.setLabelData(null);
		this.imageExercise.setLabelData("");
		assertEquals(this.imageExercise.getLabelData(),"");	
	}
	
	@Test
	public void testSetLabelDataVariableEmptyParameterEmpty(){
		this.imageExercise.setLabelData("");
		this.imageExercise.setLabelData("");
		assertEquals(this.imageExercise.getLabelData(),"");	
	}
	
	@Test
	public void testSetLabelDataVariableFilledParameterEmpty(){
		this.imageExercise.setLabelData("Test");
		this.imageExercise.setLabelData("");
		assertEquals(this.imageExercise.getLabelData(),"");	
	}
	
	@Test
	public void testSetLabelDataVariableNullParameterFilled(){
		this.imageExercise.setLabelData(null);
		this.imageExercise.setLabelData("Test");
		assertEquals(this.imageExercise.getLabelData(),"Test");	
	}
	
	@Test
	public void testSetLabelDataVariableEmptyParameterFilled(){
		this.imageExercise.setLabelData("");
		this.imageExercise.setLabelData("Test");
		assertEquals(this.imageExercise.getLabelData(),"Test");	
	}
	
	@Test
	public void testSetLabelDataVariableFilledParameterFilled(){
		this.imageExercise.setLabelData("Test1");
		this.imageExercise.setLabelData("Test");
		assertEquals(this.imageExercise.getLabelData(),"Test");	
	}

}
