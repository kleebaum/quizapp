<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<jsp:useBean id="dataManager" scope="request"
	class="de.uhd.ifi.se.quizapp.model.DataManager" />

<div class="container">
	<h1>Satzteilverbindungs-Aufgabe erstellen</h1>

	<script>
		inittinymce();
		function createForm(cols, rows) {
			var form = "<div class='row'>";
			form += "<textarea name='description' placeholder='Beschreibung'  required>Verbinde die Satzteile</textarea>";
			for (var i = 0; i < rows; i++) {
				form += "<div class='group'>";
				for (var j = 0; j < cols; j++) {
					form += "<input class='form-control' type='text' name='input"+i+j+"' placeholder='Satzteil "+j+"' required />";
				}
				form += "</div><br>";
			}
			form += "<input type='hidden' name='numberOfSentenceParts' value='"+cols+"' />";
			form += "</div>";
			return form;
		}
	</script>
	<div class="row">
		<p>
			<label id='collabel' style="display: block;" for="cols">Anzahl
				Satzteile</label> <span data-range-min="1" data-range-max="4"> <input
				id="cols" type="range" min="1" max="4" value="1"
				onchange="updateForm();inittinymce();updateSlider(this);"
				placeholder="" required /></span>
		</p>
	</div>
	<div class="row">
		<p>
			<label style="display: block;" for="rows">Anzahl Sätze</label> <span
				data-range-min="1" data-range-max="5"> <input id="rows"
				type="range" min="1" max="5" value="1"
				onchange="updateForm();inittinymce();updateSlider(this);"
				placeholder="Anzahl Sätze" required /></span>
		</p>
	</div>

	<form id="createExerciseForm" action="Administrator" method="post"
		accept-charset="UTF-8">
		<div id="formcontainer"></div>
		<div class="row">
			<p>
				<label for="difficulty">Schwierigkeit auswählen</label><br> <span
					data-range-min="1" data-range-max="3"> <input
					onchange="updateSlider(this);" type="range" min="1" max="3"
					name="difficulty" value="1"></span><span id="sliderValue"></span><br>
			</p>
		</div>
		<div class="row">
			<div class="col">
				<div class="form-group">
					<label for="name">zugeordnete Information auswählen:</label><br>
					<select class="form-control" name="information" size="1" required>
						<%
							ArrayList<Information> informationList = dataManager.getInformation();
							for (Information eachInformation : informationList) {
						%>
						<option value="<%=eachInformation.getInformationId()%>">
							<%
								out.print(eachInformation.getInformationId() + " " + eachInformation.getName());
							%>
						</option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<div class="col-2 align-self-end">
				<input type='hidden' name='type'
					value='<%=ExerciseHandler.SENTENCEPART%>' /> <input
					class="btn btn-primary" type="submit" name="createExercise"
					value="Speichern" /> <br>
			</div>
		</div>
	</form>
</div>
<script>
	updateForm();
</script>
