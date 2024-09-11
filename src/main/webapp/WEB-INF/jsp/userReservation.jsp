<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="sae.planning.pojo.Reservation,sae.planning.repository.CreneauRepository,java.sql.Date, java.util.*,sae.planning.pojo.User,sae.planning.pojo.Principal"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes réservations</title>
<link
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css'
	rel='stylesheet'
	integrity='sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN'
	crossorigin='anonymous'>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	
<link href='planningStyle.css' rel='stylesheet'>
</head>
<body>
<%
HttpSession httpSession = request.getSession();
	Principal user = (Principal) httpSession.getAttribute("user");%>
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href='/'><img src="favicon-32x32.png"/></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	
	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
     
    
	<%if(httpSession.getAttribute("user") != null){ %>
	<a class="nav-link active" href='modifAccount'>Mon compte</a>
	</li>
	</ul>
	
	
      
    </div>
  </div>
</nav>
	
	
	<h1>Mes réservations</h1>
	<table class='table table-bordered'>
		<tbody>
			<%
			if(request.getAttribute("reservations")!=null){
			List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
			for (Reservation r : reservations) {
			%>
			<tr>
				<%
				if (r.isAnnulee()) {
				%>
				<td><div class='creneau red'><%="\n" + ""%>
						<%
						} else {
						%>
					
				<td><div class='creneau'><%="\n" + ""%>
						<%
						}
						%>
						<h5><%=r.getCreneau().getDate()%></h5>
						<h5><%=r.getCreneau().getHeure()%></h5>
						<p>
							Statut:
							<%
						String statut = "";
						if (r.isAnnulee()) {
							statut = "annulée";
						%>
							<%=statut%></p>
						<%
						} else {
						%>
						<%
						statut = "à venir";
						%>
						<%=statut%></p>
						<form method='post' action='annule'>
							<input type="hidden" name="uno" value="<%=r.getUser().getUno()%>">
							<input type="hidden" name="cno" value="<%=r.getCreneau().getCno()%>">
							<button class='btn btn-primary' type='submit'>Annuler</button>
						</form>
						<%}%>


					</div></td>
			</tr>
			<%}}else{%>
			<tr>
			<td>
				Aucune reservations
			</td>
			</tr>
				
			<%}}%>
		</tbody>
	</table>

</body>
</html>