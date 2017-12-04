<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise"%>
<jsp:useBean id="dataManager"
	class="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceDataManager"></jsp:useBean>
<div class="container">
	<%
		@SuppressWarnings("unchecked")
		ArrayList<TwoChoiceExercise> exercises = (ArrayList<TwoChoiceExercise>) request.getAttribute("exercises");
		for (TwoChoiceExercise exercise : exercises) {
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
						type="hidden" name="type" value='1'>
					<button class="btn btn-primary" type="submit" name="solveExercise">Aufgabel&ouml;sen</button>
				</form>
			</div>
		</div>
	</div>
	<%
		}
	%>
</div>