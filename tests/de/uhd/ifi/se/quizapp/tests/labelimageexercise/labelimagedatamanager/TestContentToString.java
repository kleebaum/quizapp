package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageDataManager;

public class TestContentToString extends LabelImageDataTestingSuper {

	@Test
	public void testContentToStringNull() {
		List<ImageLabel> test = LabelImageDataManager.contentToString(null);
		assertTrue(test.size() == 0);
	}

	@Test(expected = java.lang.IllegalStateException.class)
	public void testContentToStringEmpty() {
		LabelImageDataManager.contentToString("");
	}

	@Test(expected = java.lang.IllegalStateException.class)
	public void testContentToStringNonJson() {
		LabelImageDataManager.contentToString("Test");
	}

	@Test(expected = java.lang.IllegalStateException.class)
	public void testContentToStringJsonNotExpected() {
		String jsonTest = "";
		jsonTest += "{";
		jsonTest += "&quot;Test&quot; :&quot;Test&quot;";
		jsonTest += "}";
		LabelImageDataManager.contentToString(jsonTest);
	}

	@Test
	public void testContentToStringJsonExpected() {
		String jsonTest = "[{&quot;image&quot;:&quot;/images/logo.png&quot;},{&quot;position&quot;:&quot;top:60.899993896484375px;left:192.5px;&quot;,&quot;label&quot;:&quot;Test&quot;}]";
		List<ImageLabel> testLabel = LabelImageDataManager.contentToString(jsonTest);
		assertEquals(testLabel.get(0).getPosition(), "\"top:60.899993896484375px;left:192.5px;\"");
	}

}
