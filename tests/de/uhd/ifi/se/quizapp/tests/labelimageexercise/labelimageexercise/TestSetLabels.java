package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimageexercise;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise;

public class TestSetLabels {

	private LabelImageExercise imageExercise;

	@Before
	public void setUp() {
		this.imageExercise = new LabelImageExercise();
	}

	@Test
	public void testSetLabelsVariableNullParameterNull() {
		this.imageExercise.setLabels(null);
		this.imageExercise.setLabels(null);
		assertNull(this.imageExercise.getLabels());
	}

	@Test
	public void testSetLabelsVariableEmptyParameterNull() {
		this.imageExercise.setLabels(new ArrayList<ImageLabel>());
		this.imageExercise.setLabels(null);
		assertNull(this.imageExercise.getLabels());
	}

	@Test
	public void testSetLabelsVariableFilledParameterNull() {
		ImageLabel label = new ImageLabel();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		labels.add(label);
		this.imageExercise.setLabels(labels);
		this.imageExercise.setLabels(null);
		assertNull(this.imageExercise.getLabels());
	}

	@Test
	public void testSetLabelsVariableNullParameterEmpty() {
		this.imageExercise.setLabels(null);
		this.imageExercise.setLabels(new ArrayList<ImageLabel>());
		assertTrue(this.imageExercise.getLabels().size() == 0);
	}

	@Test
	public void testSetLabelsVariableEmptyParameterEmpty() {
		this.imageExercise.setLabels(new ArrayList<ImageLabel>());
		this.imageExercise.setLabels(new ArrayList<ImageLabel>());
		assertTrue(this.imageExercise.getLabels().size() == 0);
	}

	@Test
	public void testSetLabelsVariableFilledParameterEmpty() {
		ImageLabel label = new ImageLabel();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		labels.add(label);
		this.imageExercise.setLabels(labels);
		this.imageExercise.setLabels(new ArrayList<ImageLabel>());
		assertTrue(this.imageExercise.getLabels().size() == 0);
	}

	@Test
	public void testSetLabelsVariableNullParameterFilled() {
		this.imageExercise.setLabels(null);
		ImageLabel label = new ImageLabel();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		labels.add(label);
		this.imageExercise.setLabels(labels);
		assertTrue(this.imageExercise.getLabels().equals(labels));
	}

	@Test
	public void testSetLabelsVariableEmptyParameterFilled() {
		this.imageExercise.setLabels(new ArrayList<ImageLabel>());
		ImageLabel label = new ImageLabel();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		labels.add(label);
		this.imageExercise.setLabels(labels);
		assertTrue(this.imageExercise.getLabels().equals(labels));
	}

	@Test
	public void testSetLabelsVariableFilledParameterFilled() {
		ImageLabel label1 = new ImageLabel();
		ArrayList<ImageLabel> labels1 = new ArrayList<ImageLabel>();
		labels1.add(label1);
		this.imageExercise.setLabels(labels1);
		ImageLabel label2 = new ImageLabel();
		ArrayList<ImageLabel> labels2 = new ArrayList<ImageLabel>();
		labels2.add(label2);
		this.imageExercise.setLabels(labels2);
		assertTrue(this.imageExercise.getLabels().equals(labels2));
	}
}
