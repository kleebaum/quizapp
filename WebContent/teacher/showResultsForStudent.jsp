<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceResult"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartResult"%>
<%@page import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.*"%>
<%@page import="de.uhd.ifi.se.quizapp.model.sentencepartexercise.*"%>
<%@page import="de.uhd.ifi.se.quizapp.model.labelimageexercise.*"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Result"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Student"%>
<jsp:useBean id="student" scope="request"
	class="de.uhd.ifi.se.quizapp.model.Student" />
<title>Details f&uuml;r Sch&uuml;lerIn</title>

${message}
<jsp:include page="/twoChoiceExercise/showTwoChoiceResultForStudent.jsp">
	<jsp:param name="path" value="" />
</jsp:include>
<jsp:include
	page="/sentencePartExercise/showSentencePartResultForStudent.jsp">
	<jsp:param name="path" value="" />
</jsp:include>
<jsp:include
	page="/labelImageExercise/showLabelImageResultForStudent.jsp">
	<jsp:param name="path" value="" />
</jsp:include>
