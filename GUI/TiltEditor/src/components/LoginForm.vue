<template>
  <div>
    <b-modal id="modal-1" title="Anmelden" @ok="login">
            <b-form-group id="ctrtUsername" label="Benutzername *">
                <b-form-input v-model="username" placeholder="Bitte Benutzername eingeben." trim aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group id="ctrlPasswort" label="Passwort *" v-bind:description="descriptionPasswort" aria-required>
                <b-form-input type = "password" v-model="passwort" placeholder="Bitte Passwort eingeben (10 Zeichen)." trim aria-required ></b-form-input>
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
      username: "",
      passwort: "",
    };
  },
  computed: {
             descriptionPasswort() {
                var len=0; 
                len = 10 - this.passwort.length
                return 'Noch ' + len.toString() + ' Zeichen übrig';
            }


},
  methods: {
    login() {
        console.log("Samy: Username = " + this.username + " Passwort = " + this.passwort);
       //Login Aufruf für an den Server  
        axios.post('http://localhost:8081/api/auth/login', {
            email: this.username,
            password: this.password,
        }).then(response =>{
           console.log(response.data);

        }).catch(function (error) {
            console.log(error);
        })
       console.log("Username= " + this.username + "Passwort= " + this.passwort);
    },
  },
};
</script>

<style scoped>
</style>