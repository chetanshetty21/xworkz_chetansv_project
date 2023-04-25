<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-Workz</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
	crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg-navbar-Light bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				src=" https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
				alt="" width="80" height="48" class="d-inline-block align text-top">
				<li class="active"><a href="index.jsp">Home</a></li> <a
				href="signup.jsp"><span class="glyphicon glyphicon-user"></span>
					Sign Up</a> <a href="SignIn.jsp"><span
					class="glyphicon glyphicon-log-in"></span> Login</a> <span
				style="color: white;">Welcome:${userID}</span> <img
				src="download?fileName=${dtoPic}" height="50" width="80">
		</div>
		<a href="SignIn.jsp">Go Back</a> <span style="color: Orage;">Hey
			:${userID}</span> <img src="download?fileName=${dtoPic}" height="50"
			width="80">
	</nav>

	<c:forEach items="${error}" var="e">
		<br>


		<span style="color: red;">${e.message}</span>
	</c:forEach>
	<div>
		<span style="color: green;">${message}</span>
	</div>

	<form action="addTech" method="post">
		UserId<input value="${userID}" name="userId" readonly="readonly">
		<div class="mb-3">
			<label for="formFile" class="form-label">TechnologyName</label> <input
				type="text" class="form-control" name="techName" id="formFile"
				value="" />
		</div>

		<div class="mb-3">
			<label for="formFile" class="form-label">language</label> <input
				type="text" class="form-control" name="lang" id="formFile"
				required="required" />
		</div>
		<div class="mb-3">
			<label for="formFile" class="form-label">version</label> <input
				type="number" class="form-control" name="version" id="formFile"
				required="required" />
		</div>
		<div class="mb-3">
			<label for="formFile" class="form-label">owner</label> <input
				type="text" class="form-control" name="owner" id="formFile"
				required="required" />
		</div>
		<div class="mb-3">
			<label for="formFile" class="form-label">supportFrom</label> <input
				type="date" class="form-control" name="supportFrom" id="formFile"
				required="required" />
		</div>
		<div class="mb-3">
			<label for="formFile" class="form-label">supportTo</label> <input
				type="date" class="form-control" name="supportTo" id="formFile"
				required="required" value="" />
		</div>
	<div class="mb-3">
			<label for="formFile" class="form-label">license</label> <input
				type="text" class="form-control" name="license" id="formFile"
				required="required" value="" />
		</div>

		<%-- license <select class="form-select"
			aria-label="Default select example" name="license"
			required="required">
			 <option selected value="${dto.license}">${dto.license}</option>
			<c:forEach items="${license}" var="items">
				<option value="${items}">${items}</option>
			</c:forEach>
			
		</select> --%>
		 openSoure <select class="form-select"
			aria-label="Default select example" name="openSoure"
			required="required">
			  <option selected value="${openSoure}"></option>
			<c:forEach items="${openSoure}" var="Soure">
				<option value="${Soure}">${Soure}</option>
			</c:forEach>
			</select>
			 osType <select class="form-select"
			aria-label="Default select example" name="osType"
			required="required">
			  <option selected value="${techEnum}"></option>
			<c:forEach items="${techEnum}" var="Source">
				<option value="${Source}">${Source}</option>
			</c:forEach>
			</select>
			<input type="submit" value="addTech" />
	</form>
	<span style="color: blue;">${techie}</span>
	<br>
	<a href="show?userId=${userID}">Show User Technology Details</a>
</body>
</html>