<template>
  <div>
    <b-modal id="modal-profile" title="Profile">
      <b-row>
        <b-col>
          <img src="../assets/benutzer.jpg" alt="" height="50">
        </b-col>
        <b-col>
          <div>{{user.email}}</div>
        </b-col>
      </b-row>
      <b-row id="profile_actions">
        <b-col>
          <div href="#" v-b-modal.modal-register size="sm">Registeren</div>
          <register-modal></register-modal>
        </b-col>
        <b-col>
          <div href="#" v-b-modal.modal-change-avatar size="sm">Profilbild ändern</div>
        </b-col>
        <b-col>
          <div href="#" v-b-modal.modal-change-avatar size="sm">Passwort ändern</div>
        </b-col>
      </b-row>
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
  #profile_actions{
    background-color: #FF876C;
  }
</style>