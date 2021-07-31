package de.uhd.ifi.se.quizapp.tests.labelimageexercise.imagelabel;

import org.junit.After;
import org.junit.Before;

import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;

public class ImageLabelTestingSuper {

	protected ImageLabel imageLabel;
	
	@Before
	public void setUp(){
		this.imageLabel=new ImageLabel();
	}
	
	@After
	public void tearDown(){
	}
}
