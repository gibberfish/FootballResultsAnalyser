
var Football = {}; // namespace

$(function() {
    console.log( "Loaded JQuery Page" );
	
	Model.DataAccess.loadSeasonsFromServer(Football.Controller.receiveSeasonsUpdate);
});

Football.Controller =  {
	seasons: [],
	divisions: [],
	teams: [],
	fixtures: [],

	receiveSeasonsUpdate: function(data) {
		console.log("Received new list of seasons...");
		   
		Football.Controller.seasons = data;
		Football.Controller.populateValuesInSeasonDropdown ();
			
		if (data.length > 0) {
			Football.Controller.loadDivisionsFromServer (data[0].id);
		}
	},

	receiveDivisionsUpdate: function (data) {
		console.log("Received new list of division...");
		
		Football.Controller.divisions = data;
		Football.Controller.populateValuesInDivisionDropdown ();
			
		if (data.length > 0) {
			Football.Controller.loadTeamsFromServer (data[0].id);
		}
	},
	
	populateValuesInSeasonDropdown: function () {
		console.log("populateValuesInSeasonDropdown: " + this.seasons);
		
		$('#seasonSelect option').remove();
		this.seasons.map(function (season, index) {
			console.log("Adding option for season " + season.display);
			$('#seasonSelect').append('<option value="'+season.id+'">'+season.display+'</option>');
		});		
	},

	populateValuesInDivisionDropdown: function () {
		console.log("populateValuesInDivisionDropdown: " + this.divisions);
		
		$('#divisionSelect option').remove();
		this.divisions.map(function (division, index) {
			console.log("Adding option for division " + division.display);
			$('#divisionSelect').append('<option value="'+division.id+'">'+division.display+'</option>');
		});		
	},

	populateValuesInTeamDropdown: function () {
		console.log("populateValuesInTeamDropdown: " + this.teams);

		$('#teamSelect option').remove();		
		this.teams.map(function (team, index) {
			console.log("Adding option for team " + team.display);
			$('#teamSelect').append('<option value="'+team.id+'">'+team.display+'</option>');
		});		
	},

	populateFixtures: function () {
		console.log("populateFixtures");
		
		$('#fixtureTable .fixtureRow').remove();
		this.fixtures.map(function (fixture, index) {
			console.log("Adding option for fixture " + fixture);
			
			var fixtureRow = '<tr class="fixtureRow">' +
				'<td class="date">'+fixture.date+'</td>' + 
				'<td class="home">'+fixture.home+'</td>' +
				'<td class="score">'+fixture.homeGoals+'</td>' +
				'<td class="versus">-</td>' +
				'<td class="score">'+fixture.awayGoals+'</td>' +
				'<td class="away">'+fixture.away+'</td>' + 
				'</tr>';
						
			$('#fixtureTable').append(fixtureRow);
		});		
	},
	
	loadDivisionsFromServer: function(season) {
	   $.ajax({
		 url: "/divisions.json?season="+season,
		 success: function(data) {
			console.log("Division Ajax call returned success: " + data);
		   
			Football.Controller.divisions = data;
			Football.Controller.populateValuesInDivisionDropdown ();
			
			if (data.length > 0) {
				Football.Controller.loadTeamsFromServer (data[0].id);
			}
		 }
	   });
	},
	
	loadTeamsFromServer: function(division) {
	   $.ajax({
		 url: "/teams.json?division="+division,
		 success: function(data) {
			console.log("Team Ajax call returned success: " + data);
		   
			Football.Controller.teams = data;
			Football.Controller.populateValuesInTeamDropdown ();
			
			if (data.length > 0) {
				Football.Controller.loadFixturesFromServer (data[0].display);
			}
		 }
	   });
	},
	
	loadFixturesFromServer: function(team) {
	   $.ajax({
		 url: "/fixtures.json?team="+team,
		 success: function(data) {
			console.log("Fixture Ajax call returned success: " + data);
		   
			Football.Controller.fixtures = data;
			Football.Controller.populateFixtures ();
		 }
	   });
	}

};
