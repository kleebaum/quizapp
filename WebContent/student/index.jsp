<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="student" scope="session"
	class="de.uhd.ifi.se.quizapp.model.Student" />
<jsp:useBean id="administrator" scope="session"
	class="de.uhd.ifi.se.quizapp.model.Administrator" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Sch&uuml;ler/Innen Zugang</title>

<script src="../js/jquery.js"></script>
<script src="../js/main.js"></script>
<script src="../js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>


<link href="../css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<link rel="stylesheet"
	href="../js/jquery-ui-1.12.1.custom/jquery-ui.css">

<script src="../js/touch_punch.js"></script>
<meta name=viewport content="width=device-width, initial-scale=1" />

</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="jumbotron" id="wrapper">
		<div class="container">
			<h1 class="display-4">Schül&shy;er/In&shy;nen Zu&shy;gang</h1>
			<p>${message}</p>
			<%
				if (student.isValid() || administrator.isValid()
						|| (request.getParameter("p") != null && request.getParameter("p").equals("register_success"))) {
					if (student.getUsername() != null) {
						out.println("<p align='center'>Hallo " + student.getUsername() + "</p>");
						request.setAttribute("student", student);
					}
					if (administrator.getUsername() != null) {
						out.println("<p align='center'>Hallo " + administrator.getUsername() + "</p>");
						request.setAttribute("administrator", administrator);
					}
			%>
		</div>
	</div>
	<div class="container">
		<%
			if (request.getParameter("p") != null) {
					String p = request.getParameter("p");
		%>
		<jsp:include page='<%=p + ".jsp"%>' />
	</div>

	<%
		}
		} else {
			out.println(
					"<h3>Sie brauchen einen Account und müssen eingeloggt sein, um Aufgaben zu bearbeiten.</h3>");
	%>
	<!-- The following divs are there to close the title block -->
	</div>
	</div>
	<jsp:include page='login.jsp' />
	<h3 align="center">ODER</h3>
	<jsp:include page='register.jsp' />
	<%
		}
	%>
	</div>
	</div>

</body>
</html>