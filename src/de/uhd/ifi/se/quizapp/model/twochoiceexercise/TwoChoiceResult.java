package de.uhd.ifi.se.quizapp.model.twochoiceexercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import de.uhd.ifi.se.quizapp.model.Result;

public class TwoChoiceResult extends Result {

	private List<BooleanStatement> booleanStatements;
	private HashMap<BooleanStatement, Boolean> result;

	public TwoChoiceResult() {
		this.booleanStatements = new ArrayList<BooleanStatement>();
		this.result = new HashMap<BooleanStatement, Boolean>();
	}

	public List<BooleanStatement> getBooleanStatements() {
		return booleanStatements;
	}

	public void setBooleanStatements(ArrayList<BooleanStatement> booleanStatements) {
		this.booleanStatements = booleanStatements;
	}

	public String toString() {
		return this.booleanStatements.toString();
	}

	/**
	 * Checks the result of the two-choice-exercise (checks for each boolean
	 * statement whether it is correct or wrong)
	 * 
	 * @return
	 */
	public HashMap<BooleanStatement, Boolean> getResult() {
		TwoChoiceExercise exercise = (TwoChoiceExercise) this.getExercise();

		if (exercise != null) {
			List<BooleanStatement> exerciseStatements = exercise.getBooleanStatements();
			List<BooleanStatement> resultStatements = this.getBooleanStatements();

			for (BooleanStatement resultStatement : resultStatements) {
				for (BooleanStatement exerciseStatement : exerciseStatements) {
					if (resultStatement.getStatement().equals(exerciseStatement.getStatement())) {
						if (resultStatement.isCorrect() == exerciseStatement.isCorrect()) {
							this.result.put(resultStatement, true);
						} else {
							this.result.put(resultStatement, false);
						}
					}
				}
			}
		}

		return this.result;
	}

	@Override
	public int getNumberOfCorrectAnswers() {
		int numberOfCorrectStatements = 0;

		Set<Map.Entry<BooleanStatement, Boolean>> entrySet = this.getResult().entrySet();
		for (Entry<BooleanStatement, Boolean> entry : entrySet) {
			if ((Boolean) entry.getValue()) {
				numberOfCorrectStatements++;
			}
		}
		return numberOfCorrectStatements;
	}

	@Override
	public int getNumberOfWrongAnswers() {
		int numberOfCorrectSentences = getNumberOfCorrectAnswers();
		return numberOfCorrectSentences - this.getBooleanStatements().size();
	}

	@Override
	public float getPercentage() {
		if (this.getExercise() instanceof TwoChoiceExercise == false) {
			return -1;
		}

		TwoChoiceExercise exercise = (TwoChoiceExercise) this.getExercise();
		if (exercise == null) {
			return -1;
		}
		if (exercise.getBooleanStatements().size() == 0) {
			return 0;
		}

		float numberOfCorrectStatements = getNumberOfCorrectAnswers();
		return numberOfCorrectStatements / this.getBooleanStatements().size();
	}

}
