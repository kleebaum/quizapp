package de.uhd.ifi.se.quizapp.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import de.uhd.ifi.se.quizapp.model.Information;
import de.uhd.ifi.se.quizapp.model.Student;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceDataManager;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceMetric;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceResult;

public class TwoChoiceExerciseHandler extends ExerciseHandler {

	TwoChoiceDataManager dataManager;

	public TwoChoiceExerciseHandler() {
		this.type = 1;
		this.dataManager = new TwoChoiceDataManager();
	}

	public TwoChoiceExerciseHandler(int type) {
		this.type = type;
		this.dataManager = new TwoChoiceDataManager();
	}

	/**
	 * Parses the parameters of the request object to boolean statements
	 */
	protected static List<BooleanStatement> parametersToBooleanStatements(HttpServletRequest request) {
		List<BooleanStatement> booleanStatements = new ArrayList<BooleanStatement>();

		Enumeration<String> parameters = request.getParameterNames();

		int currentStatementNumber = 0;

		while (parameters.hasMoreElements()) {
			String parameterName = parameters.nextElement();
			String parameterValue = request.getParameterValues(parameterName)[0];
			String radioName = parameterName.replace("input", "radio");
			String radioValue = request.getParameterValues(radioName)[0];
			if (parameterName.contains("input")) {
				if (parameterValue.isEmpty())
					return null;
				booleanStatements.add(new BooleanStatement(parameterValue));
				if (radioValue.equals("true")) {
					booleanStatements.get(currentStatementNumber).setCorrect(true);
				} else if (radioValue.equals("false")) {
					booleanStatements.get(currentStatementNumber).setCorrect(false);
				} else {
					return null;
				}
				currentStatementNumber++;
			}
		}

		if (booleanStatements.size() > 0) {
			return booleanStatements;
		}
		return null;
	}

	@Override
	protected HttpServletRequest handleCreationInChain(HttpServletRequest request) {

		List<BooleanStatement> statements = parametersToBooleanStatements(request);

		int difficulty = Integer.parseInt(request.getParameter("difficulty"));
		String selectedInformation = request.getParameter("information");
		String description = request.getParameter("description");

		String message = "";

		/*
		 * Checks if inputs are correct
		 */
		if (statements == null) {
			message = "Es muss mindestens eine Aussage erstellt werden.";
		} else if (difficulty < 1 || difficulty > 3) {
			message = "Der Schwierigkeitsgrad muss zwischen 1 und 3 liegen.";
		} else if (description.equals("")) {
			message = "Es muss eine Beschreibung eingetragen werden.";
		} else if (!tryParse(selectedInformation)) {
			message = "Es muss eine g&uuml;ltige Information hinzugef&uuml;gt werden.";
		} else {
			TwoChoiceExercise exercise = new TwoChoiceExercise();
			exercise.setDifficulty(difficulty);
			exercise.setDescription(description);
			exercise.setInformationId(Integer.parseInt(selectedInformation));
			exercise.setBooleanStatements(statements);

			try {
				dataManager.insertExercise(exercise);
				message = "Die Richtig/Falsch Aufgabe wurde erfolgreich erstellt.";
			} catch (ClassNotFoundException | SQLException e) {
				message = "Es ist ein Fehler aufgetreten";
				e.printStackTrace();
			}
		}
		request.setAttribute("message", message);
		return request;
	}

	@Override
	protected HttpServletRequest handleEditingInChain(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			TwoChoiceExercise exercise = dataManager.getExercise(id);
			request.setAttribute("exercise", exercise);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return request;
	}

	@Override
	protected HttpServletRequest handleUpdatingInChain(HttpServletRequest request) {
		String message = "";
		int id = Integer.parseInt(request.getParameter("id"));
		int difficulty = Integer.parseInt(request.getParameter("difficulty"));
		String selectedInformation = request.getParameter("information");
		int informationId = Integer.parseInt(selectedInformation.split(" ")[0]);

		TwoChoiceExercise exercise = new TwoChoiceExercise();
		try {
			exercise = dataManager.getExercise(id);
		} catch (ClassNotFoundException | SQLException e) {
			message = "Die Aufgabe konnte nicht gefunden werden.";
			e.printStackTrace();
		}
		exercise.setDifficulty(difficulty);
		exercise.setDescription(request.getParameter("description"));
		exercise.setInformationId(informationId);
		exercise.setBooleanStatements(parametersToBooleanStatements(request));

		try {
			dataManager.updateExercise(exercise);
			message = "Die Richtig/Falsch Aufgabe wurde erfolgreich erstellt.";
		} catch (ClassNotFoundException | SQLException e) {
			message = "Es ist ein Fehler aufgetreten";
			e.printStackTrace();
		}

		request.setAttribute("message", message);
		return request;
	}

	@Override
	protected HttpServletRequest handleFilteringInChain(HttpServletRequest request) {
		int difficulty = Integer.parseInt(request.getParameter("difficulty"));

		try {
			List<TwoChoiceExercise> exercises = dataManager.getExercises(difficulty);
			request.setAttribute("exercises", exercises);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	@Override
	protected HttpServletRequest handleSolvingInChain(HttpServletRequest request) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));

			TwoChoiceExercise exercise = dataManager.getExercise(id);
			Information information = dataManager.getInformation(exercise.getInformationId());

			request.setAttribute("exercise", exercise);
			request.setAttribute("information", information);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return request;
	}

	@Override
	protected HttpServletRequest handleCheckingInChain(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		TwoChoiceExercise exercise = null;

		try {
			exercise = dataManager.getExercise(id);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		TwoChoiceResult result = new TwoChoiceResult();
		result.setExercise(exercise);
		Student student = (Student) request.getSession().getAttribute("student");
		System.out.println("Username " + student.getUsername());
		result.setStudent(student);
		List<BooleanStatement> statements = parametersToBooleanStatements(request);

		if (statements != null) {
			result.setBooleanStatements(statements);

			try {
				dataManager.insertResult(result);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("result", result);
			request.setAttribute("exercise", exercise);
		} else {
			String message = "W&auml;hle Richtig oder Falsch aus.";
			request.setAttribute("message", message);
		}

		return request;
	}

	/**
	 * This function returns a String in style of an Json File. It will be used to
	 * Show the Metriks in the Teacher View
	 * 
	 * @return String (Json)
	 */
	@Override
	protected String calculateExerciseMetricsInChain(int type) {
		ArrayList<TwoChoiceExercise> twoChoiseExercises;
		ArrayList<TwoChoiceMetric> twoChoiceMetric = new ArrayList<>();
		try {
			twoChoiseExercises = (ArrayList<TwoChoiceExercise>) dataManager.getExercises();
			ArrayList<TwoChoiceResult> results = new ArrayList<>();
			for (TwoChoiceExercise exercise : twoChoiseExercises) {
				removingHTMLTags(exercise);
				results = dataManager.getAllResultsByExerciseId(exercise.getExerciseId());

				TwoChoiceMetric metric = new TwoChoiceMetric();
				metric.setExercise(exercise);
				metric.setResults(results);

				twoChoiceMetric.add(metric);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Clean alternativ but not working JsonArray metrikDataTrue =
		 * Json.createArrayBuilder().build(); JsonArray metrikDataFalse=
		 * Json.createArrayBuilder().build(); for (TwoChoiceMetrik metrik :
		 * twoChoiceMetrik) { metrikDataTrue.add(Json.createObjectBuilder().add("y",
		 * metrik.getExerciseName()).add("label", metrik.getSelectedtrue()).build());
		 * metrikDataFalse.add(Json.createObjectBuilder().add("y",
		 * metrik.getExerciseName()).add("label", metrik.getSelectedfalse()).build()); }
		 * JsonArray data = Json.createArrayBuilder().build();
		 * 
		 * JsonObject dataTrue = Json.createObjectBuilder().add("type", "stackedColumn")
		 * .add("legendText", "Richtig Angaben") .add("showInLegend",
		 * "true").add("dataPoints", metrikDataTrue).build();
		 * 
		 * JsonObject dataFalse= Json.createObjectBuilder().add("type", "stackedColumn")
		 * .add("legendText", "Falsch Angaben") .add("showInLegend",
		 * "true").add("dataPoints", metrikDataFalse).build();
		 * 
		 * data.add(dataTrue); data.add(dataFalse);
		 */
		String data = "";
		data += "[{type:'stackedColumn',";
		data += "legendText: 'Richtig Angaben',";
		data += "showInLegend: 'true',";
		data += "dataPoints : [";
		for (TwoChoiceMetric metrik : twoChoiceMetric) {
			data += "{y:" + metrik.getNumberOfCorrectAnswers() + ", label:'" + metrik.getExerciseName() + "'},";
		}
		data += "]},";
		data += "{type:'stackedColumn',";
		data += "legendText: 'Falsch Angaben',";
		data += "showInLegend: 'true',";
		data += "dataPoints : [";
		for (TwoChoiceMetric metrik : twoChoiceMetric) {
			data += "{y:" + metrik.getNumberOfWrongAnswers() + ", label:'" + metrik.getExerciseName() + "'},";
		}
		data += "]";
		data += "}]";
		return data;
	}
}
