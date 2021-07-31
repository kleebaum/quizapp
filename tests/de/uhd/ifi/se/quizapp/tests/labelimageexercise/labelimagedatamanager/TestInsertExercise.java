package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise;

public class TestInsertExercise extends LabelImageDataTestingSuper{

	@Test
	(expected= java.lang.NullPointerException.class)
	public void testInsertExerciseNull() throws ClassNotFoundException, SQLException {
		this.dataManager.insertExercise(null);
	}
	
	@Test
	public void testInsertExerciseEmpty() throws ClassNotFoundException, SQLException {
		LabelImageExercise exercise = new LabelImageExercise();
		exercise.setDescription("Test");
	
		exercise.setLabelData("[{&quot;image&quot;:&quot;/images/logo.png&quot;},{&quot;position&quot;:&quot;top:60.899993896484375px;left:192.5px;&quot;,&quot;label&quot;:&quot;Test&quot;}]");

		
		this.dataManager.insertExercise(exercise);
		
		List<LabelImageExercise> exercises =this.dataManager.getExercises();
		for(LabelImageExercise ex:exercises) {
			if(ex.getDescription().equals(exercise.getDescription())) {
				assertTrue(true);
			}
		}
	}
	
	@Test
	public void testInsertExercise() throws ClassNotFoundException, SQLException {
		LabelImageExercise exercise = new LabelImageExercise();
		exercise.setDescription("Test");
		exercise.setDifficulty(1);
		exercise.setInformationId(1);
		exercise.setLabelData("[{&quot;image&quot;:&quot;/images/logo.png&quot;},{&quot;position&quot;:&quot;top:60.899993896484375px;left:192.5px;&quot;,&quot;label&quot;:&quot;Test&quot;}]");

		
		ArrayList<ImageLabel> labels=new ArrayList<ImageLabel>();
		ImageLabel label = new ImageLabel();
		label.setImageSrc("Test");
		
		exercise.setLabels(labels);
		
		this.dataManager.insertExercise(exercise);
		
		List<LabelImageExercise> exercises =this.dataManager.getExercises();
		for(LabelImageExercise ex:exercises) {
			if(ex.getDescription().equals(exercise.getDescription())) {
				assertTrue(true);
			}
		}
		
	}

	@After
	public void tearDown() throws ClassNotFoundException, SQLException {
		List<LabelImageExercise> exercises =this.dataManager.getExercises();
		for(LabelImageExercise ex:exercises) {
			System.out.println(ex.getDescription());
			if(ex.getDescription().equals("Test")){
				this.dataManager.deleteExercise(ex.getExerciseId());
			}
		}
	}
}
