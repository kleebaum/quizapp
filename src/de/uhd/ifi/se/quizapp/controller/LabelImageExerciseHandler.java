package de.uhd.ifi.se.quizapp.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import de.uhd.ifi.se.quizapp.model.Information;
import de.uhd.ifi.se.quizapp.model.Student;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageDataManager;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageMetric;
import de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageResult;

public class LabelImageExerciseHandler extends ExerciseHandler {

	private LabelImageDataManager dataManager;

	public LabelImageExerciseHandler() {
		this.type = 3;
		this.dataManager = new LabelImageDataManager();
	}

	public LabelImageExerciseHandler(int type) {
		this.type = type;
		this.dataManager = new LabelImageDataManager();
	}

	/**
	 * Parses the parameters of the request object to an ImageLabel
	 * 
	 * @param request
	 * @param numberOfSentenceParts
	 * @return
	 */
	protected static ArrayList<ImageLabel> parametersToLabels(HttpServletRequest request) {
		ArrayList<ImageLabel> labels = new ArrayList<ImageLabel>();
		Enumeration<String> parameters = request.getParameterNames();

		List<String> parameterList = Collections.list(parameters);
		Collections.sort(parameterList);
		parameters = Collections.enumeration(parameterList);

		while (parameters.hasMoreElements()) {
			String parameterName = parameters.nextElement();
			String parameterValue = request.getParameterValues(parameterName)[0];
			if (parameterName.contains("solveinput")) {
				if (parameterValue.isEmpty()) {
					return null;
				}
				labels.add(new ImageLabel(parameterValue, "", ""));
			}
		}
		if (labels.size() > 0) {
			return labels;
		}
		return null;
	}

	@Override
	protected HttpServletRequest handleCreationInChain(HttpServletRequest request) {

		int difficulty = Integer.parseInt(request.getParameter("difficulty"));
		String selectedInformation = request.getParameter("information");
		String description = request.getParameter("description");
		String labelData = request.getParameter("labelData");
		String message = "";

		/*
		 * Checks if inputs are correct
		 */
		if (labelData.isEmpty()) {
			message = "Es muss mindestens eine Beschriftung erstellt werden.";
		} else if (difficulty < 1 || difficulty > 3) {
			message = "Der Schwierigkeitsgrad muss zwischen 1 und 3 liegen.";
		} else if (description.equals("")) {
			message = "Es muss eine Beschreibung eingetragen werden.";
		} else if (!tryParse(selectedInformation)) {
			message = "Es muss eine g&uuml;ltige Information hinzugef&uuml;gt werden.";
		} else {
			LabelImageExercise exercise = new LabelImageExercise();
			exercise.setDifficulty(difficulty);
			exercise.setDescription(description);
			exercise.setInformationId(Integer.parseInt(selectedInformation));
			exercise.setLabelData(labelData);

			try {
				this.dataManager.insertExercise(exercise);
				message = "Die Beschriftungsaufgabe wurde erfolgreich erstellt.";
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
			LabelImageExercise exercise = dataManager.getExercise(id);
			request.setAttribute("exercise", exercise);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return request;
	}

	@Override
	protected HttpServletRequest handleUpdatingInChain(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		int difficulty = Integer.parseInt(request.getParameter("difficulty"));
		String selectedInformation = request.getParameter("information");
		int informationId = Integer.parseInt(selectedInformation.split(" ")[0]);
		String description = request.getParameter("description");
		String labelData = request.getParameter("labelData");

		String message = "";

		LabelImageExercise exercise = new LabelImageExercise();
		try {
			exercise = dataManager.getExercise(id);
		} catch (ClassNotFoundException | SQLException e) {
			message = "Die Aufgabe konnte nicht gefunden werden.";
			e.printStackTrace();
		}
		exercise.setDifficulty(difficulty);
		exercise.setDescription(description);
		exercise.setInformationId(informationId);
		exercise.setLabelData(labelData);

		try {
			dataManager.updateExercise(exercise);
			message = "Die Satzverbindungsaufgabe wurde erfolgreich aktualisiert.";
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
			List<LabelImageExercise> exercises = dataManager.getExercises(difficulty);
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

			LabelImageExercise exercise = dataManager.getExercise(id);
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

		LabelImageExercise exercise = null;
		try {
			exercise = dataManager.getExercise(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		LabelImageResult result = new LabelImageResult();
		result.setExercise(exercise);

		Student student = (Student) request.getSession().getAttribute("student");
		result.setStudent(student);
		ArrayList<ImageLabel> labels = parametersToLabels(request);

		if (labels != null) {
			result.setLabels(labels);
			try {
				dataManager.insertResult(result);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("result", result);
			request.setAttribute("exercise", exercise);
		} else {
			String message = "Beschriften sie bitte die Textfelder";
			request.setAttribute("message", message);
		}

		return request;
	}

	/**
	 * This function returns a String in JSON format. It will be used to visualize
	 * the statistics in the teacher's view
	 * 
	 * @return String (JSON)
	 */
	@Override
	protected String calculateExerciseMetricsInChain(int type) {
		ArrayList<LabelImageExercise> labelImageExercises;
		ArrayList<LabelImageMetric> labelImageMetric = new ArrayList<>();

		try {
			labelImageExercises = (ArrayList<LabelImageExercise>) dataManager.getExercises();

			ArrayList<LabelImageResult> results = new ArrayList<>();
			for (LabelImageExercise exercise : labelImageExercises) {
				removingHTMLTags(exercise);
				results = dataManager.getAllResultsByExerciseId(exercise.getExerciseId());

				LabelImageMetric metric = new LabelImageMetric();
				metric.setExercise(exercise);
				metric.setResults(results);

				labelImageMetric.add(metric);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		String data = "";
		data += "[{type:'stackedColumn',";
		data += "legendText: 'Richtig Angaben',";
		data += "showInLegend: 'true',";
		data += "dataPoints : [";
		for (LabelImageMetric metric : labelImageMetric) {
			data += "{y:" + metric.getNumberOfCorrectAnswers() + ", label:'" + metric.getExerciseName() + "'},";
		}
		data += "]},";
		data += "{type:'stackedColumn',";
		data += "legendText: 'Falsch Angaben',";
		data += "showInLegend: 'true',";
		data += "dataPoints : [";
		for (LabelImageMetric metric : labelImageMetric) {
			data += "{y:" + metric.getNumberOfWrongAnswers() + ", label:'" + metric.getExerciseName() + "'},";
		}
		data += "]";
		data += "}]";
		return data;
	}
}
