<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%@page import="java.nio.file.Paths"%>
<div class="container">
	<title>Informationen eintragen</title>
	<h1>Informationen eintragen</h1>

	<jsp:include page="uploadImage.jsp"></jsp:include>
	<div class="container imagePicker">
		<%
			File file = new File(Paths.get(new File("").getAbsolutePath(), "WebContent", "images").toString());
			String fileNames[] = file.list();
			out.print("<ul>");
			for (String s : fileNames)
				out.print("<li><img class='labelImagePreview' src=/images/" + s + "></li>");

			out.print("</ul>");
		%>
	</div>

	<form id="createInformationForm" action="Administrator" method="post"
		accept-charset="UTF-8">
		<div class="form-group">
			<input class="form-control" type="text" style="width: 98%;"
				name="name" placeholder="Titel" required /> <br> <br>
			<textarea name="text" rows="10" style="width: 98%;"></textarea>
			<br> <input class="btn btn-primary" type="submit"
				name="createInformation" value="Speichern" /><br>
		</div>
	</form>
	<script>
		inittinymce();
	</script>

	<jsp:include page="showInformation.jsp"></jsp:include>
</div>