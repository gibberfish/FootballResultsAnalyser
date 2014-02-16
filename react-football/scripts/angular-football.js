
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

		$scope.receiveSeasonsUpdate = function(data) {
			console.log("Received new list of seasons...");
			
			$scope.seasons = data;
			if (data.length > 0) {
				$scope.selectedSeason = data[0].id;
			}
			$scope.$apply(); // Needed to force Angular to respond to the change in model
		},
		
		$scope.receiveDivisionsUpdate = function (data) {
			$scope.divisions = data;
			
			if(data.length > 0) {
				$scope.selectedDivision = data[0].id;
			}
			$scope.$apply(); // Needed to force Angular to respond to the change in model
		};

		$scope.receiveTeamsUpdate = function (data) {
			$scope.teams = data;
			
			if(data.length > 0) {
				$scope.selectedTeam = data[0].display;
			}
			$scope.$apply(); // Needed to force Angular to respond to the change in model
		};
		
		$scope.receiveFixtures = function (data) {
			$scope.fixtures = data;
			$scope.$apply(); // Needed to force Angular to respond to the change in model
		};
		
		$scope.$watch("selectedSeason", function(newValue, oldValue) {
			console.log("Changed selected season: " + newValue);			
			Model.DataAccess.loadDivisionsFromServer(newValue, $scope.receiveDivisionsUpdate);
		});
		
		$scope.$watch("selectedDivision", function(newValue, oldValue) {
			console.log("Changed selected division: " + newValue);
			Model.DataAccess.loadTeamsFromServer(newValue, $scope.receiveTeamsUpdate);
		});
		
		$scope.$watch("selectedTeam", function(newValue, oldValue) {
			console.log("Changed selected team: " + newValue);
			Model.DataAccess.loadFixturesFromServer(newValue, $scope.receiveFixtures);
		});
		
		// Initialise the model
		Model.DataAccess.loadSeasonsFromServer($scope.receiveSeasonsUpdate);
	}
]);
