<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>


<%
	String path = "" + request.getContextPath();
%>
<nav class="navbar navbar-expand-lg navbar-light">
	<a class="navbar-brand" href="../">NAWIDAZ</a> <a
		class="mobilemenu mobilemenunormal" href="#navi"><span></span></a>
	<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item active"><a class="nav-item nav-link"
				href="index.jsp?p=createInformation">Informationen verwalten</a></li>
			<li class="nav-item active"><a class="nav-item nav-link"
				href="index.jsp?p=createExercise&type=<%=ExerciseHandler.TWOCHOICE%>">Richtig/Falsch-Aufgaben
					verwalten</a></li>
			<li class="nav-item active"><a class="nav-item nav-link"
				href="index.jsp?p=createExercise&type=<%=ExerciseHandler.SENTENCEPART%>">Satzteilverbindungs-Aufgaben
					verwalten</a></li>
			<li class="nav-item active"><a class="nav-item nav-link"
				href="index.jsp?p=createExercise&type=<%=ExerciseHandler.LABEL%>">Beschriftungs-Aufgaben
					verwalten</a></li>
			<li class="nav-item active"><a class="nav-item nav-link"
				href="index.jsp?p=settings">Einstellungen</a></li>
			<li class="nav-item active"><a class="nav-item nav-link"
				href="Administrator?p=login&logout=logout">Logout</a></li>
		</ul>
	</div>
</nav>