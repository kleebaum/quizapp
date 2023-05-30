<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name=viewport content="width=device-width, initial-scale=1" />

<title>Lehrer/Innen Zugang</title>

<link href="../css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<link rel="stylesheet" type="text/css" href="../css/tablesorter.css">

<script type="text/javascript" src="../js/jquery.js"></script>
<script src="../js/main.js"></script>
<script type="text/javascript" src="../js/tablesorter.js"></script>
</head>
<body>

	<%
		String path = "" + request.getRequestURL();
	%>
	<jsp:include page="menu.jsp"></jsp:include>
	<div>
		<%
			if (request.getParameter("p") != null) {
				String p = request.getParameter("p");
		%>
		<jsp:include page='<%=p + ".jsp"%>' />
		<%
			} else {
		%>
	</div>

	<div class="jumbotron" id="wrapper" style="background: #28A828;">
		<div class="container">
			<h1 class="display-4">Na&shy;Wi&shy;DaZ-2.0
				Lehr&shy;er&shy;In&shy;nen Zu&shy;gang</h1>
			<p align="center">Bitte w&auml;hlen Sie eine &Uuml;bersicht aus.</p>
		</div>
	</div>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h3>Schü&shy;ler&shy;In&shy;nen-Ü&shy;ber&shy;sicht</h3>
				<p>In der Sch&uuml;lerInnen-&Uuml;bersicht k&ouml;nnen alle Sch&uuml;lerInnen
					angezeigt werden und deren bearbeitete Aufgaben.</p>
				<p>
					<a class="btn btn-secondary" href="index.jsp?p=showStudents"
						role="button">Zur &Uuml;bersicht</a>
				</p>
			</div>

			<div class="col-md-4">
				<h3>Aufgaben-&Uuml;bersicht</h3>
				<p>In der Aufgaben-&Uuml;bersicht k&ouml;nnen alle Aufgaben eingesehen
					werden sowie die Sch&uuml;lerInnen, die diese Aufgabe bearbeitet haben.</p>
				<p>
					<a class="btn btn-secondary" href="index.jsp?p=showExercises"
						role="button">Zur &Uuml;bersicht</a>
				</p>
			</div>
			<div class="col-md-4">
				<h3>Metriken-&Uuml;bersicht</h3>
				<p>In der Metriken-&Uuml;bersicht k&ouml;nnen Statistiken zu bearbeiteten
					Aufgaben eingesehen werden.</p>
				<p>
					<a class="btn btn-secondary" href=index.jsp?p=showMetrics
						role="button">Zur &Uuml;bersicht</a>
				</p>
			</div>
		</div>
	</div>

	<%
		}
	%>

	<script>
		$(".sortabletable").tablesorter();
	</script>
</body>
</html>