<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="sae.planning.pojo.Reservation,sae.planning.pojo.Principal,java.text.SimpleDateFormat,java.util.*,java.util.Calendar, java.util.Locale, java.time.ZoneId, java.time.ZonedDateTime, java.sql.*, java.util.HashMap,sae.planning.pojo.Creneau,sae.planning.repository.CreneauRepository,java.sql.Date"%>
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
			if (user != null && user.getRole().equals("manager")) {
				if (request.getAttribute("creneaux") != null) {
					List<Creneau> creneaux = (List<Creneau>) request.getAttribute("creneaux");
					List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
			%>
			<tr>
				<td>
					<div class='creneau'>
						<form action="deleteAllSlot" method="post">
							<input type="hidden" name="month"
								value="<%=calendar.get(Calendar.MONTH)%>"> <input
								type="hidden" name="year"
								value="<%=calendar.get(Calendar.YEAR)%>"> <input
								type="hidden" name="day"
								value="<%=calendar.get(Calendar.DAY_OF_MONTH)%>">
								<%if(creneaux.size() == 0){
									%>
									<button class='btn btn-primary' id="deleteButton" type='submit' disabled>supprimer
								les creneaux</button>
								<%}else{ %>
							<button class='btn btn-primary' id="deleteButton" type='submit'>supprimer
								les creneaux</button>
								<%} %>
						</form>
						<!-- <script type="text/javascript">
				const deleteButton =  document.getElementById("deleteButton");
				deleteButton.addEventListener("click",()=>{

				  const checkboxes = document.querySelectorAll("input.selected:checked");
				  if(checkboxes.length>0){
				    const confirmDelete = confirm("Voulez vous vraiment supprimer ces créneaux ?");
				    if(confirmDelete){
				      checkboxes.forEach(checkbox=>{
				        const row = checkbox.closest('tr');
				        row.remove();
				        
				      })
				    }
				  }
				  else{
				    alert("Aucun créneau selectionné")
				  }
				});
				
				</script> -->
					</div> <%
 try {
 	if (request.getAttribute("fail").equals("true")) {
 %>
					<div class="alert alert-danger" role="alert">Pas assez de
						places disponibles</div> <%
 }
 } catch (Exception e) {

 }
 %>
				</td>
			</tr>
			<%
			for (Creneau r : creneaux) {
			%>

			<tr>
				<td>

					<div class='creneau'><%="\n" + ""%>
						<form action="deleteSlot" method="post">
							<input type="hidden"
								name="creneau" value="<%= r.getCno()%>"/> <input type="hidden" name="month"
								value="<%=calendar.get(Calendar.MONTH)%>"> <input
								type="hidden" name="year"
								value="<%=calendar.get(Calendar.YEAR)%>"> <input
								type="hidden" name="day"
								value="<%=calendar.get(Calendar.DAY_OF_MONTH)%>">
								<button class='btn btn-primary' id="deleteButton" type='submit'>supprimer</button>
						</form>
						<h5><%=r.getHeure().toLocalTime().toString()%></h5>
						<p>
							Nombre de places restante :
							<%=r.getPlaces_restantes()%>
						</p>
						</p>
						<form method='post' action='reservationDay'>
							<input type="hidden" name="month"
								value="<%=calendar.get(Calendar.MONTH)%>"> <input
								type="hidden" name="year"
								value="<%=calendar.get(Calendar.YEAR)%>"> <input
								type="hidden" name="day"
								value="<%=calendar.get(Calendar.DAY_OF_MONTH)%>"> <input
								type="hidden" name="heure" value="<%=r.getHeure()%>">
							<button class='btn btn-primary' type='submit'>Voir les
								reservations</button>
						</form>
						<%
						}
						%>


					</div>
				</td>
			</tr>
			<%
			}
			} else {
			if (request.getAttribute("creneaux") != null) {
			List<Creneau> creneaux = (List<Creneau>) request.getAttribute("creneaux");

			creneaux = (List<Creneau>) request.getAttribute("creneaux");
			for (Creneau c : creneaux) {
			%>
			<tr>
				<td><div class='creneau'><%="\n" + ""%>
						<h5><%=c.getHeure().toLocalTime().toString()%></h5>
						<p>
							Places restantes:
							<%=c.getPlaces_restantes()%></p>
						<%
						if (c.getPlaces_restantes() == 0) {
						%>
						<form class="row gy-2 gx-3 align-items-center" method="post"
							action="reserve">
							<input type="hidden" name="id" value="<%=c.getCno()%>"> <input
								type="hidden" name="year"
								value="<%=calendar.get(Calendar.YEAR)%>"> <input
								type="hidden" name="month"
								value="<%=calendar.get(Calendar.MONTH)%>"> <input
								type="hidden" name="day"
								value="<%=calendar.get(Calendar.DAY_OF_MONTH)%>">

							<div class="col-auto">
								<label for="name">nombre de personnes :</label>
							</div>
							<div class="col-auto">
								<div class="form-check">
									<input class="form-control" type="number" name="nb_personnes"
										id="nb_personnes" required />
								</div>
							</div>
							<div class="col-auto">
								<button class='btn btn-primary' type='submit' disabled>Réserver</button>
							</div>
						</form>
						<%
						} else {
						%>
						<form class="row gy-2 gx-3 align-items-center" method="post"
							action="reserve">
							<input type="hidden" name="id" value="<%=c.getCno()%>"> <input
								type="hidden" name="year"
								value="<%=calendar.get(Calendar.YEAR)%>"> <input
								type="hidden" name="month"
								value="<%=calendar.get(Calendar.MONTH)%>"> <input
								type="hidden" name="day"
								value="<%=calendar.get(Calendar.DAY_OF_MONTH)%>">

							<div class="col-auto">
								<label for="name">nombre de personnes :</label>
							</div>
							<div class="col-auto">
								<div class="form-check">
									<input class="form-control" type="number" name="nb_personnes"
										id="nb_personnes" required />
								</div>
							</div>
							<div class="col-auto">
								<button class='btn btn-primary' type='submit'>Réserver</button>
							</div>
						</form>
						<%}%>
					</div></td>
			</tr>
			<%
			}
			}
			}
			%>
		</tbody>
	</table>


</body>
</html>