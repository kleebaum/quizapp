package de.uhd.ifi.se.quizapp.model;

public abstract class Exercise {

	private int exerciseId;
	private int informationId;
	private String description;
	private int difficulty;

	public Exercise() {
	}

	public Exercise(int id) {
		this();
		this.exerciseId = id;
	}

	/**
	 * @param id
	 *            of the exercise in the database.
	 * @param difficulty
	 *            of the exercise.
	 * @param description
	 *            what to do.
	 * @param informationId
	 *            id of a linked information.
	 */
	public Exercise(int id, int difficulty, String description, int informationId) {
		this(id);
		this.difficulty = difficulty;
		this.description = description;
		this.informationId = informationId;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInformationId(int informationId) {
		this.informationId = informationId;
	}

	public int getInformationId() {
		return informationId;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setId(int id) {
		this.exerciseId = id;
	}

}
