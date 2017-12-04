<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="information" scope="request"
	class="de.uhd.ifi.se.quizapp.model.Information" />
<title>Information bearbeiten</title>
<%
	String text = information.getText();
	String name = information.getName();
	int id = information.getInformationId();
%>
<form id="aufgabenform" action="Administrator" method="post"
	accept-charset="UTF-8">
	<div class="form-group">
		<input class="form-control" type="text" style="width: 98%;"
			name="name" value="<%=name%>" placeholder="Titel" required /> <br>
		<br>
		<textarea name="text" rows="10" style="width: 98%;"><%=text%></textarea>
		<br> <input type="hidden" name="id" value="<%=id%>"> <input
			class="btn btn-primary" type="submit" name="updateInformation"
			value="Speichern" /><br>
	</div>
</form>
<script>
	inittinymce();
</script>

<jsp:include page="showInformation.jsp"></jsp:include>