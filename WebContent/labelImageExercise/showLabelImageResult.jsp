<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>

<%@page
	import="de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel"%>
<jsp:useBean id="result"
	class="de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageResult"
	scope="request">
</jsp:useBean>
<jsp:useBean id="exercise"
	class="de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise"
	scope="request">
</jsp:useBean>

<%
	HashMap<ImageLabel, Boolean> checkedResult = result.getResult();
	Set<Map.Entry<ImageLabel, Boolean>> entrySet = checkedResult.entrySet();

	out.print("<div class='row' id='resultwrapper'>");
	for (Entry<ImageLabel, Boolean> entry : entrySet) {
		out.print("<div class='group'>" + entry.getKey().toString().replace(":true", "").replace(":false", "")
				+ "</div>");
		if ((Boolean) entry.getValue())
			out.print("<img class='correct' src='../img/right.png' />");
		else
			out.print("<img class='correct' src='../img/wrong.png' />");
	}
	out.print("</div>");
	out.print("<div class='row'>");
	out.print("<div>");
	out.print(
			"<p class='points'>Punktzahl: " + String.format("%.2f", (result.getPercentage() * 100)) + "%</p>");
	out.print("</div>");
	out.print("</div>");
%>