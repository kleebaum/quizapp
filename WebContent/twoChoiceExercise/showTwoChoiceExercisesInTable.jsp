<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement"%>
<jsp:useBean id="dataManager"
	class="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceDataManager"></jsp:useBean>

<div class="container">
	<table id="sortabletable"
		class="table-sm table-bordered tablesorter sortabletable">
		<thead>
			<tr>
				<th scope="row">Schwierigkeit</th>
				<th scope="row">Aufgabe</th>
				<th scope="row">Beschreibung</th>
				<th scope="row">Information</th>
				<th scope="row">SchülerInnen</th>
			</tr>
		</thead>
		<tbody>
			<%
				String exerciseView = "";

				List<TwoChoiceExercise> twoChoiceExercises = dataManager.getExercises();
				for (TwoChoiceExercise exercise : twoChoiceExercises) {
					if (exercise.getClass().getSimpleName().equals("TwoChoiceExercise")) {
						List<BooleanStatement> booleanStatements = ((TwoChoiceExercise) exercise)
								.getBooleanStatements();
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
				<td><form action="Teacher" method="post">
						<input type="hidden" name="exerciseId"
							value="<%=exercise.getExerciseId()%>"> <input
							type="hidden" name="type" value="1"> <input
							class="btn btn-primary" type="submit"
							name="showStudentsForExercise" value="Details">
					</form></td>
			</tr>
			<%
				exerciseView = "";
					}
				}
			%>
		</tbody>
	</table>
</div>