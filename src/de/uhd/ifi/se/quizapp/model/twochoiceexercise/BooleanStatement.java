package de.uhd.ifi.se.quizapp.model.twochoiceexercise;

public class BooleanStatement {

	private String statement;
	private boolean isCorrect;

	public BooleanStatement() {
	}

	/**
	 * 
	 * @param statement
	 */
	public BooleanStatement(String statement) {
		this.statement = statement;
		this.isCorrect = false;
	}

	public BooleanStatement(String statement, boolean isCorrect) {
		this(statement);
		this.isCorrect = isCorrect;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String toString() {
		StringBuffer contentBuffer = new StringBuffer();

		contentBuffer.append(this.getStatement() + ":");
		if (this.isCorrect()) {
			contentBuffer.append("true");
		} else {
			contentBuffer.append("false");
		}
		return contentBuffer.toString();
	}

}
