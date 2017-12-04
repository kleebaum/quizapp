<%@page
	import="de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Student"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Result"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartResult"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceResult"%>
<jsp:useBean id="datamanager"
	class="de.uhd.ifi.se.quizapp.model.DataManager"></jsp:useBean>

<div class="container">
	<h1>Sch&uuml;ler/innen zu ausgew&auml;hlten Aufgabe</h1>
	<table id="sortabletable"
		class="table-sm table-bordered tablesorter sortabletable">
		<thead>
			<tr>
				<th scope="row">Benutzername</th>
				<th scope="row">Vorname</th>
				<th scope="row">Nachname</th>
				<th scope="row">Ergebnis</th>
			</tr>
		</thead>
		<tbody>
			<%
				//String exerciseId = request.getParameter("exercise");
				//ArrayList<Student> students = datamanager.getStudentByResult(exerciseId);
				try {
					ArrayList<SentencePartResult> sentencePartResults = (ArrayList<SentencePartResult>) request
							.getAttribute("sentencePartResults");

					for (SentencePartResult sentencePartResult : sentencePartResults) {

						Student student = sentencePartResult.getStudent();
						String percentage = String.format("%.2f", (sentencePartResult.getPercentage()) * 100) + "%";
			%>
			<tr>
				<td data-th='UserName'><%=student.getUsername()%></td>
				<td data-th='Vorname'><%=student.getFirstname()%></td>
				<td data-th='Nachname'><%=student.getLastname()%></td>
				<td data-th='Ergebnis'><%=percentage%></td>
			</tr>
			<%
				}
				} catch (Exception e) {

				}

				try {
					ArrayList<TwoChoiceResult> twoChoiceResults = (ArrayList<TwoChoiceResult>) request
							.getAttribute("twoChoiceResults");
					for (TwoChoiceResult twoChoiceResult : twoChoiceResults) {

						Student student = twoChoiceResult.getStudent();
						String percentage = String.format("%.2f", (twoChoiceResult.getPercentage()) * 100) + "%";
			%>
			<tr>
				<td data-th='UserName'><%=student.getUsername()%></td>
				<td data-th='Vorname'><%=student.getFirstname()%></td>
				<td data-th='Nachname'><%=student.getLastname()%></td>
				<td data-th='Ergebnis'><%=percentage%></td>
			</tr>
			<%
				}
				} catch (Exception e) {

				}
				try {
					ArrayList<LabelImageResult> labelImageResults = (ArrayList<LabelImageResult>) request
							.getAttribute("labelImageResult");
					for (LabelImageResult labelImageResult : labelImageResults) {

						Student student = labelImageResult.getStudent();
						String percentage = String.format("%.2f", (labelImageResult.getPercentage()) * 100) + "%";
			%>
			<tr>
				<td data-th='UserName'><%=student.getUsername()%></td>
				<td data-th='Vorname'><%=student.getFirstname()%></td>
				<td data-th='Nachname'><%=student.getLastname()%></td>
				<td data-th='Ergebnis'><%=percentage%></td>
			</tr>
			<%
				}
				} catch (Exception e) {

				}
			%>


		</tbody>
	</table>
</div>