package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimageresult;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise;

public class TestGetPercentage extends LabelImageResultTestingSuper {
	
	@Test
	public void testGetPercentageExerciseNull(){
		this.result.setExercise(null);
		assertEquals(-1, this.result.getPercentage(), 0);
	}
	@Test
	public void testGetPercentageExerciseNullAndInstanceOfLabelImageExercise(){
		LabelImageExercise exercise = null;
		this.result.setExercise(exercise);
		assertEquals(-1, this.result.getPercentage(), 0);
	}
	
	
	
	@Test
	public void testGetPercentageExerciseNotLabelImageExercise(){
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		this.result.setExercise(exercise);
		assertEquals(-1, this.result.getPercentage(), 0);
	}
	
	@Test
	public void testGetPercentageExerciseLabelImageExerciseEmptyEntrySet(){
		ImageLabel label = new ImageLabel();
		label.setLabel("Test");
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		LabelImageExercise exercise = new LabelImageExercise();
		
		labels.add(label);	
		
		
		exercise.setDifficulty(1);
		exercise.setLabelData("");
		
		
		exercise.setLabels(labels);
		this.result.setLabels(labels);
		
		this.result.setExercise(exercise);
		
		this.result.setLabels(new ArrayList<ImageLabel>());
		assertEquals(0, this.result.getPercentage(), 0);
	}
	
	@Test
	public void testGetPercentageExerciseLabelImageExerciseNoLables(){
		LabelImageExercise exercise = new LabelImageExercise();
		this.result.setExercise(exercise);
		assertEquals(0, this.result.getPercentage(), 0);
	}
	
	@Test
	public void testGetPercentageExerciseLabelImageExerciseMoreLabels(){
		ImageLabel label = new ImageLabel();
		label.setLabel("Test");
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		LabelImageExercise exercise = new LabelImageExercise();
		
		labels.add(label);	
		
		
		exercise.setDifficulty(1);
		exercise.setLabelData("");
		
		
		exercise.setLabels(labels);
		this.result.setLabels(labels);
		
		this.result.setExercise(exercise);
		
		assertEquals(0, this.result.getPercentage(), 1);
		
	}

}
