package de.uhd.ifi.se.quizapp.model;

import java.util.List;

public abstract class Metric {
	Exercise exercise;
	
	List<Result> results;

	public String getExerciseName() {
		return this.exercise.getDescription();
	}	
	
	abstract public int getNumberOfCorrectAnswers(); 
	
	abstract public int getNumberOfWrongAnswers(); 

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
}
