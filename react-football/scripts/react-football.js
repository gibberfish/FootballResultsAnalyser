/** @jsx React.DOM */

var SelectOption = React.createClass({
	render: function () {
		return (<option value={this.props.selectOption.id}>{this.props.selectOption.display}</option>);
	}
});

var Season = React.createClass({
  getInitialState: function() {
    return {data: []};
  },
  loadSeasonsFromServer: function() {
   $.ajax({
     url: "/seasons.json",
     success: function(data) {
	   console.log("Season Ajax call returned success");
       this.setState({data: data});
     }.bind(this)
   });
  },
  componentWillMount: function() {
	console.log("Season componentWillMount");
    this.loadSeasonsFromServer();
  },
  componentDidUpdate: function (prevProps, prevState, rootNode) {
	console.log("Season componentDidUpdate");
	var newSeason = this.refs.seasonSelection.getDOMNode().value.trim();
	console.log("...newSeason = " + newSeason);
	React.renderComponent(
		<Division season={newSeason} />, document.getElementById('division')
	);
  },
  changeSeason: function () {
	var newSeason = this.refs.seasonSelection.getDOMNode().value.trim();
	console.log("Changing Season: " + newSeason);
	
	React.renderComponent(
		<Division season={newSeason} />, document.getElementById('division')
	);
  },
  render: function() {
    console.log("Render Season: " + this.state.data.length);
  
	var seasonRows = this.state.data.map(function (selectOption, index) {
	  return <SelectOption selectOption={selectOption} />;
	});
	return (<div><span className="label">Season</span><span className="selector"><select onChange={this.changeSeason} ref="seasonSelection">{seasonRows}</select></span></div>);
  }
});


var Division = React.createClass({
  getInitialState: function() {
    return {data: []};
  },
  loadDivisionsFromServer: function(season) {
    $.ajax({
      url: "/divisions.json?season="+season,
      success: function(data) {
        this.setState({data: data});
      }.bind(this)
    });
  },
  componentWillMount: function() {
    this.loadDivisionsFromServer(this.props.season);
  },
  componentWillReceiveProps: function(nextProps) {
    this.loadDivisionsFromServer(nextProps.season);
  },
  
  
  componentDidUpdate: function (prevProps, prevState, rootNode) {
	console.log("Division componentDidUpdate");
	var newDivision = this.refs.divisionSelection.getDOMNode().value.trim();
	console.log("...newDivision = " + newDivision);
	React.renderComponent(
		<Team division={newDivision} />, document.getElementById('team')
	);
  },
  changeDivision: function () {
	var newDivision = this.refs.divisionSelection.getDOMNode().value.trim();
	console.log("Changing Division: " + newDivision);
	
	React.renderComponent(
		<Team division={newDivision} />, document.getElementById('team')
	);
  },
  render: function() {
	
	console.log("Render Division: " + this.props.season + ", " + this.state.data);
	
	var divisionRows = this.state.data.map(function (selectOption, index) {
	  return <SelectOption selectOption={selectOption} />;
	});
	return (<div><span className="label">Division</span><span className="selector"><select onChange={this.changeDivision} ref="divisionSelection">{divisionRows}</select></span></div>);
  }
});

var Team = React.createClass({
    getInitialState: function() {
      return {data: []};
    },
  loadTeamsFromServer: function(division) {
    $.ajax({
      url: "/teams.json?division="+division,
      success: function(data) {
        this.setState({data: data});
      }.bind(this)
    });
  },
  componentWillMount: function() {
    this.loadTeamsFromServer(this.props.division);
  },
  componentWillReceiveProps: function(nextProps) {
    this.loadTeamsFromServer(nextProps.division);
  },
  componentDidUpdate: function (prevProps, prevState, rootNode) {
	var newTeam = this.refs.teamSelection.getDOMNode();
	var selectedIndex = newTeam.selectedIndex;
	var teamName = newTeam.options[selectedIndex].text;
	console.log("Team componentDidUpdate");
	
	React.renderComponent(
		<Fixtures team={teamName} />, document.getElementById('fixtures')
	);
  },
  changeDivision: function () {
	var newTeam = this.refs.teamSelection.getDOMNode();  
	var selectedIndex = newTeam.selectedIndex;
	var teamName = newTeam.options[selectedIndex].text;
	console.log("Changing Team: " + teamName);
	
	React.renderComponent(
		<Fixtures team={teamName} />, document.getElementById('fixtures')
	);
  },

	render: function () {
		console.log("Render Team: " + this.props.division + ", " + this.state.data);
		
		var teamRows = this.state.data.map(function (selectOption, index) {
		  return <SelectOption selectOption={selectOption} />;
		});
		return (<div><span className="label">Team</span><span className="selector"><select onChange={this.changeDivision} ref="teamSelection">{teamRows}</select></span></div>);
	}
});


var Fixture = React.createClass({
	render: function () {
		return (
			<tr>
				<td className="date">{this.props.date}</td>
				<td className="home">{this.props.home}</td>
				<td className="score">{this.props.homeGoals}</td>
				<td className="versus">-</td>
				<td className="score">{this.props.awayGoals}</td>
				<td className="away">{this.props.away}</td>
			</tr>
		);
	}
});


var Fixtures = React.createClass({
    getInitialState: function() {
      return {data: []};
    },
  loadFixturesFromServer: function(team) {
    $.ajax({
      url: "/fixtures.json?team="+team,
      success: function(data) {
        this.setState({data: data});
      }.bind(this)
    });
  },
  componentWillMount: function() {
    this.loadFixturesFromServer(this.props.team);
  },
  componentWillReceiveProps: function(nextProps) {
    this.loadFixturesFromServer(nextProps.team);
  },
	render: function () {
		console.log("Render Team: " + this.props.division + ", " + this.state.data);
		
		var fixtureRows = this.state.data.map(function (fixture, index) {
		  return <Fixture date={fixture.date} home={fixture.home} away={fixture.away} homeGoals={fixture.homeGoals} awayGoals={fixture.awayGoals} />;
		});
		return (
		<table>
			<tr>
				<th className="date">Date</th>
				<th className="home">Home</th>
				<th className="score"> </th>
				<th className="versus">-</th>
				<th className="score"> </th>
				<th className="away">Away</th>
			</tr>
			{fixtureRows}
		</table>);
	}
});




React.renderComponent(
  <Season />,
  document.getElementById('season')
);


