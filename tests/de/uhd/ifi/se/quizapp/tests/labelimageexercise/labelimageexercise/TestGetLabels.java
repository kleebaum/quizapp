package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimageexercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise;

public class TestGetLabels {
	
	private LabelImageExercise imageExercise;
	
	@Before
	public void setUp(){
		this.imageExercise=new LabelImageExercise();
	}
	
	@After
	public void tearDown(){
	}
	
	@Test
	public void testGetLabelsVariableNull(){
		this.imageExercise.setLabels(null);
		assertNull(this.imageExercise.getLabels());
	}
	
	@Test 
	public void testGetLabelsVariableEmpty(){
		this.imageExercise.setLabels(new ArrayList<ImageLabel>());
		assertTrue(this.imageExercise.getLabels().size()==0);
	}
	
	@Test
	public void testGetLabelsVariableFilled(){
		ImageLabel label = new ImageLabel();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		labels.add(label);
		this.imageExercise.setLabels(labels);
		assertTrue(this.imageExercise.getLabels().equals(labels));
	}
}
