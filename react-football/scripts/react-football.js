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
        this.setState({data: data});
      }.bind(this)
    });
  },
  componentWillMount: function() {
	console.log("componentWillMount");
    this.loadSeasonsFromServer();
  },
  componentDidMount: function (rootNode) {
	if (this.state.data.length == 0) return;
  
    console.log("componentDidMount");
  
	var newSeason = rootNode.selector;
	
	for (var name in rootNode.selector) {
		console.log("componentDidMount: " + name);
	}
	
//	React.renderComponent(
//		<Division season={newSeason} />, document.getElementById('division')
//	);
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
  render: function() {
	
	console.log("Render Division: " + this.props.season + ", " + this.state.data);
	
	var divisionRows = this.state.data.map(function (selectOption, index) {
	  return <SelectOption selectOption={selectOption} />;
	});
	return (<div><span className="label">Division</span><span className="selector"><select>{divisionRows}</select></span></div>);
  }
});


React.renderComponent(
  <Season />,
  document.getElementById('season')
);

//React.renderComponent(
//  <Division season="2013" />,
//  document.getElementById('division')
//);
