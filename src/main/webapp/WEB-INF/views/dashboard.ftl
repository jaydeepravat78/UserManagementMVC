<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>

<link href="Assets/Libraries/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- bootstrap -->

<link rel="stylesheet" href="Assets/Fonts/font.css">
<!-- font -->
<link rel="stylesheet"
	href="Assets/Libraries/datatable/datatables.min.css">
<!--  datatable -->
<link rel="stylesheet" href="Assets/CSS/dashboard.css">
</head>
<body>
		<#include "header.html">

		<div class="container">
			<nav class="text-right">
				<a href="registration" class="btn btn-success">Add User</a> <a
					href="LogoutController" class="btn btn-danger">Logout</a>
			</nav>
			<br>
			<table id="users-table" class="table table-striped">
				<!-- cell-border hover stripe -->
				<!-- Table for data -->
				<thead>
					<tr>
						<th class="dt-head-center">ID</th>
						<th class="dt-head-center">Name</th>
						<th class="dt-head-center">Email</th>
						<th class="dt-head-center">Phone</th>
						<th class="dt-head-center">Gender</th>
						<th class="dt-head-center">Game</th>
						<th class="dt-head-center">Edit</th>
						<th class="dt-head-center">Delete</th>
					</tr>
					<!-- Headings -->
				</thead>
				<tbody>
				</tbody>
			</table>
			<form class="form-inline importform" action="UsersController"
				method="post" enctype="multipart/form-data" id="fileUpload">
				<input type="file"
					accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
					name="excelFile" class="form-control"> <input type="submit"
					value="Import Users" class="btn btn-success"> <span
					class="error text-center"> <#if errorMessage??>${errorMessage} </#if>
				</span>
			</form>
		</div>
	<script src="Assets/JS/jquery-3.6.0.min.js"></script>
	<!-- jquery -->
	<script src="Assets/Libraries/bootstrap/js/bootstrap.min.js"></script>
	<!--  bootstrap -->
	<script src="Assets/Libraries/datatable/datatables.min.js"></script>
	<!-- datatable -->
	<script src="Assets/Libraries/validate/jquery.validate.min.js"></script>
	<!-- validate js -->
	<script src="Assets/JS/dashboard.js"></script>
</body>
</html>