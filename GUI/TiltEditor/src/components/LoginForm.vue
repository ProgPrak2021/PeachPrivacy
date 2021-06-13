<template>
  <div>
    <b-modal id="modal-login" title="Anmelden" hide-header-close @ok="HandleOk">
      <b-form-group id="ctrtEmail" label="E-Mail *">
        <b-form-input type="email" v-model="user.email" placeholder="Bitte Email eingeben." trim
                      aria-required=""></b-form-input>
      </b-form-group>
      <b-form-group id="ctrlPasswort" label="Passwort *" aria-required>
        <b-form-input type="password" v-model="user.password" placeholder="Bitte Passwort eingeben."
                      trim aria-required></b-form-input>
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
      user: {
        email: "",
        password: ""
      }
    };
  },

  methods: {
    login() {
      //Login Aufruf für an den Server
      console.log("Email = " + this.user.email + " Passwort = " + this.user.password);

      axios.post('/api/auth/login', null, {
        params: {
          email: this.user.email,
          password: this.user.password
        }
      }).then(response => {
        console.log(response.data);
        this.$emit('login-success', this.user);
        this.$alert('Wilkommen zurück','login-sucess',"success");


      }).catch(function (error) {
        console.log(error);
        this.$alert('Passwort oder Email ist falsch ','Fehler',"error");

      })

      console.log("Email = " + this.user.email + " Passwort = " + this.user.password);
      return;
    },
    HandleOk(bvModalEvt) {
      // Prevent modal from closing
      if(this.user.email.length>=255||this.user.password.l>=255) {
        bvModalEvt.preventDefault();
        this.$alert('Passwort oder Email ist zu lang ','Fehler',"info");
        return;
      }

      if(this.user.password.length===0 ||this.user.email.length===0) {
        bvModalEvt.preventDefault()
        this.$alert('Gib deine Email oder dein Passwort an',"Kein Passwort oder Keine Email", "info")
        return;
      }
      if(!(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.user.email))){
        bvModalEvt.preventDefault();
        this.$alert('Die Email ist nicht valide','Fehler','error');
        return;

      }
      // Trigger submit handler
      this.login();
    },
  },
};
</script>

<style scoped>
</style>