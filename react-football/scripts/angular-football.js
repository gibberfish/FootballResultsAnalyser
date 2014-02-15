
var footballApp = angular.module('footballApp',[]);

footballApp.controller ('ShowFixturesCtrl', ['$scope','$http',
	function($scope, $http, selectedSeason) {
		$scope.seasons = [];
		$scope.divisions = [];
		$scope.teams = [];
		$scope.fixtures = [];
		
		$scope.selectedSeason = 0;
		$scope.selectedDivision = 0;
		$scope.selectedTeam = 0;
		
		$scope.getSeasons = function($http) {		
			$http.get('/seasons.json').success(function(data) {
				$scope.seasons = data;
				if (data.length > 0) {
					$scope.selectedSeason = data[0].id;
				}
			});
		};
		
		$scope.getDivisions = function($http, seasonId) {
			$http.get('/divisions.json?season='+seasonId).success(function(data) {
				$scope.divisions = data;
				
				if(data.length > 0) {
					$scope.selectedDivision = data[0].id;
				}
			});	
		};
		
		$scope.getTeams = function($http, divisionId) {
			$http.get('/teams.json?division='+divisionId).success(function(data) {
				$scope.teams = data;
				
				if(data.length > 0) {
					$scope.selectedTeam = data[0].display;
				}
			});	
		};

		$scope.getFixtures = function($http, teamId) {
			$http.get('/fixtures.json?team='+teamId).success(function(data) {
				$scope.fixtures = data;
			});	
		};
		
		$scope.getSeasons ($http);

		
		$scope.$watch("selectedSeason", function(newValue, oldValue) {
			console.log("Changed selected season: " + newValue);
			
			$scope.getDivisions ($http, newValue);
		});
		
		$scope.$watch("selectedDivision", function(newValue, oldValue) {
			console.log("Changed selected division: " + newValue);
			
			$scope.getTeams ($http, newValue);
		});
		
		$scope.$watch("selectedTeam", function(newValue, oldValue) {
			console.log("Changed selected team: " + newValue);
			
			$scope.getFixtures ($http, newValue);
		});
	}
]);
