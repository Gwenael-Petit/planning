<%@page import="sae.planning.repository.UserRepository"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="jakarta.persistence.Parameter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sae.planning.pojo.Principal,sae.planning.pojo.User" %>
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
	
<title>Modification du compte</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href='/'><img src="favicon-32x32.png"/></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	
	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
	<a class="nav-link active" href='reserve'>Mes reservations</a>
	</li>
	</ul>
	<a class="btn btn-outline-success" href='deconnexion'>Se deconnecter</a>
	
      
    </div>
  </div>
</nav>

		<h2 class="mx-auto p-2" style="width: fit-content;">Modification du compte</h2>
	<div class="container">
		<!-- Content here -->
		<%
			
			User user = (User) request.getAttribute("user");
			if(user == null){%>
				<meta http-equiv="refresh" content="0; planning" />
			<%}else{
			String mail = user.getEmail();
			String nom = user.getNom();
			String prenom = user.getPrenom();
			String telephone = user.getTelephone();
			String dateNaiss = user.getDate_naiss();	
			
			
			%>
			<%if(user.getPhoto() == null) {%>
			<img alt="Profile picture" src="upload/ProfilPicture.png" width=100px height=100px>
			<%}else{ %>
			<img alt="Profil picture" src="<%=user.getPhoto()%>" width=100px height=100px>
			<%} %>
			<a class="btn btn-outline-success" href='upload'>Modifier sa photo de profil</a>
			
		<form action="modifAccount" method="post" class="form-example">
			<div class="mb-3">
			
				<input class="form-control"
					type="hidden" name="email" id="email" value="<%= mail %>" required />
			</div>
			<div class="mb-3">
				<label for="name">email: <br> <%= mail %></label>
			</div>
			<div class="mb-3">
				<label for="name">nom:</label> <input class="form-control"
					type="text" name="nom" id="nom" value="<%= nom %>" required />
			</div>
			<div class="mb-3">
				<label for="name">prenom:</label> <input class="form-control"
					type="text" name="prenom" id="prenom" value="<%= prenom %>" required />
			</div>
			<div class="mb-3">
				<label for="name">telephone:</label> <input class="form-control"
					type="tel" name="telephone" id="telephone" value="<%= telephone %>" required />
			</div>
			<div class="mb-3">
				<label for="name">date de naissance:</label> <input
					class="form-control" type="date" name="date_naiss" id="date_naiss"
					value="<%= dateNaiss %>" required />
			</div>
			
			<div class="mb-3">
				<label for="name">Mot de passe Actuel :</label> <input class="form-control"
					type="password" name="pwd" id="pwd"  />
			</div>
			<div class="mb-3">
				<label for="name">Nouveau mot de passe :</label> <input class="form-control"
					type="password" name="pwdNew" id="pwdNew"  />
			</div>
			<div class="mb-3">
				<label for="name">Confirmation du nouveau mot de passe :</label> <input class="form-control"
					type="password" name="pwdConfi" id="pwdConfi"  />
			</div>
			<div class="mb-3">
				<input type="submit" class="btn btn-primary" value="Modifiez votre compte" />
			</div>
			
			
			<%try{
			if(request.getAttribute("error")!= null){ %>
				<div class="alert alert-danger" role="alert">
				  <%=request.getAttribute("error") %>
				</div>
			<%}}catch(Exception e){
			
			}}%>
		</form>
		
		
	</div>
</body>
</html>


