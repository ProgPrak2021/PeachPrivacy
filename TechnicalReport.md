# PeachPrivacy Technical Report

Submitted by Lisa Annett Barthel, Samy Abdellah Hamdad,
Patrick Wolfgang Hein und Paskal Paesler

# Projektorganisation

Dass die Koordination und Kommunikation innerhalb eines Teams eine gro�e
Rolle spielen, sollte keine gro�e �berraschung sein. Daf�r hat das Peach Privacy Team den Dienst Trello verwendet. Das Trelloboard ist ein typisches Werkzeug f�r die agile Entwicklung und erm�glichte es uns die Aufgaben asynchron an die Teammitglieder zu verteilen. Au�erdem hatten wir so die M�glichkeit uns gegenseitig zu signalisieren, wenn Aufgaben blockiert waren oder endlich begonnen werden konnten. Durch die schrittweise Verfeinerung der Aufgaben gelang es uns das Gesamtsystem besser zu strukturieren. Trello verwaltet unsere Boards zu den Themen Frontend, Backend und Folien. Im Bereich der Folien wurden unsere Zuarbeiten f�r die Vorbereitung auf Zwischenpr�sentation und Abschlusspr�sentation im Rahmen unseres Projektes verwaltet. Mittels virtueller Meetings haben wir uns f�r die jeweiligen Pr�sentationstermine abgestimmt und letzte Anpassungen vorgenommen. Auf dem Backendboard verwalteten wir alle Teilaufgaben, die im Backend angefallen sind. Sei es Userservice, Tiltservice oder auch Templateservice. Alles war dort zu finden. Ein weiteres Board war unser Frontendboard. F�r unsere Entwicklung war es wichtig klar zu strukturieren, welche Aufgaben bez�glich der GUI alles zu erf�llen sind. Ob Bestandteile der Landingpage oder die konkreten Formulare. Wer sich um welche Funktionalit�t aktuell k�mmert oder was noch offen ist, alles wurde auf unserem Board
verwaltet. Daf�r erm�glicht Trello eine einfache Erstellung von Aufgaben und Zuweisung von Teammitgliedern zu diesen Aufgaben. Eine �nderung des Bearbeitungszustands ist dabei so einfach wie nur m�glich: Verschieben von einzelnen Aufgaben in unterschiedliche Bereiche bietet einen klaren �berblick. 

Aber ein Tool zur Entwicklung einer kooperativen Projektverwaltung ist doch nicht das Einzige, was bei der Entwicklung eines solchen Projektes ben�tigt wird. F�r die Sicherung des Programmcodes sowie die gemeinsame Arbeit an diesem war uns auch noch ein weiteres Tool eine gro�e Hilfe: Git. Besonders der Umgang mit Featurebranches war f�r uns zum Teil eine neue, aber sinnvolle Erfahrung. Durch die Erstellung von einzelnen Branches, die nur f�r die Entwicklung von ganz spezifischen Bausteinen oder f�r die L�sung von Problemen wie der Reset eines Passwortes im Rahmen der Benutzerverwaltung verwendet werden. Erst wenn sichergestellt ist, dass der entwickelte oder korrigierte Code tats�chlich funktioniert, wird er ins Gesamtprojekt eingef�gt und auf unserer offiziellen Webseite: https://peachprivacy.dev ver�ffentlicht.

# Architektur 

## Services

### UserService

### TemplateService

### TiltService

### Andere

## Dependencies
# Algorithmus

## Problemstellung

## Implementierung

# Design und Entwicklung des Frontends

Eine lange Reise: Das war die Entwicklung des Designs und des finalen Zustands des Frontends wirklich. Alles begann mit einzelnen Vorschl�gen wie das Ganze einmal aussehen k�nnte: Alles unter Verwendung von HTML und CSS. Einzelne Sheets mit kaum Funktionalit�t. Dies f�rderte die Festigung der ersten Erfahrungen mit HTML und CSS, um sp�ter die komplexen �berlagerungen von Styles in einem Framework besser verstehen zu k�nnen. Erst nach einer m�hsamen Bestimmung eines ad�quaten Logos: Unserem Pfirsich und dem daraus ableiteten Farbschema hatte das ganze Projekt ein Konzept. 

Heutzutage werden solche Applikationen nicht mehr von Hand erstellt, sondern es werden fertige Frameworks verwendet, die einem die Arbeit erheblich erleichtern und somit einen schnelleren Erfolg m�glich machen. Die Bestimmung eines geeigneten Frameworks f�r die Entwicklung hatte also f�r uns eine besonders hohe Bedeutung. Dabei viel unsere Wahl auf das Framework Vue. Bevor wir uns f�r die Verwendung des Material Kits durch Creative Tim, die dynamische Realisierung der Formulare entschieden haben bzw. bis unser Backendteam so weit war, dass dies �berhaupt m�glich war, wurden die einzelnen Formulare bez�glich der einzelnen Bestandteile der Datenschutzerkl�rungen per Hand implementiert. Das manuelle Erstellen der Formulare diente dabei dem Entwickeln des Verst�ndnisses der Funktionsweise von Vue. Das beinhaltet insbesondere die Initialisierung von Properties oder die Weitergabe von Events. Ohne dabei die Basisfunktionalit�ten der eingesetzten Frameworks zu verstehen, ist sp�ter ein Scheitern an Kleinigkeiten zu erwarten. 

Der finale Zustand des Frontends verwendet aber das bereits erw�hnte Material Kit. Dieses stellt verschiedene Bausteine wie Navbar, Loginmodale und Dropdown Funktionalit�ten bereit, die einfach f�r die eigene Webseite angepasst werden k�nnen. Zu diesem Zeitpunkt erfolgte nicht nur optisch eine komplette Umstrukturierung des Frontends, sondern auch im entsprechendem Programmcode. Mithilfe dieser neuen Bausteine entstand eine ganze neue Landingpage mit Hintergrundbild und Parallax, die neue Navbar ist nun stylischer und �bersichtlicher als zuvor: Sogar mit transparenten Peach Privacy Logo. Jetzt sind die Formulare nicht mehr f�r jedes Element einzeln gerendert, sondern werden dynamisch passend zur Vererbung und der damit verbunden unterschiedlichen auszuf�llenden Formularfeldern, die Informationen vom Backend erh�lt, gerendert. Dabei wird f�r jedes Feld der Type entsprechend ermittelt und dementsprechend das richtige Eingabeelement angezeigt. Per Wizard k�nnen einzelne Gruppen von Eingabefeldern des Formulars Schritt f�r Schritt ausgef�llt werden. �ber eine Fortschrittsanzeige erh�lt der Nutzende immer einen
�berblick �ber den aktuellen Stand und bekommt eine Hilfe f�r das Ausf�llen. Daf�r haben wir Verweise zu weiteren Informationen in Form eines Fragezeichens verwendet.

# User Experience
