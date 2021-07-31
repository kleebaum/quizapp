package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageDataManager;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise;

public class TestUpdateExercise extends LabelImageDataTestingSuper {

	LabelImageExercise exercise;

	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		super.setUp();
		this.exercise = new LabelImageExercise();
		this.exercise.setDifficulty(1);
		this.exercise.setInformationId(1);
		this.exercise.setDescription("Test");
		this.exercise.setLabelData(
				"[{&quot;image&quot;:&quot;/images/logo.png&quot;},{&quot;position&quot;:&quot;top:60.899993896484375px;left:192.5px;&quot;,&quot;label&quot;:&quot;Test&quot;}]");

		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		ImageLabel label = new ImageLabel();
		label.setPosition("Test ob es das Element ist");
		labels.add(label);

		this.exercise.setLabels(labels);

		this.dataManager.insertExercise(exercise);
	}

	@Test
	public void testUpdateNull() throws ClassNotFoundException, SQLException {
		this.dataManager.updateExercise(null);
	}

	@Test
	public void testUpdateEmpty() throws ClassNotFoundException, SQLException {

		this.dataManager.updateExercise(this.exercise);
		List<LabelImageExercise> exercises = this.dataManager.getExercises();
		for (LabelImageExercise ex : exercises) {
			if (ex.getDescription().equals(this.exercise.getDescription())) {
				assertTrue(true);
			}
		}
	}

	@Test
	public void testUpdateFilled() throws ClassNotFoundException, SQLException {
		boolean functionality = false;
		List<LabelImageExercise> exercises = this.dataManager.getExercises();
		for (LabelImageExercise ex : exercises) {
			if (ex.getDescription().equals(this.exercise.getDescription())) {
				this.exercise.setId(ex.getExerciseId());
			}
		}

		this.exercise.setDescription("Test2");
		this.exercise.setDifficulty(2);

		this.dataManager.updateExercise(this.exercise);

		LabelImageDataManager dataManagerNew = new LabelImageDataManager();
		List<LabelImageExercise> exercises2 = dataManagerNew.getExercises();
		for (LabelImageExercise ex2 : exercises2) {
			if (ex2.getDescription().equals(this.exercise.getDescription())) {
				functionality = true;
			}
		}
		assertTrue(functionality);
	}

	@After
	public void tearDown() throws ClassNotFoundException, SQLException {
		List<LabelImageExercise> exercises = this.dataManager.getExercises();
		for (LabelImageExercise ex : exercises) {
			if (ex.getDescription().equals(this.exercise.getDescription())) {
				this.dataManager.deleteExercise(ex.getExerciseId());
			}
		}
	}
}
