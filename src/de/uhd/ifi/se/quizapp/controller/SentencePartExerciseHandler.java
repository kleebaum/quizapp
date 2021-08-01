package de.uhd.ifi.se.quizapp.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import de.uhd.ifi.se.quizapp.model.Information;
import de.uhd.ifi.se.quizapp.model.Student;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.Sentence;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartMetric;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartResult;

public class SentencePartExerciseHandler extends ExerciseHandler {

	private SentencePartDataManager dataManager;

	public SentencePartExerciseHandler() {
		this.type = 2;
		this.dataManager = new SentencePartDataManager();
	}

	public SentencePartExerciseHandler(int type) {
		this.type = type;
		this.dataManager = new SentencePartDataManager();
	}

	/**
	 * Parses the parameters of the request object to sentences
	 */
	protected static ArrayList<Sentence> parametersToSentences(HttpServletRequest request, int numberOfSentenceParts) {
		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		Enumeration<String> parameters = request.getParameterNames();

		List<String> parameterList = Collections.list(parameters);
		Collections.sort(parameterList);
		parameters = Collections.enumeration(parameterList);

		int i = 0;
		while (parameters.hasMoreElements()) {
			String parameterName = parameters.nextElement();
			String parameterValue = request.getParameterValues(parameterName)[0];
			if (parameterName.contains("input")) {
				if (parameterValue.isEmpty())
					return null;
				if (i == 0 || i % numberOfSentenceParts == 0) {
					Sentence sentence = new Sentence();
					sentence.addSentencePart(parameterValue);
					sentences.add(sentence);
				} else {
					sentences.get(sentences.size() - 1).addSentencePart(parameterValue);
				}
				i++;
			}
		}
		return sentences;
	}

	@Override
	protected HttpServletRequest handleCreationInChain(HttpServletRequest request) {

		int difficulty = Integer.parseInt(request.getParameter("difficulty"));
		String selectedInformation = request.getParameter("information");
		int numberOfSentenceParts = Integer.parseInt(request.getParameter("numberOfSentenceParts"));
		String description = request.getParameter("description");
		ArrayList<Sentence> sentences = parametersToSentences(request, numberOfSentenceParts);

		String message = "";

		/*
		 * Checks if inputs are correct
		 */
		if (sentences == null) {
			message = "Es muss mindestens ein Satz erstellt werden.";
		} else if (difficulty < 1 || difficulty > 3) {
			message = "Der Schwierigkeitsgrad muss zwischen 1 und 3 liegen.";
		} else if (description.equals("")) {
			message = "Es muss eine Beschreibung eingetragen werden.";
		} else if (!tryParse(selectedInformation)) {
			message = "Es muss eine g&uuml;ltige Information hinzugef&uuml;gt werden.";
		} else {
			SentencePartExercise exercise = new SentencePartExercise();
			exercise.setDifficulty(difficulty);
			exercise.setDescription(description);
			exercise.setInformationId(Integer.parseInt(selectedInformation));
			exercise.setSentences(sentences);

			try {
				this.dataManager.insertExercise(exercise);
				message = "Die Satzverbindungsaufgabe wurde erfolgreich erstellt.";
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
			SentencePartExercise exercise = dataManager.getExercise(id);
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
		int numberOfSentenceParts = Integer.parseInt(request.getParameter("numberOfSentenceParts"));

		SentencePartExercise exercise = new SentencePartExercise();
		try {
			exercise = dataManager.getExercise(id);
		} catch (ClassNotFoundException | SQLException e) {
			message = "Die Aufgabe konnte nicht gefunden werden.";
			e.printStackTrace();
		}
		exercise.setDifficulty(difficulty);
		exercise.setDescription(request.getParameter("description"));
		exercise.setInformationId(informationId);
		exercise.setSentences(parametersToSentences(request, numberOfSentenceParts));

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
			List<SentencePartExercise> exercises = dataManager.getExercises(difficulty);
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

			SentencePartExercise exercise = dataManager.getExercise(id);
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
		SentencePartExercise exercise = null;

		try {
			exercise = dataManager.getExercise(id);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int numberOfSentenceParts = 1;
		try {
			numberOfSentenceParts = exercise.getSentences().get(0).getNumberOfSentenceParts();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		SentencePartResult result = new SentencePartResult();
		result.setExercise(exercise);
		Student student = (Student) request.getSession().getAttribute("student");
		System.out.println("Username " + student.getUsername());
		result.setStudent(student);
		ArrayList<Sentence> sentences = parametersToSentences(request, numberOfSentenceParts);

		if (sentences != null) {
			result.setSentences(sentences);

			try {
				dataManager.insertResult(result);
				System.out.println("Erfolgreich eingetragen");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("result", result);
			request.setAttribute("exercise", exercise);
		} else {
			String message = "F&uuml;ge alle Satzteile zu einem Satz zusammen.";
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
		ArrayList<SentencePartExercise> sentencePartExercises;
		ArrayList<SentencePartMetric> sentencePartMetrik = new ArrayList<>();
		try {
			sentencePartExercises = (ArrayList<SentencePartExercise>) dataManager.getExercises();

			ArrayList<SentencePartResult> results = new ArrayList<>();
			for (SentencePartExercise exercise : sentencePartExercises) {
				removingHTMLTags(exercise);
				results = dataManager.getAllResultsByExerciseId(exercise.getExerciseId());
				SentencePartMetric metric = new SentencePartMetric();
				metric.setExercise(exercise);
				metric.setResults(results);
				sentencePartMetrik.add(metric);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String data = "";
		data += "[{type:'stackedColumn',";
		data += "legendText: 'Richtig Angaben',";
		data += "showInLegend: 'true',";
		data += "dataPoints : [";
		for (SentencePartMetric metric : sentencePartMetrik) {
			data += "{y:" + metric.getNumberOfCorrectAnswers() + ", label:'" + metric.getExerciseName() + "'},";
		}
		data += "]},";
		data += "{type:'stackedColumn',";
		data += "legendText: 'Falsch Angaben',";
		data += "showInLegend: 'true',";
		data += "dataPoints : [";
		for (SentencePartMetric metric : sentencePartMetrik) {
			data += "{y:" + metric.getNumberOfWrongAnswers() + ", label:'" + metric.getExerciseName() + "'},";
		}
		data += "]";
		data += "}]";
		return data;
	}
}
