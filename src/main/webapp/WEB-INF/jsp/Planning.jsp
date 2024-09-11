
<%@page import="org.springframework.ui.ModelMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList,java.util.List,org.springframework.security.core.context.SecurityContextHolder,org.springframework.security.core.Authentication,java.security.Principal,sae.planning.pojo.User,java.util.Calendar, java.util.Locale, java.time.ZoneId, java.time.ZonedDateTime, java.sql.*, java.util.HashMap"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">

<title>Super Planning</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">

<link
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css'
	rel='stylesheet'
	integrity='sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN'
	crossorigin='anonymous'>
<link rel='stylesheet' href='planningStyle.css'>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>



</head>
<body>

	<%
	Calendar calendar = Calendar.getInstance();
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	calendar.set(Calendar.MONTH, Integer.parseInt((String) request.getAttribute("month")));
	calendar.set(Calendar.YEAR, Integer.parseInt((String) request.getAttribute("year")));
	Calendar calendarpast = (Calendar) calendar.clone();
	Calendar calendarfutur = (Calendar) calendar.clone();
	calendarpast.add(Calendar.MONTH, -1);
	calendarfutur.add(Calendar.MONTH, 1);

	String titre = "" + calendar.getDisplayName(calendar.MONTH, calendar.LONG, Locale.FRANCE)+ " "
			+ calendar.get(calendar.YEAR);

	calendar.set(Calendar.DAY_OF_MONTH, 1);
	int daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	int startDayOfWeek = Math.floorMod(calendar.get(Calendar.DAY_OF_WEEK) - 2, 7) + 1;
	HttpSession httpSession = request.getSession();
	
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


	<h1 class="text-capitalize"><%=titre%>
	</h1>
	<div class="container-md">

		<div id='calendar'>
			<table class='table table-bordered'>
				<thead class='thead-dark'>
					<tr>
						<th scope="col\"><span class="full-text">Lundi</span> <span
							class="short-text">Lu</span></th>
						</th>
						<th scope="col\"><span class="full-text">Mardi</span> <span
							class="short-text">Ma</span></th>
						</th>
						<th scope="col\"><span class="full-text">Mercredi</span> <span
							class="short-text">Me</span></th>
						</th>
						<th scope="col\"><span class="full-text">Jeudi</span> <span
							class="short-text">Je</span></th>
						</th>
						<th scope="col\"><span class="full-text">Vendredi</span> <span
							class="short-text">Ve</span></th>
						</th>
						<th scope="col\"><span class="full-text">Samedi</span> <span
							class="short-text">Sa</span></th>
						<th scope="col\"><span class="full-text">Dimanche</span> <span
							class="short-text">Di</span></th>
						</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (int i = 1; i < startDayOfWeek; i++) {
					%>
					<td></td>
					<%
					}
					%>
					<%
					int day;
				

					for (int i = 1; i <= daysOfMonth; i++) {
						day = Math.floorMod(calendar.get(Calendar.DAY_OF_WEEK) - 2, 7) + 1;
						if (day == 1) {
					%>
					<tr>
						<%}
						
						%>




						<td><a
							href='planning-day?month=<%=calendar.get(calendar.MONTH)%>&year=<%=calendar.get(calendar.YEAR)%>&day=<%=i%>'><input
								class='buttoncase green' type='button' value='<%=i%>' /></a></td>




						<%
						if (day == 7) {
						%>
					</tr>
					<%
					}

					calendar.add(Calendar.DAY_OF_MONTH, 1);
					}

					calendar.add(Calendar.DAY_OF_MONTH, -1);
					for (int i = Math.floorMod(calendar.get(Calendar.DAY_OF_WEEK) - 2, 7) + 1; i < 7; i++) {
					%>
					<td></td>
					<%} %>
					</tr>
				</tbody>
			</table>
			<div class="flexbtn">
				<form method='get' action='planning'>
					<input type="hidden" name="month"
						value="<%=calendarpast.get(Calendar.MONTH)%>"> <input
						type="hidden" name="year"
						value="<%=calendarpast.get(Calendar.YEAR)%>">
					<button class="none" type='submit'>
						<i class="bi bi-caret-left-square"></i>
					</button>
				</form>
				<form method='get' action='planning'>
					<input type="hidden" name="month"
						value="<%=calendarfutur.get(Calendar.MONTH)%>"> <input
						type="hidden" name="year"
						value="<%=calendarfutur.get(Calendar.YEAR)%>">
					<button class="none" type='submit'>
						<i class="bi bi-caret-right-square"></i>
					</button>
				</form>
			</div>
		</div>

	</div>

</body>
</html>