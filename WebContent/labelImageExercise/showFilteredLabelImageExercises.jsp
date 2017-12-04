<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise"%>
<jsp:useBean id="dataManager"
	class="de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageDataManager"></jsp:useBean>

<div class="container">
	<%
		@SuppressWarnings("unchecked")
		ArrayList<LabelImageExercise> exercises = (ArrayList<LabelImageExercise>) request.getAttribute("exercises");
		for (LabelImageExercise exercise : exercises) {
			Information information = dataManager.getInformation(exercise.getInformationId());
	%>

	<div class="container" id="exercisetabel">
		<div class="row">
			<div class="col">
				<b>Schwierigkeit: </b><%=exercise.getDifficulty()%>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<b>Name: </b><%=information.getName()%>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<b>Informationen: </b><%=information.getText()%>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<form action="Student" method="post">
					<input type="hidden" name="id"
						value='<%=exercise.getExerciseId()%>'> <input
						type="hidden" name="type" value='3'>
					<button class="btn btn-primary" type="submit" name="solveExercise">Aufgabel&ouml;sen</button>
				</form>
			</div>
		</div>
	</div>
	<%
		}
	%>
</div>