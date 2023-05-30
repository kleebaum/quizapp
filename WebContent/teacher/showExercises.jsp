<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Exercise"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<div class="container">
	<h1>Aufgaben-&Uuml;bersicht</h1>

	<h2>Satzverbindungs-Aufgaben</h2>
	<jsp:include
		page="../sentencePartExercise/showSentencePartExercisesInTable.jsp"></jsp:include>

	<h2>Richtig/Falsch-Aufgaben</h2>
	<jsp:include
		page="../twoChoiceExercise/showTwoChoiceExercisesInTable.jsp"></jsp:include>

	<h2>Beschriftungs-Aufgaben</h2>
	<jsp:include
		page="../labelImageExercise/showLabelImageExerciseInTable.jsp"></jsp:include>
</div>