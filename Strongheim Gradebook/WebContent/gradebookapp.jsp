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
<title>Gradebook</title>
</head>
<body>
	<div class="container">
		<h2>Gradebook</h2>
		<form role="form" action="SearchGradebook" method="post">
				<button type="submit" class="btn btn-default" name="search">Search</button>
			</form>
		<form role="form" action="GradebookApp" method="post">
			<div class="form-group">
				<label for="studentId">Student Id:</label> <input type="text"
					class="form-control" name="studentId"
					placeholder="Enter Student Id">
			</div>

			<select name="className">
				<option value="Earth Science">9:30 Earth Science</option>
				<option value="Intro to Physics">1pm Intro to Physics</option>

			</select>

			<div class="form-group">
				<label for="assignment">Assignment:</label> <input type="text"
					class="form-control" name="assignment"
					placeholder="Enter Assignment">
			</div>



			<select name="type">
				<option value="Homework">Homework</option>
				<option value="Quiz">Quiz</option>
				<option value="Test">Test</option>
				<option value="Project" selected>Project</option>
			</select>

			<div class="form-group">
				<label for="date">Date:</label> <input type="date"
					class="form-control" name="date" placeholder="Enter Date">
			</div>
			<div class="form-group">
				<label for="grade">Grade:</label> <input type="number"
					class="form-control" min="0" max="100" name="grade"
					placeholder="Enter Grade">
			</div>
			
			<div class="form-group">
				<label for="homeworkweight">Homework Weight:</label> <input
					type="number" step="0.01 class="form-control" 
					name="homeworkweight" placeholder="Enter Weight">
			</div>
			<div class="form-group">
				<label for="quizweight">Quiz Weight:</label> <input type="number" step="0.01
					class="form-control"  name="quizweight"
					placeholder="Enter Weight">
			</div>
			<div class="form-group">
				<label for="testweight">Test Weight:</label> <input type="number" sstep="0.01
					class="form-control"  name="testweight"
					placeholder="Enter Weight">
			</div>
			<div class="form-group">
				<label for="projectweight">Project Weight:</label> <input
					type="number" step="0.01 class="form-control"
					name="projectweight" placeholder="Enter Weight">
			</div>
			<button type="submit" class="btn btn-default" name="submit">Submit</button>
		</form>
		<p></p>


	</div>
	</div>
	</div>

</body>


</html>