<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Student"%>
<jsp:useBean id="datamanager"
	class="de.uhd.ifi.se.quizapp.model.DataManager"></jsp:useBean>
<title>Lehrer/Innen Zugang</title>
<script type="text/javascript" src="release/jsc/jsComponents.js"></script>

<div class="container">
	<h1>Sch&uuml;ler/Innen-&Uuml;bersicht</h1>
	<table id="sortabletable"
		class="table-sm table-bordered tablesorter sortabletable">
		<thead>
			<tr>
				<th scope="row">Benutzername</th>
				<th scope="row">Vorname</th>
				<th scope="row">Nachname</th>
				<th scope="row">Details</th>
			</tr>
		</thead>
		<tbody>
			<%
				@SuppressWarnings("unchecked")
				List<Student> students = datamanager.getStudents();
				for (Student student : students) {
			%>

			<tr>
				<td data-th='UserName'><%=student.getUsername()%></td>
				<td data-th='Vorname'><%=student.getFirstname()%></td>
				<td data-th='Nachname'><%=student.getLastname()%></td>
				<td data-th='Details'><form action="Teacher" method="post">
						<input type="hidden" name="username"
							value="<%=student.getUsername()%>"> <input
							class="btn btn-primary" type="submit" name="showStudentsDetails"
							value="Details">
					</form></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>