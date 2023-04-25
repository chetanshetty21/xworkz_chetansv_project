<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
body {
	margin: 0;
	padding-top: 40px;
	color: #2e323c;
	background: #f5f6fa;
	position: relative;
	height: 100%;
}

.account-settings .user-profile {
	margin: 0 0 1rem 0;
	padding-bottom: 1rem;
	text-align: center;
}

.account-settings .user-profile .user-avatar {
	margin: 0 0 1rem 0;
}

.account-settings .user-profile .user-avatar img {
	width: 90px;
	height: 90px;
	-webkit-border-radius: 100px;
	-moz-border-radius: 100px;
	border-radius: 100px;
}

.account-settings .user-profile h5.user-name {
	margin: 0 0 0.5rem 0;
}

.account-settings .user-profile h6.user-email {
	margin: 0;
	font-size: 0.8rem;
	font-weight: 400;
	color: #9fa8b9;
}

.account-settings .about {
	margin: 2rem 0 0 0;
	text-align: center;
}

.account-settings .about h5 {
	margin: 0 0 15px 0;
	color: #007ae1;
}

.account-settings .about p {
	font-size: 0.825rem;
}

.form-control {
	border: 1px solid #cfd1d8;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border-radius: 2px;
	font-size: .825rem;
	background: #ffffff;
	color: #2e323c;
}

.card {
	background: #ffffff;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	border: 0;
	margin-bottom: 1rem;
}
</style>
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
	<nav class="navbar navbar-expand-lg navbar-bright bg-dark">
		<div class="container-fluid">
			<img
				src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
				alt="" width="90" height="60" class="d-inline-block align-text-top">

			<ul class="nav justify-content-end">
				<li class="nav-item"><a class="nav-link" href="Signup.jsp">SignUp</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="index.jsp">HOME</a>
				</li>


			</ul>


		</div>

	</nav>
	<form action="UpdateImg" method="post" enctype="multipart/form-data">
		<div class="container">
			<div class="row gutters">

				<div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
					<div class="card h-100">
						<div class="card-body">
							<div class="account-settings">
								<div class="user-profile">
									<div class="user-avatar">
										<img src="download?fileName=${dtoPic}" height="50" width="80"
											alt="${userID}">

									</div>
									<input class="user-name" placeholder="${userID}"
										readonly="readonly"></input> <input class="user-email"
										placeholder="${dto.email}" readonly="readonly"></input> <input
										type="file" name="chitra">
									<button class="btn btn-success" type="submit">Upload
										new image</button>
								</div>
								<div class="about">
									<h5>About</h5>
									<p>I'm chetan Full Stack Designer I enjoy creating
										user-centric, delightful .</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- </form>
			<form action="UpdateData" method="post">// -->
				<div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
					<div class="card h-100">
						<div class="card-body">
							<div class="row gutters">
								<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
									<h6 class="mb-2 text-primary">Personal Details</h6>
								</div>
								<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="form-group">
										<label for="fullName">USER ID</label> <input type="text"
											id="userId" onchange="onUser()" name="userId"
											class="form-control" required="required"
											value="${dto.userId}"> <span id="displayUserName"
											style="color: red"></span>
									</div>
								</div>
								<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="form-group">
										<label for="eMail">Email</label> <input type="email"
											id="emailId" onchange="onEmail()" name="email"
											readonly="readonly" class="form-control" required="required"
											value="${dto.email}"> <span id="displayEmail"
											style="color: red"></span>
									</div>
								</div>
								<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="form-group">
										<label for="phone">mobile</label> <input type="number"
											id="mobileId" onchange="onMobile()" name="mobile"
											class="form-control" value="${dto.mobile}"> <span
											id="displayMobile" style="color: red"></span>
									</div>
								</div>
								<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="form-group">
										<label for="website">Website URL</label> <input type="url"
											class="form-control" id="website" placeholder="Website url">
									</div>
								</div>
							</div>

							<div class="row gutters">
								<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="text-right">
										<button type="button" id="submit" name="submit"
											class="btn btn-secondary">Cancel</button>
										<button type="submit" value="Update" class="btn btn-primary">Update</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</form>
	<script>
		
		
		function onUser() {
			console.log('running onUser');
			var userInput = document.getElementById('userId');
			var userValue = userInput.value;

			if (userValue != null && userValue != "" && userValue.length > 3
					&& userValue.length < 30) {
				console.log('valid user')

				var agree = document.getElementById('agreementConfirm');
				console.log(agree.checked);
				if (agree.checked) {
					document.getElementById('submitId').disabled = false;
				}
				document.getElementById('nameError').innerHTML = '';
			} else {
				console.log('invalid user')
				document.getElementById('submitId').disabled = 'disabled';
				alert('Invalid user please enter min 3 and max 30');
			}
			const xhttp = new XMLHttpRequest();
			console.log('Running in ajax');
			xhttp.open("GET",
					"http://localhost:8080/xworkz_chetansv_cm/userId/"
							+ userValue);
			xhttp.send();

			xhttp.onload = function() {
				
				document.getElementById('displayUserName').innerHTML = this.responseText

			}
		}

		function onEmail() {
			console.log('running onEmail');
			var emailInput = document.getElementById('emailId');
			var emailValue = emailInput.value;
		
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
			
				document.getElementById('displayEmail').innerHTML = this.responseText

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

		

	
	</script>
</body>
</html>



