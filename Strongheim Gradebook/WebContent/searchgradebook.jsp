<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type=text/css href="custom.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="/path/to/jquery.js"></script>
<script type="text/javascript" src="/path/to/moment.js"></script>
<script type="text/javascript" src="/path/to/bootstrap/js/transition.js"></script>
<script type="text/javascript" src="/path/to/bootstrap/js/collapse.js"></script>
<script type="text/javascript"
	src="/path/to/bootstrap/dist/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/path/to/bootstrap-datetimepicker.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Records</title>
</head>
<body>
<form role="form" action="SearchGradebook" method="post">
			<label for="studentsearch">Search by Student Id:</label> <input type="text"
					class="form-control" name="studentsearch"
					placeholder="Search by Student Id">
					<p></p>
			<select name="class">
				<option value="Earth Science">9:30 Earth Science </option>
				<option value="Intro to Physics">1pm Intro to Physics</option>
				
			</select>
			<p>Search by Assignment Type</p>
			<select name="typesearch">
				<option value="Homework">Homework</option>
				<option value="Quiz">Quiz</option>
				<option value="Test">Test</option>
				<option value="Project" selected>Project</option>
			</select>
			</div>
	<select name="searchmode">
				<option value="allbystudent">All assignments by a student</option>
				<option value="typebyall">All assignments of a particular type by anyone</option>
				<option value="typebystudent">All assignments of a particular type by a particular student</option>
				<option value="avgbystudent" selected>The average for a student</option>
				<option value="avgbyassignment">The average for a student by assignment type</option>
				<option value="highandlow" selected>The highest and lowest grade for a particular assignment type (highest quiz grade, lowest project grade, etc)</option>
				
			</select>
			<button type="submit" class="btn btn-default" name="search2">Search</button>
			
			</form>
			
			<p></p>
			
			${message}
</body>
</html>