package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.Student;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageResult;

public class TestGetResultByStudent extends LabelImageDataTestingSuper {

	@Test
	public void testGetResultByStudentNull() throws ClassNotFoundException, SQLException {
		assertEquals(new ArrayList<>(), this.dataManager.getResultByStudent(null));
	}

	@Test
	public void testGetResultByStudentNotInDatabase() throws ClassNotFoundException, SQLException {
		Student student = new Student();
		List<LabelImageResult> results = this.dataManager.getResultByStudent(student);
		assertEquals(0, results.size(), 0);
	}

	@Test
	@Ignore
	public void testGetResultByStudentInDatabase() throws ClassNotFoundException, SQLException {
		Student student = this.dataManager.getStudent("t");
		List<LabelImageResult> results = this.dataManager.getResultByStudent(student);
		assertNotEquals(0, results.size(), 0);
	}
}
