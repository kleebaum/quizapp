package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimageresult;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;

public class TestSetLabels extends LabelImageResultTestingSuper {
	
	@Test
	public void testSetLabelsVariableNullParameterNull(){
		this.result.setLabels(null);
		this.result.setLabels(null);
		assertNull(this.result.getLabels());
	}
	
	@Test
	public void testSetLabelsVariableEmptyParameterNull(){
		this.result.setLabels(new ArrayList<ImageLabel>());
		this.result.setLabels(null);
		assertNull(this.result.getLabels());
	}
	
	@Test
	public void testSetLabelsVariableFilledParameterNull(){
		ImageLabel label = new ImageLabel();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		labels.add(label);
		this.result.setLabels(labels);
		this.result.setLabels(null);
		assertNull(this.result.getLabels());
	}
	
	@Test
	public void testSetLabelsVariableNullParameterEmpty(){
		this.result.setLabels(null);
		this.result.setLabels(new ArrayList<ImageLabel>());
		assertTrue(this.result.getLabels().size()==0);
	}
	
	@Test
	public void testSetLabelsVariableEmptyParameterEmpty(){
		this.result.setLabels(new ArrayList<ImageLabel>());
		this.result.setLabels(new ArrayList<ImageLabel>());
		assertTrue(this.result.getLabels().size()==0);
	}
	
	@Test
	public void testSetLabelsVariableFilledParameterEmpty(){
		ImageLabel label = new ImageLabel();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		labels.add(label);
		this.result.setLabels(labels);
		this.result.setLabels(new ArrayList<ImageLabel>());
		assertTrue(this.result.getLabels().size()==0);
	}
	
	@Test
	public void testSetLabelsVariableNullParameterFilled(){
		this.result.setLabels(null);
		ImageLabel label = new ImageLabel();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		labels.add(label);
		this.result.setLabels(labels);
		assertTrue(this.result.getLabels().equals(labels));
	}
	
	@Test
	public void testSetLabelsVariableEmptyParameterFilled(){
		this.result.setLabels(new ArrayList<ImageLabel>());
		ImageLabel label = new ImageLabel();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		labels.add(label);
		this.result.setLabels(labels);
		assertTrue(this.result.getLabels().equals(labels));
	}
	
	@Test
	public void testSetLabelsVariableFilledParameterFilled(){
		ImageLabel label1 = new ImageLabel();
		ArrayList<ImageLabel> labels1 = new ArrayList<ImageLabel>();
		labels1.add(label1);
		this.result.setLabels(labels1);
		ImageLabel label2 = new ImageLabel();
		ArrayList<ImageLabel> labels2 = new ArrayList<ImageLabel>();
		labels2.add(label2);
		this.result.setLabels(labels2);
		assertTrue(this.result.getLabels().equals(labels2));
	}

}
