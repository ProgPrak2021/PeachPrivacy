<template>
  <div>
    <b-modal id="modal-1" title="Anmelden" @ok="login">
            <b-form-group id="ctrtEmail" label="E-Mail *">
                <b-form-input v-model="email" placeholder="Bitte E-Mail eingeben." trim aria-required=""></b-form-input>
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
      email: "",
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
        axios.post('/api/auth/login', {
            email: this.email,
            password: this.password,
        }).then(response => {
           console.log(response.data);
        }).catch(function (error) {
            console.log(error);
        })
    },
  },
};
</script>

<style scoped>
</style>