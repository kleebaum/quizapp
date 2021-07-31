package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimageresult;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;

public class TestGetLabels extends LabelImageResultTestingSuper {

	@Test
	public void testGetLabelsVariableNull(){
		this.result.setLabels(null);
		assertNull(this.result.getLabels());
	}
	
	@Test 
	public void testGetLabelsVariableEmpty(){
		this.result.setLabels(new ArrayList<ImageLabel>());
		assertTrue(this.result.getLabels().size()==0);
	}
	
	@Test
	public void testGetLabelsVariableFilled(){
		ImageLabel label = new ImageLabel();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		labels.add(label);
		this.result.setLabels(labels);
		assertTrue(this.result.getLabels().equals(labels));
	}
}
