package de.uhd.ifi.se.quizapp.model.sentencepartexercise;

import java.util.List;

import de.uhd.ifi.se.quizapp.model.Metric;

/**
 * This Class can be used to define SentencePartExercise spezific Metrics
 */
public class SentencePartMetric extends Metric {

	private List<SentencePartResult> results;

	@Override
	public int getNumberOfCorrectAnswers() {
		int rightanswer = 0;
		for (SentencePartResult labelResult : results) {
			if (labelResult.getPercentage() == 1) {
				rightanswer++;
			}
		}
		return rightanswer;
	}

	@Override
	public int getNumberOfWrongAnswers() {
		return this.results.size() - this.getNumberOfCorrectAnswers();
	}

	public void setResults(List<SentencePartResult> results) {
		this.results = results;
	}

}
