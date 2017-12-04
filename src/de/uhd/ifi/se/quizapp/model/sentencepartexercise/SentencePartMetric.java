package de.uhd.ifi.se.quizapp.model.sentencepartexercise;

import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.Metric;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageResult;

/**
 * This Class can be used to define SentencePartExercise 
 * spezific Metrics 
 * 
 * @author timku
 *
 */
public class SentencePartMetric extends Metric {

	ArrayList<SentencePartResult> results;
	
	@Override
	public int getNumberOfCorrectAnswers() {
		int rightanswer=0;
		for (SentencePartResult labelResult : results) {
			if (labelResult.getPercentage() == 1) {
				rightanswer++;
			}
		}
		return rightanswer;
	}

	@Override
	public int getNumberOfWrongAnswers() {
		return this.results.size()-this.getNumberOfCorrectAnswers();
	}

	public void setResults(ArrayList<SentencePartResult> results) {
		this.results = results;
	}
	
}
