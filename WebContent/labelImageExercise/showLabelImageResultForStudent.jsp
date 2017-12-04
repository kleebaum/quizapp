<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="de.uhd.ifi.se.quizapp.model.labelimageexercise.*"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Result"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Student"%>

<div class="container">
	<h3>Beschriftungs-Aufgaben</h3>
	<table id="sortabletable" class="tablesorter sortabletable">
		<thead>
			<tr>
				<th>Result</th>
				<th>Prozent</th>
			</tr>
		</thead>
		<tbody>
			<%
				ArrayList<LabelImageResult> labelImageResults = (ArrayList<LabelImageResult>) request
						.getAttribute("labelImageResults");
				if (labelImageResults != null) {
					for (LabelImageResult result : labelImageResults) {
						out.println("<tr>");
						out.println("<p>");

						HashMap<ImageLabel, Boolean> checkedResult = result.getResult();
						Set<Map.Entry<ImageLabel, Boolean>> entrySet = checkedResult.entrySet();

						out.println("<td>");
						out.print("<div id='resultwrapper'>");

						out.print("<p>" + result.getExercise().toString()
								.replace("true", "<img class='correct' src='../img/right.png' />")
								.replace("false", "<img class='correct' src='../img/wrong.png' />") + "</p>");
						for (Entry<ImageLabel, Boolean> entry : entrySet) {
							out.print("<div class='group'>" + entry.getKey().toString().replace("true", "") + "</div>");
							if ((Boolean) entry.getValue())
								out.print("<img class='correct' src='../img/right.png' />");
							else
								out.print("<img class='correct' src='../img/wrong.png' />");
						}
						out.print("</div>");
						out.println("</td>");

						out.println("<td>");
						out.print(String.format("%.2f", (result.getPercentage()) * 100) + "%");
						out.println("</td>");

						out.println("</p>");
						out.println("</tr>");
					}
				}
			%>
		</tbody>
	</table>
</div>