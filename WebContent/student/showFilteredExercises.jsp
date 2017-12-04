<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<div class="container">
	<%
		if (request.getAttribute("exercises") != null) {
		int type = Integer.parseInt(request.getParameter("type"));
		out.print("<div class='row'>");
			out.print("<div class='col'>");
				out.print("<br/><b>Alle Aufgaben vom Typ '"
						+ (type == ExerciseHandler.TWOCHOICE ? "Richtig/Falsch" :
							type == ExerciseHandler.SENTENCEPART ? "Satzteile" : "Beschriftung")							
						+ "' mit Schwierigkeitsgrad '" + request.getParameter("difficulty") + "':</b>");
			out.print("</div>");
			out.print("</div>");
			if (type == ExerciseHandler.TWOCHOICE) {
	%>
</div>
<jsp:include
	page="../twoChoiceExercise/showFilteredTwoChoiceExercises.jsp"></jsp:include>
<%
	} else if (type == ExerciseHandler.SENTENCEPART) {
%>
<jsp:include
	page="../sentencePartExercise/showFilteredSentencePartExercises.jsp"></jsp:include>
<%
	} else if(type == ExerciseHandler.LABEL){
%>
<jsp:include
	page="../labelImageExercise/showFilteredLabelImageExercises.jsp"></jsp:include>	
<%
	}
	}
%>