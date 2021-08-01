package de.uhd.ifi.se.quizapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import de.uhd.ifi.se.quizapp.model.Administrator;
import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Student;

/**
 * Fills the pages for students with data and handles the registration of new
 * students.
 */
@WebServlet("/Student")
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TwoChoiceExerciseHandler exerciseHandler;
	private DataManager dataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet() {
		super();
		exerciseHandler = new TwoChoiceExerciseHandler(ExerciseHandler.TWOCHOICE);
		SentencePartExerciseHandler sentencePartExerciseHandler = new SentencePartExerciseHandler(
				ExerciseHandler.SENTENCEPART);
		LabelImageExerciseHandler labelImageExerciseHandler = new LabelImageExerciseHandler(ExerciseHandler.LABEL);

		exerciseHandler.setSuccessor(sentencePartExerciseHandler);
		sentencePartExerciseHandler.setSuccessor(labelImageExerciseHandler);

		dataManager = new DataManager();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		// request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;

		if (request.getParameter("filterExercises") != null) {
			request = exerciseHandler.handleFiltering(request);
			dispatcher = request.getRequestDispatcher("/student/index.jsp?p=filterExercises");
		} else if (request.getParameter("solveExercise") != null) {
			request = exerciseHandler.handleSolving(request);
			dispatcher = request.getRequestDispatcher("/student/index.jsp?p=solveExercise");
		} else if (request.getParameter("checkResult") != null) {
			request = exerciseHandler.handleChecking(request);
			dispatcher = request.getRequestDispatcher("/student/index.jsp?p=showResult");
		} else if (request.getParameter("register") != null) {
			request = this.handleRegistration(request);
			dispatcher = request.getRequestDispatcher("/student/index.jsp?p=register_success");
		} else if (request.getParameter("login") != null) {
			HttpSession session = request.getSession();
			request = this.handleLogin(request, session);
			dispatcher = request.getRequestDispatcher("/student/index.jsp?p=filterExercises");
		} else if (request.getParameter("logout") != null) {
			HttpSession session = request.getSession();
			try {
				Student student = (Student) session.getAttribute("student");
				student.setValid(false);
				request.setAttribute("message", "Logout erfolgreich.");
				student = null;
				session.setAttribute("student", student);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

			try {
				Administrator administrator = (Administrator) session.getAttribute("administrator");
				administrator.setValid(false);
				request.setAttribute("message", "Logout erfolgreich.");
				administrator = null;
				session.setAttribute("administrator", administrator);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

			dispatcher = request.getRequestDispatcher("/student/index.jsp");
		}

		// No request
		else

		{
			out.println("No request.");
		}

		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	public HttpServletRequest handleRegistration(HttpServletRequest request) {
		Student student = new Student();
		student.setUsername(request.getParameter("username"));
		student.setFirstname(request.getParameter("firstname"));
		student.setLastname(request.getParameter("lastname"));
		String passwd2 = request.getParameter("password2");

		String message = "";

		if (request.getParameter("password").equals(passwd2)) {
			try {
				student.hashPassword(request.getParameter("password"));
				this.dataManager.insertStudent(student);

				message = "Hallo " + request.getParameter("firstname") + " " + request.getParameter("lastname")
						+ ". Ihr Benutzername lautet <strong>" + request.getParameter("username") + "</strong>";

				System.out.println("Registrierung erfolgreich");
			} catch (NoSuchAlgorithmException | ClassNotFoundException | SQLException e) {
				System.err.println("Fehler!");
				message = "Registrierung nicht erfolgreich";
				e.printStackTrace();
			}
		} else {
			message = "Passw&ouml;rter stimmen nicht &uuml;berein";
		}
		request.setAttribute("message", message);

		return request;
	}

	public HttpServletRequest handleLogin(HttpServletRequest request, HttpSession session) {

		String message = null;

		if (request.getParameter("username") == null || request.getParameter("password") == null) {
			message = "Login war nicht erfolgreich";
			request.setAttribute("message", message);
			return request;
		}

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			byte[] digest = messageDigest.digest();
			password = DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}

		Student student = null;
		message = "Login war nicht erfolgreich";
		try {
			student = this.dataManager.getStudent(username);
			if (student != null && password.equals(student.getPasswordHash())) {
				student.setValid(true);
				session.setAttribute("student", student);
				message = "Erfolgreich als Student angemeldet";
			} else {
				Administrator administrator = this.dataManager.getAdministrator(username);
				if (password.equals(administrator.getPasswordHash())) {
					administrator.setValid(true);
					session.setAttribute("administrator", administrator);
					message = "Erfolgreich als Administrator angemeldet";
				}

			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("message", message);
		return request;
	}

}
