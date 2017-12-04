<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="administrator" scope="session"
	class="de.uhd.ifi.se.quizapp.model.Administrator" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name=viewport content="width=device-width, initial-scale=1" />

<script src="../js/jquery.js"></script>
<script src="../js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="../js/main.js"></script>


<link href="../css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="../css/main.css">
<link rel="stylesheet"
	href="../js/jquery-ui-1.12.1.custom/jquery-ui.css">

<title>Administrator/Innen Zugang</title>

<script src="../js/tinymce/tinymce.min.js"></script>
<script>
	function inittinymce() {
		tinymce.init({
			selector : 'textarea',
			plugins : "image colorpicker textcolor media  emoticons link"
		});
	}
</script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="jumbotron" id="wrapper">
		${message}
		<div class="container">
			<h1 class="display-4">Ad&shy;mi&shy;nis&shy;tra&shy;tor/&shy;Innen Zu&shy;gang</h1>
			<p>W&auml;hlen Sie eine Aufgabe oder Information aus, die Sie
				erstellen m&ouml;chten.</p>

			<%
				if (administrator.isValid()
						|| (request.getParameter("p") != null && request.getParameter("p").equals("register_success"))) {
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
					"<p>Sie brauchen einen Account und m&uuml;ssen eingeloggt sein, um Aufgaben zu administrieren.</p>");
	%>
	<!-- The following divs are there to close the title block -->
	</div>
	</div>

	<jsp:include page='login.jsp' />
	<%
		}
	%>

</body>
</html>