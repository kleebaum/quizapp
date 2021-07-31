package de.uhd.ifi.se.quizapp.model.sentencepartexercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import de.uhd.ifi.se.quizapp.model.Result;

public class SentencePartResult extends Result {

	private List<Sentence> sentences;
	private Map<Sentence, Boolean> result;

	public SentencePartResult() {
		this.sentences = new ArrayList<Sentence>();
		this.result = new HashMap<Sentence, Boolean>();
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(ArrayList<Sentence> sentences) {
		this.sentences = sentences;
	}

	public String toString() {
		return this.sentences.toString();
	}

	/*
	 * Checks the result of the sentence-part-exercise (checks for each sentence
	 * whether it is correct or wrong)
	 */
	public Map<Sentence, Boolean> getResult() {

		if (this.getSentences() != null) {
			// init result map
			for (Sentence sentence : this.getSentences()) {
				this.result.put(sentence, false);
			}

			SentencePartExercise exercise = (SentencePartExercise) this.getExercise();
			List<Sentence> exerciseSentences = exercise.getSentences();
			List<Sentence> resultSentences = this.getSentences();

			for (int i = 0; i < exerciseSentences.size(); i++) {
				for (int j = 0; j < resultSentences.size(); j++) {
					if (exerciseSentences.get(i).toString().replaceAll("\\s+", "")
							.equals(resultSentences.get(j).toString().replaceAll("\\s+", ""))) {
						this.result.put(resultSentences.get(j), true);
					}
				}
			}
		}

		return this.result;
	}

	@Override
	public int getNumberOfCorrectAnswers() {
		int numberOfCorrectSentences = 0;
		Set<Map.Entry<Sentence, Boolean>> entrySet = this.getResult().entrySet();

		if (entrySet.isEmpty())
			return 0;

		for (Entry<Sentence, Boolean> entry : entrySet) {
			if ((Boolean) entry.getValue()) {
				numberOfCorrectSentences++;
			}
		}
		return numberOfCorrectSentences;
	}

	@Override
	public int getNumberOfWrongAnswers() {
		int numberOfCorrectSentences = getNumberOfCorrectAnswers();
		return numberOfCorrectSentences - this.getSentences().size();
	}

	@Override
	public float getPercentage() {
		if (this.getExercise() instanceof SentencePartExercise == false) {
			return -1;
		}

		SentencePartExercise exercise = (SentencePartExercise) this.getExercise();
		if (exercise == null) {
			return -1;
		}
		if (exercise.getSentences().size() == 0 || this.getSentences().size() == 0) {
			return 0;
		}

		float numberOfCorrectSentences = getNumberOfCorrectAnswers();
		return numberOfCorrectSentences / this.getSentences().size();
	}
}
