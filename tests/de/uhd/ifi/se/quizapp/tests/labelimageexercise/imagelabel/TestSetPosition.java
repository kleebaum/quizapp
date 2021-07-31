package de.uhd.ifi.se.quizapp.tests.labelimageexercise.imagelabel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestSetPosition extends ImageLabelTestingSuper{

	@Test
	public void testSetPositionVariableNullParameterNull(){
		this.imageLabel.setPosition(null);
		this.imageLabel.setPosition(null);
		assertNull(this.imageLabel.getPosition());		
	}
	
	@Test
	public void testSetPositionVariableEmptyParameterNull(){
		this.imageLabel.setPosition("");
		this.imageLabel.setPosition(null);
		assertNull(this.imageLabel.getPosition());		
	}
	
	@Test
	public void testSetPositionVariableFilledParameterNull(){
		this.imageLabel.setPosition("Test");
		this.imageLabel.setPosition(null);
		assertNull(this.imageLabel.getPosition());		
	}
	
	@Test
	public void testSetPositionVariableNullParameterEmpty(){
		this.imageLabel.setPosition(null);
		this.imageLabel.setPosition("");
		assertEquals(this.imageLabel.getPosition(),"");	
	}
	
	@Test
	public void testSetPositionVariableEmptyParameterEmpty(){
		this.imageLabel.setPosition("");
		this.imageLabel.setPosition("");
		assertEquals(this.imageLabel.getPosition(),"");	
	}
	
	@Test
	public void testSetPositionVariableFilledParameterEmpty(){
		this.imageLabel.setPosition("Test");
		this.imageLabel.setPosition("");
		assertEquals(this.imageLabel.getPosition(),"");	
	}
	
	@Test
	public void testSetPositionVariableNullParameterFilled(){
		this.imageLabel.setPosition(null);
		this.imageLabel.setPosition("Test");
		assertEquals(this.imageLabel.getPosition(),"Test");	
	}
	
	@Test
	public void testSetPositionVariableEmptyParameterFilled(){
		this.imageLabel.setPosition("");
		this.imageLabel.setPosition("Test");
		assertEquals(this.imageLabel.getPosition(),"Test");	
	}
	
	@Test
	public void testSetPositionVariableFilledParameterFilled(){
		this.imageLabel.setPosition("Test1");
		this.imageLabel.setPosition("Test");
		assertEquals(this.imageLabel.getPosition(),"Test");	
	}
}
