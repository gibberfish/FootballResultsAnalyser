<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="css\fra.css">
<script src="js\jquery-1.8.2.js"></script>
<script src="js\fra.js"></script>
</head>

<body>
	LETS GET FIXTURES FOR A TEAM
	<p>
		Season: <select id="selectSeason">
			<option>..select</option>
			<c:forEach var="season" items="${seasons}">
				<option>
					<c:out value='${season.ssnNum}' />
				</option>
			</c:forEach>
		</select>
	<p>
		Division: <select id="selectDivision">
		</select>
	<p>
		Team: <select id="selectTeam">
		</select>
	<p>Fixtures:
	<div id="fixtures">
		<table id="fixturesTable">
			<thead>
				<tr>
					<th>Date</th>
					<th>Division</th>
					<th>Home Team</th>
					<th>Score</th>
					<th>Away Team</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</body>
</html>
