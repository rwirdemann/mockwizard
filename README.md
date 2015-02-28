## Hinweis für Leser JavaSpektrum Artikels "Über das Testen von Microservices"
Der im Master-Branch dieses Repositories enthaltene Code wurde in den letzten Wochen massgeblich weiterentwickelt. 
Den im Artikel verwendeten Beispielcode finden Sie im Branch https://github.com/rwirdemann/orderservice/tree/javaspektrum.

## Mockwizard
Mockwizard ist ein Framework für das Mock-basierte Testen von Microservices.

## Voraussetzung 
* Maven
* MongoDB-Instanz auf 127.0.0.1:27017 gestartet
* Datenbank: orderservice

## Begriffe
* Stub: Antworten auf einen Methodenaufruf in einer zuvor defnierten Art und Weise 
* Mock: wie Stubs, erlauben aber zusätzlich die nachträgliche Verifikation  

## Testausführung
mvn clean test


