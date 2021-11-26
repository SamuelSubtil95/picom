<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Bienvenue sur PI'COM !</h1>
	${notification}
	<form action="connexion" method="post">
	<input type="email" name="EMAIL" placeHolder="Email" required><br>
	<input type="password" name="MOT_DE_PASSE" placeHolder="Mot de Passe" required><br>
	<input type="submit" value="Connexion">
</form>
<a href="inscription">Inscription</a>
</body>
</html>