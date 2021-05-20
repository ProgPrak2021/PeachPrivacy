<template>
  <div>
    <b-modal id="modal-profile"  title ="Profile" ok-only hide-header-close>
      <b-row>
        <b-col>
          <img src="../assets/benutzer.jpg" alt="" height="50">
        </b-col>
        <b-col>
          <div>{{user.email}}</div>
        </b-col>
      </b-row>
      <b-row id="profile_actions">
        <div class="col-4">
          <b-btn class="profile_action_btn" href="#" v-b-modal.modal-register >Jetzt Registeren</b-btn>
          <register-modal></register-modal>
        </div>
        <div class="col-4">
          <b-btn class="profile_action_btn" href="#" v-b-modal.modal-change-avatar >Profilbild ändern</b-btn>
        </div>
        <div class="col-4">
          <b-btn class="profile_action_btn" href="#" v-b-modal.modal-change-avatar>Passwort ändern</b-btn>
        </div>
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
  #modal-profile{
    background-color: whitesmoke;
  }
  .profile_action_btn{
    margin: 20px 10px 10px 10px;
    background-color: #FF876C;
    border-color:#D9304F;
    border-style: solid;
    border-radius: 5px 5px 5px 5px;
    border-width: 100%;
    /*color: #D9304F; */
  }


</style>