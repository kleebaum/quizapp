<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<title>Ihre L&ouml;sung</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<%
	if (request.getAttribute("exercise") != null) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (type == ExerciseHandler.TWOCHOICE) {
%>
<jsp:include page="../twoChoiceExercise/showTwoChoiceResult.jsp"></jsp:include>
<%
	} else if (type == ExerciseHandler.SENTENCEPART) {
%>
<jsp:include page="../sentencePartExercise/showSentencePartResult.jsp"></jsp:include>
<%
	} else if (type == ExerciseHandler.LABEL) {
%>
<jsp:include page="../labelImageExercise/showLabelImageResult.jsp"></jsp:include>
<%
	} else {
			out.println("Keine Aufgabe verfügbar");
		}
	}
%>
<a class="btn btn-primary" href="index.jsp?p=filterExercises">N&auml;chste
	Aufgabe</a>
