
var Football = {}; // namespace

$(function() {
    console.log( "Loaded JQuery Page" );
	
	Football.Controller.loadSeasonsFromServer();
});

Football.Controller =  {
	seasons: [],
	populateValuesInSeasonDropdown: function () {
		console.log("populateValuesInSeasonDropdown: " + this.seasons);
		for(var season in this.seasons) {
			console.log("Adding option for season " + season);
			$('#seasonSelect').after('<option value="'+season.id+'">'+season.display+'</option>');
		}
	},

	loadSeasonsFromServer: function() {
	   $.ajax({
		 url: "/seasons.json",
		 success: function(data) {
			console.log("Season Ajax call returned success: " + data);
		   
			for (var key in data) {
				//if (data.hasOwnProperty(key)) {
					console.log("..key: " + key);
				//}
			}
		   
			Football.Controller.seasons = data;
			Football.Controller.populateValuesInSeasonDropdown ();
		 }
	   });
	}
};
