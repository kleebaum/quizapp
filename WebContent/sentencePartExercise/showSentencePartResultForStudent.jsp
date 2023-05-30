<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartResult"%>
<%@page import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.*"%>
<%@page import="de.uhd.ifi.se.quizapp.model.sentencepartexercise.*"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Result"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Student"%>

<div class="container">
	<h3>Satzverbindungs-Aufgaben</h3>
	<table id="sortabletable" class="tablesorter sortabletable">
		<thead>
			<tr>
				<th>Result</th>
				<th>Prozent</th>
			</tr>
		</thead>
		<tbody>
			<%
				ArrayList<SentencePartResult> sentencePartResults = (ArrayList<SentencePartResult>) request
						.getAttribute("sentencePartResults");
				if (sentencePartResults != null) {
					for (SentencePartResult sentencePartResult : sentencePartResults) {
						out.println("<tr>");
						out.println("<p>");

						Map<Sentence, Boolean> checkedResult = sentencePartResult.getResult();

						Set<Map.Entry<Sentence, Boolean>> entrySet = checkedResult.entrySet();

						out.println("<td>");
						out.print("<div id='resultwrapper'>");

						out.println("<p>" + sentencePartResult.getExercise().toString() + "</p>");

						for (Entry<Sentence, Boolean> entry : entrySet) {
							out.print("<div class='group'>" + entry.getKey().toString() + "</div>");
							if ((Boolean) entry.getValue())
								out.print("<img class='correct' src='../img/right.png' />");
							else
								out.print("<img class='correct' src='../img/wrong.png' />");
						}

						out.print("</div>");
						out.println("</td>");

						out.println("<td>");
						out.print(String.format("%.2f", (sentencePartResult.getPercentage()) * 100) + "%");
						out.println("</td>");

						out.println("</p>");
						out.println("</tr>");
					}
				}
			%>
		</tbody>
	</table>
</div>
