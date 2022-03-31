# Mini-review de l'implémentation fournie par la Team Rocket

## Problèmes fonctionnels

Cette implémentation ne permet de créer que 3 Pokémons différents, `Ash's Pikachu`, `Missing n°` et `Bulbasaur`, ce qui est loin des 150 Pokémons souhaités.  
N'importe quel index de Pokémon demandé autre que `1` ou `-1` renvoi un `Missing n°`, seul l'index `1` renvoie bien le bon type de Pokémon.  
Pour tous les Pokémons sauf `Ash's Pikachu` les statistiques sont complètement aléatoires avec une valeur comprise entre 0 et 100 aussi bien pour `attack` que `defense` et `stamina`.  
Pour `Ash's Pikachu` c'est encore pire puisque toutes ses statistiques sont à `1000`, certainement une manière pour la Team Rocket de mettre la main sur ce Pokémon qu'ils convoitent tant...

## Problèmes de sécurité

Cette implémentation fait usage de la bibliothèque `org.apache.commons.collections4` dans sa version 4.0.  
Cette version de la bibliothèque a une vulnérabilité connue (`CVE-2015-6420`) et elle est donc à remplacer par une version plus récente, purgée de cette vulnérabilité.  
Ce problème peut être révélé au travers d'une analyse de vulnérabilité des dépendences du projet, faite ici par l'outil `dependency-check` de OWASP.

## Problèmes de performances

En plus d'être complètement aléatoire et donc fausse la façon dont sont calculés les statistiques des Pokémons est terriblement inefficace.  
Plutôt que de directement générer une valeur aléatoire entre 0 et 100, un million (1 000 000) valeurs de 0 ou 1 sont générées, sommées et enfin la somme est divisée par 10 000 pour revenir à un maximum de 100.
