# quizapp
![alt tag](Screenshots/WelcomePage.jpg)
[![Travis-CI Status]][travis]
## Motivation

Mobilgeräte wie Smartphones und Tablets sind aus unserem Alltag kaum mehr wegzudenken und ermöglichen es,
Anwendungssoftware (Apps) überall einzusetzen. In Schulen haben Schüler oft das Problem das die 
Lehreinheiten nicht interaktiv genug sind und die Bewertung von einem Lehrer durchgeführt werden muss.

Das heiEDUCATION Projekt ist ein gemeinsames Projekt der Universität Heidelberg und der Pädagogischen Hochschule Heidelberg. 
Im Rahmen dieses Projekts soll das Erlernen von Sprache im naturwissenschaftlichen Kontext untersucht werden. 
Dafür eine Android App entwickelt, die es ermöglicht naturwissenschaftliche Fachsprache ohne Verwendung der Muttersprache des Lernenden zu vermitteln.
Die App kann im Unterricht eingesetzt werden ohne das eine Verbindung in das Internet von Nöten ist. Die App ist lauffähig auf einen Raspberry Pi und 
von dort aus dann erreichbar auf den Lokalen Geräten. Hiermit kann man eine Sichere Lernumgebung für Schüler sichern. 

## Beschreibung der Entwicklung

Bei der Entwicklung der App werden Methoden kontinuierlicher Softwareentwicklung eingesetzt. Zur Sicherung der Qualität wurde das Testsystem in der 
kontinuierlichen Softwareentwicklung eingebunden so das nach jeder Version in der Versionsverwaltung (Git) Regressionstest durchlaufen wurden. Test der 
Erreichbarkeit und Nutzbarkeit wurden mit Hilfe von Selenium und Selenium Grid durchgeführt. Komponenten Test wurden mit Hilfe von JUnit Test durchgeführt.

# Benutzerbeschreibung
## Installationsbeschreibung
## Lehrer nutzung
![alt tag](Screenshots/LehreStart.jpg)
Lehrer können die Ergebnisse der Schüler im Lehrerzugang sich anzeigen lassen. Der Lehrerzug ermöglicht zwischen allgemeine Ergebnissen, für alle Aufgaben, 
sich ebenso die Ergebnisse für jeden Schüler separat anzeigen zu lassen. Eine Dritte Möglichkeit bietet hier die Metrik Ansicht in der Visuell 
die anzahlen der Richtigen bzw. Falschen Antworten dargestellt wird.

## Administrator nutzung
![alt tag](Screenshots/AdministratorLogin.jpg)
Der Administrator Zugang ermöglicht die Verwaltung des Systems. Nach dem Einlogen kann der Administrator neben der Aufgabenerstellung der 3 Typen 
(Richtig/Falsch- , Satzverbindungs- und Beschriftungs-Aufgaben)  ebenso noch die Verwendeten Informationen Verwalten. Unter den Einstellung ist es möglich die 
Datenbank zu löschen und eine Neue Datenbank zu integrieren.

## Schüler nutzung
![alt tag](Screenshots/SchuelerLogin.jpg)
Schüler haben, nach dem sie sich Registriert haben, die Möglichkeit
Aufgaben zu lösen. Hier können sie zwischen 3 Typen von Aufgaben auswählen
(Richtig/Falsch- , Satzverbindungs- und Beschriftung-Aufgaben) ebenso wie die
Schwierigkeit der Aufgaben. Nach der Bearbeitung einer Aufgaben bekommt der
Schüler direktes Feedback und hat die Möglichkeit sich weitere Aufgaben aus dem
Katalog zu suchen und diese weiter zu bearbeiten.
