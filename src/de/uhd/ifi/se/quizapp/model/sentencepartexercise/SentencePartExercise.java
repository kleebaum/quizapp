package de.uhd.ifi.se.quizapp.model.sentencepartexercise;

import java.util.ArrayList;
import java.util.List;

import de.uhd.ifi.se.quizapp.model.Exercise;

public class SentencePartExercise extends Exercise {

	private List<Sentence> sentences;

	public SentencePartExercise() {
		this.sentences = new ArrayList<Sentence>();
	}

	public SentencePartExercise(int id, int difficulty, String description, int informationId,
			List<Sentence> sentences) {
		super(id, difficulty, description, informationId);
		this.sentences = sentences;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	public String toString() {
		return this.sentences.toString();
	}

}
