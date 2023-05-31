<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<div class="container">
	<jsp:useBean id="dataManager"
		class="de.uhd.ifi.se.quizapp.model.DataManager"></jsp:useBean>
	<%
		List<Information> informationList = dataManager.getInformation();
		for (Information information : informationList) {
	%>
	<div class="row">
		<div class="col">
			<div class="row">
				Id:
				<%=information.getInformationId()%>
			</div>
			<div class="row">
				Name:
				<%=information.getName()%>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<%=information.getText()%>
		</div>
		<div class="row">
			<form action="Administrator" method="post">
				<input type="hidden" name="id"
					value='<%=information.getInformationId()%>'> <input
					class="btn btn-primary" type="submit" name="deleteInformation"
					value="L&ouml;schen"
					onclick="return confirm('ACHTUNG! Alle mit dieser Information verbundenen Aufgaben werden gel&ouml;scht! Klicken Sie OK zum fortfahren.')">
				<input class="btn btn-primary" type="submit" name="editInformation"
					value="Bearbeiten">
			</form>
		</div>
	</div>
	<div class="seperator"></div>
	<%
		}
	%>
</div>
