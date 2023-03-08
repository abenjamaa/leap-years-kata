# KATA Leap years

## Architecture

![archi.png](src%2Fmain%2Fresources%2Fimage%2Farchi.png)
```
Le projet est découpé de la manière suivante :
  
  - controller : repertoire contenant les resources exposés vers l'exterieur
      Sous le répertoire on trouve :
       - V1 : pour la gestion du versionning de l'api
       - dto : contient les objets à envoyer lors des appels Rest
       - YearsResource : endpoint exposé
       - HolidaysMapper : mapper qui transfome l'objet de la couche métier vers le Dto exposé.
  
  - service : contient la couche métier
       sous le repertoire on trouve :
        - YearsService : le service qui permet d'appeler l'api gouv pour les jours fériés
        - model : contient les objets de la couche métier
  
  - exception : contient la gestion des erreurs
       sous le repertoire on trouve :
        - handler :
            - RestExceptionHandler : permet d'intercepter toutes les exceptions afin de les formatées de la manière suivante:
               {
                    "message": "< toto > is not a number",
                    "error": "fr.abj.leap.years.kata.exception.NotANumberException",
                    "path": "/v1/years/zezez/leap"
               }
            - NotANumberException : exception levée lorsq'on essaye de formater une variable qui n'est pas un nombre.
  
  - resources : contient les resources de l'application
        - application.properties : contient des variables. 
            par exemple : api.gouv.calendrier 
        - image : pour illustrer l'architecture du projet dans le README
        
```

## Prérquis
```
Java 17
Apache Maven 3.6.3
```
## Démarrage spring boot
```
    - Se mettre à la racine du dossier leap.years.kata
    - lancer la commande : mvn spring-boot:run
```
## open api

Vous pouvez trouver la documentation swagger aux adresses suivantes :  
http://localhost:8080/v3/api-docs 

http://localhost:8080/swagger-ui/index.html#/

## Postman
Vous pouvez utiliser le curl suivant pour vérifier si l'année est bissextile  :
```
curl --location --request GET 'http://localhost:8080/v1/years/3498/leap'
```

Vous pouvez utiliser le curl suivant pour récuppérer les jours feriés en france métropolitaine d'une année  :
```
curl --location --request GET 'http://localhost:8080/v1/years/2023/holidays'
```~~~~