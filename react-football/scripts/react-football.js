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
    this.loadSeasonsFromServer();
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
	return (<div><span class="label">Season</span><span class="selector"><select onChange={this.changeSeason} ref="seasonSelection">{seasonRows}</select></span></div>);
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
	
	console.log("render: " + this.props.season);
	
	var divisionRows = this.state.data.map(function (selectOption, index) {
	  return <SelectOption selectOption={selectOption} />;
	});
	return (<div><span class="label">Division</span><span class="selector"><select>{divisionRows}</select></span></div>);
  }
});


React.renderComponent(
  <Season />,
  document.getElementById('season')
);

React.renderComponent(
  <Division season="2013" />,
  document.getElementById('division')
);
