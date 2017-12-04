package de.uhd.ifi.se.quizapp.model.labelimageexercise;

public class ImageLabel {

	private String label;
	private String position;
	private String imageSource;

	public ImageLabel() {

	}

	public ImageLabel(String label) {
		this.label = label;
	}

	/**
	 * 
	 * @param label
	 * @param position
	 * @param imageSource
	 */
	public ImageLabel(String label, String position, String imageSource) {
		this.label = label;
		this.position = position;
		this.imageSource = imageSource;
	}

	public String toString() {
		if (this.getLabel() == null) {
			return null;
		}
		StringBuffer contentBuffer = new StringBuffer();

		contentBuffer.append(this.getLabel());
		return contentBuffer.toString();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getImageSrc() {
		return imageSource;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSource = imageSrc;
	}
}
