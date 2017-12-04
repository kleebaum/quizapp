<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = "" + request.getContextPath();
%>
<nav class="navbar navbar-expand-lg navbar-light">
	<a class="navbar-brand" href="<%=path%>">NAWIDAZ</a> <a
		class="mobilemenu mobilemenunormal" href="#navi"><span></span></a>
	<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item active"><a class="nav-item nav-link"
				href="index.jsp?p=filterExercises">Aufgaben finden</a></li>
			<li class="nav-item active"><a class="nav-item nav-link"
				href="Student?logout=logout">Logout</a></li>
		</ul>
	</div>
</nav>