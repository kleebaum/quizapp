package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimageresult;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise;

public class TestGetResult extends LabelImageResultTestingSuper {

	@Test
	public void testGetResultVariableNull(){
		this.result.setLabels(null);
		assertNull(this.result.getResult());		
	}
	
	@Test
	public void testGetResultVariableEmpty(){
		this.result.setLabels(new ArrayList<ImageLabel>());
		assertNotNull(this.result.getResult());		
	}
	
	@Test
	public void testGetResultVariableFilledExerciseLabelsEmpty(){
		ImageLabel label = new ImageLabel();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		labels.add(label);
		this.result.setLabels(labels);
		
		LabelImageExercise exercise = new LabelImageExercise();
		this.result.setExercise(exercise);
		
		assertNotNull(this.result.getResult());	
	}
	
	@Test 
	public void testGetResultVariableFilledLabelsFilledUnequal(){
		ImageLabel label = new ImageLabel();
		label.setLabel("Test1");
		
		ImageLabel label2 = new ImageLabel();
		label.setLabel("Test2");
		
		ArrayList<ImageLabel> labels1 = new ArrayList<ImageLabel>();
		ArrayList<ImageLabel> labels2 = new ArrayList<ImageLabel>();
		
		LabelImageExercise exercise = new LabelImageExercise();
		
		labels1.add(label);	
		labels2.add(label2);
		
		exercise.setDifficulty(1);
		exercise.setLabelData("");
		
		
		exercise.setLabels(labels1);
		this.result.setLabels(labels2);
		
		this.result.setExercise(exercise);
		
		assertNotNull(this.result.getResult());		
	}
	
	@Test 
	public void testGetResultVariableFilledLabelsFilledEqual(){
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
		
		assertNotNull(this.result.getResult());		
	}
}
