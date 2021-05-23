<template>
  <div>
    <b-modal id="modal-register" title="Register" hide-header-close @ok="handleOk">
      <b-form-group id="ctrtEmail" label="E-Mail *">
        <b-form-input type="email" v-model="user.email" placeholder="Bitte Email eingeben." trim
                      aria-required=""></b-form-input>
      </b-form-group>

      <b-form-group id="Passwort" label="Passwort *"  valid-feedback="Richtig" :invalid-feedback="write"
                    :state="status" aria-required>
        <b-form-input type="password" v-model="user.password" placeholder="Bitte Passwort eingeben (10 Zeichen)." trim
                      aria-required></b-form-input>
      </b-form-group>
      <b-form-group id="repeatPasswort" label="Passwort wiederholen*"  valid-feedback="Richtig" :invalid-feedback="confirmiswrong"
                    :state="state" aria-required>
        <b-form-input type="password" v-model="confirm_password" placeholder="Bitte Passwort wiederholen." trim
                      aria-required></b-form-input>
      </b-form-group>
    </b-modal>
  </div>
</template>RR

<script>
import axios from 'axios';

export default {
  name: "RegisterModal",
  data() {
    return {
      user: {
        email: "",
        password: ""
      },
      confirm_password: "",
      register_response: ""
    };
  },
  computed: {
    confirmiswrong(){
        return 'Beide Passwörter stimmen nicht überein';

    },
    state() {
      if(this.confirm_password.length===0){
        return null;
      }

      return this.user.password === this.confirm_password
    },
    write(){
      var len =10-this.user.password.length;
      return 'Passwort braucht noch ' +len.toString() +' Zeichen';

    },
    status() {
      if(this.user.password.length === 0){
        return null;
      }
      return this.user.password.length>9;
    }


  },
  methods: {
    register() {
      //Login Aufruf für an den Server
      console.log("Email = " + this.user.email + " Passwort = " + this.user.password);

      axios.post('/api/auth/register', null, {
        params: {
          email: this.user.email,
          password: this.user.password
        }
      }).then(response => {
        this.register_response = response.data
      }).catch(function (error) {
        console.log(error);


      })
      this.$emit('register-success', this.user);
      console.log(this.register_response);
      alert('register-success');
      console.log("Email = " + this.user.email + " Passwort = " + this.user.password);
    },
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      if(!this.state||!this.status) {
        bvModalEvt.preventDefault()
        return;
      }
      // Trigger submit handler
      this.register();
    },
  },
};

</script>
<style scoped>
</style>