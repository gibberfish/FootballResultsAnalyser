var fs = require('fs');

var express = require('express');
var app = express();

var seasonValues = [
		{id: "2010", display: "2010/2011"},
		{id: "2011", display: "2011/2012"},
		{id: "2012", display: "2012/2013"},
		{id: "2013", display: "2013/2014"}
	];

var divisionValues2010 = [
		{id: "1", display: "Premier League1"},
		{id: "2", display: "Champtionship1"},
		{id: "3", display: "League 11"},
		{id: "4", display: "League 21"}
	];

var divisionValues2011 = [
		{id: "1", display: "Premier League2"},
		{id: "2", display: "Champtionship2"},
		{id: "3", display: "League 12"},
		{id: "4", display: "League 22"}
	];

var divisionValues2012 = [
		{id: "1", display: "Premier League3"},
		{id: "2", display: "Champtionship3"},
		{id: "3", display: "League 13"},
		{id: "4", display: "League 23"}
	];

var divisionValues2013 = [
		{id: "1", display: "Premier League4"},
		{id: "2", display: "Champtionship4"},
		{id: "3", display: "League 14"},
		{id: "4", display: "League 24"}
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
	returnValue = divisionValues2010;
  } else if (season == "2011") {
	returnValue = divisionValues2011;
  } else if (season == "2012") {
	returnValue = divisionValues2012;
  } else if (season == "2013") {
	returnValue = divisionValues2013;
  }
  
  res.setHeader('Content-Type', 'application/json');
  res.send(JSON.stringify(returnValue));
});

app.listen(3000);