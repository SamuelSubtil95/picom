<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Zones</title>
</head>
<body>
	<h1>Zones</h1>
<!-- On parcourt le contenu de la page de zones -->
		<c:forEach items="${pageDeZones.content}" var="zone">
			<li>${zone.nom}
			</li>
		</c:forEach>
		Zone de ${pageDeZones.numberOfElements * pageDeZones.number + 1} à ${pageDeZones.numberOfElements * (pageDeZones.number + 1)} sur ${pageDeZones.totalElements}
		<br><br>
		<a href="zones?page=0">Première page</a>
		<a href="zones?page=${pageDeZones.number-1}">Page précédente</a>
<a href="zones?page=${pageDeZones.number+1}">Page suivante</a>
<a href="zones?page=${pageDeZones.totalPages - 1}">Dernière page</a>
	<br><br>
	<a href="zone">Ajouter une nouvelle zone</a><br>
	<a href="arretAvecModelAttribute">Ajouter une nouvel arret</a>
</body>
</html>