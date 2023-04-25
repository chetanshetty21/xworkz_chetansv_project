<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
.gradient-custom-3 {
	/* fallback for old browsers */
	background: #84fab0;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 0.5),
		rgba(143, 211, 244, 0.5));
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to right, rgba(132, 250, 176, 0.5),
		rgba(143, 211, 244, 0.5))
}

.gradient-custom-4 {
	/* fallback for old browsers */
	background: #84fab0;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 1),
		rgba(143, 211, 244, 1));
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to right, rgba(132, 250, 176, 1),
		rgba(143, 211, 244, 1))
}
</style>
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
	<!-- <nav class="navbar navbar-expand-lg navbar-bright bg-dark">
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

	</nav> -->


	<section class="vh-110 bg-image"
		style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
		<div class="mask d-flex align-items-center h-100 gradient-custom-3">
			<div class="container h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-12 col-md-9 col-lg-7 col-xl-6">
						<div class="card" style="border-radius: 15px;">
							<div class="card-body p-5">
								<h2 class="text-uppercase text-center mb-5">Create an
									account</h2>
								<span style="color: red;" ${success}></span>
								<c:forEach items="${errors}" var="e">

									<span style="color: red;">${e.message }</span>
								</c:forEach>
								<span style="color: red;">${error}</span>

								<form action="saveSignUp" method="Post">

									<div class="form-outline mb-4">
										<input type="text" id="userId"
											class="form-control form-control-lg" onchange="onUser()"
											value="${dto.userId}" name="userId" placeholder="Your Name" />
										<label class="form-label" for="form3Example1cg"><span
											id="displayUserName" style="color: red"></span></label>
									</div>

									<div class="form-outline mb-4">
										<input type="email" id="emailId"
											class="form-control form-control-lg" onchange="onEmail()"
											value="${dto.email}" name="email" placeholder="Your Email" />
										<label class="form-label" for="form3Example3cg"><span
											id="displayEmail" style="color: red"></span></label>
									</div>
									<div class="form-outline mb-4">
										<input type="number" id="mobileId"
											class="form-control form-control-lg" onchange="onMobile()"
											value="${dto.mobile}" name="mobile"
											placeholder="Your Mobile Number" /> <label
											class="form-label" for="form3Example3cg"><span
											id="displayMobile" style="color: red"></span></label>
									</div>
									<div class="form-outline mb-4">
										<input type="password" id="confrimPasswordId"
											class="form-control form-control-lg"
											onchange="onConfrimPassword()" name="password"
											placeholder="Password" /> <label class="form-label"
											for="form3Example4cg"><span id="passwordError"
											style="color: red"></span></label><input type="checkbox"
											onclick="myFunction1()">Show Password
									</div>

									<div class="form-outline mb-4">
										<input type="password" id="passwordId"
											class="form-control form-control-lg" onchange=" onPassword()"
											name="confirmPassword" placeholder="Repeat your password" />
										<label class="form-label" for="form3Example4cdg"> <span
											id="passwordCompare" style="color: red"></span></label> <input
											type="checkbox" onclick="myFunction2()">Show Confirm
										Password
									</div>

									<div class="form-check d-flex justify-content-center mb-5">
										<input class="form-check-input me-2" type="checkbox"
											value="true" id="agreementId" name="acceptAgreement"
											onclick="onAgreement()" /> <label class="form-check-label"
											for="form2Example3g"> I agree all statements in <a
											href="#!" class="text-body">Terms of service</a>
										</label>
									</div>

									<div class="d-flex justify-content-center">
										<input type="button" id="submitId" disabled="True"
											value="Register"
											class="btn btn-success btn-block btn-lg gradient-custom-4 text-body"></input>
									</div>

									<p class="text-center text-muted mt-5 mb-0">
										Have already an account? <a href="SignIn.jsp"
											class="fw-bold text-body"><u>Login here</u></a>
									</p>

								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script>
		function myFunction1() {
			var x = document.getElementById("confrimPasswordId");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
		function myFunction2() {
			var x = document.getElementById("passwordId");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
		function onUser() {
			console.log('running onUser');
			var userInput = document.getElementById('userId');
			var userValue = userInput.value;

			if (userValue != null && userValue != "" && userValue.length > 3
					&& userValue.length < 30) {
				console.log('valid user')

				/* var agree = document.getElementById('agreementConfirm');
				console.log(agree.checked);
				if (agree.checked) {
					document.getElementById('submitId').disabled = false;
				}
				document.getElementById('nameError').innerHTML = ''; */
			} else {
				console.log('invalid user')
				document.getElementById('submitId').disabled = 'disabled';
				//document.getElementById('userError').innerHTML = 'Invalid user please enter min 3 andf max 30'
				alert('Invalid user please enter min 3 and max 30');
			}
			const xhttp = new XMLHttpRequest();
			console.log('Running in ajax');
			xhttp.open("GET",
					"http://localhost:8080/xworkz_chetansv_cm/userId/"
							+ userValue);
			xhttp.send();

			xhttp.onload = function() {
				//alert('user is exist ');
				document.getElementById('displayUserName').innerHTML = this.responseText

			}
		}

		function onEmail() {
			console.log('running onEmail');
			var emailInput = document.getElementById('emailId');
			var emailValue = emailInput.value;
			//var atposition = emailValue.indexOf("@");
			//var dotposition = emailValue.lastIndexOf(".");
			if (emailValue.length > 3 && emailValue.length < 30) {
				console.log('email is valid')
			} else {
				console.log('invalid email')
				alert('Please enter a valid e-mail address ');

			}
			const xhttp = new XMLHttpRequest();
			console.log('Running in ajax');
			xhttp.open("GET",
					"http://localhost:8080/xworkz_chetansv_cm/emailId/"
							+ emailValue);
			xhttp.send();

			xhttp.onload = function() {
				//alert('user is exist ');
				document.getElementById('displayEmail').innerHTML = this.responseText

			}

		}
		/*if (userValue != null && userValue != "" && userValue.length > 3
					&& userValue.length < 30)

				if (agreement.checked) {
					document.getElementById('submitId').disabled = false;
				}
				alert('enter the user name');
			} else {
				console.log('invalid user')
				document.getElementById('submitId').disabled = 'disabled';
				//document.getElementById('userError').innerHTML = 'Invalid user please enter min 3 andf max 30'
				alert('Invalid user please enter min 3 and max 30');
			}*/
		function onMobile() {
			console.log('running onMobile');
			var mobileInput = document.getElementById('mobileId');
			var mobileValue = mobileInput.value;

			if (mobileValue.length == 10) {
				console.log('mobile Number is valid')
			} else {
				console.log('invalid mobile number')
				alert('Please enter a valid mobile numbers  which contains 10');

			}
			const xhttp = new XMLHttpRequest();
			console.log('Running in ajax');
			xhttp.open("GET",
					"http://localhost:8080/xworkz_chetansv_cm/mobile/"
							+ mobileValue);
			xhttp.send();

			xhttp.onload = function() {
				//alert('user is exist ');
				document.getElementById('displayMobile').innerHTML = this.responseText

			}

		}
		function onPassword() {
			console.log('running onPassword');
			var passwordInput = document.getElementById('passwordId');
			var passwordValue = passwordInput.value;

			if (passwordValue != null && passwordValue != ""
					&& passwordValue.length > 3 && passwordValue.length < 30) {
				console.log('valid password')
			} else {
				console.log('invalid password')

				alert('Invalid password please enter min 3 and max 30');
			}
		}

		function onConfrimPassword() {
			console.log('running onConfrimPassword');
			var confrimPasswordInput = document
					.getElementById('confrimPasswordId');
			var confrimPasswordValue = confrimPasswordInput.value;
			if (document.getElementById('confrimPasswordId') !== document
					.getElementById('passwordId')) {
				console.log('valid confrimPassword')
			} else {
				console.log('invalid confrimPassword')

				alert('Invalid confrimPassword and password ia not match');
			}
		}
		function onAgreement() {
			var agreement = document.getElementById('agreementId');
			console.log(agreement.checked)
			if (agreement.checked) {
				document.getElementById('submitId').disabled = false;
			} else {
				document.getElementById('submitId').disabled = true;

				alert('mark the check box');
			}
		}
	</script>


</body>
</html>

