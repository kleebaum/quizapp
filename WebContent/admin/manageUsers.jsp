<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@page import="de.uhd.ifi.se.quizapp.model.User"%>
<%@page import="java.util.ArrayList"%>

<jsp:useBean id="dataManager" scope="request"
	class="de.uhd.ifi.se.quizapp.model.DataManager"></jsp:useBean>


<%
	ArrayList<User> userList = dataManager.listAllUser();
%>

<div class="container">
	<table id="sortabletable"
		class="table-sm table-bordered tablesorter sortabletable">
		<thead>
			<tr>
				<th scope="row">Benutzername</th>
				<th scope="row">Vorname</th>
				<th scope="row">Nachname</th>
			</tr>
		</thead>
		<tbody>
			<%
				int IDcount = 0;

				for (User user : userList) {

					out.println("<tr>");
					out.println("<td>" + user.getUsername() + "</td>");
					out.println("<td>" + user.getFirstname() + "</td>");
					out.println("<td>" + user.getLastname() + "</td>");
			%>
			<td>
				<form action="Administrator" method="post">
					<input type="hidden" name="username"
						value="<%=user.getUsername()%>" /> <input type="submit"
						value="Löschen" name="deleteUser"
						onclick="return confirm('ACHTUNG! Sie löschen einen Benutzer dauerhaft! Klicken Sie OK zum fortfahren.')" />
				</form>
			</td>
			<td>
				<form action="Administrator" method="post">
					<input type="hidden" name="username"
						value="<%=user.getUsername()%>" /> <label
						for="adminrole<%=IDcount%>">AdministratorIn</label> <input
						id="adminrole<%=IDcount%>" type="radio" name="role"
						value="administrator"
						<%if (user.getRole().equals("administrator"))
					out.println("checked");%> />
					<label for="teacherrole<%=IDcount%>">LehrerIn</label> <input
						id="teacherrole<%=IDcount%>" type="radio" name="role"
						value="teacher"
						<%if (user.getRole().equals("teacher"))
					out.println("checked");%> />
					<label for="studentrole<%=IDcount%>">SchülerIn</label> <input
						id="studentrole<%=IDcount%>" type="radio" name="role"
						value="student"
						<%if (user.getRole().equals("student"))
					out.println("checked");%> />

					<input type="submit" value="Ändern" name="changeRole"
						onclick="return confirm('ACHTUNG! Sie ändern die Rolle eines Benutzers.')" />
				</form>

			</td>
			<%
				IDcount = IDcount + 1;
					out.println("</tr");
				}
			%>
		</tbody>
	</table>