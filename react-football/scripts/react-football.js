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
  render: function() {
	var seasonRows = this.state.data.map(function (selectOption, index) {
	  return <SelectOption selectOption={selectOption} />;
	});
	return (<div><span class="label">Season</span><span class="selector"><select>{seasonRows}</select></span></div>);
  }
});


var Division = React.createClass({
  getInitialState: function() {
    return {data: []};
  },
  loadDivisionsFromServer: function() {
    $.ajax({
      url: "/divisions.json",
      success: function(data) {
        this.setState({data: data});
      }.bind(this)
    });
  },
  componentWillMount: function() {
    this.loadDivisionsFromServer();
  },
  render: function() {
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
  <Division />,
  document.getElementById('division')
);
