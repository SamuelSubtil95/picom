<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.erreur {
	color: red;
}
</style>
</head>
<body>
	<form:form modelAttribute="annonceImage" action="annonceImage" method="post">
		<form:label path="dateDebut">Date de début de diffusion</form:label>
		<form:input path="dateDebut" type="date"/>
		<form:errors path="dateDebut" cssClass="erreur" />
		<br>
		<form:label path="dateFin">Date de fin de diffusion</form:label>
		<form:input path="dateFin" type="date"/>
		<form:errors path="dateFin" cssClass="erreur" />
		<br>
		<form:label path="zones">Zone(s)</form:label>
		<form:select path="zones" multiple="multiple">
			<form:option value="">Merci de choisir une ou plusieurs zones</form:option>
			<form:options items="${zones}" itemValue="id" itemLabel="nom"></form:options>
		</form:select>
		<form:errors path="zones" cssClass="erreur" />
		<form:button type="submit">Enregistrer</form:button>
	</form:form>
</body>
</html>