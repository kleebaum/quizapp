<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="dataManager"
	class="de.uhd.ifi.se.quizapp.model.DataManager"></jsp:useBean>
<div class="container">
	<h1>Einstellungen und generelle Verwaltung</h1>
	<br> <br>
	<div class="row">
		<div class="col">
			<form id="resetDatabaseForm" action="Administrator" method="post">
				<input class="btn btn-secondary" type="submit" name="resetDatabase"
					value="Datenbankinhalt l&ouml;schen"
					onclick="return confirm('ACHTUNG! Alle Inhalte der Datenbank werden gel&ouml;scht! Klicken Sie OK zum fortfahren.')">
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<form>
				<a class="btn btn-secondary"
					href="http://heieducation.ifi.uni-heidelberg.de:8080/db/heieducation.sqlite"
					role="button"> Datenbank downloaden </a>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<form id="uploadForm" method="post" action="Administrator"
				enctype="multipart/form-data">
				W&auml;hle eine Datenbank zum Hochladen aus: <input
					class="btn btn-primary" accept=".sqlite" type="file"
					name="dataFile" id="fileChooser" /> <input class="btn btn-primary"
					type="submit" value="Upload" />
			</form>
		</div>
	</div>
</div>
<br>
<br>
<h1>Einstellungen des Administrator Accounts</h1>
<br>
<br>
<div class="row">
	<div class="col">
		<form id="changeAdministratorPasswordForm" action="Administrator"
			method="post">
			<label class="sr-only" for="password">Password</label> <input
				class="form-control" id="password" type="password"
				placeholder="Neues Passwort" name="password" required> <input
				class="btn btn-primary" type="submit"
				value="Neues Password Speichern" name="changeAdministratorPassword">
		</form>
	</div>
</div>

<script>
	$("#uploadForm").submit(function() {
		if ($("#fileChooser").val() == "") {
			alert("Wählen Sie eine Datenbank aus.");
			return false;
		}

	});
</script>