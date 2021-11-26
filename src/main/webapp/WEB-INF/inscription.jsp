<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
<style>
.erreur {
	color: red;
}
</style>
</head>
<body>
<h1>Inscription</h1>
<!-- On injecte un objet métier dans le formulaire Spring MVC -->
<form:form modelAttribute="client" action="inscription" method="post">
	<form:label path="nom">Nom</form:label>
	<form:input path="nom" />
	<form:errors path="nom" cssClass="erreur" /><br>
	<form:label path="prenom">Prénom</form:label>
	<form:input path="prenom" />
	<form:errors path="prenom" cssClass="erreur" /><br>
	<form:label path="email">Email</form:label>
	<form:input path="email" />
	<form:errors path="email" cssClass="erreur" /><br>
	<form:label path="motDePasse">Mot de passe</form:label>
	<form:input type="password" path="motDePasse" />
	<form:errors path="motDePasse" cssClass="erreur" /><br>
	<form:label path="numeroDeTelephone">Numéro de téléphone</form:label>
	<form:input path="numeroDeTelephone" />
	<form:errors path="numeroDeTelephone" cssClass="erreur" /><br>
	<form:button type="submit">Inscription</form:button>
</form:form>
</body>
</html>