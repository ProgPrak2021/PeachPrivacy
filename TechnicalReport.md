# PeachPrivacy Technical Report

Submitted by Lisa Annett Barthel, Samy Abdellah Hamdad,
Patrick Wolfgang Hein und Paskal Paesler

# Projektorganisation

Dass die Koordination und Kommunikation innerhalb eines Teams eine große
Rolle spielen, sollte keine große Überraschung sein. Dafür hat das Peach Privacy Team den Dienst Trello verwendet. Das Trelloboard ist ein typisches Werkzeug für die agile Entwicklung und ermöglichte es uns die Aufgaben asynchron an die Teammitglieder zu verteilen. Außerdem hatten wir so die Möglichkeit uns gegenseitig zu signalisieren, wenn Aufgaben blockiert waren oder endlich begonnen werden konnten. Durch die schrittweise Verfeinerung der Aufgaben gelang es uns das Gesamtsystem besser zu strukturieren. Trello verwaltet unsere Boards zu den Themen Frontend, Backend und Folien. Im Bereich der Folien wurden unsere Zuarbeiten für die Vorbereitung auf Zwischenpräsentation und Abschlusspräsentation im Rahmen unseres Projektes verwaltet. Mittels virtueller Meetings haben wir uns für die jeweiligen Präsentationstermine abgestimmt und letzte Anpassungen vorgenommen. Auf dem Backendboard verwalteten wir alle Teilaufgaben, die im Backend angefallen sind. Sei es Userservice, Tiltservice oder auch Templateservice. Alles war dort zu finden. Ein weiteres Board war unser Frontendboard. Für unsere Entwicklung war es wichtig klar zu strukturieren, welche Aufgaben bezüglich der GUI alles zu erfüllen sind. Ob Bestandteile der Landingpage oder die konkreten Formulare. Wer sich um welche Funktionalität aktuell kümmert oder was noch offen ist, alles wurde auf unserem Board
verwaltet. Dafür ermöglicht Trello eine einfache Erstellung von Aufgaben und Zuweisung von Teammitgliedern zu diesen Aufgaben. Eine Änderung des Bearbeitungszustands ist dabei so einfach wie nur möglich: Verschieben von einzelnen Aufgaben in unterschiedliche Bereiche bietet einen klaren Überblick. 

Aber ein Tool zur Entwicklung einer kooperativen Projektverwaltung ist doch nicht das Einzige, was bei der Entwicklung eines solchen Projektes benötigt wird. Für die Sicherung des Programmcodes sowie die gemeinsame Arbeit an diesem war uns auch noch ein weiteres Tool eine große Hilfe: Git. Besonders der Umgang mit Featurebranches war für uns zum Teil eine neue, aber sinnvolle Erfahrung. Durch die Erstellung von einzelnen Branches, die nur für die Entwicklung von ganz spezifischen Bausteinen oder für die Lösung von Problemen wie der Reset eines Passwortes im Rahmen der Benutzerverwaltung verwendet werden. Erst wenn sichergestellt ist, dass der entwickelte oder korrigierte Code tatsächlich funktioniert, wird er ins Gesamtprojekt eingefügt und auf unserer offiziellen Webseite: https://peachprivacy.dev veröffentlicht.

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

Eine lange Reise: Das war die Entwicklung des Designs und des finalen Zustands des Frontends wirklich. Alles begann mit einzelnen Vorschlägen wie das Ganze einmal aussehen könnte: Alles unter Verwendung von HTML und CSS. Einzelne Sheets mit kaum Funktionalität. Dies förderte die Festigung der ersten Erfahrungen mit HTML und CSS, um später die komplexen Überlagerungen von Styles in einem Framework besser verstehen zu können. Erst nach einer mühsamen Bestimmung eines adäquaten Logos: Unserem Pfirsich und dem daraus ableiteten Farbschema hatte das ganze Projekt ein Konzept. 

Heutzutage werden solche Applikationen nicht mehr von Hand erstellt, sondern es werden fertige Frameworks verwendet, die einem die Arbeit erheblich erleichtern und somit einen schnelleren Erfolg möglich machen. Die Bestimmung eines geeigneten Frameworks für die Entwicklung hatte also für uns eine besonders hohe Bedeutung. Dabei viel unsere Wahl auf das Framework Vue. Bevor wir uns für die Verwendung des Material Kits durch Creative Tim, die dynamische Realisierung der Formulare entschieden haben bzw. bis unser Backendteam so weit war, dass dies überhaupt möglich war, wurden die einzelnen Formulare bezüglich der einzelnen Bestandteile der Datenschutzerklärungen per Hand implementiert. Das manuelle Erstellen der Formulare diente dabei dem Entwickeln des Verständnisses der Funktionsweise von Vue. Das beinhaltet insbesondere die Initialisierung von Properties oder die Weitergabe von Events. Ohne dabei die Basisfunktionalitäten der eingesetzten Frameworks zu verstehen, ist später ein Scheitern an Kleinigkeiten zu erwarten. 

Der finale Zustand des Frontends verwendet aber das bereits erwähnte Material Kit. Dieses stellt verschiedene Bausteine wie Navbar, Loginmodale und Dropdown Funktionalitäten bereit, die einfach für die eigene Webseite angepasst werden können. Zu diesem Zeitpunkt erfolgte nicht nur optisch eine komplette Umstrukturierung des Frontends, sondern auch im entsprechendem Programmcode. Mithilfe dieser neuen Bausteine entstand eine ganze neue Landingpage mit Hintergrundbild und Parallax, die neue Navbar ist nun stylischer und übersichtlicher als zuvor: Sogar mit transparenten Peach Privacy Logo. Jetzt sind die Formulare nicht mehr für jedes Element einzeln gerendert, sondern werden dynamisch passend zur Vererbung und der damit verbunden unterschiedlichen auszufüllenden Formularfeldern, die Informationen vom Backend erhält, gerendert. Dabei wird für jedes Feld der Type entsprechend ermittelt und dementsprechend das richtige Eingabeelement angezeigt. Per Wizard können einzelne Gruppen von Eingabefeldern des Formulars Schritt für Schritt ausgefüllt werden. Über eine Fortschrittsanzeige erhält der Nutzende immer einen
Überblick über den aktuellen Stand und bekommt eine Hilfe für das Ausfüllen. Dafür haben wir Verweise zu weiteren Informationen in Form eines Fragezeichens verwendet.

# User Experience
