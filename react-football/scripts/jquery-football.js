
var Football = {}; // namespace

$(function() {	
	Model.DataAccess.loadSeasonsFromServer(Football.Controller.receiveSeasonsUpdate);
});

Football.Controller =  {
	seasons: [],
	divisions: [],
	teams: [],
	fixtures: [],

	receiveSeasonsUpdate: function(data) {		   
		Football.Controller.seasons = data;
		Football.Controller.populateValuesInSeasonDropdown ();
			
		if (data.length > 0) {
			Model.DataAccess.loadDivisionsFromServer (data[0].id, Football.Controller.receiveDivisionsUpdate);
		}
	},

	receiveDivisionsUpdate: function (data) {
		Football.Controller.divisions = data;
		Football.Controller.populateValuesInDivisionDropdown ();
			
		if (data.length > 0) {
			Model.DataAccess.loadTeamsFromServer (data[0].id, Football.Controller.receiveTeamsUpdate);
		}
	},
	
	receiveTeamsUpdate: function(data) {
		Football.Controller.teams = data;
		Football.Controller.populateValuesInTeamDropdown ();
		
		if (data.length > 0) {
			Model.DataAccess.loadFixturesFromServer (data[0].display, Football.Controller.receiveFixtures);
		}
	},

	receiveFixtures: function(data) {
		Football.Controller.fixtures = data;
		Football.Controller.populateFixtures ();
	},
	
	populateValuesInSeasonDropdown: function () {
		$('#seasonSelect option').remove();
		this.seasons.map(function (season, index) {
			$('#seasonSelect').append('<option value="'+season.id+'">'+season.display+'</option>');
		});		
	},

	populateValuesInDivisionDropdown: function () {		
		$('#divisionSelect option').remove();
		this.divisions.map(function (division, index) {
			$('#divisionSelect').append('<option value="'+division.id+'">'+division.display+'</option>');
		});		
	},

	populateValuesInTeamDropdown: function () {
		$('#teamSelect option').remove();		
		this.teams.map(function (team, index) {
			$('#teamSelect').append('<option value="'+team.id+'">'+team.display+'</option>');
		});		
	},

	populateFixtures: function () {		
		$('#fixtureTable .fixtureRow').remove();
		this.fixtures.map(function (fixture, index) {			
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
