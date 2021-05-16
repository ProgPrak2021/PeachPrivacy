<template>
  <div>
    <b-modal id="modal-profile" title="Profile">
            <img src="../assets/benutzer.jpg" alt="" height="50">
            <div>{{user.email}}</div>
            <div> Registrieren</div>
            <div href="#" v-b-modal.modal-register size="sm">Register</div>
                        <register-modal></register-modal>
            <div><button> Profilbild ändern</button></div>
            <div><button> Passwort ändern</button></div>

    </b-modal>
  </div>
</template>

<script>
import RegisterModal from "./RegisterForm.vue";

export default {
  name: "ProfileModal",
  components: {
    'register-modal':RegisterModal
  },
  props:["user"]

}
</script>
<style scoped>
  .button{
  margin: 5px;
}
</style>