package de.uhd.ifi.se.quizapp.tests.labelimageexercise.imagelabel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestGetPosition extends ImageLabelTestingSuper{

	@Test 
	public void testGetPositionVariableNull(){
		this.imageLabel.setPosition(null);		
		assertNull(this.imageLabel.getPosition());
	}
	
	@Test 
	public void testGetPositionVariableEmpty(){
		this.imageLabel.setPosition("");
		assertEquals(this.imageLabel.getPosition(),"");
	}
	
	@Test
	public void testGetPositionVariableFilled(){
		this.imageLabel.setPosition("Test");
		assertEquals(this.imageLabel.getPosition(), "Test");
	}
}
