var fs = require('fs');

var express = require('express');
var app = express();

var seasonValues = [
		{id: "2010", display: "2010/2011"},
		{id: "1990", display: "1990/1991"},
		{id: "1980", display: "1981/1982"}
	];

var divisionValuesNow = [
		{id: "1", display: "Premier League"},
		{id: "2", display: "Championship"},
		{id: "3", display: "League 1"},
		{id: "4", display: "League 2"}
	];

var divisionValues1990 = [
		{id: "1", display: "Premier League"},
		{id: "2", display: "Division 1"},
		{id: "3", display: "Division 2"},
		{id: "4", display: "Division 3"}
	];

var divisionValues1980 = [
		{id: "1", display: "Division 1"},
		{id: "2", display: "Division 2"},
		{id: "3", display: "Division 3"},
		{id: "4", display: "Division 4"}
	];

var teamValues2010_1 = [
	{id: "1", display: "Portsmouth"},
	{id: "2", display: "Liverpool"},
	{id: "3", display: "Man United"},
];
var teamValues2010_2 = [
	{id: "1", display: "Leeds"},
	{id: "2", display: "Wigan"},
	{id: "3", display: "Bournemouth"},
];
var teamValues2010_3 = [
	{id: "1", display: "Bristol City"},
	{id: "2", display: "Bradford"},
	{id: "3", display: "Leyton Orient"},
];
var teamValues2010_4 = [
	{id: "1", display: "Crewe"},
	{id: "2", display: "Wrexham"},
	{id: "3", display: "Southampton"},
];
	
app.use('/', express.static(__dirname));
app.use(express.bodyParser());

app.get('/seasons.json', function(req, res) {
  res.setHeader('Content-Type', 'application/json');
  res.send(JSON.stringify(seasonValues));
});

app.get('/divisions.json', function(req, res) {
  var divUrl = req.url;
  var season = divUrl.substring(divUrl.indexOf('?season=')+8);
  
  console.log("Season = " + season);

  var returnValue = null;
  if (season == "2010") {
	returnValue = divisionValuesNow;
  } else if (season == "1980") {
	returnValue = divisionValues1980;
  } else if (season == "1990") {
	returnValue = divisionValues1990;
  }
  
  res.setHeader('Content-Type', 'application/json');
  res.send(JSON.stringify(returnValue));
});


app.get('/teams.json', function(req, res) {
  var divUrl = req.url;
  var division = divUrl.substring(divUrl.indexOf('?division=')+10);
  
  console.log("Division = " + division);

  var returnValue = null;
  if (division == "1") {
	returnValue = teamValues2010_1;
  } else if (division == "2") {
	returnValue = teamValues2010_2;
  } else if (division == "3") {
	returnValue = teamValues2010_3;
  } else if (division == "4") {
	returnValue = teamValues2010_4;
  }
  
  res.setHeader('Content-Type', 'application/json');
  res.send(JSON.stringify(returnValue));
});

app.get('/fixtures.json', function(req, res) {
  var divUrl = req.url;
  var team = divUrl.substring(divUrl.indexOf('?team=')+6);
  
  console.log("Team = " + team);

  var fixtures = [
	{id: "1", home: "Bristol Rovers", away: team, homeGoals: 1, awayGoals: 2, date: "20 Dec 2013"},
	{id: "2", home: team, away: "Blackburn Rovers", homeGoals: 1, awayGoals: 1, date: "28 Dec 2013"},
	{id: "3", home: "Wycombe Wanderers", away: team, homeGoals: 2, awayGoals: 1, date: "01 Jan 2014"},
	{id: "4", home: team, away: "Yeovil", homeGoals: 1, awayGoals: 3, date: "08 Jan 2014"}
  ];
    
  res.setHeader('Content-Type', 'application/json');
  res.send(JSON.stringify(fixtures));
});


app.listen(3000);