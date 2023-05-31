<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<jsp:useBean id="dataManager"
	class="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager"></jsp:useBean>

<div class="container">
	<table id="sortabletable"
		class="table-sm table-bordered tablesorter sortabletable">
		<thead>
			<tr>
				<th scope="row">Schwierigkeit</th>
				<th scope="row">Aufgabe</th>
				<th scope="row">Beschreibung</th>
				<th scope="row">Information</th>
				<th scope="row">Sch&uuml;lerInnen</th>
			</tr>
		</thead>
		<tbody>
			<%
				String exerciseView = "";
				List<SentencePartExercise> sentencePartExercises = dataManager.getExercises();
				for (SentencePartExercise exercise : sentencePartExercises) {
					if (exercise.getClass().getSimpleName().equals("SentencePartExercise")) {
			%>
			<tr>
				<%
					int numberOfSentenceParts = ((SentencePartExercise) exercise).getSentences().get(0)
									.getSentenceParts().size();

							for (int j = 0; j < ((SentencePartExercise) exercise).getSentences().size(); j++) {
								exerciseView += "<div class='group'>";
								for (int k = 0; k < numberOfSentenceParts; k++) {
									exerciseView += "<span> "
											+ ((SentencePartExercise) exercise).getSentences().get(j).getSentenceParts().get(k)
											+ " </span>";
								}
								exerciseView += "</div>";
							}
				%>
				<td data-th='Schwierigkeitsgrad'><%=exercise.getDifficulty()%></td>
				<td data-th='Aufgabe'><%=exerciseView%></td>
				<td data-th='Beschreibung'><%=exercise.getDescription()%></td>
				<td data-th='text'><%=dataManager.getInformation(exercise.getInformationId()).getName()%></td>
				<td><form action="Teacher" method="post">
						<input type="hidden" name="exerciseId"
							value="<%=exercise.getExerciseId()%>"> <input
							type="hidden" name="type" value="2"> <input
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