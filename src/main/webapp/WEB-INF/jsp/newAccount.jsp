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
	
<title>nouveau compte</title>
</head>
<body>
		<h2 class="mx-auto p-2" style="width: fit-content;">Créer un compte</h2>
	<div class="container">
		<!-- Content here -->
		<form action="/createAccount" method="post" class="form-example">
			<div class="mb-3">
				<label for="name">email:</label> <input class="form-control"
					type="text" name="email" id="email" required />
			</div>
			<div class="mb-3">
				<label for="name">password:</label> <input class="form-control"
					type="password" name="pwd" id="pwd" required />
			</div>
			<div class="mb-3">
				<label for="name">nom:</label> <input class="form-control"
					type="text" name="nom" id="nom" required />
			</div>
			<div class="mb-3">
				<label for="name">prenom:</label> <input class="form-control"
					type="text" name="prenom" id="prenom" required />
			</div>
			<div class="mb-3">
				<label for="name">telephone:</label> <input class="form-control"
					type="tel" name="telephone" id="telephone" required />
			</div>
			<div class="mb-3">
				<label for="name">date de naissance:</label> <input
					class="form-control" type="date" name="date_naiss" id="date_naiss"
					required />
			</div>
			<div class="mb-3 mx-auto p-2" style="width: fit-content;">
				<input type="submit" class="btn btn-primary" value="Créez un compte" />
			</div>
			<%try{
			if(request.getAttribute("error")!= null){ %>
				<div class="alert alert-danger" role="alert">
				  <%=request.getAttribute("error") %>
				</div>
			<%}}catch(Exception e){
			
			}%>
		</form>
		
		<div class="mx-auto p-2" style="width: fit-content;">
			<h6>Déjà utilisateur ? <a href="loginAccount">connexion</a></h6>
		</div>
	</div>
</body>
</html>


