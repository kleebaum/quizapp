package de.uhd.ifi.se.quizapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uhd.ifi.se.quizapp.model.Student;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageDataManager;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageResult;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartResult;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceDataManager;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceResult;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SentencePartDataManager sentencePartDataManager;
	TwoChoiceDataManager twoChoiceDataManager;
	LabelImageDataManager lableImageDataManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherServlet() {
		super();
		sentencePartDataManager = new SentencePartDataManager();
		twoChoiceDataManager = new TwoChoiceDataManager();
		lableImageDataManager = new LabelImageDataManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = null;

		if (request.getParameter("showStudentsDetails") != null) {
			request = this.handleStudentDetails(request);
			dispatcher = request.getRequestDispatcher("/teacher/index.jsp?p=showResultsForStudent");
		} else if (request.getParameter("showStudentsForExercise") != null) {
			try {
				request = this.handleShowStudentsForExercise(request);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispatcher = request.getRequestDispatcher("/teacher/index.jsp?p=showStudentForExercise");
		}
		/*
		 * else if (request.getParameter("filterExercise") != null) { request =
		 * this.handleFilterExercises(request); dispatcher =
		 * request.getRequestDispatcher("/teacher/index.jsp?p=showExercises"); }
		 */

		// No request
		else {
			out.println("No request.");
		}

		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}

	/**
	 * Handle Teacher Requests
	 * @param request
	 * @return
	 */
	public HttpServletRequest handleStudentDetails(HttpServletRequest request) {
		String username = request.getParameter("username");
		Student student = new Student();
		student.setUsername(username);
		ArrayList<TwoChoiceResult> twoChoiceResults = null;
		ArrayList<SentencePartResult> sentencePartResults = null;
		ArrayList<LabelImageResult> labelImageResults = null;
		try {
			twoChoiceResults = new TwoChoiceDataManager().getResultByStudent(student);
			sentencePartResults = new SentencePartDataManager().getResultByStudent(student);
			labelImageResults = new LabelImageDataManager().getResultByStudent(student);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String message = "Details f&uuml;r " + username;
		request.setAttribute("message", message);
		request.setAttribute("twoChoiceResults", twoChoiceResults);
		request.setAttribute("sentencePartResults", sentencePartResults);
		request.setAttribute("labelImageResults", labelImageResults);
		return request;
	}

	/*
	 * Handle Exercise Filter for the teachers exercise overview
	 * 
	 *
	 * public HttpServletRequest handleFilterExercises(HttpServletRequest request) {
	 * Enumeration<String> parameters = request.getParameterNames(); Map<String,
	 * String> filterArguments = new HashMap<String, String>();
	 * 
	 * while (parameters.hasMoreElements()) { String paramName =
	 * parameters.nextElement(); String paramValue =
	 * request.getParameterValues(paramName)[0]; filterArguments.put(paramName,
	 * paramValue); }
	 * 
	 * ArrayList<Exercise> exercises = null; try { exercises =
	 * datamanager.getFilteredExercises(filterArguments); } catch
	 * (ClassNotFoundException e) { e.printStackTrace(); } catch (SQLException e) {
	 * e.printStackTrace(); }
	 * 
	 * request.setAttribute("exercises", exercises); return request; }
	 */
	/**
	 * 
	 * @param request
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public HttpServletRequest handleShowStudentsForExercise(HttpServletRequest request)
			throws ClassNotFoundException, SQLException {
		String exerciseId = null;
		if (request.getParameter("exerciseId") != null && request.getParameter("type") != null) {
			exerciseId = request.getParameter("exerciseId");
			String type = request.getParameter("type");

			if (type.equals("1")) {
				ArrayList<TwoChoiceResult> twoChoiceResults = twoChoiceDataManager.getAllResultsByExercise(exerciseId);
				request.setAttribute("twoChoiceResults", twoChoiceResults);

			} else if (type.equals("2")) {
				ArrayList<SentencePartResult> sentencePartResults = sentencePartDataManager
						.getAllResultsByExercise(exerciseId);
				request.setAttribute("sentencePartResults", sentencePartResults);
			} else if (type.equals("3")) {
				ArrayList<LabelImageResult> labelImageResult = lableImageDataManager
						.getAllResultsByExercise(exerciseId);
				request.setAttribute("labelImageResults", labelImageResult);
			}

		}
		return request;
	}
}
