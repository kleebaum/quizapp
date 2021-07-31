package de.uhd.ifi.se.quizapp.tests.sentencePartDataManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise;

public class TestGetExerciseSuper {

	protected SentencePartDataManager dataManager;
	protected List<SentencePartExercise> exerciseList;

	/*
	 * @After public void cleanUp() throws ClassNotFoundException, SQLException {
	 * System.out.println("After GetExercise");
	 * 
	 * this.removeDataBase(); this.createDataBase(); }
	 */
	protected void removeExerciseData() throws SQLException {
		SentencePartDataManager newDataManager = new SentencePartDataManager();
		this.dataManager = newDataManager;

		String sql = "delete from exercise";
		PreparedStatement stmt = this.dataManager.initConnection().prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	/**
	 * At the moment the Database has to be consistend protected void
	 * removeDataBase() {
	 * 
	 * Path path = Paths.get(new File("").getAbsolutePath(), "WebContent", "db",
	 * "heieducation.sqlite");
	 * 
	 * try { Files.delete(path); } catch (NoSuchFileException x) {
	 * System.err.format("%s: no such" + " file or directory%n", path); } catch
	 * (DirectoryNotEmptyException x) { System.err.format("%s not empty%n", path); }
	 * catch (IOException x) { // File permission problems are caught here.
	 * System.err.println(x); }
	 * 
	 * }
	 * 
	 * protected void createDataBase() throws ClassNotFoundException, SQLException {
	 * InitDatabase.main(null); }
	 **/
}
