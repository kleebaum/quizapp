package de.uhd.ifi.se.quizapp.model;

public class Information {

	private int informationId;
	private String name;
	private String text;

	public Information() {
		// required for (de)serialization
	}

	public Information(String name, String text) {
		this();
		this.name = name;
		this.text = text;
	}

	public Information(int id, String name, String text) {
		this(name, text);
		this.informationId = id;
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