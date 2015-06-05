<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
	<!--
    <link rel="icon" href="../../favicon.ico">
	-->

    <title>Football Results Analyser</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="football-results-analyser.css" rel="stylesheet">
    <!--
	<link href="css\fra.css" rel="stylesheet">
	-->
	
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!--
	<script src="bootstrap/js/ie-emulation-modes-warning.js"></script>
	-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <div class="container">
		<div class="starter-template">
        	<h1>Football Results Analyser</h1>
        	<p class="lead">Full Season view (all divisions)</p>
		</div>

		<div>
			Seasons: <select class="form-control" id="seasonSelector"/>
		</div>
		<div>
			Divisions: <select class="form-control" id="divisionSelector"/>
		</div>
		<div>
			Teams: <select class="form-control" id="teamSelector"/>
		</div>

    </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="jquery-1.11.3.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js\fra.js"></script>
	<script>
	
	$(function() {
	
		var seasons = $("#seasonSelector");
	
		$.ajax({
			type: "GET",
			url: "/football-results-analyser/getSeasons.html",
			success: function (json) {
				$.each(json, function() {
					seasons.append($("<option />").val(this.seasonNumber).text(this.seasonNumber));
				});
			},
			error: function (request,textStatus,err) {
				alert("Error getting seasons");
			}
		});
	});
	
	
	</script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!--
    <script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script>
	-->
  </body>
</html>
