
    <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList,java.util.List,org.springframework.security.core.context.SecurityContextHolder,org.springframework.security.core.Authentication,java.security.Principal,sae.planning.pojo.User,java.util.Calendar, java.util.Locale, java.time.ZoneId, java.time.ZonedDateTime, java.sql.*, java.util.HashMap"%>
    
<!DOCTYPE html> 
<html> 
<head> 
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">

<link
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css'
	rel='stylesheet'
	integrity='sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN'
	crossorigin='anonymous'>
<link rel='stylesheet' href='planningStyle.css'>
<title>Upload</title> 
</head> 
<body>
<%HttpSession httpSession = request.getSession(); %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

		<div class="container-fluid">
			<a class="navbar-brand" href='/'><img src="favicon-32x32.png" /></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>

			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">

				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item">
						<%
						String user = request.getRemoteUser();
						Principal p = request.getUserPrincipal();
						Authentication auth = SecurityContextHolder.getContext().getAuthentication();
						if (request.getRemoteUser() != null) {
						%> <a class="nav-link active" href='modifAccount'>Mon compte</a>
					</li>
				</ul>

				<%
				} else {
				%>
				</li>
				</ul>
				<a class=" btn btn-outline-success" href='loginAccount'>Connexion</a>

				<%}%>

				<%
				if (p != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("manager"))
						&& httpSession.getAttribute("parametres") == null) {
				%>
				<a class="btn btn-outline-success" href='addPlanning'>Créer son
					planning</a>
				<%}%>








			</div>
		</div>
	</nav>

  <form method="post" action="/upload" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" value="Upload" />
  </form>

</body>
</html>