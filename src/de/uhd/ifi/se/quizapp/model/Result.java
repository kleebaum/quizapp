package de.uhd.ifi.se.quizapp.model;

public abstract class Result {

	private Exercise exercise;
	private Student student;

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public abstract int getNumberOfCorrectAnswers();

	public abstract int getNumberOfWrongAnswers();

	public abstract float getPercentage();
}
