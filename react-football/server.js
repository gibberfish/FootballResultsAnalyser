var fs = require('fs');

var express = require('express');
var app = express();

var seasonValues = [
		{id: "2010", display: "2010/2011"},
		{id: "2011", display: "2011/2012"},
		{id: "2012", display: "2012/2013"},
		{id: "2013", display: "2013/2014"}
	];

var divisionValues = [
		{id: "1", display: "Premier League"},
		{id: "2", display: "Champtionship"},
		{id: "3", display: "League 1"},
		{id: "4", display: "League 2"}
	];
	
app.use('/', express.static(__dirname));
app.use(express.bodyParser());

app.get('/seasons.json', function(req, res) {
  res.setHeader('Content-Type', 'application/json');
  res.send(JSON.stringify(seasonValues));
});

app.get('/divisions.json', function(req, res) {
  res.setHeader('Content-Type', 'application/json');
  res.send(JSON.stringify(divisionValues));
});

app.listen(3000);