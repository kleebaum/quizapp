package de.uhd.ifi.se.quizapp.model.sentencepartexercise;

import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.Exercise;

public class SentencePartExercise extends Exercise {

	private ArrayList<Sentence> sentences;

	public SentencePartExercise() {
		this.sentences = new ArrayList<Sentence>();
	}

	public SentencePartExercise(int id, int difficulty, String description, int informationId,
			ArrayList<Sentence> sentences) {
		super(id, difficulty, description, informationId);
		this.sentences = sentences;
	}

	public ArrayList<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(ArrayList<Sentence> sentences) {
		this.sentences = sentences;
	}

	public String toString() {
		return this.sentences.toString();
	}

}
