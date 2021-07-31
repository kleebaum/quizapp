package de.uhd.ifi.se.quizapp.tests.labelimageexercise.imagelabel;

import static org.junit.Assert.*;


import org.junit.Test;


public class TestToString extends ImageLabelTestingSuper{
	
	@Test 
	public void testToStringVariableNull(){
		this.imageLabel.setLabel(null);
		assertNull(this.imageLabel.toString());
	}
	
	@Test
	public void testToStringVariableEmpty(){
		this.imageLabel.setLabel("");
		assertNotNull(this.imageLabel.toString());
	}
	
	@Test
	public void testToStringVariableFilled(){
		this.imageLabel.setLabel("Test");
		assertNotNull(this.imageLabel.toString());
	}
	
}
