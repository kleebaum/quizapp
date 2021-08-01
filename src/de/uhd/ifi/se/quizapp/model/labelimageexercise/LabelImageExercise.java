package de.uhd.ifi.se.quizapp.model.labelimageexercise;

import java.util.ArrayList;
import java.util.List;

import de.uhd.ifi.se.quizapp.model.Exercise;

public class LabelImageExercise extends Exercise {
	private List<ImageLabel> labels;
	private String labelData;
	private String imageSource;
	// @Decision save Labels as JSONArray in Database
	// @Argument data is sent as an JSONArray from HTML Form
	// @Alternative convert JSONArray to ImageLabel and save in database

	public LabelImageExercise() {
		this.labels = new ArrayList<ImageLabel>();
	}

	public LabelImageExercise(int id, int difficulty, String description, int informationId, List<ImageLabel> labels) {
		super(id, difficulty, description, informationId);
		this.labels = labels;
		this.imageSource = this.labels.get(0).getImageSrc();
	}

	public List<ImageLabel> getLabels() {
		return labels;
	}

	public void setLabels(ArrayList<ImageLabel> labels) {
		this.labels = labels;
	}

	public String getLabelData() {
		return labelData;
	}

	public void setLabelData(String labelData) {
		this.labelData = labelData;
	}

	public String toString() {
		return this.labels.toString();
	}

	public String getImageSrc() {
		return imageSource;
	}

	public void setImageSrc(String imageSource) {
		this.imageSource = imageSource;
	}

}
