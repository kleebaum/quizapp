<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<jsp:useBean id="dataManager" scope="request"
	class="de.uhd.ifi.se.quizapp.model.DataManager" />

<div class="container">
	<h1>Richtig/Falsch-Aufgabe erstellen</h1>

	<script>
		inittinymce();
		function createForm(cols, rows) {
			var form = "<div class='row'>";
			for (var i = 0; i < rows; i++) {
				form += "<div class='group'>";
				for (var j = 0; j < cols; j++) {
					var id2 = rows * j + i;
					number = id2 + 1;
					form += "<input class='form-control' type='text' name='input"+i+j+"' placeholder='Aussage "+ number+"' required /><fieldset><label for='radio"+i+j+"'>Richtig</label><input name='radio"+i+j+"' id='radio"+i+j+"' type='radio' value='true' required /><label for='"+id2+"'>Falsch</label><input id='"+id2+"' name='radio"+i+j+"' type='radio' value='false' /></fieldset>";
				}
				form += "</div><br>";
			}
			form += "<input type='hidden' name='dimensioncol' value='"+cols+"' /><input type='hidden' name='dimensionrow' value='"+rows+"' />";
			form += "</div>";
			return form;
		}
	</script>
	<div class="row">
		<p>
			<label style='display: block;'>Anzahl von Aussagen</label> <span
				data-range-min="1" data-range-max="4"> <input id="cols"
				type="range" min="1" max="4" value="1"
				onchange="updateForm();updateSlider(this);"
				placeholder="Anzahl von Aussagen" required />
			</span>
		</p>
	</div>

	<form id="createExerciseForm" action="Administrator" method="post"
		accept-charset="UTF-8">
		<textarea rows="2" style='width: 98%;' name='description'
			placeholder='Beschreibung' required>Sind die Aussagen richtig oder falsch?</textarea>
		<div id="formcontainer"></div>
		<div class="row">
			<p>
				<label for="difficulty">Schwierigkeit auswählen</label><br> <span
					data-range-min="1" data-range-max="3"> <input
					onchange="updateSlider(this);" type="range" min="1" max="3"
					name="difficulty" value="1">
				</span> <span id="sliderValue"></span> <br>
			</p>
		</div>
		<div class="row">
			<div class="col">
				<div class="form-group">
					<label for="name">zugeordnete Information auswählen:</label> <br>
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
					value='<%=ExerciseHandler.TWOCHOICE%>' /> <input
					class="btn btn-primary" type="submit" name="createExercise"
					value="Speichern" />
			</div>
			<br>
		</div>
	</form>
</div>
<script>
	updateForm();
</script>