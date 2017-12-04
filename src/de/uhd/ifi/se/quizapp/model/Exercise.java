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
	 * 
	 * @param id
	 * @param difficulty
	 * @param description
	 * @param informationId
	 */
	public Exercise(int id, int difficulty, String description, int informationId) {
		this(id);
		this.difficulty = difficulty;
		this.description = description;
		this.informationId = informationId;
	}

	public int getDifficulty() {
		return this.difficulty;
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
		return this.informationId;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setId(int id) {
		this.exerciseId = id;
	}

}
