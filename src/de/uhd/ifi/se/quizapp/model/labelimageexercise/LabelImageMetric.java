package de.uhd.ifi.se.quizapp.model.labelimageexercise;

import java.util.List;

import de.uhd.ifi.se.quizapp.model.Metric;

/**
 * This Class can be used to define LabelImageExercise specific metrics
 */
public class LabelImageMetric extends Metric {

	List<LabelImageResult> results;

	@Override
	public int getNumberOfCorrectAnswers() {
		int rightanswer = 0;
		for (LabelImageResult labelImageResult : results) {
			if (labelImageResult.getPercentage() == 1) {
				rightanswer++;
			}
		}
		return rightanswer;
	}

	@Override
	public int getNumberOfWrongAnswers() {
		return this.results.size() - this.getNumberOfCorrectAnswers();
	}

	public void setResults(List<LabelImageResult> results) {
		this.results = results;
	}

}
