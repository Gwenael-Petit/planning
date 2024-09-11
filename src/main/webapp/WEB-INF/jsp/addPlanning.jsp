<html>
<head>
    <meta charset="UTF-8">
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN' crossorigin='anonymous'>
    <link href="test.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    
    <script src="checkbox.js" defer></script> 
    <title>Création du super planning</title>
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
<h2 class="mx-auto p-2" style="width: fit-content;">Creer votre planning !</h2>
<div class="container">
<form action="addPlanning" method="post" class="form-example">
    <div class="mb-3">
      <label for="name">Nombre de place disponibles par creneau</label>
      <input class="form-control" type="number" name="nb_places" id="nb_places" required />
    </div>
    <div class="mb-3">
      <label for="name">Duree d'un creneau (en minutes)</label>
      <input class="form-control" type="number" name="duree" id="duree" required />
    </div>
    <div class="mb-3">
      <label for="time">Veuillez choisir vos jours de travails : </label>
      <br>
        <input type="checkbox" class="form-check-input lundi" id="exampleCheck1">
      <label > : Lundi</label>
      <input type="hidden" name="lundi" id="lundiTime" value="2" disabled />
      <br>
        <input type="checkbox" class="form-check-input mardi" id="exampleCheck1">
      <label > : Mardi</label>
      <input type="hidden" name="mardi" id="mardiTime" value="3" disabled />
      <br>
        <input type="checkbox" class="form-check-input mercredi" id="exampleCheck1">
      <label > : Mercredi</label>
      <input type="hidden" name="mercredi" id="mercrediTime" value="4" disabled />
      <br>
        <input type="checkbox" class="form-check-input jeudi" id="exampleCheck1">
      <label > : Jeudi</label>
      <input type="hidden" name="jeudi" id="jeudiTime" value="5" disabled />
      <br>
        <input type="checkbox" class="form-check-input vendredi" id="exampleCheck1">
      <label > : Vendredi</label>
      <input type="hidden" name="vendredi" id="vendrediTime" value="6" disabled />
      <br>
        <input type="checkbox" class="form-check-input samedi" id="exampleCheck1">
      <label > : Samedi</label>
      <input type="hidden" name="samedi" id="samediTime" value="7" disabled />
      <br>
        <input type="checkbox" class="form-check-input dimanche" id="exampleCheck1">
      <label > : Dimanche</label>
      <input type="hidden" name="dimanche" id="dimancheTime" value="1" disabled />
     
      
    
    <div class="mb-3">
        <label for="time">Veuillez choisir vos horraires de travail : </label>
        <br>
          <input type="checkbox" class="form-check-input matin" id="exampleCheck1">
        <label for="time">Matin : </label>
        <input type="time" name="debutmat" id="matinTime" disabled />
        <input type="time" name="finmat" id="matinTime" disabled />
        <br>
        <input type="checkbox" class="form-check-input aprem" id="exampleCheck1">
        <label for="time">Apres-midi : </label>
        <input type="time" name="debutap" id="apremTime" disabled />
        <input type="time" name="finap" id="apremTime" disabled />
      </div>

     
     
    <div class="mb-3">
      <input type="submit" class="btn btn-primary" value="Submit!" />
    </div>
    <%
			try{
			if(request.getAttribute("error")!= null){ %>
				<div class="alert alert-danger" role="alert">
				  <%=request.getAttribute("error") %> 
				</div>
			<%}}catch(Exception e){
			
			}%>
  
  </form>
  </div>
  
</body>
</html>
