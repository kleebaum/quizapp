<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<jsp:useBean id="dataManager"
	class="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceDataManager"></jsp:useBean>

<div class="container">
	<table id="exercisetable">
		<%
			String exerciseView = "";

			List<TwoChoiceExercise> twoChoiceExercises = dataManager.getExercises();
			for (TwoChoiceExercise exercise : twoChoiceExercises) {
				List<BooleanStatement> booleanStatements = exercise.getBooleanStatements();
				for (BooleanStatement statement : booleanStatements) {
					exerciseView += "<div class='group'>" + statement.getStatement();
					if (statement.isCorrect()) {
						exerciseView += "<input name='radio" + "' type='checkbox' checked  disabled/>";
					} else {
						exerciseView += "<input name='radio" + "' type='checkbox' disabled/>";
					}
					exerciseView += "</div>";
				}
		%>
		<tr>
			<td data-th='Schwierigkeitsgrad'><%=exercise.getDifficulty()%></td>
			<td data-th='Aufgabe'><%=exerciseView%></td>
			<td data-th='Beschreibung'><%=exercise.getDescription()%></td>
			<td data-th='text'><%=dataManager.getInformation(exercise.getInformationId()).getName()%></td>
			<td>
				<form action="Administrator" method="post">
					<input type="hidden" name="id"
						value='<%=exercise.getExerciseId()%>'> <input
						type="hidden" name="type" value='<%=ExerciseHandler.TWOCHOICE%>'>
					<input class="btn btn-primary" type="submit" name="deleteExercise"
						value="Löschen"
						onclick="return confirm('Diesen Eintrag löschen?')"> <input
						class="btn btn-primary" type="submit" name="editExercise"
						value="Bearbeiten">
				</form>
			</td>
		</tr>
		<%
			exerciseView = "";
			}
		%>
	</table>
</div>