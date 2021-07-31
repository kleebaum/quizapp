package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimageexercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise;

public class TestToString {
	
private LabelImageExercise imageExercise;
	
	@Before
	public void setUp(){
		this.imageExercise=new LabelImageExercise();
	}
	
	@After
	public void tearDown(){
	}
	
	@Test (expected = NullPointerException.class)
	public void testToStringVariableNull(){
		this.imageExercise.setLabels(null);
		this.imageExercise.toString();
	}
	
	@Test
	public void testToStringVariableEmpty(){
		this.imageExercise.setLabels(new ArrayList<ImageLabel>());
		assertNotNull(this.imageExercise.toString());
	}
	
	@Test
	public void testToStringVariableFilled(){
		ImageLabel label = new ImageLabel();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		labels.add(label);
		this.imageExercise.setLabels(labels);
		assertNotNull(this.imageExercise.toString());
	}

}
