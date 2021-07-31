package de.uhd.ifi.se.quizapp.tests.labelimageexercise.imagelabel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestGetLabel extends ImageLabelTestingSuper {
	
	@Test 
	public void testGetLabelVariableNull(){
		this.imageLabel.setLabel(null);		
		assertNull(this.imageLabel.getLabel());
	}
	
	@Test 
	public void testGetLabelsVariableEmpty(){
		this.imageLabel.setLabel("");
		assertEquals(this.imageLabel.getLabel(),"");
	}
	
	@Test
	public void testGetLabelsVariableFilled(){
		this.imageLabel.setLabel("Test");
		assertEquals(this.imageLabel.getLabel(), "Test");
	}
}
