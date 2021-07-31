package de.uhd.ifi.se.quizapp.tests.sentencePartDataManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.sentencepartexercise.Sentence;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;

public class TestContentToSentences {

	private String content;

	@Before
	public void testSetUp() throws ClassNotFoundException, SQLException {
		this.content = null;

	}

	// AQ1
	@Test(expected = java.lang.NullPointerException.class)
	public void testStringNullSentencePartsLessSentencesLess() throws ClassNotFoundException, SQLException {
		this.content = null;
		SentencePartDataManager.contentToSentences(content, -1, -1);
	}

	// AQ2
	@Test
	public void testStringEmptySentencePartsLessSentencesLess() throws ClassNotFoundException, SQLException {
		this.content = "";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, -1, -1);
		assertNull(sentences);
	}

	// AQ3
	@Test
	public void testStringFullSentencePartsLessSentencesLess() throws ClassNotFoundException, SQLException {
		this.content = "Test";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, -1, -1);
		assertNull(sentences);
	}

	// AQ4
	@Test(expected = java.lang.NullPointerException.class)
	public void testStringNullSentencePartsOkSentencesLess() throws ClassNotFoundException, SQLException {
		this.content = null;
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 1, -1);
		assertNull(sentences);
	}

	// AQ5
	@Test
	public void testStringEmptySentencePartsOkSentencesLess() throws ClassNotFoundException, SQLException {
		this.content = "";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 1, -1);
		assertNull(sentences);
	}

	// AQ6
	@Test
	public void testStringFullSentencePartsOkSentencesLess() throws ClassNotFoundException, SQLException {
		this.content = "Test";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 1, -1);
		assertNull(sentences);
	}

	// AQ7
	@Test(expected = java.lang.NullPointerException.class)
	public void testStringNullSentencePartsLessentencesOk() throws ClassNotFoundException, SQLException {
		this.content = null;
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, -1, 1);
		assertNull(sentences);
	}

	// AQ8
	@Test
	public void testStringEmptySentencePartsLessSentencesOk() throws ClassNotFoundException, SQLException {
		this.content = "";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, -1, 1);
		assertNull(sentences);
	}

	// AQ9
	@Test
	public void testStringFullSentencePartsLessSentencesOk() throws ClassNotFoundException, SQLException {
		this.content = "Test";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, -1, 1);
		assertNull(sentences);
	}

	// AQ10
	@Test(expected = java.lang.NullPointerException.class)
	public void testStringNullSentencePartsOkSentencesOk() throws ClassNotFoundException, SQLException {
		this.content = null;
		SentencePartDataManager.contentToSentences(content, 1, 1);
	}

	// AQ11
	@Test
	public void testStringEmptySentencePartsOkSentencesOk() throws ClassNotFoundException, SQLException {
		this.content = "";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 1, 1);
		assertNotNull(sentences);
	}

	// AQ12
	@Test
	public void testStringFullSentencePartsOkSentencesOk() throws ClassNotFoundException, SQLException {
		this.content = "Test";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 1, 1);
		assertNotNull(sentences);
	}

	// AQ13
	@Test(expected = java.lang.NullPointerException.class)
	public void testStringNullSentencePartsBigSentencesOk() throws ClassNotFoundException, SQLException {
		this.content = null;
		List<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 2, 1);
		assertNull(sentences);
	}

	// AQ14
	@Test
	public void testStringEmptySentencePartsBigSentencesOk() throws ClassNotFoundException, SQLException {
		this.content = "";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 2, 1);
		assertNull(sentences);
	}

	// AQ15
	@Test
	public void testStringFullSentencePartsBigSentencesOk() throws ClassNotFoundException, SQLException {
		this.content = "Test";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 2, 1);
		assertNull(sentences);
	}

	// AQ16
	@Test(expected = java.lang.NullPointerException.class)
	public void testStringNullSentencePartsOkSentencesBig() throws ClassNotFoundException, SQLException {
		this.content = null;
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 1, 2);
		assertNull(sentences);
	}

	// AQ17
	@Test
	public void testStringEmptySentencePartsOkSentencesBig() throws ClassNotFoundException, SQLException {
		this.content = "";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 1, 2);
		assertNull(sentences);
	}

	// AQ18
	@Test
	public void testStringFullSentencePartsOkSentencesBig() throws ClassNotFoundException, SQLException {
		this.content = "Test";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 1, 2);
		assertNull(sentences);
	}

	// AQ19
	@Test(expected = java.lang.NullPointerException.class)
	public void testStringNullSentencePartsLessSentencesBig() throws ClassNotFoundException, SQLException {
		this.content = null;
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, -1, 2);
		assertNull(sentences);
	}

	// AQ20
	@Test
	public void testStringEmptySentencePartsLessSentencesBig() throws ClassNotFoundException, SQLException {
		this.content = "";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, -1, 2);
		assertNull(sentences);
	}

	// AQ21
	@Test
	public void testStringFullSentencePartsLessSentencesBig() throws ClassNotFoundException, SQLException {
		this.content = "Test";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, -1, 2);
		assertNull(sentences);
	}

	// AQ22
	@Test(expected = java.lang.NullPointerException.class)
	public void testStringNullSentencePartsBigSentencesLess() throws ClassNotFoundException, SQLException {
		this.content = null;
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 2, -1);
		assertNull(sentences);
	}

	// AQ23
	@Test
	public void testStringEmptySentencePartsBigSentencesLess() throws ClassNotFoundException, SQLException {
		this.content = "";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 2, -1);
		assertNull(sentences);
	}

	// AQ24
	@Test
	public void testStringFullSentencePartsBigSentencesLess() throws ClassNotFoundException, SQLException {
		this.content = "Test";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 2, -1);
		assertNull(sentences);
	}

	// AQ25
	@Test(expected = java.lang.NullPointerException.class)
	public void testStringNullSentencePartsBigSentencesBig() throws ClassNotFoundException, SQLException {
		this.content = null;
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 2, 2);
		assertNull(sentences);
	}

	// AQ26
	@Test
	public void testStringEmptySentencePartsBigSentencesBig() throws ClassNotFoundException, SQLException {
		this.content = "";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 2, 2);
		assertNull(sentences);
	}

	// AQ27
	@Test
	public void testStringFullSentencePartsBigSentencesBig() throws ClassNotFoundException, SQLException {
		this.content = "Test";
		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(content, 2, 2);
		assertNull(sentences);
	}

}
