package de.uhd.ifi.se.quizapp.model.init;

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
						+ "\r\n<p>&nbsp;</p>\r\n<p>"
						+ "<img src='https://s14-eu5.ixquick.com/cgi-bin/serveimage?url=https:%2F%2Fs-media-cache-ak0.pinimg.com%2F736x%2Fec%2F52%2F5e%2Fec525ebb8219bd06c7785e71dbd17292.jpg&amp;sp=163cf6b64309a95eacb8a34be66076cf'/></p>");
		dataManager.insertInformation(sampleInformation);
		SentencePartExercise sampleExercise = new SentencePartExercise();
		sampleExercise.setDescription("Verbinde die Satzteile");

		ArrayList<Sentence> sentences = SentencePartDataManager.contentToSentences(
				"Obst und Gem&uumlse|sind gesund.|Du kannst Obst und Gem&uumlse|essen.|Es gibt viele|Sorten.", 2, 3);

		sampleExercise.setSentences(sentences);
		sampleExercise.setInformationId(1);
		sampleExercise.setDifficulty(1);

		sentencePartDataManager.insertExercise(sampleExercise);

		Administrator administrator = new Administrator("admin", "admin", "admin", "A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3");
		dataManager.insertAdministrator(administrator);
	}
}