<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>Login</title>
<div class="container">
	<form class="form-signin" id="registerform" action="Student"
		method="post">
		<h2 align="center" class="form-singin-heading">Login</h2>

		<label class="sr-only" for="inputUsername">Username</label> <input
			class="form-control" type="text" id="inputUsername"
			placeholder="Benutzername" name="username" required> <label
			class="sr-only" for="inputPassword">Password</label> <input
			class="form-control" type="password" id="inputPassword"
			placeholder="Passwort" name="password" required>
		<div align="center">
			<input class="btn btn-primary" type="submit" value="Login"
				name="login">
		</div>
	</form>
</div>