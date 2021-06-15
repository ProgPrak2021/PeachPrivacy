<template>
    <div class="tilt-block">
        <tilt-new-here id="userStory">
            <div v-if="showNewHere">
                <h3>Brauchst du Hilfe?</h3>
            <div>
                Zum ersten Mal hier? 
            </div>
            <div>
                Peach Privacy bietet dir die Möglichkeiten, die du brauchst, um deinen Kunden mitzuteilen, 
                welche Daten du über sie speicherst. Und das alles DSGVO-konform!
            </div>
            <div>
                Noch nie von der DSGVO gehört?
                <div>
                <a href="https://dsgvo-gesetz.de/" target="_blank">Lies hier mehr über die DSGVO</a>
                </div>
                <a href="https://eur-lex.europa.eu/legal-content/DE/TXT/PDF/?uri=CELEX:32016R0679" target="_blank"> Oder für Spezialisten: Der offizielle Gesetzestext</a>
            </div>
            </div>
        </tilt-new-here>
    </div>
</template>

<script>
export default {
  name: "tilt_userStory",
  data() {
    return {
        showNewHere: true,
    }
  }

 };
</script>
<style scoped>
#userStory{
    padding: 20px 20px 20px 20px;
    margin: 10px 10px 10px 10px;
}
</style>