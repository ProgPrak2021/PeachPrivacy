<template>
    <div class="tilt-block">
        <tilt-new-user id="userStory">
            <div v-if="showNewUser">
                <h3>Noch nicht registriert?</h3>
            <div>
                Registriere dich unter <b>Benutzer->Registrieren</b>, um die Funktionalitäten unseres Services nutzen zu können.
            </div>
            </div>
        </tilt-new-user>
    </div>
</template>

<script>
export default {
  name: "tilt_userStory",
  data() {
    return {
        showNewUser: true,
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