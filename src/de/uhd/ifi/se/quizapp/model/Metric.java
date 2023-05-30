package de.uhd.ifi.se.quizapp.model;

import java.util.List;

public abstract class Metric {
	private Exercise exercise;
	protected List<Result> results;

	public String getExerciseName() {
		return exercise.getDescription();
	}

	abstract public int getNumberOfCorrectAnswers();

	abstract public int getNumberOfWrongAnswers();

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
}
