package de.uhd.ifi.se.quizapp.model.sentencepartexercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;

import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Student;

public class SentencePartDataManager extends DataManager {

	public SentencePartDataManager() {
		super();
	}

	/**
	 * Retrieves all two choice exercises from database.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SentencePartExercise> getExercises() throws ClassNotFoundException, SQLException {
		List<SentencePartExercise> exercises = new ArrayList<SentencePartExercise>();
		ResultSet resultSet;

		String sql = "SELECT * FROM exercise WHERE type = 2";
		Statement stmt = this.getConnection().createStatement();

		resultSet = stmt.executeQuery(sql);

		while (resultSet.next()) {
			ArrayList<Sentence> sentences = contentToSentences(resultSet.getString(3), resultSet.getInt(5),
					resultSet.getInt(6));
			SentencePartExercise exercise = new SentencePartExercise(resultSet.getInt(1), resultSet.getInt(2),
					StringEscapeUtils.unescapeHtml(resultSet.getString(4)), resultSet.getInt(7), sentences);
			exercises.add(exercise);
		}
		stmt.close();
		return exercises;
	}

	/**
	 * Parses the content of the exercise separated by "|" to sentences.
	 * 
	 * @param content
	 * @param numberOfSentenceParts
	 * @param numberOfSentences
	 * @return
	 */
	public static ArrayList<Sentence> contentToSentences(String content, int numberOfSentenceParts,
			int numberOfSentences) {
		ArrayList<String> contentParts = new ArrayList<String>(Arrays.asList(content.split("\\|")));

		if (numberOfSentenceParts < 1 || numberOfSentences < 1) {
			System.out.println("Fail");
			return null;
		}
		if ((numberOfSentenceParts * numberOfSentences) != contentParts.size()) {
			System.out.println("Fail");
			return null;
		}

		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		for (int i = 0; i < numberOfSentences; i++) {
			List<String> sentenceParts = new ArrayList<String>();
			for (int j = 0; j < numberOfSentenceParts; j++) {
				String sentencePart = contentParts.get(i * numberOfSentenceParts + j);
				sentenceParts.add(StringEscapeUtils.unescapeHtml(sentencePart));
			}
			sentences.add(new Sentence(sentenceParts));
		}
		return sentences;
	}

	/**
	 * Returns a String with sentences and sentence parts separated by "|" (to be
	 * stored in the database).
	 * 
	 * @param sentences
	 * @return
	 */
	public static String sentencesToString(ArrayList<Sentence> sentences) {
		StringBuffer contentBuffer = new StringBuffer();
		for (Sentence sentence : sentences) {
			for (String sentencePart : sentence.getSentenceParts()) {
				contentBuffer.append(sentencePart + "|");
			}
		}
		return contentBuffer.toString();
	}

	/**
	 * Retrieves a sentence-part-exercise from database by id.
	 * 
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public SentencePartExercise getExercise(int id) throws ClassNotFoundException, SQLException {
		if (id <= 0) {
			System.out.println("The id is not in the Database");
			return null;
		}
		ResultSet resultSet = null;
		SentencePartExercise exercise = null;

		String sql = "SELECT * FROM exercise WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, id);
		resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			if (resultSet.getInt(1) == id) {
				ArrayList<Sentence> sentences = contentToSentences(resultSet.getString(3), resultSet.getInt(5),
						resultSet.getInt(6));
				exercise = new SentencePartExercise(resultSet.getInt(1), resultSet.getInt(2),
						StringEscapeUtils.unescapeHtml(resultSet.getString(4)), resultSet.getInt(7), sentences);

			}
		}

		stmt.close();
		resultSet.close();

		return exercise;
	}

	/**
	 * Inserts an object of class SentencePartExercise into database.
	 * 
	 * @param exercise
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void insertExercise(SentencePartExercise exercise) throws SQLException, ClassNotFoundException {
		ArrayList<Sentence> sentences = exercise.getSentences();
		String content = sentencesToString(sentences);

		String sql = "INSERT INTO exercise (difficulty, content, description, width, height, information_id, type) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exercise.getDifficulty());
		stmt.setString(2, StringEscapeUtils.escapeHtml(Jsoup.parse(content).text()));
		stmt.setString(3, exercise.getDescription());
		stmt.setLong(4, sentences.get(0).getNumberOfSentenceParts());
		stmt.setLong(5, sentences.size());
		stmt.setInt(6, exercise.getInformationId());
		stmt.setInt(7, 2);

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Insertion of sentence-part-exercise was successful.");
		} else {
			System.out.println("Insertion of sentence-part-exercise failed.");
		}
	}

	/**
	 * Update a sentence-part-exercise in database.
	 * 
	 * @param exercise
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void updateExercise(SentencePartExercise exercise) throws SQLException, ClassNotFoundException {
		ArrayList<Sentence> sentences = exercise.getSentences();
		String content = sentencesToString(sentences);

		String sql = "UPDATE exercise SET difficulty = ?, content = ?, description = ?,"
				+ "width = ?, height = ?, information_id = ? WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exercise.getDifficulty());
		stmt.setString(2, StringEscapeUtils.escapeHtml(Jsoup.parse(content).text()));
		stmt.setString(3, exercise.getDescription());
		stmt.setLong(4, sentences.get(0).getNumberOfSentenceParts());
		stmt.setLong(5, sentences.size());
		stmt.setInt(6, exercise.getInformationId());
		stmt.setInt(7, exercise.getExerciseId());

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Update of sentence-part-exercise was successful.");
		} else {
			System.out.println("Update of sentence-part-exercise failed.");
		}
	}

	/**
	 * Retrieves sentence-part-exercises from database by difficulty.
	 * 
	 * @param difficulty
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SentencePartExercise> getExercises(int difficulty) throws ClassNotFoundException, SQLException {
		if (difficulty < 1 || difficulty > 3) {
			System.out.println("Difficulty is: " + difficulty + "but has to be 0<difficulty<4");
			return null;
		}
		List<SentencePartExercise> exercises = new ArrayList<SentencePartExercise>();

		String sql = "SELECT * FROM exercise, information WHERE information.id = exercise.information_id AND exercise.difficulty = ? "
				+ "AND exercise.type = ?";

		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, difficulty);
		stmt.setInt(2, 2);

		ResultSet resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			ArrayList<Sentence> sentences = contentToSentences(resultSet.getString(3), resultSet.getInt(5),
					resultSet.getInt(6));
			SentencePartExercise exercise = new SentencePartExercise(resultSet.getInt(1), resultSet.getInt(2),
					StringEscapeUtils.unescapeHtml(resultSet.getString(4)), resultSet.getInt(7), sentences);
			exercises.add(exercise);
		}

		stmt.close();
		resultSet.close();

		return exercises;
	}

	/**
	 * Inserts a result of a student
	 * 
	 * @param result
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insertResult(SentencePartResult result) throws ClassNotFoundException, SQLException {
		ArrayList<Sentence> sentences = result.getSentences();
		String content = sentencesToString(sentences);
		String sql = "INSERT INTO result (exercise_id, result_content, student_id) VALUES (?,?,?)";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, result.getExercise().getExerciseId());
		stmt.setString(2, StringEscapeUtils.escapeHtml(content));
		stmt.setString(3, StringEscapeUtils.escapeHtml(result.getStudent().getUsername()));

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Insertion of SentencePartResult was successful.");
		} else {
			System.out.println("Insertion of SentencePartResult failed.");
		}
	}

	/**
	 * Get all Results for A Student
	 * 
	 * @param student
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<SentencePartResult> getResultByStudent(Student student)
			throws SQLException, ClassNotFoundException {
		ArrayList<SentencePartResult> results = new ArrayList<SentencePartResult>();
		ResultSet resultSet;

		if (student == null) {
			System.out.println("Student was Null -> Could not be found in Database");
			return results;

		}

		String sql = "SELECT * FROM result,exercise WHERE result.student_id = ? AND result.exercise_id = exercise.id AND exercise.type=2";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, StringEscapeUtils.unescapeHtml(student.getUsername()));

		resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			SentencePartResult result = new SentencePartResult();
			SentencePartExercise exercise = new SentencePartExercise();

			ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(resultSet.getString("content"),
					resultSet.getInt("width"), resultSet.getInt("height"));
			exercise = new SentencePartExercise(resultSet.getInt("id"), resultSet.getInt("difficulty"),
					StringEscapeUtils.unescapeHtml(resultSet.getString("description")),
					resultSet.getInt("information_id"), sentences);

			ArrayList<Sentence> sentences_result = SentencePartDataManager.contentToSentences(
					resultSet.getString("result_content"), resultSet.getInt("width"), resultSet.getInt("height"));

			result.setSentences(sentences_result);
			result.setExercise(exercise);
			result.setStudent(student);

			results.add(result);
		}

		stmt.close();
		resultSet.close();
		return results;
	}

	/**
	 * Retrieves all results for an exercise
	 * 
	 * @param exerciseId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<SentencePartResult> getAllResultsByExercise(String exerciseId)
			throws SQLException, ClassNotFoundException {
		ArrayList<SentencePartResult> results = new ArrayList<SentencePartResult>();
		ResultSet resultSet;

		String sql = "SELECT * FROM result,exercise WHERE result.exercise_id = ? AND exercise.id = ? AND exercise.type=2";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, exerciseId);
		stmt.setString(2, exerciseId);

		resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			SentencePartResult result = new SentencePartResult();
			SentencePartExercise exercise = new SentencePartExercise();

			ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(resultSet.getString("content"),
					resultSet.getInt("width"), resultSet.getInt("height"));
			exercise = new SentencePartExercise(resultSet.getInt("id"), resultSet.getInt("difficulty"),
					StringEscapeUtils.unescapeHtml(resultSet.getString("description")),
					resultSet.getInt("information_id"), sentences);

			ArrayList<Sentence> sentences_result = SentencePartDataManager.contentToSentences(
					resultSet.getString("result_content"), resultSet.getInt("width"), resultSet.getInt("height"));

			Student student = this.getStudent(resultSet.getString("student_id"));
			result.setSentences(sentences_result);
			result.setExercise(exercise);
			result.setStudent(student);

			results.add(result);
		}
		stmt.close();
		resultSet.close();
		return results;
	}

	/**
	 * get all Results By Exercise ID
	 * 
	 * @param exerciseId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<SentencePartResult> getAllResultsByExerciseId(int exerciseId)
			throws ClassNotFoundException, SQLException {
		ArrayList<SentencePartResult> results = new ArrayList<SentencePartResult>();
		ResultSet resultSet;

		String sql = "SELECT * FROM result,exercise WHERE result.exercise_id = ? AND exercise.id = ? AND exercise.type=2";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exerciseId);
		stmt.setInt(2, exerciseId);

		resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			SentencePartResult result = new SentencePartResult();
			SentencePartExercise exercise = new SentencePartExercise();

			ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(resultSet.getString("content"),
					resultSet.getInt("width"), resultSet.getInt("height"));
			exercise = new SentencePartExercise(resultSet.getInt("id"), resultSet.getInt("difficulty"),
					StringEscapeUtils.unescapeHtml(resultSet.getString("description")),
					resultSet.getInt("information_id"), sentences);

			ArrayList<Sentence> sentences_result = SentencePartDataManager.contentToSentences(
					resultSet.getString("result_content"), resultSet.getInt("width"), resultSet.getInt("height"));

			Student student = this.getStudent(resultSet.getString("student_id"));
			result.setSentences(sentences_result);
			result.setExercise(exercise);
			result.setStudent(student);

			results.add(result);
		}
		stmt.close();
		resultSet.close();
		return results;
	}

}
