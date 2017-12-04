<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<c:choose>
	<c:when test="${param.type == ExerciseHandler.TWOCHOICE}">
		<jsp:include page="../twoChoiceExercise/showTwoChoiceExercises.jsp"></jsp:include>
	</c:when>
	<c:when test="${param.type == ExerciseHandler.SENTENCEPART}">
		<jsp:include
			page="../sentencePartExercise/showSentencePartExercises.jsp"></jsp:include>
	</c:when>
	<c:when test="${param.type == ExerciseHandler.LABEL}">
		<jsp:include page="../labelImageExercise/showLabelImageExercise.jsp"></jsp:include>
	</c:when>
</c:choose>