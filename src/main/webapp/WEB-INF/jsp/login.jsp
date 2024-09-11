<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css'
	rel='stylesheet'
	integrity='sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN'
	crossorigin='anonymous'>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	
<title>Connexion</title>
</head>
<body>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href='/'><img src="favicon-32x32.png"/></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  </div>
</nav>

	<h2 class="mx-auto p-2" style="width: fit-content;">Connectez vous</h2>
	<div class="container">
		<form action="loginAccount" method="post" class="form-example">
			<div class="mb-3 col-auto">
				<label for="username">email:</label> <input class="form-control"
					type="text" name="username" id="username" required />
			</div>
			<div class="mb-3 col-auto">
				<label for="password">password:</label> <input class="form-control"
					type="password" name="password" id="password" required />
			</div>
			<div class="mb-3 mx-auto p-2" style="width: fit-content;">
				<input type="submit" class="btn btn-primary" value="Connexion" />
			</div>
			
		
			<%
			try{
			if(request.getAttribute("fail").equals("true")){ %>
				<div class="alert alert-danger" role="alert">
				  Email ou mot de passe incorrect ! 
				</div>
			<%}}catch(Exception e){
			
			}%>
			
		</form>
		
		<div class="mx-auto p-2" style="width: fit-content;">
			<h6>Pas de compte ? <a href="createAccount">Créez en un !</a></h6>
		</div>
	</div>
</body>
</html>