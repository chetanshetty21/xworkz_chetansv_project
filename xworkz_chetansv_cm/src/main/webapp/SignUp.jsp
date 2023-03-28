<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-workz</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-bright bg-dark">
		<div class="container-fluid">
			<img
				src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
				alt="" width="90" height="60" class="d-inline-block align-text-top">

			<ul class="nav justify-content-end">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="index.jsp">Home</a></li>
			</ul>
		</div>
	</nav>




	<c:forEach items="${errors}" var="e">
		<span style="color: red;">${e.message }</span>
	</c:forEach>
	<form action="saveSignUp" method="Post">


		<div class="input-group input-group-lg">
			<span class="input-group-text" id="inputGroup-sizing-lg">USERId</span>
			<input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-lg" name="userId">
		</div>
		<br>

		<div class="input-group input-group-lg">
			<span class="input-group-text" id="inputGroup-sizing-lg">EMAIL</span>
			<input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-lg" name="email">
		</div>
		<br>

		<div class="input-group input-group-lg">
			<span class="input-group-text" id="inputGroup-sizing-lg">Mobile</span>
			<input type="number" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-lg" name="mobile">
		</div>
		<br>
		<div class="input-group input-group-lg">
			<span class="input-group-text" id="inputGroup-sizing-lg">PASSWORD</span>
			<input type="password" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-lg" name="password">
		</div>
		<br>
		<div class="input-group input-group-lg">
			<span class="input-group-text" id="inputGroup-sizing-lg">COMFRIM
				PASSWORD</span> <input type="password" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-lg" name="confirmPassword">
		</div>
		<br> AGGREMENT YES:<input type="radio" value="true"
			name="acceptAgreement" /> <br> <input type="submit"
			value="save" class="btn btn-primary" />
	</form>

</body>
</html>