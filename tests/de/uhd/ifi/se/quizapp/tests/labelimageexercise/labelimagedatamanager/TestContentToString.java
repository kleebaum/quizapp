package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;

public class TestContentToString extends LabelImageDataTestingSuper{
	
	@Test
	public void testContentToStringNull(){
		ArrayList<ImageLabel> test=this.dataManager.contentToString(null);
		assertTrue(test.size()==0);
	}

	@Test
	(expected = java.lang.IllegalStateException.class)
	public void testContentToStringEmpty(){
		this.dataManager.contentToString("");
	}
	
	@Test
	(expected = java.lang.IllegalStateException.class)
	public void testContentToStringNonJson(){
		this.dataManager.contentToString("Test");
	}	
	
	@Test
	(expected = java.lang.IllegalStateException.class)
	public void testContentToStringJsonNotExpected(){
		String jsonTest="";
		jsonTest+="{";
		jsonTest+="&quot;Test&quot; :&quot;Test&quot;";
		jsonTest+="}";
		this.dataManager.contentToString(jsonTest);
	}	
	
	@Test
	public void testContentToStringJsonExpected(){
		String jsonTest="[{&quot;image&quot;:&quot;/images/logo.png&quot;},{&quot;position&quot;:&quot;top:60.899993896484375px;left:192.5px;&quot;,&quot;label&quot;:&quot;Test&quot;}]";
		ArrayList<ImageLabel> testLabel=dataManager.contentToString(jsonTest);
		assertEquals(testLabel.get(0).getPosition(),"\"top:60.899993896484375px;left:192.5px;\"");
	}	
	
}
