package de.uhd.ifi.se.quizapp.tests.sentencepartexercise.sentencepartdatamanager;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.sentencepartexercise.Sentence;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise;

public class TestSentenceToString {

	private String content;
	private ArrayList<Sentence> sentences;
	private Sentence sentenceInstance;
	private SentencePartExercise partExercise;
	private SentencePartDataManager dataManager;

	@Before
	public void testSetUp() throws ClassNotFoundException, SQLException {

		this.partExercise = new SentencePartExercise();
		this.sentenceInstance = new Sentence();
		this.dataManager = new SentencePartDataManager();

		this.sentences = new ArrayList<>();
		this.content = "Test";

		this.sentenceInstance.addSentencePart(this.content);
		this.sentences.add(this.sentenceInstance);

		this.partExercise.setSentences(this.sentences);
		this.partExercise.setDifficulty(3);
		this.partExercise.setDescription("Test BlaBla ");
		this.partExercise.setInformationId(1);

		this.dataManager.insertExercise(partExercise);

	}

	@Test
	public void testSentencesToString() throws ClassNotFoundException, SQLException {
		StringBuffer contBuffer = new StringBuffer();
		contBuffer.append(this.content + "|");
		assertEquals(SentencePartDataManager.sentencesToString(this.sentences), contBuffer.toString());
	}

}
