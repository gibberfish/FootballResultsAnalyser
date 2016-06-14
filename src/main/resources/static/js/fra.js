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
		var data = {};
		data.ssn = season;
		data.div = division;
		data.team = team;
		$.getJSON ("getFixturesForTeamInSeason.html", data, function(data) {
			$.each(data.fixtures, function (index,fixture) {
				$("#fixturesTable tbody").append("<tr><td>"+fixture.date+"</td><td>"+fixture.div+"</td><td>"+fixture.homeTeam+"</td><td>"+fixture.score+"</td><td>"+fixture.awayTeam+"</td></tr>");
			});
		});
	}
	
	function populateDivisionDropdown(season) {
		var url = "getDivisionsForSeason.html?ssn=" + season;
	
		$.getJSON (url, function(data) {
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
