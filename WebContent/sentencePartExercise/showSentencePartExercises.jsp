<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<jsp:useBean id="dataManager"
	class="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager"></jsp:useBean>

<div class="container">
	<table id="exercisetable">
		<%
			String exerciseView = "";
			List<SentencePartExercise> sentencePartExercises = dataManager.getExercises();
			for (SentencePartExercise exercise : sentencePartExercises) {
		%>
		<tr>
			<%
				int numberOfSentenceParts = exercise.getSentences().get(0).getSentenceParts().size();

					for (int j = 0; j < exercise.getSentences().size(); j++) {
						exerciseView += "<div class='group'>";
						for (int k = 0; k < numberOfSentenceParts; k++) {
							exerciseView += "<span> " + exercise.getSentences().get(j).getSentenceParts().get(k)
									+ " </span>";
						}
						exerciseView += "</div>";
					}
			%>
			<td data-th='Schwierigkeitsgrad'><%=exercise.getDifficulty()%></td>
			<td data-th='Aufgabe'><%=exerciseView%></td>
			<td data-th='Beschreibung'><%=exercise.getDescription()%></td>
			<td data-th='text'><%=dataManager.getInformation(exercise.getInformationId()).getName()%></td>
			<td>
				<form action="Administrator" method="post">
					<input type="hidden" name="id"
						value='<%=exercise.getExerciseId()%>'> <input
						type="hidden" name="type"
						value='<%=ExerciseHandler.SENTENCEPART%>'> <input
						class="btn btn-primary" type="submit" name="deleteExercise"
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