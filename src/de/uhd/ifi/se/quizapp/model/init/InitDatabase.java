package de.uhd.ifi.se.quizapp.model.init;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.Administrator;
import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Information;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.Sentence;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise;

public class InitDatabase {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		SentencePartDataManager sentencePartDataManager = new SentencePartDataManager();
		dataManager.createDatabase();

		Information sampleInformation = new Information("Obst und Gem&uumlse",
				"<p>Obst und Gem&uuml;se sind gesund. Sie haben viele Vitamine. "
						+ "Du kannst Obst und Gem&uuml;se essen. Es gibt viele Sorten. "
						+ "Es gibt in jeder Jahreszeit verschiedene Sorten. "
						+ "Obst ist h&auml;ufig s&uuml;&szlig;. Obst hat viel Wasser. "
						+ "Gem&uuml;se ist h&auml;ufig nicht s&uuml;&szlig;.</p>\r\n"
						+ "<p>Du kannst Obst und Gem&uuml;se kaufen. " + "Es gibt Obst und Gem&uuml;se auf dem Markt. "
						+ "Obst und Gem&uuml;se sind auf dem Markt frisch. " + "Du isst Obst h&auml;ufig roh. "
						+ "Du kannst aus Obst einen Obstsalat machen.</p>\r\n<p>"
						+ "Du kannst Obst und Gem&uuml;se mit oder ohne Schale essen. "
						+ "Obst w&auml;chst h&auml;ufig an B&auml;umen, an Str&auml;uchern oder auf dem Boden. "
						+ "Du isst Gem&uuml;se h&auml;ufig gekocht. Du kannst eine Suppe kochen. "
						+ "Gem&uuml;se W&auml;chst h&auml;ufig am Boden oder an Str&auml;uchern.</p>"
						+ "<img src='https://upload.wikimedia.org/wikipedia/commons/3/3d/Fruit%2C_Vegetables_and_Grain_NCI_Visuals_Online.jpg'/></p>");
		dataManager.insertInformation(sampleInformation);
		SentencePartExercise sampleExercise = new SentencePartExercise();
		sampleExercise.setDescription("Verbinde die Satzteile");

		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(
				"Obst und Gem&uumlse|sind gesund.|Du kannst Obst und Gem&uumlse|essen.|Es gibt viele|Sorten.", 2, 3);
		sampleExercise.setSentences(sentences);
		sampleExercise.setInformationId(1);
		sampleExercise.setDifficulty(1);

		sentencePartDataManager.insertExercise(sampleExercise);

		Administrator administrator = new Administrator("admin", "admin", "admin", "admin");
		try {
			administrator.hashPassword("admin");
		} catch (NoSuchAlgorithmException e) {
		}
		dataManager.insertAdministrator(administrator);
	}
}