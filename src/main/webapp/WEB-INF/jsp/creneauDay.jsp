<%@page import="org.springframework.ui.ModelMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="sae.planning.pojo.Principal,java.text.SimpleDateFormat,java.util.*,java.util.Calendar, java.util.Locale, java.time.ZoneId, java.time.ZonedDateTime, java.sql.*, java.util.HashMap,sae.planning.pojo.Creneau,sae.planning.pojo.Reservation,sae.planning.repository.CreneauRepository,java.sql.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css'
	rel='stylesheet'
	integrity='sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN'
	crossorigin='anonymous'>
<link href='planningStyle.css' rel='stylesheet'>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<link href='day.css' rel='stylesheet'>
<%
Calendar calendar = (Calendar) request.getAttribute("calendar");
String titre = calendar.get(calendar.DAY_OF_MONTH) + " "
		+ calendar.getDisplayName(calendar.MONTH, calendar.LONG, Locale.FRENCH) + " " + calendar.get(calendar.YEAR);
%>

<title><%=titre%></title>
</head>
<body>
	<%
	HttpSession httpSession = request.getSession();
	Principal user = (Principal) httpSession.getAttribute("user");
	%>

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
						if (httpSession.getAttribute("user") != null) {
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
				if (httpSession.getAttribute("user") != null && user.getRole().equals("manager")
						&& httpSession.getAttribute("parametres") == null) {
				%>
				<a class="btn btn-outline-success" href='addPlanning'>Créer son
					planning</a>
				<%}%>


			</div>
		</div>
	</nav>
	<h1 class="text-capitalize"><%=calendar.get(calendar.DAY_OF_MONTH) + " "
		+ calendar.getDisplayName(calendar.MONTH, calendar.LONG, Locale.FRENCH) + " " + calendar.get(calendar.YEAR)%></h1>

	<table class='table table-bordered'>
		<thead class='thead-dark'>
			<tr>
				<th scope="col" class="text-capitalize"><h3><%=new SimpleDateFormat("EEEE").format(calendar.getTime())%></h3></th>
			</tr>
		</thead>
		<tbody>
			<%
			if (request.getAttribute("reservations") != null) {
				List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
				for (Reservation r : reservations) {
			%>
			<tr>
				<td>
					<%
					if (r.isAnnulee()) {
					%>
					<div class='creneau red'><%="\n" + ""%>
						<%
						} else {
						%>
						
						<div class='creneau'><%="\n" + ""%>
							<%
							}
							%>
							
							<h5><%=r.getCreneau().getHeure().toLocalTime().toString()%></h5>
							<img alt="Profil picture" src="<%=r.getUser().getPhoto()%>" width=100px height=100px>
							<p>
								Mr/Mme :
								<%=r.getUser().getNom().toString()%>
								<%=r.getUser().getPrenom().toString()%><br> email :
								<%=r.getUser().getEmail().toString()%>
								<br>tel :
								<%=r.getUser().getTelephone()%>
								<%
								if (r.isAnnulee()) {
								%>
								<br> Status : Annulée
							</p>
							</p>
							<form method='post' action='annuleManager'>
								<input type="hidden" name="uno"
									value="<%=r.getUser().getUno()%>"> <input type="hidden"
									name="cno" value="<%=r.getCreneau().getCno()%>"> <input
									type="hidden" name="month"
									value="<%=calendar.get(Calendar.MONTH)%>"> <input
									type="hidden" name="year"
									value="<%=calendar.get(Calendar.YEAR)%>"> <input
									type="hidden" name="day"
									value="<%=calendar.get(Calendar.DAY_OF_MONTH)%>">
								<button class='btn btn-primary' type='submit' disabled>Annuler</button>
							</form>
							<%
							} else {
							%>
							<br> Status : A venir
							</p>
							</p>
							<form method='post' action='annuleManager'>
								<input type="hidden" name="uno"
									value="<%=r.getUser().getUno()%>"> <input type="hidden"
									name="cno" value="<%=r.getCreneau().getCno()%>"> <input
									type="hidden" name="month"
									value="<%=calendar.get(Calendar.MONTH)%>"> <input
									type="hidden" name="year"
									value="<%=calendar.get(Calendar.YEAR)%>"> <input
									type="hidden" name="day"
									value="<%=calendar.get(Calendar.DAY_OF_MONTH)%>">
								<button class='btn btn-primary' type='submit'>Annuler</button>
							</form>
							<%
							}
							%>



						</div>
				</td>
			</tr>
			<%
			}
			}
			%>
	</table>


</body>
</html>