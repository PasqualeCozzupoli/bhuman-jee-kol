bhuman-jee-kol
==============

Progetto utilizzato per le esercitazioni JEE

descrizione
-----------

l'applicazione mostra una lista di corsi e dei relativi studenti

utilizza Java 7, maven per effettuare la build e JBoss 7 per eseguire l'applicazione.

presentation logic
-----------

la presentation logic utilizza alcune funzionalità di JSF (core e facelets) ed è realizzata tramite AngularJS e Bootstrap

per maggiori informazioni:

* http://www.oracle.com/technetwork/java/javaee/documentation/index-137726.html
* http://docs.angularjs.org/tutorial
* http://getbootstrap.com/getting-started

business logic
-----------

ci sono alcuni WS RESTful utilizzati per ottenere i dati (realizzati secondo JAX-RS, classe CoursesRESTServices)

* /courses
* /course/id
* /course/id/attendees

i dati sono gestiti da uno store fittizio (classe DummyDataStore, implementato come un Singleton Session Bean)

TEST 1
-----------

Rimpiazzare l'implementazione dummy del data layer con una basata su DB (MySQL o PostgresSQL a tua scelta).

Tramite JPA, rendi le classi Student e Course persistenti. Fai attenzione alla relazione tra Course e Student (N:N, unidirezionale; con caricamento LAZY).

Implementa un SessionBean per rimpiazzare DummyDataStore con metodi analoghi.

Attenzione. Quando ritorni la lista dei corsi, non passare anche gli studenti nel json.

TEST 2
-----------

nella tabella dei corsi, c'è una colonna vuota. Questa deve valorizzare il rapporto tra studenti maschi e femmine per ogni corso.

Implementa una query in modo da ottenere il dato, e modifica la tabella in modo da visualizzarlo.


FINE
-----------
invia il risultato tramite pull-request

https://help.github.com/articles/using-pull-requests

ed invia a hr@bhuman.it una mail specificando il tuo username github per discutere il risultato.
