<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Student"%>
<jsp:useBean id="administrator" scope="session"
	class="de.uhd.ifi.se.quizapp.model.Administrator" />
<title>Logout</title>
<%
	request.setAttribute("logout", false);
%>