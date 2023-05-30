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
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;

/**
 * Responsible for CRUD operations, i.e. creates, reads, updates, and deletes
 * objects in/from database.
 * 
 * @issue Which database do we use?
 * @decision We use a SQLite database!
 * @pro Easier to set up than MySQL or PostgreSQL.
 * @alternative We could store data in text files, e.g. in JSON format.
 * 
 * @issue How to prevent SQL injections?
 * @decision Use prepared statements to prevent SQL injections!
 */
public class DataManager {

	private String dbName = "org.sqlite.JDBC";
	private Path dbPath = Paths.get(new File("").getAbsolutePath(), "WebContent", "db", "heieducation.sqlite");
	private String dbURL = "jdbc:sqlite:" + dbPath.toString();
	private String dbUserName = "";
	private String dbPassword = "";

	private Connection connection = null;

	public DataManager() {
		this.connection = initConnection();
	}

	public String getDbName() {
		return dbName;
	}

	public String getDbURL() {
		return dbURL;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public Connection initConnection() {
		try {
			connection = DriverManager.getConnection(getDbURL());
		} catch (SQLException e) {
			System.err.println("Could not connect to DB: " + e.getMessage());
		}
		return connection;
	}

	public Connection getConnection() {
		return connection;
	}

	/**
	 * Inserts an object of class {@link Information} into database.
	 */
	public boolean insertInformation(Information information) {
		if (information == null) {
			return false;
		}
		String sql = "INSERT INTO information (name, text) VALUES (?, ?)";
		PreparedStatement stmt;
		int status = 0;
		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setString(1, StringEscapeUtils.escapeHtml(Jsoup.parse(information.getName()).text()));
			stmt.setString(2, StringEscapeUtils.escapeHtml(information.getText()));

			status = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Insertion of information to database failed. " + e.getMessage());
		}

		return status == 1;
	}

	/**
	 * @return all {@link Information} objects from database.
	 */
	public List<Information> getInformation() {
		List<Information> informationList = new ArrayList<Information>();
		ResultSet resultSet;

		String sql = "SELECT * FROM information";
		Statement stmt;
		try {
			stmt = getConnection().createStatement();
			resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				informationList.add(new Information(resultSet.getInt("id"),
						StringEscapeUtils.unescapeHtml(resultSet.getString("name")),
						StringEscapeUtils.unescapeHtml(resultSet.getString("text"))));
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Get information from database failed. " + e.getMessage());
		}
		return informationList;
	}

	/**
	 * Retrieves a single {@link Information} object from database by its id.
	 */
	public Information getInformation(int id) {
		List<Information> informationList = getInformation();
		Optional<Information> informationWithId = informationList.stream()
				.filter(information -> information.getInformationId() == id).findAny();
		if (informationWithId.isPresent()) {
			return informationWithId.get();
		}
		return null;
	}

	/**
	 * Updates an existing {@link Information} object in database.
	 */
	public boolean updateInformation(Information information) {
		int id = information.getInformationId();
		String name = StringEscapeUtils.escapeHtml(information.getName());
		String text = StringEscapeUtils.escapeHtml(information.getText());

		String sql = "UPDATE information SET text = ?, name = ? WHERE id = ?";
		PreparedStatement stmt;
		int status = 0;
		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setString(1, text);
			stmt.setString(2, name);
			stmt.setInt(3, id);

			status = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Update of information to database failed. " + e.getMessage());
		}

		return status == 1;
	}

	/**
	 * Deletes an {@link Information} object in database by id.
	 */
	public boolean deleteInformation(int id) {
		String sql = "DELETE FROM information WHERE id = ?";
		PreparedStatement stmt;
		int status = 0;
		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			status = stmt.executeUpdate();
			stmt.close();

			sql = "DELETE FROM exercise WHERE information_id = ?";
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Deletion of information failed. " + e.getMessage());
		}

		return status == 1;
	}

	/**
	 * Deletes an information in database.
	 */
	public boolean deleteInformation(Information information) {
		return deleteInformation(information.getInformationId());
	}

	/**
	 * Deletes an {@link Exercise} by id in database.
	 */
	public boolean deleteExercise(int id) {
		String sql = "DELETE FROM exercise WHERE id = ?";
		PreparedStatement stmt;
		int status = 0;
		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			status = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Deletion of exercise was not successful. " + e.getMessage());
		}
		return status == 1;
	}

	/**
	 * Inserts a {@link Students} object into database.
	 */
	public boolean insertStudent(Student student) {
		if (student.getUsername() == null || student.getFirstname() == null || student.getLastname() == null
				|| student.getPasswordHash() == null)
			return false;

		String sql = "INSERT INTO user (username, firstname, lastname, password, role) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement stmt;
		int status = 0;
		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setString(1, StringEscapeUtils.escapeHtml(Jsoup.parse(student.getUsername()).text()));
			stmt.setString(2, StringEscapeUtils.escapeHtml(Jsoup.parse(student.getFirstname()).text()));
			stmt.setString(3, StringEscapeUtils.escapeHtml(Jsoup.parse(student.getLastname()).text()));
			stmt.setString(4, StringEscapeUtils.escapeHtml(Jsoup.parse(student.getPasswordHash()).text()));
			stmt.setString(5, "student");

			status = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Insertion of student to database failed. " + e.getMessage());
		}

		return status == 1;
	}

	/**
	 * Inserts an {@link Administrator} object into database.
	 */
	public boolean insertAdministrator(Administrator administrator) {
		if (administrator.getUsername() == null || administrator.getFirstname() == null
				|| administrator.getLastname() == null || administrator.getPasswordHash() == null)
			return false;

		String sql = "INSERT INTO user (username, firstname, lastname, password, role) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement stmt;
		int status = 0;
		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setString(1, StringEscapeUtils.escapeHtml(Jsoup.parse(administrator.getUsername()).text()));
			stmt.setString(2, StringEscapeUtils.escapeHtml(Jsoup.parse(administrator.getFirstname()).text()));
			stmt.setString(3, StringEscapeUtils.escapeHtml(Jsoup.parse(administrator.getLastname()).text()));
			stmt.setString(4, StringEscapeUtils.escapeHtml(Jsoup.parse(administrator.getPasswordHash()).text()));
			stmt.setString(5, "administrator");

			status = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Insertion of administrator to database failed. " + e.getMessage());
		}

		return status == 1;
	}

	/**
	 * @return all {@link Student}s from database.
	 */
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		ResultSet resultSet;

		String sql = "SELECT * FROM user WHERE role = ?";
		PreparedStatement stmt;
		try {
			stmt = getConnection().prepareStatement(sql);
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
		} catch (SQLException e) {
			System.err.println("Reading students from database failed. " + e.getMessage());
		}

		return students;
	}

	/**
	 * @return {@link Student} from database by username.
	 */
	public Student getStudent(String username) {
		Student student = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM user WHERE username = ? AND role = ?";
		PreparedStatement stmt;
		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, "student");
			resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				student = new Student(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4));
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Reading student from database failed. " + e.getMessage());
		}

		return student;
	}

	/**
	 * @return {@link Administrator} from database by username
	 */
	public Administrator getAdministrator(String username) {
		Administrator administrator = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM user WHERE username = ? AND role = ?";
		PreparedStatement stmt;
		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, "administrator");
			resultSet = stmt.executeQuery();

			administrator = new Administrator(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4));
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Reading admin from database failed. " + e.getMessage());
		}

		return administrator;
	}

	/**
	 * @return all {@link Student}s who performed a certain exercise.
	 */
	public List<Student> getStudentByResult(int exerciseId) {
		List<Student> students = new ArrayList<Student>();
		ResultSet resultSet;

		String sql = "SELECT * FROM result WHERE exercise_id = ?";
		PreparedStatement stmt;
		try {
			stmt = this.getConnection().prepareStatement(sql);
			stmt.setInt(1, exerciseId);

			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				Student student = getStudent(resultSet.getString(4));
				students.add(student);
			}
			stmt.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
		} catch (SQLException e) {
			System.err.println(e.getMessage());
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
		} catch (SQLException e) {
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

		this.insertInformation(sampleInformation1);
		this.insertInformation(sampleInformation2);
		this.insertStudent(student);
	}

	public boolean changePassword(String username, String newPassword) {
		String sql = "UPDATE user  SET password=? WHERE username=?";
		PreparedStatement stmt;
		int status = 0;
		try {
			stmt = getConnection().prepareStatement(sql);

			stmt.setString(1, newPassword);
			stmt.setString(2, username);

			status = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Update of the password failed. " + e.getMessage());
		}
		return status == 1;
	}

	/**
	 * Deletes a user //TODO ALSO DELETE ALL CONNECTED INFORMATION TO THAT USER
	 */
	public boolean deleteUser(String username) {
		String sql = "DELETE FROM user WHERE username = ?";
		PreparedStatement stmt;
		int status = 0;
		try {
			stmt = this.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			stmt.executeUpdate();
			stmt.close();

			status = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Deletion of user failed. " + e.getMessage());
		}
		return status == 1;
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
