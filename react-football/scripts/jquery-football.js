
var Football = {}; // namespace

$(function() {
    console.log( "Loaded JQuery Page" );
	
	Football.Controller.loadSeasonsFromServer();
});

Football.Controller =  {
	seasons: [],
	populateValuesInSeasonDropdown: function () {
		console.log("populateValuesInSeasonDropdown: " + this.seasons);
		
		this.seasons.map(function (season, index) {
			console.log("Adding option for season " + season.display);
			$('#seasonSelect').append('<option value="'+season.id+'">'+season.display+'</option>');
		});		
	},

	loadSeasonsFromServer: function() {
	   $.ajax({
		 url: "/seasons.json",
		 success: function(data) {
			console.log("Season Ajax call returned success: " + data);
		   
			Football.Controller.seasons = data;
			Football.Controller.populateValuesInSeasonDropdown ();
		 }
	   });
	}
};
