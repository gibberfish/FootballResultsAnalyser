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
  
  receiveSeasonsUpdate: function(data) {
	this.setState({data: data});
  },
  
  componentWillMount: function() {
	Model.DataAccess.loadSeasonsFromServer(this.receiveSeasonsUpdate);
  },
  
  componentDidUpdate: function (prevProps, prevState, rootNode) {
	var newSeason = this.refs.seasonSelection.getDOMNode().value.trim();
	React.renderComponent(
		<Division season={newSeason} />, document.getElementById('division')
	);
  },
  
  changeSeason: function () {
	var newSeason = this.refs.seasonSelection.getDOMNode().value.trim();
	React.renderComponent(
		<Division season={newSeason} />, document.getElementById('division')
	);
  },
  
  render: function() {
	var seasonRows = this.state.data.map(function (selectOption, index) {
	  return <SelectOption selectOption={selectOption} />;
	});
	return (<div><span className="label">Season</span><span className="selector"><select onChange={this.changeSeason} ref="seasonSelection">{seasonRows}</select></span></div>);
  }
});


var Division = React.createClass({

  getInitialState: function() {
    return {data: [], selectedId: -1};
  },
  
  receiveDivisionsUpdate: function(data) {
	if (data.length > 0) {
		this.setState({data: data, selectedId: data[0].id});
	}
  },

  componentWillMount: function() {
	Model.DataAccess.loadDivisionsFromServer(this.props.season, this.receiveDivisionsUpdate);
  },
    
  componentWillReceiveProps: function(nextProps) {
	Model.DataAccess.loadDivisionsFromServer(nextProps.season, this.receiveDivisionsUpdate);
  },
  
  componentDidUpdate: function (prevProps, prevState, rootNode) {
	var newDivision = this.refs.divisionSelection.getDOMNode().value.trim();	
	React.renderComponent(
		<Team division={newDivision} />, document.getElementById('team')
	);
  },
  
  changeDivision: function () {
	var newDivision = this.refs.divisionSelection.getDOMNode().value.trim();
	
	this.state.selectedId = newDivision;
	this.forceUpdate();
	
	React.renderComponent(
		<Team division={newDivision} />, document.getElementById('team')
	);
  },
  
  render: function() {
	var divisionRows = this.state.data.map(function (selectOption, index) {
	   return <SelectOption selectOption={selectOption} />;
	});
	return (<div><span className="label">Division</span><span className="selector"><select value={this.state.selectedId} onChange={this.changeDivision} ref="divisionSelection">{divisionRows}</select></span></div>);
  }
});

var Team = React.createClass({
  getInitialState: function() {
    return {data: [], selectedId: -1};
  },
	
  receiveTeamsUpdate: function(data) {
	if (data.length > 0) {
		this.setState({data: data, selectedId: data[0].id});
	}
  },

  componentWillMount: function() {
	Model.DataAccess.loadTeamsFromServer(this.props.division, this.receiveTeamsUpdate);
  },
  
  componentWillReceiveProps: function(nextProps) {
  	Model.DataAccess.loadTeamsFromServer(nextProps.division, this.receiveTeamsUpdate);
  },
  
  componentDidUpdate: function (prevProps, prevState, rootNode) {
	var newTeam = this.refs.teamSelection.getDOMNode();
	var selectedIndex = newTeam.selectedIndex;
	var teamName = newTeam.options[selectedIndex].text;

	React.renderComponent(
		<Fixtures team={teamName} />, document.getElementById('fixtures')
	);
  },
  
  changeTeam: function () {
	var newTeam = this.refs.teamSelection.getDOMNode();  
	var selectedIndex = newTeam.selectedIndex;
	var teamName = newTeam.options[selectedIndex].text;
	
	this.state.selectedId = newTeam.options[selectedIndex].val;
	this.forceUpdate();

	React.renderComponent(
		<Fixtures team={teamName} />, document.getElementById('fixtures')
	);
  },

  render: function () {
	var teamRows = this.state.data.map(function (selectOption, index) {
	  return <SelectOption selectOption={selectOption} />;
	});
	return (<div><span className="label">Team</span><span className="selector"><select value={this.state.selectedId} onChange={this.changeTeam} ref="teamSelection">{teamRows}</select></span></div>);
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
  
  receiveFixtures: function(data) {
	this.setState({data: data});
  },
  
  componentWillMount: function() {
	Model.DataAccess.loadFixturesFromServer(this.props.team, this.receiveFixtures);
  },
  
  componentWillReceiveProps: function(nextProps) {
	Model.DataAccess.loadFixturesFromServer(nextProps.team, this.receiveFixtures);
  },
  
  render: function () {
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
