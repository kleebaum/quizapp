package de.uhd.ifi.se.quizapp.tests.sentencepartexercise.sentencepartdatamanager;

import static org.junit.Assert.assertTrue;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.sentencepartexercise.Sentence;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise;

public class TestInsertExercise {
	SentencePartDataManager dataManager;
	SentencePartExercise exercise;
	List<Sentence> sentencesEmpty;

	@Before
	public void setUp() {
		this.dataManager = new SentencePartDataManager();
		this.exercise = new SentencePartExercise();
		this.exercise.setDifficulty(1);
		this.sentencesEmpty = new ArrayList<>();
	}

	/**
	 * 
	 * @param exerciseList
	 * @return boolean if an exercise is the same
	 */
	public boolean compareExercis(List<SentencePartExercise> exerciseList) {
		for (SentencePartExercise sentenceExercise : exerciseList) {
			for (Sentence returnSentence : sentenceExercise.getSentences()) {
				String sentence = returnSentence.getSentenceParts().get(0);
				String exSentence = this.exercise.getSentences().get(0).getSentenceParts().get(0);
				if (sentence.equals(exSentence)) {
					return true;
				}
			}
		}
		return false;
	}

	@Test(expected = NullPointerException.class)
	public void testAK1() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription(null);
		this.exercise.setSentences(null);
		this.dataManager.insertExercise(this.exercise);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAK2() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription(null);
		this.exercise.setSentences(this.sentencesEmpty);
		this.dataManager.insertExercise(this.exercise);
	}

	@Test
	public void testAK3() throws ClassNotFoundException, SQLException {

		Sentence sentence = new Sentence(Arrays.asList("TestAK3"));
		ArrayList<Sentence> sentencesContent = new ArrayList<>();
		sentencesContent.add(sentence);

		this.exercise.setDescription(null);
		this.exercise.setSentences(sentencesContent);
		this.dataManager.insertExercise(exercise);
		assertTrue(compareExercis(this.dataManager.getExercises()));
	}

	@Test(expected = NullPointerException.class)
	public void testAK4() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription(null);
		this.exercise.setInformationId(1);
		this.exercise.setSentences(null);
		this.dataManager.insertExercise(exercise);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAK5() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription(null);
		this.exercise.setInformationId(1);
		this.exercise.setSentences(sentencesEmpty);
		this.dataManager.insertExercise(exercise);
	}

	@Test
	public void testAK6() throws ClassNotFoundException, SQLException {
		Sentence sentence = new Sentence(Arrays.asList("TestAK6"));
		ArrayList<Sentence> sentencesContent = new ArrayList<>();
		sentencesContent.add(sentence);

		this.exercise.setDescription(null);
		this.exercise.setInformationId(1);
		this.exercise.setSentences(sentencesContent);
		this.dataManager.insertExercise(exercise);
		assertTrue(compareExercis(this.dataManager.getExercises()));
	}

	@Test(expected = NullPointerException.class)
	public void testAK7() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription(null);
		this.exercise.setId(1);
		this.exercise.setSentences(null);
		this.dataManager.insertExercise(exercise);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAK8() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription(null);
		this.exercise.setId(1);
		this.exercise.setSentences(sentencesEmpty);
		this.dataManager.insertExercise(exercise);
	}

	@Test
	public void testAK9() throws ClassNotFoundException, SQLException {
		Sentence sentence = new Sentence(Arrays.asList("TestAK9"));
		ArrayList<Sentence> sentencesContent = new ArrayList<>();
		sentencesContent.add(sentence);

		this.exercise.setDescription(null);
		this.exercise.setId(1);
		this.exercise.setSentences(sentencesContent);
		this.dataManager.insertExercise(exercise);
		assertTrue(compareExercis(this.dataManager.getExercises()));
	}

	@Test(expected = NullPointerException.class)
	public void testAK10() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription(null);
		this.exercise.setId(1);
		this.exercise.setInformationId(1);
		this.exercise.setSentences(null);
		this.dataManager.insertExercise(exercise);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAK11() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription(null);
		this.exercise.setId(1);
		this.exercise.setInformationId(1);
		this.exercise.setSentences(sentencesEmpty);
		this.dataManager.insertExercise(exercise);
	}

	@Test
	public void testAK12() throws ClassNotFoundException, SQLException {
		Sentence sentence = new Sentence(Arrays.asList("TestAK12"));
		ArrayList<Sentence> sentencesContent = new ArrayList<>();
		sentencesContent.add(sentence);

		this.exercise.setDescription(null);
		this.exercise.setId(1);
		this.exercise.setInformationId(1);
		this.exercise.setSentences(sentencesContent);
		this.dataManager.insertExercise(exercise);

		assertTrue(compareExercis(this.dataManager.getExercises()));
	}

	@Test(expected = NullPointerException.class)
	public void testAK13() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("");
		this.exercise.setSentences(null);
		this.dataManager.insertExercise(exercise);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAK14() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("");
		this.exercise.setSentences(sentencesEmpty);
		this.dataManager.insertExercise(exercise);
	}

	@Test
	public void testAK15() throws ClassNotFoundException, SQLException {
		Sentence sentence = new Sentence(Arrays.asList("TestAK15"));
		ArrayList<Sentence> sentencesContent = new ArrayList<>();
		sentencesContent.add(sentence);

		this.exercise.setDescription("");
		this.exercise.setSentences(sentencesContent);
		this.dataManager.insertExercise(exercise);

		assertTrue(compareExercis(this.dataManager.getExercises()));
	}

	@Test(expected = NullPointerException.class)
	public void testAK16() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("");
		this.exercise.setInformationId(1);
		this.exercise.setSentences(null);
		this.dataManager.insertExercise(exercise);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAK17() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("");
		this.exercise.setInformationId(1);
		this.exercise.setSentences(sentencesEmpty);
		this.dataManager.insertExercise(exercise);
	}

	@Test
	public void testAK18() throws ClassNotFoundException, SQLException {
		Sentence sentence = new Sentence(Arrays.asList("TestAK18"));
		ArrayList<Sentence> sentencesContent = new ArrayList<>();
		sentencesContent.add(sentence);

		this.exercise.setDescription("");
		this.exercise.setInformationId(1);
		this.exercise.setSentences(sentencesContent);
		this.dataManager.insertExercise(exercise);

		assertTrue(compareExercis(this.dataManager.getExercises()));
	}

	@Test(expected = NullPointerException.class)
	public void testAK19() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("");
		this.exercise.setId(1);
		this.exercise.setSentences(null);
		this.dataManager.insertExercise(exercise);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAK20() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("");
		this.exercise.setId(1);
		this.exercise.setSentences(sentencesEmpty);
		this.dataManager.insertExercise(exercise);
	}

	@Test
	public void testAK21() throws ClassNotFoundException, SQLException {
		Sentence sentence = new Sentence(Arrays.asList("TestAK21"));
		ArrayList<Sentence> sentencesContent = new ArrayList<>();
		sentencesContent.add(sentence);

		this.exercise.setDescription("");
		this.exercise.setId(1);
		this.exercise.setSentences(sentencesContent);
		this.dataManager.insertExercise(exercise);

		assertTrue(compareExercis(this.dataManager.getExercises()));
	}

	@Test(expected = NullPointerException.class)
	public void testAk22() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("");
		this.exercise.setId(1);
		this.exercise.setInformationId(1);
		this.exercise.setSentences(null);
		this.dataManager.insertExercise(exercise);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAk23() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("");
		this.exercise.setId(1);
		this.exercise.setInformationId(1);
		this.exercise.setSentences(sentencesEmpty);
		this.dataManager.insertExercise(exercise);
	}

	@Test
	public void testAk24() throws ClassNotFoundException, SQLException {
		Sentence sentence = new Sentence(Arrays.asList("TestAK24"));
		ArrayList<Sentence> sentencesContent = new ArrayList<>();
		sentencesContent.add(sentence);

		this.exercise.setDescription("");
		this.exercise.setId(1);
		this.exercise.setInformationId(1);
		this.exercise.setSentences(sentencesContent);
		this.dataManager.insertExercise(exercise);

		assertTrue(compareExercis(this.dataManager.getExercises()));
	}

	@Test(expected = NullPointerException.class)
	public void testAK25() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("Test");
		this.exercise.setSentences(null);
		this.dataManager.insertExercise(exercise);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAK26() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("Test");
		this.exercise.setSentences(sentencesEmpty);
		this.dataManager.insertExercise(exercise);
	}

	@Test
	public void testAK27() throws ClassNotFoundException, SQLException {
		Sentence sentence = new Sentence(Arrays.asList("TestAK27"));
		ArrayList<Sentence> sentencesContent = new ArrayList<>();
		sentencesContent.add(sentence);

		this.exercise.setDescription("Test");
		this.exercise.setSentences(sentencesContent);
		this.dataManager.insertExercise(exercise);

		assertTrue(compareExercis(this.dataManager.getExercises()));
	}

	@Test(expected = NullPointerException.class)
	public void testAK28() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("Test");
		this.exercise.setInformationId(1);
		this.exercise.setSentences(null);
		this.dataManager.insertExercise(exercise);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAK29() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("Test");
		this.exercise.setInformationId(1);
		this.exercise.setSentences(sentencesEmpty);
		this.dataManager.insertExercise(exercise);
	}

	@Test
	public void testAK30() throws ClassNotFoundException, SQLException {
		Sentence sentence = new Sentence(Arrays.asList("TestAK30"));
		ArrayList<Sentence> sentencesContent = new ArrayList<>();
		sentencesContent.add(sentence);

		this.exercise.setDescription("Test");
		this.exercise.setInformationId(1);
		this.exercise.setSentences(sentencesContent);
		this.dataManager.insertExercise(exercise);

		assertTrue(compareExercis(this.dataManager.getExercises()));
	}

	@Test(expected = NullPointerException.class)
	public void testAK31() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("Test");
		this.exercise.setId(1);
		this.exercise.setSentences(null);
		this.dataManager.insertExercise(exercise);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAK32() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("Test");
		this.exercise.setId(1);
		this.exercise.setSentences(sentencesEmpty);
		this.dataManager.insertExercise(exercise);
	}

	@Test
	public void testAK33() throws ClassNotFoundException, SQLException {
		Sentence sentence = new Sentence(Arrays.asList("TestAK33"));
		ArrayList<Sentence> sentencesContent = new ArrayList<>();
		sentencesContent.add(sentence);

		this.exercise.setDescription("Test");
		this.exercise.setId(1);
		this.exercise.setSentences(sentencesContent);
		this.dataManager.insertExercise(exercise);

		assertTrue(compareExercis(this.dataManager.getExercises()));
	}

	@Test(expected = NullPointerException.class)
	public void testAk34() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("Test");
		this.exercise.setId(1);
		this.exercise.setInformationId(1);
		this.exercise.setSentences(null);
		this.dataManager.insertExercise(exercise);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAk35() throws ClassNotFoundException, SQLException {
		this.exercise.setDescription("Test");
		this.exercise.setId(1);
		this.exercise.setInformationId(1);
		this.exercise.setSentences(sentencesEmpty);
		this.dataManager.insertExercise(exercise);
	}

	@Test
	public void testAk36() throws ClassNotFoundException, SQLException {
		Sentence sentence = new Sentence(Arrays.asList("TestAK36"));
		ArrayList<Sentence> sentencesContent = new ArrayList<>();
		sentencesContent.add(sentence);

		this.exercise.setDescription("Test");
		this.exercise.setId(1);
		this.exercise.setInformationId(1);
		this.exercise.setSentences(sentencesContent);
		this.dataManager.insertExercise(exercise);

		assertTrue(compareExercis(this.dataManager.getExercises()));
	}

	@After
	public void cleanUp() throws ClassNotFoundException, SQLException {
		this.removeExercise();
	}

	private boolean removeExercise() throws SQLException, ClassNotFoundException {
		String sql = "delete from exercise where content like 'TestAK%'";
		PreparedStatement stmt = this.dataManager.getConnection().prepareStatement(sql);
		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Deletion of exercise was successful.");
			return true;
		} else {
			System.out.println("Deletion of exercise was not successful.");
			return false;
		}
	}
}
