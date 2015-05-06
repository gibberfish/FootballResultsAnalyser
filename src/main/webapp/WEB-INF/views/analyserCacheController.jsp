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
	
		<c:forEach var="seasonDivision" items="${seasonCacheEntry.value.seasonDivisionsInCache}">
			<h2>Division Name : <c:out value="${seasonDivision.division.divisionName}"/></h2>
			
			<c:set var="seasonDivisionCache" scope="session" value="${seasonCacheEntry.value.divisionCaches[seasonDivision]}"/>
			
			<c:forEach var="fixtureDate" items="${seasonDivisionCache.fixtureDates}">
				<h3><fmt:formatDate type="both" pattern="dd-MM-yyyy" value="${fixtureDate.time}"/></h3>
				
				<table>
				<tr>
					<th>Pos</th>
					<th>Team</th>
					<c:forEach var="attributeDefinition" items="${calculationMapFactory.attributeDefinitionList}">
						<th><c:out value="${attributeDefinition.shortDescription}"/></th>
					</c:forEach>					
				</tr>				
				
				<c:forEach var="tableRowEntry" items="${seasonDivisionCache.tablesForDivision[fixtureDate].sortedTable}" varStatus="loop">
				<tr>
					<td><c:out value="${loop.index+1}"/></td>
					<td><c:out value="${tableRowEntry.team.teamName}"/></td>
					<c:forEach var="attributeDefinition" items="${calculationMapFactory.attributeDefinitionList}">
						<td><c:out value="${tableRowEntry.getAttribute(attributeDefinition.attributeId)}"/></td>
					</c:forEach>					
					<td>(<c:out value="${seasonDivisionCache.teamFixtureContexts[fixtureDate][tableRowEntry.team].leaguePosition}"/>)
					
					
					
				</tr>					
				</c:forEach>
				
				</table>

				<c:forEach var="fixture" items="${seasonDivisionCache.fixturesForDivision[fixtureDate]}">
					<br>  <c:out value="${fixture}"/>
				</c:forEach>	

			</c:forEach>
		</c:forEach>
	</c:forEach>
</body>
</html>
