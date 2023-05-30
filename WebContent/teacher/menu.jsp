<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = "" + request.getContextPath();
%>
<nav class="navbar navbar-expand-lg navbar-light">
	<a class="navbar-brand" href="../">NAWIDAZ</a> <a
		class="mobilemenu mobilemenunormal" href="#navi"><span></span></a>
	<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item active"><a class="nav-item nav-link"
				href="index.jsp?p=showStudents">Sch&uuml;lerInnen-&Uuml;bersicht</a></li>
			<li class="nav-item active"><a class="nav-item nav-link"
				href="index.jsp?p=showExercises">Aufgaben-&Uuml;bersicht</a></li>
			<li class="nav-item active"><a class="nav-item nav-link"
				href="index.jsp?p=showMetrics">Metriken-&Uuml;bersicht</a></li>
		</ul>
	</div>
</nav>