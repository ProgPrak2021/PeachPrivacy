<template>
  <div class="wrapper">
    <div id="nav-tabs">
        <div class="md-layout-item md-size-100 md-small-size-100">
          <h3><small class="text-primary">Das Produkt Peach Privacy</small></h3>
          <nav-tabs-card no-label tabs-plain>
            <template slot="content">
              <md-tabs class="md-primary" md-alignment="left">
                <md-tab id="tab-home" md-label="Was ist Peach Privacy?">
                  <p align="left">
                   Peach Privacy bietet dir die Möglichkeiten, die du brauchst, um deinen Kunden mitzuteilen, welche Daten du über sie speicherst. Und das alles DSGVO-konform!
                  </p>
                </md-tab>
                <md-tab id="tab-pages" md-label="Mehr zum Produkt">
                  <p align="left">
                    Auf unserer Seite kannst du step by step Projekte erstellen, um mit Vorlagen deine eigene Datenschutzseite zu modellieren. 
                    Diese Vorlagen können in einem objektorientierten Stil von (vordefinierten) Vorlagen erben und von dir selbst erstellt werden. 
                    Jede Vorlage kann abstrakte Felder/Werte definieren, die in der Konfiguration befüllt werden sollen. 
                    Mit diesen Vorlagen kannst du Tiltdokumente erzeugen, die über eine öffentliche URL verfügbar sind.
                  </p>
                </md-tab>
                <md-tab id="tab-posts" md-label="Unsere Services">
                  <p align="left">
                      Template-Service zum Speichern/Lesen der Templates aus der MongoDB und zum Rendern der entsprechenden Anfragen.
                  </p>
                  <p align="left">
                      User-Service, um benutzerbezogene Aktionen auszuführen
                  </p>
                  <p align="left" >
                      Tilt-Service zum Speichern der Metadaten der Templates in PostgreSQL und zum Bereitstellen der öffentlichen Endpoints für die generierten Tilt-Dokumente
                  </p>
                </md-tab>
              </md-tabs>
            </template>
          </nav-tabs-card>
        </div>
      </div>
    </div>
</template>
<script>
import { NavTabsCard } from "@/components";

export default {
  components: {
    NavTabsCard
  }
};
</script>

<style lang="css">

</style>
