<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<title>Aktualisiere Aufgabe</title>
<script>
	inittinymce();
</script>
<c:choose>
	<c:when test="${param.type == 1}">
		<jsp:include page="../twoChoiceExercise/updateTwoChoiceExercise.jsp"></jsp:include>
	</c:when>
	<c:when test="${param.type == 2}">
		<jsp:include
			page="../sentencePartExercise/updateSentencePartExercise.jsp"></jsp:include>
	</c:when>
	<c:when test="${param.type == 3}">
		<jsp:include page="../labelImageExercise/updateLabelImageExercise.jsp"></jsp:include>
	</c:when>
</c:choose>
<jsp:include page="showExercise.jsp"></jsp:include>