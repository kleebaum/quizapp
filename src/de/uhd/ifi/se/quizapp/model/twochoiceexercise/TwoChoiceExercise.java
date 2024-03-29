package de.uhd.ifi.se.quizapp.model.twochoiceexercise;

import java.util.ArrayList;
import java.util.List;

import de.uhd.ifi.se.quizapp.model.Exercise;

public class TwoChoiceExercise extends Exercise {

	private List<BooleanStatement> booleanStatements;

	public TwoChoiceExercise() {
		this.booleanStatements = new ArrayList<BooleanStatement>();
	}

	public TwoChoiceExercise(int id, int difficulty, String description, int informationId,
			ArrayList<BooleanStatement> booleanStatements) {
		super(id, difficulty, description, informationId);
		this.booleanStatements = booleanStatements;
	}

	public List<BooleanStatement> getBooleanStatements() {
		return booleanStatements;
	}

	public void setBooleanStatements(List<BooleanStatement> booleanStatements) {
		this.booleanStatements = booleanStatements;
	}

	public String toString() {
		return this.booleanStatements.toString();
	}
}
