package de.uhd.ifi.se.quizapp.model.twochoiceexercise;

import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.Metric;

/**
 * This Class can be used to define TwoChoiceMetric
 * 
 */

public class TwoChoiceMetric extends Metric {

	private ArrayList<TwoChoiceResult> results;

	@Override
	public int getNumberOfCorrectAnswers() {
		int numberOfRightAnswers = 0;
		for (TwoChoiceResult labelResult : results) {
			numberOfRightAnswers += labelResult.getNumberOfCorrectAnswers();
		}
		return numberOfRightAnswers;
	}

	@Override
	public int getNumberOfWrongAnswers() {
		int numberOfWrongAnswers = 0;
		for (TwoChoiceResult labelResult : results) {
			numberOfWrongAnswers += labelResult.getNumberOfWrongAnswers();
		}
		return numberOfWrongAnswers;
	}

	public void setResults(ArrayList<TwoChoiceResult> results) {
		this.results = results;
	}

}
