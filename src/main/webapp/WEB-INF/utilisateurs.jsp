<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Utilisateurs</title>
</head>
<body>
	<h1>Utilisateurs</h1>
	<form>
	<input type="text" name="FILTRE_NOM" placeHolder="Filtrer par nom" value="${filtreNom}">
	<input type="submit" value="Filtrer">
	</form>
	<!-- On parcourt le contenu de la page d'utilisateurs -->
	<table width="50%">
		<tr>
			<td>Nom <a href="utilisateurs?sort=nom<c:if test="${filtreNom ne null && filtreNom ne ''}">&FILTRE_NOM=${filtreNom}</c:if>">Trier asc</a> <a href="utilisateurs?sort=nom,desc<c:if test="${filtreNom ne null && filtreNom ne ''}">&FILTRE_NOM=${filtreNom}</c:if>">Trier desc</a></td>
			<td>Prénom <a href="utilisateurs?sort=prenom<c:if test="${filtreNom ne null && filtreNom ne ''}">&FILTRE_NOM=${filtreNom}</c:if>">Trier asc</a> <a href="utilisateurs?sort=prenom,desc<c:if test="${filtreNom ne null && filtreNom ne ''}">&FILTRE_NOM=${filtreNom}</c:if>">Trier desc</a></td>
			<td>Type d'utilisateur</td>
		</tr>
	<c:forEach items="${pageDUtilisateurs.content}" var="utilisateur">
		<tr>
			<td>${utilisateur.nom}</td>
			<td>${utilisateur.prenom}</td>
			<td>${utilisateur.getClass().simpleName}</td>
		</tr>
	</c:forEach>
	</table>
	Utilisateurs de ${pageDUtilisateurs.numberOfElements * pageDUtilisateurs.number + 1} à ${pageDUtilisateurs.numberOfElements * (pageDUtilisateurs.number + 1)} sur ${pageDUtilisateurs.totalElements}
	<br><br>
	<!-- 	On teste s'il ne s'agit pas de la première page -->
	<c:if test="${ ! pageDUtilisateurs.first}">
	<a href="utilisateurs?page=0&sort=${pageDUtilisateurs.sort.iterator().next().property},${pageDUtilisateurs.sort.iterator().next().direction}<c:if test="${filtreNom ne null && filtreNom ne ''}">&FILTRE_NOM=${filtreNom}</c:if>">Première page</a>
	<a href="utilisateurs?page=${pageDUtilisateurs.number-1}&sort=${pageDUtilisateurs.sort.iterator().next().property},${pageDUtilisateurs.sort.iterator().next().direction}<c:if test="${filtreNom ne null && filtreNom ne ''}">&FILTRE_NOM=${filtreNom}</c:if>">Page précédente</a>
	</c:if>
	<!-- 	On teste s'il s'agit pas de la première page -->
	<c:if test="${ pageDUtilisateurs.first}">
	Première page
	Page précédente
	</c:if>
	<!-- 	On teste s'il ne s'agit pas de la dernière page -->
	<c:if test="${ ! pageDUtilisateurs.last}">
	<a href="utilisateurs?page=${pageDUtilisateurs.number+1}&sort=${pageDUtilisateurs.sort.iterator().next().property},${pageDUtilisateurs.sort.iterator().next().direction}<c:if test="${filtreNom ne null && filtreNom ne ''}">&FILTRE_NOM=${filtreNom}</c:if>">Page suivante</a>
	<a href="utilisateurs?page=${pageDUtilisateurs.totalPages - 1}&sort=${pageDUtilisateurs.sort.iterator().next().property},${pageDUtilisateurs.sort.iterator().next().direction}<c:if test="${filtreNom ne null && filtreNom ne ''}">&FILTRE_NOM=${filtreNom}</c:if>">Dernière page</a>
	</c:if>
</body>
</html>