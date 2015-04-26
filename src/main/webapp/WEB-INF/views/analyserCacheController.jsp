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
	ANALYSER CACHE CONTENTS...
	<p>
	Number of seasons: <c:out value="${analyserCache.seasonCaches.size}"/>


	<c:forEach var="seasonCacheEntry" items="${analyserCache.seasonCaches}">
		<br>Season : <c:out value="${seasonCacheEntry.key}"/>
	
		<c:forEach var="divisionCacheEntry" items="${seasonCacheEntry.value.divisionCaches}">
			<br>Division : <c:out value="${divisionCacheEntry.key}"/>
			
			<c:forEach var="tableCacheEntry" items="${divisionCacheEntry.value.tablesForDivision}">
				<br>Date: <fmt:formatDate type="both" dateStyle="short" value="${tableCacheEntry.key.time}"/>
				
				<c:forEach var="tableRowEntry" items="${tableCacheEntry.value.sortedTable}">
					<br>  Team: <c:out value="${tableRowEntry.team.teamName}"/>
				</c:forEach>
			</c:forEach>	
		</c:forEach>
	</c:forEach>
</body>
</html>
