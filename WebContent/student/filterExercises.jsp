<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<div class="container">
	<title>Aufgaben finden</title>
	<div class="form-group">
		<form id="filterform" action="Student" method="post">
			<label for="difficulty">Schwierigkeitsgrad</label> <select
				class="form-control" name="difficulty" size=3>
				<option selected>1</option>
				<option>2</option>
				<option>3</option>
			</select> <label for="type">Aufgabentyp</label> <select class="form-control"
				name="type" size=3>
				<option value="<%=ExerciseHandler.TWOCHOICE%>" selected>Richtig/Falsch</option>
				<option value="<%=ExerciseHandler.SENTENCEPART%>">Satzteile</option>
				<option value="<%=ExerciseHandler.LABEL%>">Beschriftung</option>
			</select> <input class="btn btn-primary" type="submit" name="filterExercises"
				value="Suchen & Anzeigen">
		</form>
	</div>
</div>
<jsp:include page="showFilteredExercises.jsp"></jsp:include>