<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.sentencepartexercise.Sentence"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise"%>
<jsp:useBean id="dataManager" scope="request"
	class="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager" />

<div class="container">
	<h1>Aufgaben bearbeiten</h1>

	<%
		int id = Integer.parseInt(request.getParameter("id"));
		SentencePartExercise exercise = dataManager.getExercise(id);
		ArrayList<Sentence> sentences = exercise.getSentences();
		int width = sentences.get(0).getNumberOfSentenceParts();
		int height = sentences.size();
		List<String> teile = new ArrayList<String>();
		for (Sentence sentence : sentences) {
			for (String sentencePart : sentence.getSentenceParts()) {
				teile.add(sentencePart);
			}
		}
		String form = "<div class'row'>";
		for (int i = 0; i < height; i++) {
			form += "<div class='group'>";
			for (int j = 0; j < width; j++) {
				form += "<input class='form-control' type='text' name='input" + i + j + "' placeholder='Satzteil "
						+ j + "' value='" + teile.get(i * width + j) + "' />";
			}
			form += "</div><br>";
		}
		form += "<input type='hidden' name='numberOfSentenceParts' value='" + width + "' />";
		form += "<input type='hidden' name='type' value='" + ExerciseHandler.SENTENCEPART + "' />";
		form += "<input type='hidden' name='id' value='" + id + "' />";
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
			<label for="difficulty">Schwierigkeit auswählen</label> <span
				data-range-min="1" data-range-max="3"> <input
				onchange="updateSlider(this);" type="range" min="1" max="3"
				name="difficulty" value="<%=exercise.getDifficulty()%>">
			</span> <span id="sliderValue"></span> <br>
		</div>
		<br>
		<div class="row">
			<label for="name">Text auswählen:</label>
		</div>
		<br>
		<div class="row">
			<div class="col">
				<select class="form-control" name="information" size="1">
					<%
						ArrayList<Information> informationList = dataManager.getInformation();
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
				<input class="btn btn-primary" type="submit" name="updateExercise"
					value="Speichern" />
			</div>
			<br>
		</div>
	</form>
</div>