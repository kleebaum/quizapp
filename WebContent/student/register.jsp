<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>Registrieren</title>

<div class="container">
	<form class="form-signin" id='registerform' action=Student
		method="post">
		<h2 align="center" class="form-singin-heading">Registrieren</h2>

		<input class="form-control" type="text" placeholder="Benutzername"
			name="username" id="username1" required> <input
			class="form-control" type="text" placeholder="Vorname"
			name="firstname" id="username2" required> <input
			class="form-control" type="text" placeholder="Nachname"
			name="lastname" id="username3" required> <input
			class="form-control" type="password" placeholder="Passwort"
			name="password" id="password1" required> <input
			class="form-control" type="password"
			placeholder="Passwort wiederholen" name="password2" id="password2"
			required>
		<div align="center">
			<input class="btn btn-primary" type="submit" value="Registrieren"
				name="register">
		</div>
	</form>
</div>