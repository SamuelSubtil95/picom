<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Arret</title>
<style>
.erreur {
	color: red;
}
</style>
</head>
<body>
	<form:form modelAttribute="arret" action="arretAvecModelAttribute" method="post">
		<form:label path="nom">Nom</form:label>
		<form:input path="nom" />
		<form:errors path="nom" cssClass="erreur" />
		<br>
		<form:label path="zone">Nom</form:label>
		<form:select path="zone">
			<form:option value="">Merci de choisir une zone</form:option>
			<form:options items="${zones}" itemValue="id" itemLabel="nom"></form:options>
		</form:select>
		<form:errors path="zone" cssClass="erreur" />
		<form:button type="submit">Ajouter</form:button>
	</form:form>
</body>
</html>