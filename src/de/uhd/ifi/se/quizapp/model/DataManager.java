package de.uhd.ifi.se.quizapp.model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;

import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartResult;

/**
 * @Decision Datenbank Verbindungsdaten sind hardcodiert, da sie hier nicht
 *           verändert werden
 * 
 * @Alternative Verbindungsdaten können auch in der web.xml gespeichert werden
 * 
 * @Problems Verbindungsdaten Ãndern sich. Man müsste jedesmal den Code Ãndern.
 *           Wo soll die DB Verbindung initialisiert werden? D
 * 
 * @Solutions Verbindungsdaten tatsächlich in web.xml auslagern
 *
 * 
 */
public class DataManager {

	private String dbName = "org.sqlite.JDBC";
	private Path dbPath = Paths.get(new File("").getAbsolutePath(), "WebContent", "db", "heieducation.sqlite");
	private String dbURL = "jdbc:sqlite:" + dbPath.toString();
	private String dbUserName = "";
	private String dbPassword = "";

	private Connection conn = null;

	public DataManager() {
		this.conn = initConnection();
	}

	public String getDbName() {
		return this.dbName;
	}

	/*
	 * public void setDbName(String dbName) { this.dbName = dbName; }
	 */

	public String getDbURL() {
		return this.dbURL;
	}

	/*
	 * public void setDbURL(String dbURL) { this.dbURL = dbURL; }
	 */

	public String getDbUserName() {
		return this.dbUserName;
	}

	/*
	 * public void setDbUserName(String dbUserName) { this.dbUserName = dbUserName;
	 * }
	 */

	public String getDbPassword() {
		return this.dbPassword;
	}

	/*
	 * public void setDbPassword(String dbPassword) { this.dbPassword = dbPassword;
	 * }
	 */

	public Connection initConnection() {
		try {
			Class.forName(this.getDbName());
			this.conn = DriverManager.getConnection(this.getDbURL());
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Could not connect to DB: " + e.getMessage());
		}
		return this.conn;
	}

	public Connection getConnection() throws ClassNotFoundException {
		return this.conn;
	}

	/**
	 * 
	 * @param conn
	 */
	public void putConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * @Decision Verwende Prepared Statements, um SQL Injection zu verhindern.
	 * 
	 * @Alternative Verwende einen normalen String
	 * 
	 * @Problem Sicherheit vernachlÃ¤ssigt
	 */

	/**
	 * Inserts an object of class Information into database.
	 * 
	 * @param information
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean insertInformation(Information information) throws SQLException, ClassNotFoundException {
		if (information == null) {
			return false;
		}
		String sql = "INSERT INTO information (name, text) VALUES (?, ?)";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, StringEscapeUtils.escapeHtml(Jsoup.parse(information.getName()).text()));
		stmt.setString(2, StringEscapeUtils.escapeHtml(information.getText()));

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			return true;
		} else {
			System.err.println("Insertion of information to database failed.");
			return false;
		}
	}

	/**
	 * Retrieves all information from database.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Information> getInformation() throws ClassNotFoundException, SQLException {
		ArrayList<Information> informationList = new ArrayList<Information>();
		ResultSet resultSet;

		String sql = "SELECT * FROM information";
		Statement stmt = this.getConnection().createStatement();

		resultSet = stmt.executeQuery(sql);

		while (resultSet.next()) {
			informationList.add(
					new Information(resultSet.getInt("id"), StringEscapeUtils.unescapeHtml(resultSet.getString("name")),
							StringEscapeUtils.unescapeHtml(resultSet.getString("text"))));
		}
		stmt.close();
		return informationList;
	}

	/**
	 * Retrieves a single information from database by id.
	 * 
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Information getInformation(int id) throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;

		String sql = "SELECT * FROM information WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, id);
		resultSet = stmt.executeQuery();

		Information information = new Information(resultSet.getInt("id"),
				StringEscapeUtils.unescapeHtml(resultSet.getString("name")),
				StringEscapeUtils.unescapeHtml(resultSet.getString("text")));

		stmt.close();
		resultSet.close();

		return information;
	}

	/**
	 * Update existing information in database.
	 * 
	 * @param information
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean updateInformation(Information information) throws SQLException, ClassNotFoundException {
		int id = information.getInformationId();
		String name = StringEscapeUtils.escapeHtml(information.getName());
		String text = StringEscapeUtils.escapeHtml(information.getText());

		String sql = "UPDATE information SET text = ?, name = ? WHERE id = ?";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		stmt.setString(1, text);
		stmt.setString(2, name);
		stmt.setInt(3, id);

		int status = stmt.executeUpdate();
		stmt.close();

		if (status == 1) {
			System.out.println("Update of information was successful.");
			return true;
		} else {
			System.err.println("Update of information to database failed.");
			return false;
		}
	}

	/**
	 * Deletes an information in database by id.
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean deleteInformation(int id) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM information WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		stmt.close();

		sql = "DELETE FROM exercise WHERE information_id = ?";
		stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, id);
		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Deletion of information was successful.");
			return true;
		} else {
			System.err.println("Deletion of information failed.");
			return false;
		}

	}

	/**
	 * Deletes an information in database.
	 * 
	 * @param information
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean deleteInformation(Information information) throws SQLException, ClassNotFoundException {
		return this.deleteInformation(information.getInformationId());
	}

	/**
	 * Deletes an exercise by id in database.
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean deleteExercise(int id) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM exercise WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, id);
		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Deletion of exercise was successful.");
			return true;
		} else {
			System.out.println("Deletion of exercise was not successful.");
			return false;
		}
	}

	/**
	 * Inserts an object of class Student into database.
	 * 
	 * @param student
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean insertStudent(Student student) throws SQLException, ClassNotFoundException {
		if (student.getUsername() == null || student.getFirstname() == null || student.getLastname() == null
				|| student.getPasswordHash() == null)
			return false;

		String sql = "INSERT INTO user (username, firstname, lastname, password, role) VALUES (?, ?, ?, ?,?)";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, StringEscapeUtils.escapeHtml(Jsoup.parse(student.getUsername()).text()));
		stmt.setString(2, StringEscapeUtils.escapeHtml(Jsoup.parse(student.getFirstname()).text()));
		stmt.setString(3, StringEscapeUtils.escapeHtml(Jsoup.parse(student.getLastname()).text()));
		stmt.setString(4, StringEscapeUtils.escapeHtml(Jsoup.parse(student.getPasswordHash()).text()));
		stmt.setString(5, "student");

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Insertion of student to database was successful.");
			return true;
		} else {
			System.err.println("Insertion of student to database failed.");
			return false;
		}
	}

	/**
	 * Inserts an object of class Administrator into database.
	 * 
	 * @param administrator
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean insertAdministrator(Administrator administrator) throws SQLException, ClassNotFoundException {
		if (administrator.getUsername() == null || administrator.getFirstname() == null
				|| administrator.getLastname() == null || administrator.getPasswordHash() == null)
			return false;

		String sql = "INSERT INTO user (username, firstname, lastname, password,role) VALUES (?, ?, ?, ?,?)";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, StringEscapeUtils.escapeHtml(Jsoup.parse(administrator.getUsername()).text()));
		stmt.setString(2, StringEscapeUtils.escapeHtml(Jsoup.parse(administrator.getFirstname()).text()));
		stmt.setString(3, StringEscapeUtils.escapeHtml(Jsoup.parse(administrator.getLastname()).text()));
		stmt.setString(4, StringEscapeUtils.escapeHtml(Jsoup.parse(administrator.getPasswordHash()).text()));
		stmt.setString(5, "administrator");

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Insertion of student to database was successful.");
			return true;
		} else {
			System.err.println("Insertion of student to database failed.");
			return false;
		}
	}

	/**
	 * Retrieves all students from database.
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Student> getStudents() throws SQLException, ClassNotFoundException {
		ArrayList<Student> students = new ArrayList<Student>();
		ResultSet resultSet;

		String sql = "SELECT * FROM user WHERE role = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, "student");

		resultSet = stmt.executeQuery();
		while (resultSet.next()) {
			Student student = new Student();
			student.setUsername(resultSet.getString(1));
			student.setFirstname(resultSet.getString(2));
			student.setLastname(resultSet.getString(3));
			student.setPassword(resultSet.getString(4));
			students.add(student);
		}
		stmt.close();

		return students;
	}

	/**
	 * Retrieves student from database by username
	 * 
	 * @param username
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Student getStudent(String username) throws SQLException, ClassNotFoundException {
		Student student = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM user WHERE username = ? AND role = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, "student");
		resultSet = stmt.executeQuery();

		if (resultSet.next()) {
			student = new Student(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4));
		}
		stmt.close();
		return student;
	}

	/**
	 * Retrieves administrator from database by username
	 * 
	 * @param username
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Administrator getAdministrator(String username) throws SQLException, ClassNotFoundException {
		Administrator administrator;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM user WHERE username = ? AND role = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, "administrator");
		resultSet = stmt.executeQuery();

		administrator = new Administrator(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
				resultSet.getString(4));
		stmt.close();
		return administrator;
	}

	/**
	 * Retrieves all results given by a student
	 * 
	 * @param exerciseId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Result> getResultsOfExercise(int exerciseId) throws ClassNotFoundException, SQLException {
		ArrayList<Result> results = new ArrayList<Result>();
		ResultSet resultSet;

		String sql = "SELECT * FROM result WHERE exercise_id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exerciseId);

		resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			Student student = this.getStudent(resultSet.getString(4));

			Result result = new SentencePartResult();
			result.setStudent(student);

			results.add(result);
		}
		stmt.close();
		resultSet.close();
		return results;
	}

	/**
	 * Retrieves all students who gave a result
	 * 
	 * @param exerciseId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Student> getStudentByResult(int exerciseId) throws SQLException, ClassNotFoundException {
		ArrayList<Student> students = new ArrayList<Student>();
		ResultSet resultSet;

		String sql = "SELECT * FROM result WHERE exercise_id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exerciseId);

		resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			Student student = this.getStudent(resultSet.getString(4));
			students.add(student);
		}
		stmt.close();
		resultSet.close();
		return students;
	}

	/*
	 * Creates a new database.
	 */
	public void createDatabase() {
		// load the sqlite-JDBC driver using the current class loader
		try {
			Class.forName(this.getDbName());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		try {
			// create a database connection
			Statement statement = this.getConnection().createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("CREATE TABLE exercise (id integer PRIMARY KEY AUTOINCREMENT,"
					+ "difficulty INTEGER NOT NULL, content TEXT, description VARCHAR(256),"
					+ "width INTEGER NOT NULL, height INTEGER NOT NULL,"
					+ "information_id INTEGER NOT NULL, type INTEGER NOT NULL);");
			statement.executeUpdate("CREATE TABLE information (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "text TEXT, name VARCHAR(256));");
			statement.executeUpdate("CREATE TABLE result (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "exercise_id INTEGER NOT NULL, result_content TEXT, student_id TEXT NOT NULL);");
			statement.executeUpdate("CREATE TABLE user (username VARCHAR(32) NOT NULL PRIMARY KEY," // @Decision
																									// A
																									// table
																									// for
																									// every
																									// user
																									// with
																									// a
																									// different
																									// role
					+ "firstname VARCHAR(20) NOT NULL, lastname VARCHAR(40) NOT NULL, " // @Alternative
																						// Many
																						// tables
																						// for
																						// every
																						// role
					+ "password CHAR(64) NOT NULL, role varchar(20) NOT NULL );"); // @Rationale
																					// Needed
																					// a
																					// password
																					// protected
																					// access
																					// for
																					// an
																					// administrator
			statement.close(); // @Argument only difference is the role
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Resets database, i.e. deletes database contents.
	 */
	public void resetDatabase() {
		// load the sqlite-JDBC driver using the current class loader
		try {
			Class.forName(this.getDbName());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		try {
			// create a database connection
			Statement statement = this.getConnection().createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("DELETE FROM exercise;");
			statement.executeUpdate("DELETE FROM information;");
			statement.executeUpdate("DELETE FROM result;");
			statement.executeUpdate("DELETE FROM student;");
			statement.close();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Adds sample database entries.
	 */
	public void fillDatabaseWithSampleData() {
		Information sampleInformation1 = new Information("Obst und Gem&uumlse",
				"<p>Obst und Gem&uuml;se sind gesund. Sie haben viele Vitamine. "
						+ "Du kannst Obst und Gem&uuml;se essen. Es gibt viele Sorten. "
						+ "Es gibt in jeder Jahreszeit verschiedene Sorten. "
						+ "Obst ist h&auml;ufig s&uuml;&szlig;. Obst hat viel Wasser. "
						+ "Gem&uuml;se ist h&auml;ufig nicht s&uuml;&szlig;.</p>\r\n"
						+ "<p>Du kannst Obst und Gem&uuml;se kaufen. Es gibt Obst und Gem&uuml;se auf dem Markt. "
						+ "Obst und Gem&uuml;se sind auf dem Markt frisch. Du isst Obst h&auml;ufig roh. "
						+ "Du kannst aus Obst einen Obstsalat machen.</p>\r\n<p>"
						+ "Du kannst Obst und Gem&uuml;se mit oder ohne Schale essen. "
						+ "Obst w&auml;chst h&auml;ufig an B&auml;umen, an Str&auml;uchern oder auf dem Boden. "
						+ "Du isst Gem&uuml;se h&auml;ufig gekocht. Du kannst eine Suppe kochen. "
						+ "Gem&uuml;se W&auml;chst h&auml;ufig am Boden oder an Str&auml;uchern.</p>"
						+ "\r\n<p>&nbsp;</p>\r\n<p>"
						+ "<img src='https://s14-eu5.ixquick.com/cgi-bin/serveimage?url=https:%2F%2Fs-media-cache-ak0.pinimg.com%2F736x%2Fec%2F52%2F5e%2Fec525ebb8219bd06c7785e71dbd17292.jpg&amp;sp=163cf6b64309a95eacb8a34be66076cf'/></p>");

		Information sampleInformation2 = new Information("Chloroplasten und Photosynthese",
				"Chloroplasten sind Organellen der Zellen von Gr&ouml;nalgen und h&ouml;heren Pflanzen, die Photosynthese betreiben. "
						+ "In einer photosynthetisch aktiven Zelle einer Gef&auml;&szlig;pflanze befinden sich in der Regel etwa 10 bis 50 Chloroplasten, "
						+ "die einen Durchmesser von etwa 4 bis 8 &micro;m haben. Viele Algen haben dagegen pro Zelle nur einen einzigen Chloroplasten, "
						+ "der einen gro&szlig;en Teil der Zelle einnimmt. Die Photosynthese ist der Prozess zur Erzeugung von energiereichen Biomolek&uuml;len "
						+ "aus energie&auml;rmeren Stoffen mithilfe von Lichtenergie. "
						+ "Sie wird von Pflanzen, Algen und einigen Bakterien betrieben. "
						+ "Bei diesem biochemischen Vorgang wird zun&auml;chst mithilfe von lichtabsorbierenden Farbstoffen, "
						+ "wie z. B. Chlorophyll und Bakteriochlorophyll, die Lichtenergie in chemische Energie umgewandelt. "
						+ "Diese wird dann unter anderem zum Aufbau energiereicher, organischer Verbindungen (meist Kohlenhydrate) "
						+ "aus energiearmen, anorganischen Stoffen (Kohlenstoffdioxid CO2 (Kohlenstoffdioxid-Assimilation) und meist Wasser H2O) "
						+ "verwendet. Da die energiereichen organischen Stoffe zu Bestandteilen des Lebewesens werden, bezeichnet man deren Synthese "
						+ "als Assimilation.");

		Student student = new Student("admin", "admin", "admin",
				"8F8145E0F8D63C646E48F5A0377007C2193FCE8C87399B5D9A59DEC43B4CB45B");

		try {
			this.insertInformation(sampleInformation1);
			this.insertInformation(sampleInformation2);
			this.insertStudent(student);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param username
	 * @param newpassword
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean changePassword(String username, String newpassword) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE user  SET password=? WHERE username=?";
		PreparedStatement stmt = getConnection().prepareStatement(sql);

		stmt.setString(1, newpassword);
		stmt.setString(2, username);

		int status = stmt.executeUpdate();
		stmt.close();

		if (status == 1) {
			System.out.println("Update of the password was successful");
			return true;
		} else {
			System.err.println("Update of the Password failed.");
			return false;
		}
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<User> listAllUser() throws SQLException, ClassNotFoundException {
		ArrayList<User> userList = new ArrayList<User>();

		ResultSet resultSet;

		String sql = "SELECT * FROM user";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);

		resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			User user = new User(resultSet.getString("username"), resultSet.getString("firstname"),
					resultSet.getString("lastname"), "", resultSet.getString("role"));
			userList.add(user);
		}
		stmt.close();
		resultSet.close();

		return userList;
	}

	/**
	 * delete a user //TODO ALSO DELETE ALL CONNECTED INFORMATION TO THAT USER
	 * 
	 * @param username
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean deleteUser(String username) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM user WHERE username = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, username);
		stmt.executeUpdate();
		stmt.close();

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Deletion of user was successful.");
			return true;
		} else {
			System.err.println("Deletion of user failed.");
			return false;
		}
	}

	/**
	 * 
	 * @param username
	 * @param role
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean changeRole(String username, String role) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE user SET role = ? WHERE username = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, role);
		stmt.setString(2, username);

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("User role changed successfully.");
			return true;
		} else {
			System.err.println("Changing User role failed.");
			return false;
		}
	}
}
