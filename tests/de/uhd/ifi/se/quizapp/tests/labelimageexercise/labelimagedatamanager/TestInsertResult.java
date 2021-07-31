package de.uhd.ifi.se.quizapp.tests.labelimageexercise.labelimagedatamanager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.Student;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageResult;

public class TestInsertResult extends LabelImageDataTestingSuper {

	@Test(expected = java.lang.NullPointerException.class)
	public void testInsertResultNull() throws ClassNotFoundException, SQLException {
		this.dataManager.insertResult(null);
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void testInsertResultEmpty() throws ClassNotFoundException, SQLException {
		LabelImageResult result = new LabelImageResult();

		Student student = new Student("test", "test", "test", "bla");
		result.setStudent(student);

		this.dataManager.insertResult(result);
	}

	@Test
	@Ignore
	public void testInsertResultFilled() throws ClassNotFoundException, SQLException {
		LabelImageResult result = new LabelImageResult();

		Student student = new Student("test", "test", "test", "bla");
		LabelImageExercise exercise = this.dataManager.getExercise(11);
		ArrayList<ImageLabel> labels = new ArrayList<>();
		ImageLabel label = new ImageLabel("Test", "", "");
		labels.add(label);

		result.setStudent(student);
		result.setExercise(exercise);
		result.setLabels(labels);
		this.dataManager.insertResult(result);
	}

	@After
	public void tearDown() throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM result WHERE student_id=?";
		PreparedStatement stmt = this.dataManager.getConnection().prepareStatement(sql);
		stmt.setString(1, "test");
		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Deletion of Result was successful.");
		} else {
			System.out.println("Deletion of Result was not successful.");
		}
	}

}
