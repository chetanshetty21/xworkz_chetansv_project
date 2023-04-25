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

<style>
.divider:after, .divider:before {
	content: "";
	flex: 1;
	height: 1px;
	background: #eee;
}
</style>
<title>Xworkz</title>
</head>
<body><nav class="navbar navbar-expand-lg navbar-bright bg-dark">
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


	<section class="vh-100">
		<div class="container py-5 h-100">
			<div
				class="row d-flex align-items-center justify-content-center h-100">
				<div class="col-md-8 col-lg-7 col-xl-6">
					<img
						src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
						class="img-fluid" alt="Phone image">
				</div>
				<div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
					<form action="findByUserAndPassword" method="Post">

						<span style="color: red;" ${message}></span>
						<!-- Email input -->
						<h5 style="color: red">${match}</h5>
						<h5 style="color: red">${msg}</h5>
						<h5 style="color: red">${msgs}</h5>
						<div class="form-outline mb-4">
							<input type="text" name="userId" id="userName"
								onchange="ValideName()" placeholder="USERID"
								class="form-control form-control-lg" /> <label
								class="form-label" for="form1Example13"> <span
								id="displayUserName" style="color: red"></span></label>
						</div>

						<!-- Password input -->
						<div class="form-outline mb-4">
							<input type="password" id="userPassword" name="password"
								placeholder="PASSWORD" class="form-control form-control-lg" />
							<input type="checkbox" onclick="myFunction()">Show
							Password<label class="form-label" for="form1Example23"></label>
						</div>

						<div class="d-flex justify-content-around align-items-center mb-4">
							<!-- Checkbox -->
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value=""
									id="form1Example3" checked /> <label class="form-check-label"
									for="form1Example3"> Remember me </label>
							</div>
							<a href="resetpassword.jsp">Forgot password?</a>
						</div>

						<!-- Submit button -->

						<button type="submit" class="btn btn-primary btn-lg btn-block"
							value="search">Sign in</button>

						<div class="divider d-flex align-items-center my-4">
							<p class="text-center fw-bold mx-3 mb-0 text-muted">OR</p>
						</div>

						<a class="btn btn-primary btn-lg btn-block"
							style="background-color: #3b5998" href="#!" role="button"> <i
							class="fab fa-facebook-f me-2"></i>Continue with Facebook
						</a> <a class="btn btn-primary btn-lg btn-block"
							style="background-color: #55acee" href="#!" role="button"> <i
							class="fab fa-twitter me-2"></i>Continue with Twitter
						</a>

					</form>
				</div>
			</div>
		</div>
	</section>
	<script>
		function ValideName() {
			var user = document.getElementById('userName');
			var uservalue = user.value;
			console.log(uservalue);
			if (uservalue != null && uservalue != "" && uservalue.length > 3
					&& uservalue.length < 30) {
				console.log('valide name');
				//	document.getElementById('displayUserName').innerHTML = '';
			} else {
				console.log('invalide name');
				document.getElementById('displayUserName').innerHTML = 'Plese enter valide name min 4 and max 30 character';
			}
		}
		function myFunction() {
			var x = document.getElementById("userPassword");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
	</script>
</body>

</html>