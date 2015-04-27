<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="css\fra.css">
<script src="js\jquery-1.8.2.js"></script>
<script src="js\fra.js"></script>
</head>

<body>
	<c:forEach var="seasonCacheEntry" items="${analyserCache.seasonCaches}">
		<h1>Season : <c:out value="${seasonCacheEntry.key}"/></h1>
	
		<c:forEach var="divisionCacheEntry" items="${seasonCacheEntry.value.divisionCaches}">
			<h2>Division Name : <c:out value="${divisionCacheEntry.key.division.divisionName}"/></h2>
			
			<h3>Tables</h3>
			<c:forEach var="tableCacheEntry" items="${divisionCacheEntry.value.tablesForDivision}">
				<h4>Date: <fmt:formatDate type="both" pattern="dd-MM-yyyy" value="${tableCacheEntry.key.time}"/></h4>
				
				<table>
				<tr>
					<th>Pos</th>
					<th>Team</th>
					<c:forEach var="attributeDefinition" items="${calculationMapFactory.attributeDefinitionList}">
						<th><c:out value="${attributeDefinition.shortDescription}"/></th>
					</c:forEach>					
				</tr>				
				
				<c:forEach var="tableRowEntry" items="${tableCacheEntry.value.sortedTable}" varStatus="loop">
				<tr>
					<td><c:out value="${loop.index+1}"/></td>
					<td><c:out value="${tableRowEntry.team.teamName}"/></td>
					<c:forEach var="attributeDefinition" items="${calculationMapFactory.attributeDefinitionList}">
						<td><c:out value="${tableRowEntry.getAttribute(attributeDefinition.attributeId)}"/></td>
					</c:forEach>
				</tr>					
				</c:forEach>
				
				</table>
			</c:forEach>
			
			<h3>Fixtures</h3>
			<c:forEach var="fixtureCacheEntry" items="${divisionCacheEntry.value.fixturesForDivision}">
				<h4>Fixture Date: <fmt:formatDate type="both" pattern="dd-MM-yyyy" value="${fixtureCacheEntry.key.time}"/></h4>
				
				<c:forEach var="fixture" items="${fixtureCacheEntry.value}">
					<br>  <c:out value="${fixture}"/>
				</c:forEach>
			</c:forEach>	
	
		</c:forEach>
	</c:forEach>
</body>
</html>
