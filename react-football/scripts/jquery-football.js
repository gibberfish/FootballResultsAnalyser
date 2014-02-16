
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
			Model.DataAccess.loadDivisionsFromServer (data[0].id, Football.Controller.receiveDivisionsUpdate);
		}
	},

	receiveDivisionsUpdate: function (data) {
		console.log("Received new list of division...");
		
		Football.Controller.divisions = data;
		Football.Controller.populateValuesInDivisionDropdown ();
			
		if (data.length > 0) {
			Model.DataAccess.loadTeamsFromServer (data[0].id, Football.Controller.receiveTeamsUpdate);
		}
	},
	
	receiveTeamsUpdate: function(data) {
		console.log("Received new list of teams...");
	   
		Football.Controller.teams = data;
		Football.Controller.populateValuesInTeamDropdown ();
		
		if (data.length > 0) {
			Model.DataAccess.loadFixturesFromServer (data[0].display, Football.Controller.receiveFixtures);
		}
	},

	receiveFixtures: function(data) {
		console.log("Received new list of fixtures...");
		   
		Football.Controller.fixtures = data;
		Football.Controller.populateFixtures ();
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
};
