<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>

<jsp:useBean id="exerciseHandler" class="de.uhd.ifi.se.quizapp.controller.TwoChoiceExerciseHandler"/>
<jsp:useBean id="sentencePartExerciseHandler" class="de.uhd.ifi.se.quizapp.controller.SentencePartExerciseHandler" />
<jsp:useBean id="labelImageExerciseHandler" class="de.uhd.ifi.se.quizapp.controller.LabelImageExerciseHandler" />
<%
 	exerciseHandler.setSuccessor(sentencePartExerciseHandler);
 	sentencePartExerciseHandler.setSuccessor(labelImageExerciseHandler);
%>

<title>Lehrer/Innen Zugang</title>
<html>
<head>
<script type="text/javascript" src="../js/canvasjs.min.js"></script>
<script type="text/javascript">
	var twoChoicedata =
<%=exerciseHandler.calculateExerciseMetrics(ExerciseHandler.TWOCHOICE)%>
	var sentencedata =
<%=exerciseHandler.calculateExerciseMetrics(ExerciseHandler.SENTENCEPART)%>
	var labeldata =
<%=exerciseHandler.calculateExerciseMetrics(ExerciseHandler.LABEL)%>
	window.onload = function() {
		var chartTwoChoice = new CanvasJS.Chart("chartTwoChoice", {
			title : {
				text : "Ergebnisse pro Richtig/Falsch-Aufgaben"
			},
			data : twoChoicedata
		});

		var chartSentencePart = new CanvasJS.Chart("chartSentencePart", {
			title : {
				text : "Ergebnisse pro Satzteilverbindungs-Aufgabe"
			},
			data : sentencedata
		});

		var chartLabelImage = new CanvasJS.Chart("chartLabelImage", {
			title : {
				text : "Ergebnisse pro Beschriftungs-Aufgabe"
			},
			data : labeldata
		});

		chartSentencePart.render();
		chartTwoChoice.render();
		chartLabelImage.render();
	}
</script>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<div id="chartTwoChoice" style="height: 500px; width: 100%;"></div>
				<br> <br>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<div id="chartSentencePart" style="height: 500px; width: 100%;"></div>
				<br> <br>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<div id="chartLabelImage" style="height: 500px; width: 100%;"></div>
				<br> <br>
			</div>
		</div>
	</div>


</body>
</html>