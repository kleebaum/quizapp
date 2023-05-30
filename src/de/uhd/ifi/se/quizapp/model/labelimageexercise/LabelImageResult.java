package de.uhd.ifi.se.quizapp.model.labelimageexercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import de.uhd.ifi.se.quizapp.model.Result;

public class LabelImageResult extends Result {
	private List<ImageLabel> labels;
	private HashMap<ImageLabel, Boolean> result;

	public LabelImageResult() {
		this.labels = new ArrayList<ImageLabel>();
		this.result = new HashMap<ImageLabel, Boolean>();
	}

	public List<ImageLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<ImageLabel> labels) {
		this.labels = labels;
	}

	/**
	 * Checks the result of the Labels. Labels have to be at the same Place in the
	 * Array
	 * 
	 * @return HashMap with the String and if it was on the right place or not
	 */
	public HashMap<ImageLabel, Boolean> getResult() {
		if (this.getLabels() != null) {
			if (this.getLabels().size() > 0) {
				for (ImageLabel label : this.getLabels()) {
					this.result.put(label, false);
				}
			} else {
				return this.result = new HashMap<>();
			}
		} else {
			return null;
		}

		LabelImageExercise exercise = (LabelImageExercise) this.getExercise();
		List<ImageLabel> exerciseLabels = exercise.getLabels();
		List<ImageLabel> resultLabels = this.getLabels();

		if (exerciseLabels.size() == resultLabels.size()) {
			for (int i = 0; i < exerciseLabels.size(); i++) {
				if (exerciseLabels.get(i).getLabel().equals(resultLabels.get(i).getLabel())) {
					this.result.put(resultLabels.get(i), true);
				}
			}
		}
		return this.result;
	}

	@Override
	public int getNumberOfCorrectAnswers() {
		int numberOfCorrectLabels = 0;
		Set<Map.Entry<ImageLabel, Boolean>> entrySet = this.getResult().entrySet();
		if (entrySet.isEmpty()) {
			return 0;
		}
		for (Entry<ImageLabel, Boolean> entry : entrySet) {
			if ((Boolean) entry.getValue()) {
				numberOfCorrectLabels++;
			}
		}
		return numberOfCorrectLabels;
	}

	@Override
	public int getNumberOfWrongAnswers() {
		int numberOfCorrectSentences = getNumberOfCorrectAnswers();
		return numberOfCorrectSentences - this.getLabels().size();
	}

	@Override
	public float getPercentage() {
		if (this.getExercise() instanceof LabelImageExercise == false) {
			return -1;
		}
		LabelImageExercise exercise = (LabelImageExercise) this.getExercise();
		if (exercise == null) {
			return -1;
		}
		if (exercise.getLabels().size() == 0) {
			return 0;
		}

		float numberOfCorrectLabels = getNumberOfCorrectAnswers();
		return numberOfCorrectLabels / exercise.getLabels().size();
	}

}
