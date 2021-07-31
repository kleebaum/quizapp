<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name=viewport content="width=device-width, initial-scale=1" />

	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	
	
	<title>Mobile Quiz-Web-App</title>
</head>
<body>

	<%
	String path = ""+request.getRequestURL();
	%>	

	<noscript>Bitte aktivieren Sie Javascript, um die Applikation
		richtig nutzen zu können.</noscript>
	<div class="jumbotron" id="wrapper" style="background: #28A828;">
		<div class="container">
			<h1 class="display-4">Na&shy;Wi&shy;DaZ-2.0 Sprach&shy;lern-App </h1>
			<p align="center">Bitte wählen Sie einen Zugang aus.</p>
		</div>
	</div>
	<br><br>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h3>Ad&shy;mi&shy;nis&shy;tra&shy;tor&shy;Innen Zu&shy;gang</h3>
				<p> 
					Im AdministratorInnen Zugang können verschiedene Arten von Aufgaben erstellt, gel&ouml;scht oder bearbeitet werden.
				</p>
				<p>
			 		<a class="btn btn-secondary" href="admin/index.jsp" role="button">Zum Zugang</a>
			 	</p>
			</div>
			
			<div class="col-md-4">
				<h3>Schü&shy;ler&shy;Innen Zu&shy;gang</h3>
				<p> 
					Im SchülerInnen Zugang können Satzteilverbindungs-Aufgaben, Richtig/Falsch-Aufgaben und Beschriftungs-Aufgaben bearbeitet werden.  
				</p>
				<p>
					<a class="btn btn-secondary" href="student/index.jsp" role="button">Zum Zugang</a>
				</p>
			</div>
			<div class="col-md-4">
				<h3>Lehrer&shy;Innen Zu&shy;gang</h3>
				<p>
					Im LehrerInnen Zugang können die von den SchülerInnen bearbeiteten Aufgaben inklusive ihrer Korrektur eingesehen werden. 
				</p>
				<p>
					<a class="btn btn-secondary" href="teacher/index.jsp" role="button">Zum Zugang</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>