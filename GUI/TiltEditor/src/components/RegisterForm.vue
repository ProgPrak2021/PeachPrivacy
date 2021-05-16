<template>
  <div>
    <b-modal id="modal-register" title="Register" hide-header-close @ok="register" >
         <b-form-group id="ctrtUsername" label="Benutzername *">
                <b-form-input v-model="user.email" placeholder="Bitte Email als Benutzername wählen." trim aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group id="Passwort" label="Passwort *" v-bind:description="descriptionPassword" aria-required>
                <b-form-input type = "password" v-model="user.password" placeholder="Bitte Passwort eingeben (10 Zeichen)." trim aria-required ></b-form-input>
            </b-form-group>
             <b-form-group id="repeatPasswort" label="Passwort wiederholen*" aria-required>
                <b-form-input type = "password" v-model="confirm_password" placeholder="Bitte Passwort wiederholen." trim aria-required ></b-form-input>
            </b-form-group>

    </b-modal>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "RegisterModal",
  data() {
    return {
      user:{
        email: "",
        password: ""
      },
      confirm_password: "",
      register_response:""
    };
  },
  computed: {
    descriptionPassword() {
      var len=0;
      len = 10 - this.user.password.length
      return 'Noch ' + len.toString() + ' Zeichen übrig';
    }

  },
  methods: {
    register() {
      //Login Aufruf für an den Server
      console.log("Username= " + this.user.email + " Passwort= " + this.user.password);

      axios.post('http://34.89.150.159:8080/api/auth/register', this.user).then(response =>{
        this.register_response=response.data
      }).catch(function (error) {
        console.log(error);
      })
      this.$emit('register-success', this.user);
      console.log(this.register_response);
      console.log("Username= " + this.user.email + " Passwort= " + this.user.password);
    },
  },
};

</script>
<style scoped>
</style>