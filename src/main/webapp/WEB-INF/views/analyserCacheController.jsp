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
			<h2>Division Name : <c:out value="${divisionCacheEntry.key.divisionName}"/></h2>
			
			<h3>Tables</h3>
			<c:forEach var="tableCacheEntry" items="${divisionCacheEntry.value.tablesForDivision}">
				<h4>Date: <fmt:formatDate type="both" pattern="dd-MM-yyyy" value="${tableCacheEntry.key.time}"/></h4>
				
				<c:forEach var="tableRowEntry" items="${tableCacheEntry.value.sortedTable}">
					<br>  Team: <c:out value="${tableRowEntry.team.teamName}"/>
					
					<c:forEach var="rawCalc" items="${calculationMapFactory.rawCalculationClassMap}">
						<br><c:out value="${rawCalc.key}"/> = <c:out value="${tableRowEntry.getAttribute(rawCalc.key)}"/>
					</c:forEach>

					<c:forEach var="derivedCalc" items="${calculationMapFactory.derivedCalculationClassMap}">
						<br><c:out value="${derivedCalc.key}"/> = <c:out value="${tableRowEntry.getAttribute(derivedCalc.key)}"/>
					</c:forEach>
					
				</c:forEach>
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
