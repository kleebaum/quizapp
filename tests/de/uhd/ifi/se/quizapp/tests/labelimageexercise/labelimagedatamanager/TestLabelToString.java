package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageDataManager;

public class TestLabelToString extends LabelImageDataTestingSuper {

	@Test(expected = java.lang.NullPointerException.class)
	public void testLabelsToStringNull() {
		ArrayList<ImageLabel> labels = null;
		LabelImageDataManager.labelsToString(labels);
	}

	@Test
	public void testLabelsToStringEmpty() {
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		assertEquals("[]", LabelImageDataManager.labelsToString(labels));
	}

	@Test
	public void testLabelsToStringFilled() {
		String expected = "[";
		expected += "{" + '"' + "image" + '"' + ':' + '"' + "null" + '"' + '}' + ","; // {"image":""}
		expected += "{" + '"' + "position" + '"' + ':' + '"' + "null" + '"' + ","; // {"position":""}
		expected += '"' + "label" + '"' + ':' + '"' + "Test" + '"' + '}'; // {"label":"Test1"}
		expected += "]";
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		ImageLabel label = new ImageLabel("Test");
		labels.add(label);
		assertEquals(expected, LabelImageDataManager.labelsToString(labels));
	}

	@Test
	public void testLabelsToStringFilledMore() {
		String expected = "[";
		expected += "{" + '"' + "image" + '"' + ':' + '"' + "null" + '"' + '}' + ","; // {"image":""}
		expected += "{" + '"' + "position" + '"' + ':' + '"' + "null" + '"' + ","; // {"position":""}
		expected += '"' + "label" + '"' + ':' + '"' + "Test1" + '"' + '}' + ","; // {"label":"Test1"}

		expected += "{" + '"' + "image" + '"' + ':' + '"' + "null" + '"' + '}' + ","; // {"image":""}
		expected += "{" + '"' + "position" + '"' + ':' + '"' + "null" + '"' + ","; // {"position":""}
		expected += '"' + "label" + '"' + ':' + '"' + "Test2" + '"' + '}'; // {"label":"Test2"}
		expected += "]";

		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		ImageLabel label1 = new ImageLabel("Test1");
		ImageLabel label2 = new ImageLabel("Test2");
		labels.add(label1);
		labels.add(label2);
		assertEquals(LabelImageDataManager.labelsToString(labels), expected);
	}
}
