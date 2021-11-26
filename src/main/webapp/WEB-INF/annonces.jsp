<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Bienvenue ${sessionScope.utilisateur.prenom} !</h1>
<a href="deconnexion">Se déconnecter</a>
<h2>Voici vos annonces en cours</h2>
<table>
	<tr>
		<td>Image</td>
		<td>Date création</td>
		<td>Date début</td>
		<td>Date fin</td>
		<td>Zone(s)</td>
		<td>Actions</td>
	</tr>
	<c:forEach items="${pageDAnnonces.content}" var="annonce">
	<tr>
		<td><img src="images/${annonce.id}" height="200px"></td>
		<td><fmt:formatDate pattern="dd/MM/yyyy" value="${annonce.dateCreation}"/></td>
		<td><fmt:formatDate pattern="dd/MM/yyyy" value="${annonce.dateDebut}"/></td>
		<td><fmt:formatDate pattern="dd/MM/yyyy" value="${annonce.dateFin}"/></td>
		<td><c:forEach items="${annonce.zones}" var="zone">
				${zone.nom}<br>
			</c:forEach>
		</td>
		<td><a href="annonceImage?ID=${annonce.id}">Modifier</a>
		<a href="televersementImage?ID=${annonce.id}">Téléverser l'image</a>
		<a href="suppressionAnnonce?ID=${annonce.id}">Supprimer</a>
	</tr>
</c:forEach>
</table>
<a href="annonceImage">Ajouter une annonce image</a>
</body>
</html>