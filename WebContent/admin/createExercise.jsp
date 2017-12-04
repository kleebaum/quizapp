<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<title>Aufgaben erstellen</title>
<script>
	function updateForm() {
		var cols = $("#cols").val();
		if ($("#rows").length) {
			var rows = $("#rows").val();
			var form = createForm(cols, rows);
		} else {
			var form = createForm(cols, 1);
		}
		$("#formcontainer").html(form);
	}

	function updateSlider(obj) {
		var value = $(obj).val();
		$(obj).after($("#sliderValue").text(value));
	}
</script>
<div class="container">
	<h1 class="display-4">Aufgaben erstellen</h1>
	<c:choose>
		<c:when test="${param.type == 1}">
			<jsp:include page="../twoChoiceExercise/createTwoChoiceExercise.jsp"></jsp:include>
		</c:when>
		<c:when test="${param.type == 2}">
			<jsp:include
				page="../sentencePartExercise/createSentencePartExercise.jsp"></jsp:include>
		</c:when>
		<c:when test="${param.type == 3}">
			<jsp:include
				page="../labelImageExercise/createLabelImageExercise.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<p>Keine Aufgabe ausgewählt</p>
		</c:otherwise>
	</c:choose>

	<p>${message}</p>
	<jsp:include page="showExercise.jsp"></jsp:include>
</div>
