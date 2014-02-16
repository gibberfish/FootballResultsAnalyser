
var Model = {};

Model.DataAccess =  {
	seasons: [],
	divisions: [],
	teams: [],
	fixtures: [],
	
	loadSeasonsFromServer: function(callBack) {
	   $.ajax({
		 url: "/seasons.json",
		 success: function(data) {
			console.log("Season Ajax call returned success: " + data);
			callBack(data);		   
		 }
	   });
	},
	
	loadDivisionsFromServer: function(season, callBack) {
	   $.ajax({
		 url: "/divisions.json?season="+season,
		 success: function(data) {
			console.log("Division Ajax call returned success: " + data);
			callBack(data);		   
		 }
	   });
	},
	
	loadTeamsFromServer: function(division, callBack) {
	   $.ajax({
		 url: "/teams.json?division="+division,
		 success: function(data) {
			console.log("Team Ajax call returned success: " + data);
			callBack(data);		   
		 }
	   });
	},
	
	loadFixturesFromServer: function(team, callBack) {
	   $.ajax({
		 url: "/fixtures.json?team="+team,
		 success: function(data) {
			console.log("Fixture Ajax call returned success: " + data);
			callBack(data);		   
		 }
	   });
	}
};
