<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="de.uhd.ifi.se.quizapp.model.labelimageexercise.*"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<jsp:useBean id="dataManager"
	class="de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageDataManager"></jsp:useBean>

<div class="container">
	<table id="exercisetable">
		<%
			String exerciseView = "";
			List<LabelImageExercise> labelImageExercises = dataManager.getExercises();
			for (LabelImageExercise exercise : labelImageExercises) {
		%>
		<tr>

			<td data-th='Schwierigkeitsgrad'><%=exercise.getDifficulty()%></td>
			<td data-th='Aufgabe'><%=exerciseView%><img width=100
				height=auto src=<%=exercise.getImageSrc()%>></td>
			<td data-th='Beschreibung'><%=exercise.getDescription()%></td>
			<td data-th='text'><%=dataManager.getInformation(exercise.getInformationId()).getName()%></td>
			<td>
				<form action="Administrator" method="post">
					<input type="hidden" name="id"
						value='<%=exercise.getExerciseId()%>'> <input
						type="hidden" name="type" value='<%=ExerciseHandler.LABEL%>'>
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