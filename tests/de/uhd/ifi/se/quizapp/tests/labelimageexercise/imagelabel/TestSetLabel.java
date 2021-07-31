package de.uhd.ifi.se.quizapp.tests.labelimageexercise.imagelabel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestSetLabel extends ImageLabelTestingSuper {

	@Test
	public void testSetLabelVariableNullParameterNull(){
		this.imageLabel.setLabel(null);
		this.imageLabel.setLabel(null);
		assertNull(this.imageLabel.getLabel());		
	}
	
	@Test
	public void testSetLabelVariableEmptyParameterNull(){
		this.imageLabel.setLabel("");
		this.imageLabel.setLabel(null);
		assertNull(this.imageLabel.getLabel());		
	}
	
	@Test
	public void testSetLabelVariableFilledParameterNull(){
		this.imageLabel.setLabel("Test");
		this.imageLabel.setLabel(null);
		assertNull(this.imageLabel.getLabel());		
	}
	
	@Test
	public void testSetLabelVariableNullParameterEmpty(){
		this.imageLabel.setLabel(null);
		this.imageLabel.setLabel("");
		assertEquals(this.imageLabel.getLabel(),"");	
	}
	
	@Test
	public void testSetLabelVariableEmptyParameterEmpty(){
		this.imageLabel.setLabel("");
		this.imageLabel.setLabel("");
		assertEquals(this.imageLabel.getLabel(),"");	
	}
	
	@Test
	public void testSetLabelVariableFilledParameterEmpty(){
		this.imageLabel.setLabel("Test");
		this.imageLabel.setLabel("");
		assertEquals(this.imageLabel.getLabel(),"");	
	}
	
	@Test
	public void testSetLabelVariableNullParameterFilled(){
		this.imageLabel.setLabel(null);
		this.imageLabel.setLabel("Test");
		assertEquals(this.imageLabel.getLabel(),"Test");	
	}
	
	@Test
	public void testSetLabelVariableEmptyParameterFilled(){
		this.imageLabel.setLabel("");
		this.imageLabel.setLabel("Test");
		assertEquals(this.imageLabel.getLabel(),"Test");	
	}
	
	@Test
	public void testSetLabelVariableFilledParameterFilled(){
		this.imageLabel.setLabel("Test1");
		this.imageLabel.setLabel("Test");
		assertEquals(this.imageLabel.getLabel(),"Test");	
	}
}
