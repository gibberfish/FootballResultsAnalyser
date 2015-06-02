<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
	<!--
    <link rel="icon" href="../../favicon.ico">
	-->

    <title>Football Results Analyser</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="football-results-analyser.css" rel="stylesheet">
    <!--
	<link href="css\fra.css" rel="stylesheet">
	-->
	
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!--
	<script src="bootstrap/js/ie-emulation-modes-warning.js"></script>
	-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <div class="container">

      <div class="starter-template">
        <h1>Football Results Analyser</h1>
        <p class="lead">Full Season view (all divisions)</p>
      </div>

	  <div>






	<c:forEach var="seasonCacheEntry" items="${analyserCache.seasonCaches}">
		<h1>Season : <c:out value="${seasonCacheEntry.key}"/></h1>
	
		<c:forEach var="seasonDivision" items="${seasonCacheEntry.value.seasonDivisionsInCache}">
			<h2>Division Name : <c:out value="${seasonDivision.division.divisionName}"/></h2>
			
			<c:set var="seasonDivisionCache" scope="session" value="${seasonCacheEntry.value.divisionCaches[seasonDivision]}"/>
			
			<c:forEach var="fixtureDate" items="${seasonDivisionCache.fixtureDates}">
				<h3><fmt:formatDate type="both" pattern="dd-MM-yyyy" value="${fixtureDate.time}"/></h3>
				
				<table class="table table-striped">
				<thead>
				<tr>
					<th>Pos</th>
					<th>Team</th>
					<c:forEach var="attributeDefinition" items="${tableShapes.homeAwayTable}">
						<th><c:out value="${attributeDefinition.shortDescription}"/></th>
					</c:forEach>					
				</tr>
				</thead>				
				
				<tbody>
				<c:forEach var="tableRowEntry" items="${seasonDivisionCache.tablesForDivision[fixtureDate].sortedTable}" varStatus="loop">
				<tr>
					<td><c:out value="${loop.index+1}"/></td>
					<td><c:out value="${tableRowEntry.team.teamName}"/></td>
					<c:forEach var="attributeDefinition" items="${tableShapes.homeAwayTable}">
						<td><c:out value="${tableRowEntry.getAttribute(attributeDefinition.attributeId)}"/></td>
					</c:forEach>					
				</tr>					
				</c:forEach>
				</tbody>
				</table>

				<table class="table table-striped">
				<thead>
				<tr>
					<th>Pos</th>
					<th>Above?</th>
					<th>Home</th>
					<th>Score</th>
					<th>Away</th>
					<th>Above?</th>
					<th>Pos</th>
				</tr>
				</thead>				

				<tbody>
				<c:forEach var="fixture" items="${seasonDivisionCache.fixturesForDivision[fixtureDate]}">
					<tr>				
						<td><c:out value="${seasonDivisionCache.teamFixtureContexts[fixtureDate][fixture.homeTeam].leaguePosition}"/></td>
						<td><c:out value="${seasonDivisionCache.teamFixtureContexts[fixtureDate][fixture.homeTeam].playingTeamAbove}"/></td>
						<td><c:out value="${fixture.homeTeam.teamName}"/></td>
						<td><c:out value="${fixture.homeGoals}"/>-<c:out value="${fixture.awayGoals}"/></td>
						<td><c:out value="${fixture.awayTeam.teamName}"/></td>
						<td><c:out value="${seasonDivisionCache.teamFixtureContexts[fixtureDate][fixture.awayTeam].playingTeamAbove}"/></td>
						<td><c:out value="${seasonDivisionCache.teamFixtureContexts[fixtureDate][fixture.awayTeam].leaguePosition}"/></td>
					</tr>
				</c:forEach>	
				</tbody>
				</table>

			</c:forEach>
		</c:forEach>
	</c:forEach>
	  
		</div>	  
    </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="jquery-1.11.3.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js\fra.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!--
    <script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script>
	-->
  </body>
</html>
