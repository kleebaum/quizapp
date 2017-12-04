<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container">
	<form class="form-signin" id="registerform" action="Administrator"
		method="post">
		<h2 class="form-singin-heading">Login</h2>
		<label class="sr-only" for="inputUsername">Username</label> <input
			class="form-control" id="inputUsername" type="text"
			placeholder="Benutzername" name="username" required> <label
			class="sr-only" for="inputPassword">Password</label> <input
			class="form-control" id="inputPassword" type="password"
			placeholder="Passwort" name="password" required>
		<div align="center">
			<input class="btn btn-primary" type="submit" value="Login"
				name="login">
		</div>
	</form>
</div>