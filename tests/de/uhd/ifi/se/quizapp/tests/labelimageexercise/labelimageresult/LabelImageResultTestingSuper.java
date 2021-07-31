package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimageresult;

import org.junit.After;
import org.junit.Before;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageResult;

public class LabelImageResultTestingSuper {
	protected LabelImageResult result;
	
	@Before
	public void setUp(){
		this.result=new LabelImageResult();
	}
	
	@After
	public void tearDown(){
	}

}
