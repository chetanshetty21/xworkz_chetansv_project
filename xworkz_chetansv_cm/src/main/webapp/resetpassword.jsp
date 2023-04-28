

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" />

<title>Xworkz</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-bright bg-dark">
		<div class="container-fluid">
			<img
				src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
				alt="" width="90" height="60" class="d-inline-block align-text-top">

			<ul class="nav justify-content-end">
				<li class="nav-item"><a class="nav-link" href="Signup.jsp">SignUp</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="SignIn.jsp">SignIn</a>
				</li>


			</ul>


		</div>

	</nav>
	<section class="vh-110 gradient-custom"
		bg-image" style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
		<div class="container py-5 h-100">
			<div
				class="row align-items-center justify-content-center
      min-vh-100 g-0">
				<div
					class="col-12 col-md-8 col-lg-4 border-top border-3 border-primary">
					<div class="card shadow-sm">
						<div class="card-body">
							<div class="mb-4">

								<h5>Forgot Password?</h5>
								
								<p class="mb-2">Enter your registered email ID to reset the
									password</p>
							</div>
							<form action="reset" method="post">
							<span style="color: Red;">${msg}</span>
								<div class="mb-3">
									<label for="email" class="form-label">Email</label> <input
										type="email" id="emailId" class="form-control" name="email"
										onchange="valideEmail()" placeholder="Enter Your Email"
										required=" ">
								</div>
								<div class="mb-3 d-grid">
									<button type="submit" class="btn btn-primary">Reset
										Password</button>
								</div>
								<span>Don't have an account? <a href="SignIn.jsp">sign
										in</a></span>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
		<script>
			function valideEmail() {
				var userEmail = document.getElementById('emailId');
				var userEmailvalue = userEmail.value;
				console.log(userEmailvalue);
				if (userEmailvalue != null && userEmailvalue != ""
						&& userEmailvalue.length > 4
						&& userEmailvalue.length < 40) {
					console.log('valide email');
					const xhttp = new XMLHttpRequest();
					console.log('Running in ajax');
					console.log(userEmailvalue);
					xhttp.open("GET",
							"http://localhost:8088/xworkz_chetansv_cm/reemail/"
									+ userEmailvalue);
					xhttp.send();
					xhttp.onload = function() {
						console.log(this);
						document.getElementById("display").innerHTML = this.responseText
					}
					document.getElementById('emailError').innerHTML = '';
				} else {
					console.log('invalide email');
					document.getElementById('emailError').innerHTML = 'Plese enter valide email min 4 and max 40 charactes ';
				}
			}
		</script>
</body>
</html>
