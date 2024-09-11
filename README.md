# SAE développement avancé - SuperPlanning

## 1) Bien démarrer

### Participants

Misplon Benoit

Petit Gwenael


###### Objectif du projet


A la manière de **prendreunrendezvous**, de **Doctolib** ou de nombreux sites de prise de rendez-vous mis en place
durant la crise COVID-19, l’objectif de ce projet consiste à réaliser un site internet de gestion de rendez-vous multiutilisateurs. Le site doit permettre d’une part de montrer aux utilisateurs les créneaux libres, d’autre part de permettre
aux utilisateurs de saisir et gérer leurs rendez-vous, et évidemment de n’autoriser des rendez-vous que s’ils respectent
les contraintes souhaitées pour ce site.


##### Demarage de l'application

Dézipper le dossier planning.zip
Ouvrir un terminal et se placer dans le dossier du projet ensuite il suffit de faire ces deux commandes :

    mvn clean package
    mvn spring-boot:run

    
#### Connexion à l'administrateur/user par défaut

Vous pouvez simplement utiliser celui déjà créer avec comme login : **a@example.com** et comme mot de passe : **admin**.
Si vous souhaitez modifier ce compte il vous suffira de modifier le fichier **import.sql** situé dans le dossier **src/main/ressource**.

Il existe aussi un utilisateur déjà créer avec comme login : **u@example.com** et comme mot de passe : **user**.
Les utilisateurs peuvent créer leur compte directement sur le site.


### Modification de variables

Vous pouvez aussi modifiez quelques variables dans le fichier **applications.properties** situé aussi dans le dossier **src/main/ressource**.
Vous pourrez y modifiez par exemple **spring.datasource.url=** pour changer l'url de la base de donné, **spring.datasource.username=** pour changer le nom et **spring.datasource.password=** pour changer le mot de passe.
Vous pouvez ensuite aussi changez les deux variables **spring.servlet.multipart.max-file-size=** et **spring.servlet.multipart.max-request-size=** pour changer la taille maximum pour les photos de profil de vos utilisateurs.


### Stockage des photos de profils

Veuillez
Pour ce qui est des photos de profil veuillez à bien vérifiez que la photo **ProfilPictures.png** soit toujours présente dans le dossier **src/main/webapp/upload**.
Veuillez à vider ce meme dossier à chaque fermeture de l'application hormis bien sur celle citée ci-dessus.

## 2) Technos utilisées

	- Java EE
	- SQL
	- JavaScript
	- HTML/CSS
	- Hibernate
	- Spring boot
	- Spring Security
	- H2
	- JPA
	- JSP

## 3) Points difficiles

Le passage du projet en spring, il nous à poser problème car celui-ci avait crée des erreurs dans tout notre code donc nous avons décidé de recréer un nouveau projet spring.
Un des points difficiles que nous avons abordés était de lier le système de connexion de spring security avec notre propre base de donnés, pour régler ce problème nous avons donc créer notre propre userDetailsService pour pouvoir vérifier que l'utilisateur existe dans notre base de donnés.


## 4) Améliorations potentielles

Ajouter le changement de couleur lorsqu'une journée est pleine.
Possibilité d'ajouter un créneau spécifique une fois le planning déjà créer.
