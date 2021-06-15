<template>
    <div class="tilt-block">
        <tilt-old-user id="userStory">
            <div v-if="showOldUser">
                <h3>Hast du bereits ein Konto?</h3>
            <div>
                Melde dich unter <b>Benutzer->Anmelden</b> an, um an deinen Projekten weiterarbeiten zu können.
            </div>
            </div>
        </tilt-old-user>
    </div>
</template>

<script>
export default {
  name: "tilt_userStory",
  data() {
    return {
        showOldUser: true,
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