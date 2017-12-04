package de.uhd.ifi.se.quizapp.model.sentencepartexercise;

import java.util.ArrayList;
import java.util.List;

public class Sentence {

	private List<String> sentenceParts;

	public Sentence() {
		this.sentenceParts = new ArrayList<String>();
	}

	public Sentence(List<String> sentenceParts) {
		this.sentenceParts = sentenceParts;
	}

	public List<String> getSentenceParts() {
		return sentenceParts;
	}

	public void setSentenceParts(List<String> sentenceParts) {
		this.sentenceParts = sentenceParts;
	}

	public boolean addSentencePart(String sentencePart) {
		if (this.sentenceParts.add(sentencePart))
			return true;
		else
			return false;
	}

	public int getNumberOfSentenceParts() {
		return this.sentenceParts.size();
	}

	public String toString() {
		StringBuffer contentBuffer = new StringBuffer();
		for (String sentencePart : this.getSentenceParts()) {
			contentBuffer.append(sentencePart + " ");
		}
		return contentBuffer.toString();
	}
}
