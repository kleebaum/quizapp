<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<title>Aufgabe l&ouml;sen</title>

<style>
#shadow {
	background: rgba(0, 0, 0, 0.5);
	display: none;
	position: fixed;
	height: 100%;
	width: 100%;
	left: 0;
	top: 0;
}

#exerciseform {
	z-index: 999;
}
</style>
<jsp:useBean id="information"
	class="de.uhd.ifi.se.quizapp.model.Information" scope="request" />
<div id="shadow"></div>
<div id="exercisetable">
	<div id="textbox">
		<%
			out.println(information.getName());
			out.println(information.getText());
		%>
	</div>
	<%
		if (request.getAttribute("exercise") != null) {
			int type = Integer.parseInt(request.getParameter("type"));
			out.print("<div class='row'>");
			out.print("<div class='col'>");
			out.print("<br/><b>Alle Aufgaben vom Typ '"
					+ (type == ExerciseHandler.TWOCHOICE
							? "Richtig/Falsch"
							: type == ExerciseHandler.SENTENCEPART ? "Satzteile" : "Beschriftung")
					+ "' mit Schwierigkeitsgrad '" + request.getParameter("difficulty") + "':</b>");
			out.print("</div>");
			out.print("</div>");
			if (type == ExerciseHandler.TWOCHOICE) {
	%>
	<jsp:include page="../twoChoiceExercise/solveTwoChoiceExercise.jsp"></jsp:include>
	<%
		} else if (type == ExerciseHandler.SENTENCEPART) {
	%>
	<jsp:include
		page="../sentencePartExercise/solveSentencePartExercise.jsp"></jsp:include>
	<%
		} else if (type == ExerciseHandler.LABEL) {
	%>
	<jsp:include page="../labelImageExercise/solveLabelImageExercise.jsp"></jsp:include>
	<%
		}
		}
	%>
</div>
<script>
	function goBack() {
		window.history.back();
	}

	buttons = new Array();
	results = new Array();
	/*
	 $(".part").click(function() {
	 $("#shadow").show();
	 var text = $(this).text();
	 var elem = $(this)
	 $(".group").addClass("highlighted");
	 $(".output").click(function() {
	 if (text != "") {
	 var res = $(this);
	 $(this).val(text).attr("disabled", false);
	 elem.hide();
	 $(".group").removeClass("highlighted");
	 text = "";
	 $("#shadow").hide();
	 buttons.push(elem);
	 results.push(res);
	 }
	 });

	 });
	 */
	$("#undo").click(function() {
		var elem = buttons.pop();
		var res = results.pop();
		elem.show();
		res.val("").attr("disabled", false);
		;
	});

	$("#reset").click(function() {
		$(".part").show();
		$(".output").val("").attr("disabled", false);
		buttons = new Array();
		results = new Array();
	});

	$(".part").draggable();

	$(".output").droppable();

	$(".output").droppable({
		accept : ".part",
		hoverClass : 'droppable-hover',
		drop : function(ev, ui) {
			var text = $(ui.draggable).text();
			$(ui.draggable).hide();
			$(this).val(text);
			$(this).droppable("disable");
		}
	});

	$("#exerciseform").droppable({
		accept : ".part",
		drop : function(ev, ui) {
			$(ui.draggable).css({
				"background" : "",
				"left" : "0",
				"top" : "0"
			});
		},
		out : function(ev, ui) {
			$(ui.draggable).css({
				"background" : "salmon",
				"left" : "0",
				"top" : "0"
			});
		}
	});
</script>