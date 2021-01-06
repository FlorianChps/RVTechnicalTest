# RVTechnicalTest

# Introduction
Ce test technique a été réalisé avec les librairies suivantes :

# Librairies
- Retrofit + Room + RXJava2 + RXAndroid + Hilt + Architecture Components (LiveData / ViewModel)
- Mockk + JUnit + architecture components testing

# Choix / Overview
Je ne travaille pas directement dans ma vue avec les objets Moshi mais directement via des Models
propres correspondant plus à ce que l'on trouve dans l'UI.
Côté abstraction, j'ai fait le choix d'abstraire uniquement les Repository.

Vous pourrez trouver également un mode Night avec des couleurs que j'ai choisi un petit peu
arbitrairement par rapport au design fourni en mode clair.

Egalement, petit problème au  niveau de l'icone étoile lors de son export, lorsqu'il n'est pas rempli,
le svg semble corrompu, même problème pour l'image représentant le métro, je les ai mise telles qu'elles
étaient exportables du coup :)

NB : En ce qui concerne l'API, certaines stations renvoient un id qui ne permet pas d'effectuer la 
deuxième requête, c'est pourquoi parfois l'écran détails affiche uniquement un Toast avec une erreur
et non une liste avec l'ensemble des prochains départs.