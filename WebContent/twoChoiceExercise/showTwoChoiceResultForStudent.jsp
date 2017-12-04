<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceResult"%>
<%@page import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.*"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Result"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Student"%>

<div class="container">
	<h3>Richtig/Falsch-Aufgaben</h3>
	<table id="sortabletable" class="tablesorter sortabletable">
		<thead>
			<tr>
				<th>Result</th>
				<th>Prozent</th>
			</tr>
		</thead>
		<tbody>
			<%
				ArrayList<TwoChoiceResult> twoChoiceResults = (ArrayList<TwoChoiceResult>) request
						.getAttribute("twoChoiceResults");
				if (twoChoiceResults != null) {
					for (TwoChoiceResult twoChoiceResult : twoChoiceResults) {
						out.println("<tr>");
						out.println("<p>");

						HashMap<BooleanStatement, Boolean> checkedResult = twoChoiceResult.getResult();

						Set<Map.Entry<BooleanStatement, Boolean>> entrySet = checkedResult.entrySet();

						out.println("<td>");
						out.print("<div id='resultwrapper'>");

						out.print("<p>" + twoChoiceResult.getExercise().toString()
								.replace("true", "<img class='correct' src='../img/right.png' />")
								.replace("false", "<img class='correct' src='../img/wrong.png' />") + "</p>");

						for (Entry<BooleanStatement, Boolean> entry : entrySet) {
							out.print("<div class='group'>" + entry.getKey().toString().replace("true", "") + "</div>");
							if ((Boolean) entry.getValue())
								out.print("<img class='correct' src='../img/right.png' />");
							else
								out.print("<img class='correct' src='../img/wrong.png' />");
						}

						out.print("</div>");
						out.println("</td>");

						out.println("<td>");
						out.print(String.format("%.2f", (twoChoiceResult.getPercentage()) * 100) + "%");
						out.println("</td>");

						out.println("</p>");
						out.println("</tr>");
					}
				}
			%>
		</tbody>
	</table>
</div>