$(function(){
	//alert("Hello!");
	$("#selectSeason").attr("disabled", false);
	$("#selectDivision").attr("disabled", true);
	$("#selectTeam").attr("disabled", true);
	
	$("#selectSeason").change(function(e) {
		resetDivision();
		resetTeam();
		resetTable();

		var season = $("#selectSeason").val();
		if (season != '..select') {
			populateDivisionDropdown(season);
			$("#selectDivision").attr("disabled", false);
		}
	});
	
	$("#selectDivision").change(function(e) {
		resetTeam();
		resetTable();

		var season = $("#selectSeason").val();
		var division = $("#selectDivision").val();
		if (division != '..select') {
			populateTeamDropdown(season, division);
			$("#selectTeam").attr("disabled", false);
		}
	});

	$("#selectTeam").change(function(e) {
		resetTable();

		var season = $("#selectSeason").val();
		var division = $("#selectDivision").val();
		var team = $("#selectTeam").val();
		if (team != '..select') {
			populateTableFor(season, division, team);
		}
	});
	
	function resetDivision () {
		$("#selectDivision").empty();
		$("#selectDivision").append("<option>..select</option>");
		$("#selectDivision").attr("disabled", true).val("..select");
	}
	
	function resetTeam () {
		//$("#selectTeam").empty();
		$("#selectTeam").html("<option>..select</option>");
		$("#selectTeam").attr("disabled", true).val("..select");
	}

	function resetTable () {
		$("#fixtures").find("tr:gt(0)").remove();
	}
	
	function populateTableFor(season, division, team) {
		resetTable();
		if (team == 'Portsmouth') {
			$("#fixturesTable tbody").append("<tr><td>21/08/2012</td><td>League 1</td><td>Portsmouth</td><td>1-0</td><td>Walsall</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>29/08/2012</td><td>League 1</td><td>Leeds</td><td>1-3</td><td>Portsmouth</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>07/09/2012</td><td>League 1</td><td>Portsmouth</td><td>0-0</td><td>Liverpool</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>15/09/2012</td><td>League 1</td><td>Portsmouth</td><td>2-1</td><td>Scunthorpe</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>21/09/2012</td><td>League 1</td><td>Wigan</td><td>1-0</td><td>Portsmouth</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>28/09/2012</td><td>League 1</td><td>Portsmouth</td><td>5-0</td><td>Southampton</td></tr>");
		} else if (team == 'Leeds') {
			$("#fixturesTable tbody").append("<tr><td>21/08/2012</td><td>League 1</td><td>Leeds</td><td>1-0</td><td>Walsall</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>29/08/2012</td><td>League 1</td><td>Leeds</td><td>1-3</td><td>Portsmouth</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>07/09/2012</td><td>League 1</td><td>Leeds</td><td>0-0</td><td>Liverpool</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>15/09/2012</td><td>League 1</td><td>Leeds</td><td>2-1</td><td>Scunthorpe</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>21/09/2012</td><td>League 1</td><td>Wigan</td><td>1-0</td><td>Leeds</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>28/09/2012</td><td>League 1</td><td>Leeds</td><td>5-0</td><td>Southampton</td></tr>");
		} else if (team == 'Millwall') {
			$("#fixturesTable tbody").append("<tr><td>21/08/2012</td><td>League 1</td><td>Millwall</td><td>1-0</td><td>Walsall</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>29/08/2012</td><td>League 1</td><td>Millwall</td><td>1-3</td><td>Portsmouth</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>07/09/2012</td><td>League 1</td><td>Millwall</td><td>0-0</td><td>Liverpool</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>15/09/2012</td><td>League 1</td><td>Millwall</td><td>2-1</td><td>Scunthorpe</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>21/09/2012</td><td>League 1</td><td>Wigan</td><td>1-0</td><td>Millwall</td></tr>");
			$("#fixturesTable tbody").append("<tr><td>28/09/2012</td><td>League 1</td><td>Millwall</td><td>5-0</td><td>Southampton</td></tr>");
		}
	}
	
	function populateDivisionDropdown(season) {
		$.getJSON ("getDivisionsForSeason.html", "ssn="+season, function(data) {
			$.each(data.divisions, function (index,division) {
				$("#selectDivision").append("<option value=\""+ division.id +"\">" + division.name + "</option>");
			});
		});
	}
	
	function populateTeamDropdown(season, division) {
		var data = {};
		data.ssn = season;
		data.div = division;
		$.getJSON ("getTeamsForDivision.html", data, function(data) {
			$.each(data.teams, function (index,team) {
				$("#selectTeam").append("<option value=\""+ team.id +"\">" + team.name + "</option>");
			});
		});
	}
});
