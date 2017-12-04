package de.uhd.ifi.se.quizapp.model;

public class Information {

	private int informationId;
	private String name;
	private String text;

	public Information() {

	}

	/**
	 * 
	 * @param name
	 * @param text
	 */
	public Information(String name, String text) {
		this.name = name;
		this.text = text;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param text
	 */
	public Information(int id, String name, String text) {
		this.informationId = id;
		this.name = name;
		this.text = text;
	}

	public int getInformationId() {
		return this.informationId;
	}

	public void setInformationId(int id) {
		this.informationId = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}