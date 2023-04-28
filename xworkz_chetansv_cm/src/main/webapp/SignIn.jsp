
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<style>
.gradient-custom {
	/* fallback for old browsers */
	background: #6a11cb;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1),
		rgba(37, 117, 252, 1));
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to right, rgba(106, 17, 203, 1),
		rgba(37, 117, 252, 1))
}
</style>

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




	<section class="vh-100  gradient-custom"
	 style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-9 col-lg-7 col-xl-6">
					<div class="card " style="border-radius: 1rem;">
						<div class="card-body p-5 text-center">
							<form action="findByUserAndPassword" method="Post">

								<div class="mb-md-5 mt-md-4 pb-5">

									<h2 class="fw-bold mb-2 text-uppercase">Login</h2>
									<p class="text-white-50 mb-5">Please enter your UserId and
										password!</p>
									<span style="color: red;" ${message}></span>
									<!-- Email input -->
									<h5 style="color: red">${match}</h5>
									<h5 style="color: red">${msg}</h5>
									<h5 style="color: red">${msgs}</h5>
									<div class="form-outline form-white mb-4">


										<input type="text" name="userId" id="userName"
											onchange="ValideName()" placeholder="USERID"
											class="form-control form-control-lg" /> <label
											class="form-label" for="form1Example13"> <span
											id="displayUserName" style="color: red"></span></label>
									</div>

									<div class="form-outline form-white mb-4">
										<input type="password" id="userPassword" name="password"
											placeholder="PASSWORD" class="form-control form-control-lg" />
										<input type="checkbox" onclick="myFunction()">Show
										Password<label class="form-label" for="form1Example23"></label>
									</div>

									<p class="small mb-5 pb-lg-2">
										<a class="text-black-50" href="resetpassword.jsp">Forgot
											password?</a>
									</p>

									<button type="submit" class="btn btn-primary btn-lg btn-block"
										value="search" type="submit">Login</button>

									<div
										class="d-flex justify-content-center text-center mt-4 pt-1">
										<a href="#!" class="text-black"><i
											class="fab fa-facebook-f "></i></a> <a href="#!"
											class="text-black"><i
											class="fab fa-twitter fa-lg mx-4 px-2"></i></a> <a href="#!"
											class="text-black"><i class="fab fa-google fa-lg"></i></a>
									</div>

								</div>

								<div>
									<p class="mb-0">
										Don't have an account? <a href="Signup.jsp"
											class="text-black-50 fw-bold">Sign Up</a>
									</p>
								</div>
							</form>
						</div>
					</div>
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
