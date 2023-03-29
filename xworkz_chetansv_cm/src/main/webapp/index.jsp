<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Xworkz</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" />
</head>
<body>

	<section class="vh-100" style="background-color: black;">
		<div class="container h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-lg-12 col-xl-11">
					<div class="card text-black" style="border-radius: 25px;">
						<div class="card-body p-md-5">
							<div class="row justify-content-center">
								<div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

									<p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign
										up</p>
									<c:forEach items="${errors}" var="e">

										<span style="color: red;">${e.message }</span>
									</c:forEach>
									

										<span style="color: red;">${error }</span>
									
									<form class="mx-1 mx-md-4" action="saveSignUp" method="Post">

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-user fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">

												<input type="text" id="userId" onChange="onUser()"
													class="form-control" name="userId" /> <label
													class="form-label" for="form3Example1c">USERId</label><br>
											</div>
										</div>

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">

												<input type="email" id="emailId" onChange="onEmail()"
													class="form-control" name="email" /> <label
													class="form-label" for="form3Example3c">Your EMAIL</label>
											</div>
										</div>


										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="number" id="mobileId" onChange="onMobile()"
													class="form-control" name="mobile" /> <label
													class="form-label" for="form3Example3c">Mobile</label>
											</div>
										</div>

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-lock fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="password" 
													 class="form-control" id="confrimPasswordId" onChange="onConfrimPassword()"
													name="password" /> <label class="form-label"
													for="form3Example4c">Password</label>
											</div>
										</div>

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-key fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="password" id="passwordId" onChange="onPassword()"
													 class="form-control" name="confirmPassword"/> <label
													class="form-label" for="form3Example4cd">Confrim
													Password</label>
											</div>
										</div>

										<div class="form-check d-flex justify-content-center mb-5">
											<input class="form-check-input me-2" type="checkbox"
												value="true" name="acceptAgreement" id="agreementId"
												onClick="onAgreement()" /> <label class="form-check-label"
												for="form2Example3"> I agree all statements in <a>Agreement</a>
											</label>
										</div>

										<div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
											<input type="submit" id="submitId" disabled="true"
												value="save" class="btn btn-primary" />
										</div>

									</form>

								</div>
								<div
									class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

									<img
										src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
										class="img-fluid" alt="Sample image">

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script>
		function onUser() {
			console.log('running onUser');
			var userInput = document.getElementById('userId');
			var userValue = userInput.value;

			if (userValue != null && userValue != "" && userValue.length > 3
					&& userValue.length < 30) {
				console.log('valid user')
			} else {
				console.log('invalid user')

				//document.getElementById('userError').innerHTML = 'Invalid user please enter min 3 andf max 30'
				alert('Invalid user please enter min 3 and max 30');
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
		}
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

