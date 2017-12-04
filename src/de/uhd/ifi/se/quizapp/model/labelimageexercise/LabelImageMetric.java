package de.uhd.ifi.se.quizapp.model.labelimageexercise;

import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.Metric;
import de.uhd.ifi.se.quizapp.model.Result;

/**
 * This Class can be used to define LabelImageExercise specific metrics
 * 
 * @author timku
 *
 */

public class LabelImageMetric extends Metric {

	ArrayList<LabelImageResult> results;

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

	public void setResults(ArrayList<LabelImageResult> results) {
		this.results = results;
	}

}
