<template>
  <div>
    <b-modal id="modal-login" title="Anmelden" @ok="login">
            <b-form-group id="ctrtUsername" label="Benutzername *">
                <b-form-input v-model="user.email" placeholder="Bitte Email als Benutzername eingeben." trim aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group id="ctrlPasswort" label="Passwort *" v-bind:description="descriptionPasswort" aria-required>
                <b-form-input type = "password" v-model="user.password" placeholder="Bitte Passwort eingeben (10 Zeichen)." trim aria-required ></b-form-input>
            </b-form-group>
    </b-modal>
  </div>
</template>
<script>


import axios from 'axios';

export default {
  name: "LoginModal",
  data() {
    return {
      user:{
        email: "",
        password: ""
      }
    };
  },
  computed: {
             descriptionPasswort() {
                var len=0; 
                len = 10 - this.user.password.length
                return 'Noch ' + len.toString() + ' Zeichen übrig';
            }

  },
  methods: {
    login() {
       //Login Aufruf für an den Server
      console.log("Username= " + this.user.email + " Passwort= " + this.user.password);

        axios.post('http://34.89.150.159:8080/api/auth/login', this.user).then(response =>{
           console.log(response.data);

        }).catch(function (error) {
            console.log(error);
        })
       this.$emit('login-success', this.user);
       console.log("Username= " + this.user.email + " Passwort= " + this.user.password);
    },
  },
};
</script>

<style scoped>
</style>