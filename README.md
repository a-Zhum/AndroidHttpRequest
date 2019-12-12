# LP3 - TP23

### Fonctionnement de l'application

*TP23* est une application mobile permettant la recherche de film,
celle ci dispose d'un champ qui permet de renseigner un titre de film, puis lors du clique sur le 
bouton de recherche une liste de films s'affichera selon le champ renseigné auparavant.(la totalité)

Cette liste affiche le titre du film ainsi que son année de création, en cliquant
sur un film on peut alors obtenir quelques détails de celui ci et accéder à ses détails sur le site [imdb](https://www.imdb.com/).


Les données sont récupéré sur [l'API d'OMDb](http://www.omdbapi.com/).

### Structure de l'application

L'application comporte deux activités : 

- `MainActivity` : l'activité principale, qui utilise un fragment pour rechercher les films
- `DetailsActivity` : l'activité de détails qui s'occupe d'afficher les details lié au film

Le modèle du film (`Movie`) est constitué d'un identifiant unique, un titre et d'une année de creation.

`MovieAdapter` permet de faire le lien entre la liste des 
films et la vue du RecyclerView.

## Choix de développements

J'ai utilisé Volley, ne l'ayant jamais utilisé.

J'ai décidé de récupérer des JSONOBJECT afin d'adapter directement mon modèle en renseignant les
champs que je veux uniquement.

J'ai trouvé la source `RecyclerItemClickListener`sur le internet, elle me permet de gerer
le CLickListerner de l'element RecyclerView.

J'utilise un Intent afin d'obtenir les détails d'un film et aussi pour naviguer vers la correspondance
sur le site [imdb](https://www.imdb.com/).

Concernant la manipulation de l'image j'ai choisi `Picasso` pour sa simplicité.

### Améliorations possibles

L'application est totalement fonctionnelle, il n'y a aucun problème à corriger dans son fonctionnement.

Cependant plusieurs test pourrait y être encore ajouté afin de mieux gérer les erreurs,
mais aussi revoir l'interface qui est extremement simpliste et non vraiment abouti.

On pourrait aussi envisagé d'ajouté des élements d'attentes afin que l'utilisateur ait un
retour visuel de l'état de sa requête.

On pourrait aussi envisagé de charge qu'une seul parti de la la liste de correspondance de film,
afin d'évité de saturer le quotas de requête à l'API.

Il serait également utile de gerer un effet visuel lorsque la recherche ne contient pas de poster
afin que l'utilisateur ne s'attend pas à ce qu'elle charge.
