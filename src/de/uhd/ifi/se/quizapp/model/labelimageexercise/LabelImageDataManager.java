package de.uhd.ifi.se.quizapp.model.labelimageexercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Student;

public class LabelImageDataManager extends DataManager {

	public LabelImageDataManager() {
		super();
	}

	/**
	 * Parses the content of the exercise.
	 * 
	 * @param content
	 * @return
	 */
	public static ArrayList<ImageLabel> contentToString(String content) {
		if (content != null) {
			if (content.contains("null")) {
				return new ArrayList<ImageLabel>();
			}
			content = content.replace("&quot;", "\"");
		}
		if (content == null) {
			return new ArrayList<ImageLabel>();
		}
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(content);
		JsonArray jsonArray = element.getAsJsonArray();
		JsonObject jsonobject = jsonArray.get(0).getAsJsonObject();

		String imageSource = jsonobject.get("image").toString();
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		jsonArray.remove(0);
		for (JsonElement jsonElement : jsonArray) {
			JsonObject jsonObject = jsonElement.getAsJsonObject();

			// Cutting the first and last Item of the String so the " are no part of the
			// Array
			String labelString = jsonObject.get("label").toString();
			labelString = (String) labelString.subSequence(1, labelString.length() - 1);

			ImageLabel label = new ImageLabel(labelString, jsonObject.get("position").toString(), imageSource);
			labels.add(label);
		}

		return labels;
	}

	/**
	 * 
	 * @param labels
	 * @return
	 */
	public static String labelsToString(ArrayList<ImageLabel> labels) {
		StringBuffer contentBuffer = new StringBuffer();
		contentBuffer.append("[");
		if (labels.size() == 1) {
			// Image
			contentBuffer.append("{");
			contentBuffer.append('\"' + "image" + '\"' + ':');
			contentBuffer.append('\"' + labels.get(0).getImageSrc() + '\"');
			contentBuffer.append("},");
			// Position
			contentBuffer.append("{");
			contentBuffer.append('\"' + "position" + '\"' + ":");
			contentBuffer.append('\"' + labels.get(0).getPosition() + '\"' + ",");
			// Label
			contentBuffer.append('\"' + "label" + '\"' + ':');
			contentBuffer.append('\"' + labels.get(0).getLabel() + '\"');
			contentBuffer.append("}");
		} else {
			for (int i = 0; labels.size() > i; i++) {
				ImageLabel label = labels.get(i);
				// Last Element
				if (labels.size() - 1 == i) {
					contentBuffer.append("{");
					contentBuffer.append('\"' + "image" + '\"' + ':');
					contentBuffer.append('\"' + label.getImageSrc() + '\"');
					contentBuffer.append("},");
					// Position
					contentBuffer.append("{");
					contentBuffer.append('\"' + "position" + '\"' + ":");
					contentBuffer.append('\"' + label.getPosition() + '\"' + ",");
					// Label
					contentBuffer.append('\"' + "label" + '\"' + ':');
					contentBuffer.append('\"' + label.getLabel() + '\"');
					contentBuffer.append("}");

				} else {
					contentBuffer.append("{");
					contentBuffer.append('\"' + "image" + '\"' + ':');
					contentBuffer.append('\"' + label.getImageSrc() + '\"');
					contentBuffer.append("},");
					// Position
					contentBuffer.append("{");
					contentBuffer.append('\"' + "position" + '\"' + ":");
					contentBuffer.append('\"' + label.getPosition() + '\"' + ",");
					// Label
					contentBuffer.append('\"' + "label" + '\"' + ':');
					contentBuffer.append('\"' + label.getLabel() + '\"');
					contentBuffer.append("},");
				}
			}
		}
		contentBuffer.append("]");
		return contentBuffer.toString();
	}

	/**
	 * Retrieves all two choice exercises from database.
	 *
	 * @return All Label Image Exercises from the Database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<LabelImageExercise> getExercises() throws ClassNotFoundException, SQLException {
		List<LabelImageExercise> exercises = new ArrayList<LabelImageExercise>();
		ResultSet resultSet;

		String sql = "SELECT * FROM exercise WHERE type=3";
		Statement stmt = this.getConnection().createStatement();

		resultSet = stmt.executeQuery(sql);

		while (resultSet.next()) {
			ArrayList<ImageLabel> labels = contentToString(resultSet.getString(3));
			LabelImageExercise exercise = new LabelImageExercise(resultSet.getInt(1), resultSet.getInt(2),
					StringEscapeUtils.unescapeHtml(resultSet.getString(4)), resultSet.getInt(7), labels);
			exercises.add(exercise);
		}

		stmt.close();
		return exercises;
	}

	/**
	 * Retrieves a sentence-part-exercise from database by id.
	 * 
	 * @param id
	 * @return The LabelImageExercise with the given id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public LabelImageExercise getExercise(int id) throws ClassNotFoundException, SQLException {
		if (id <= 0) {
			System.out.println("The id is not in the Database");
			return null;
		}
		ResultSet resultSet = null;
		LabelImageExercise exercise = null;

		String sql = "SELECT * FROM exercise WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, id);
		resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			if (resultSet.getInt(1) == id) {
				ArrayList<ImageLabel> labels = contentToString(resultSet.getString(3));
				exercise = new LabelImageExercise(resultSet.getInt(1), resultSet.getInt(2),
						StringEscapeUtils.unescapeHtml(resultSet.getString(4)), resultSet.getInt(7), labels);
			}
		}

		stmt.close();
		resultSet.close();

		return exercise;
	}

	/**
	 * Get LabelImageExercise by difficulty
	 * 
	 * @param difficulty
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<LabelImageExercise> getExercises(int difficulty) throws ClassNotFoundException, SQLException {
		if (difficulty < 1 || difficulty > 3) {
			System.out.println("Difficulty is: " + difficulty + "but has to be 0<difficulty<4");
			return null;
		}

		List<LabelImageExercise> exercises = new ArrayList<LabelImageExercise>();

		String sql = "SELECT * FROM exercise WHERE type=3 AND difficulty=?";

		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, difficulty);

		ResultSet resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			ArrayList<ImageLabel> labels = contentToString(resultSet.getString(3));
			LabelImageExercise exercise = new LabelImageExercise(resultSet.getInt(1), resultSet.getInt(2),
					StringEscapeUtils.unescapeHtml(resultSet.getString(4)), resultSet.getInt(7), labels);
			exercises.add(exercise);
		}

		stmt.close();
		resultSet.close();

		return exercises;
	}

	/**
	 * Gets all Results for Label Image Exercises a student has done
	 * 
	 * @param student
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<LabelImageResult> getResultByStudent(Student student) throws ClassNotFoundException, SQLException {
		ArrayList<LabelImageResult> results = new ArrayList<LabelImageResult>();
		ResultSet resultSet;

		if (student == null) {
			System.out.println("Student was Null -> Could not be found in Database");
			return results;
		}

		String sql = "SELECT * FROM result,exercise WHERE result.student_id = ? AND result.exercise_id = exercise.id AND exercise.type=3";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, StringEscapeUtils.unescapeHtml(student.getUsername()));

		resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			LabelImageResult result = new LabelImageResult();
			LabelImageExercise exercise = new LabelImageExercise();
			ArrayList<ImageLabel> labels = contentToString(resultSet.getString("content"));
			exercise = new LabelImageExercise(resultSet.getInt("id"), resultSet.getInt("difficulty"),
					StringEscapeUtils.unescapeHtml(resultSet.getString("description")),
					resultSet.getInt("information_id"), labels);
			ArrayList<ImageLabel> labels_result = contentToString(resultSet.getString("result_content"));

			result.setLabels(labels_result);
			result.setExercise(exercise);
			result.setStudent(student);

			results.add(result);
		}

		stmt.close();
		resultSet.close();
		return results;
	}

	/**
	 * Gets all Results for one Exercise by his Id
	 * 
	 * @param exerciseId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<LabelImageResult> getAllResultsByExercise(String exerciseId)
			throws SQLException, ClassNotFoundException {

		if (exerciseId == null) {
			System.out.println("Exercise Id was null");
			return null;
		}
		ArrayList<LabelImageResult> results = new ArrayList<LabelImageResult>();
		ResultSet resultSet;

		String sql = "SELECT * FROM result,exercise WHERE result.exercise_id = ? AND exercise.id = ? AND exercise.type=3";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, exerciseId);
		stmt.setString(2, exerciseId);

		resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			LabelImageResult result = new LabelImageResult();
			LabelImageExercise exercise = new LabelImageExercise();

			ArrayList<ImageLabel> labels = contentToString(resultSet.getString("content"));
			exercise = new LabelImageExercise(resultSet.getInt("id"), resultSet.getInt("difficulty"),
					StringEscapeUtils.unescapeHtml(resultSet.getString("description")),
					resultSet.getInt("information_id"), labels);

			ArrayList<ImageLabel> labels_result = contentToString(resultSet.getString("result_content"));

			Student student = this.getStudent(resultSet.getString("student_id"));

			result.setLabels(labels_result);
			result.setExercise(exercise);
			result.setStudent(student);

			results.add(result);
		}

		stmt.close();
		resultSet.close();
		return results;

	}

	/**
	 * get LabelImageResult by Exercise ID
	 * 
	 * @param exerciseId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<LabelImageResult> getAllResultsByExerciseId(int exerciseId)
			throws SQLException, ClassNotFoundException {
		ArrayList<LabelImageResult> results = new ArrayList<LabelImageResult>();
		ResultSet resultSet;

		String sql = "SELECT * FROM result,exercise WHERE result.exercise_id = ? AND exercise.id = ? AND exercise.type=3";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exerciseId);
		stmt.setInt(2, exerciseId);

		resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			LabelImageResult result = new LabelImageResult();
			LabelImageExercise exercise = new LabelImageExercise();

			ArrayList<ImageLabel> labels = contentToString(resultSet.getString("content"));
			exercise = new LabelImageExercise(resultSet.getInt("id"), resultSet.getInt("difficulty"),
					StringEscapeUtils.unescapeHtml(resultSet.getString("description")),
					resultSet.getInt("information_id"), labels);

			ArrayList<ImageLabel> labels_result = contentToString(resultSet.getString("result_content"));

			Student student = this.getStudent(resultSet.getString("student_id"));

			result.setLabels(labels_result);
			result.setExercise(exercise);
			result.setStudent(student);

			results.add(result);
		}

		stmt.close();
		resultSet.close();
		return results;

	}

	/**
	 * insert a LabelImageExercise
	 * 
	 * @param exercise
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insertExercise(LabelImageExercise exercise) throws ClassNotFoundException, SQLException {
		String content = exercise.getLabelData();

		String sql = "INSERT INTO exercise (difficulty, content, description, width, height, information_id, type) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exercise.getDifficulty());
		stmt.setString(2, content);
		stmt.setString(3, exercise.getDescription());
		stmt.setLong(4, 0);
		stmt.setLong(5, 0);
		stmt.setInt(6, exercise.getInformationId());
		stmt.setInt(7, 3);

		int status = stmt.executeUpdate();
		stmt.close();
		// status can not be 0 Because of the right use of the preparedStatment
		if (status == 1) {
			System.out.println("Insertion of label-exercise was successful.");
		} else {
			System.out.println("Insertion of label-exercise failed.");
		}

	}

	/**
	 * Inserts a result of a student
	 * 
	 * @param result
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insertResult(LabelImageResult result) throws ClassNotFoundException, SQLException {
		ArrayList<ImageLabel> labels = result.getLabels();
		String content = labelsToString(labels);

		String sql = "INSERT INTO result (exercise_id, result_content, student_id) VALUES (?,?,?)";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, result.getExercise().getExerciseId());
		stmt.setString(2, StringEscapeUtils.escapeHtml(content));
		stmt.setString(3, StringEscapeUtils.escapeHtml(result.getStudent().getUsername()));

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Insertion of LabelImageResult was successful.");
		} else {
			System.out.println("Insertion of LabelImageResult failed.");
		}
	}

	/**
	 * update LabelImageExercise
	 * 
	 * @param exercise
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void updateExercise(LabelImageExercise exercise) throws SQLException, ClassNotFoundException {
		if (exercise == null) {
			System.out.println("Update of label-exercise failed.");
			return;
		}
		String content = exercise.getLabelData();

		String sql = "UPDATE exercise SET difficulty = ?, content = ?, description = ?,"
				+ "width = ?, height = ?, information_id = ? WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exercise.getDifficulty());
		stmt.setString(2, StringEscapeUtils.escapeHtml(Jsoup.parse(content).text()));
		stmt.setString(3, exercise.getDescription());
		stmt.setLong(4, 0);
		stmt.setLong(5, 0);
		stmt.setInt(6, exercise.getInformationId());
		stmt.setInt(7, exercise.getExerciseId());

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Update of label-exercise was successful.");
		} else {
			System.out.println("Update of label-exercise failed.");
		}
	}

}
