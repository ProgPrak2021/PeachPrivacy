<template>
  <div>
    <b-modal id="modal-login" title="Anmelden" hide-header-close @ok="login">
            <b-form-group id="ctrtUsername" label="E-Mail *">
                <b-form-input type="email" v-model="user.email" placeholder="Bitte Email als Benutzername eingeben." trim aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group id="ctrlPasswort" label="Passwort *" v-bind:description="descriptionPasswort" aria-required>
                <b-form-input type = "password" v-model="user.password" placeholder="Bitte Passwort eingeben (Min. 10 Zeichen)." trim aria-required ></b-form-input>
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
      console.log("Email= " + this.user.email + " Passwort= " + this.user.password);

        axios.post('/api/auth/login', {
            email: this.email,
            password: this.password,
        }).then(response => {
           console.log(response.data);
        }).catch(function (error) {
            console.log(error);
        })
       this.$emit('login-success', this.user);
       console.log("Email= " + this.user.email + " Passwort= " + this.user.password);
    },
  },
};
</script>

<style scoped>
</style>