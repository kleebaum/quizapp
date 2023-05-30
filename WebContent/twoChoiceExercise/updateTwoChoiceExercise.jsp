<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<jsp:useBean id="dataManager" scope="request"
	class="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceDataManager" />

<div class="container">
	<h1>Aufgaben bearbeiten</h1>

	<%
		int id = Integer.parseInt(request.getParameter("id"));
		TwoChoiceExercise exercise = dataManager.getExercise(id);
		List<BooleanStatement> booleanStatements = exercise.getBooleanStatements();

		String form = "<div class='group'>";
		int i = 0;
		for (BooleanStatement statement : booleanStatements) {
			int radioId = i * 2;
			int radioId2 = radioId + 1;
			form += "<input class='form-control' type='text' name='input" + i + "' value='"
					+ statement.getStatement() + "' required  />";
			if (statement.isCorrect()) {
				form += "<fieldset><label for='" + radioId + "'>Richtig</label><input id='" + radioId
						+ "' name='radio" + i + "' type='radio' value='true' checked required />" + "<label for='"
						+ radioId2 + "'>Falsch</label><input id='" + radioId2 + "' name='radio" + i
						+ "' type='radio' value='false' /></fieldset>";
			} else {
				form += "<fieldset><label for='" + radioId + "'>Richtig</legend><input id='" + radioId
						+ "' name='radio" + i + "' type='radio' value='true' required />" + "<label for='"
						+ radioId2 + "'>Falsch</legend><input id='" + radioId2 + "' name='radio" + i
						+ "' type='radio' value='false' checked/></fieldset>";
			}
			i++;
		}
		form += "</div>";
	%>


	<form id="updateExerciseForm" action="Administrator" method="post"
		accept-charset="UTF-8">
		<div class="row">
			<textarea name='description' rows='2' style='width: 98%;'
				placeholder='Beschreibung' required><%=exercise.getDescription()%></textarea>
		</div>
		<div id="formcontainer"><%=form%></div>
		<div class="row">
			<p>
				<label for="difficulty">Schwierigkeit auswählen</label><br> <span
					data-range-min="1" data-range-max="3"> <input
					onchange="updateSlider(this);" type="range" min="1" max="3"
					name="difficulty" value="<%=exercise.getDifficulty()%>">
				</span> <span id="sliderValue"></span> <br>
			</p>
		</div>
		<label for="name">Text auswählen:</label> <br>
		<div class="row">
			<div class="col">
				<select class="form-control" name="information" size="1">
					<%
						List<Information> informationList = dataManager.getInformation();
						for (Information eachInformation : informationList) {
					%><option>
						<%
							out.print(eachInformation.getInformationId() + " " + eachInformation.getName());
						%>
					</option>
					<%
						}
					%>
				</select>
			</div>
			<div class="col-2">
				<input type='hidden' name='type'
					value='<%=ExerciseHandler.TWOCHOICE%>' /> <input type='hidden'
					name='id' value='<%=id%>' /> <input class="btn btn-primary"
					type="submit" name="updateExercise" value="Speichern" />
			</div>
		</div>
		<br>
	</form>
</div>