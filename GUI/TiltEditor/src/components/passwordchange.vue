<template>
  <div>
  <b-modal id="modal-change-pw" title="Passwort ändern " hide-header-close @ok="handleOk">
    <b-form-group id="oldPasswort" label="Passwort *"  aria-required>
      <b-form-input type="password" v-model="old_password" placeholder="Bitte altes Passwort eingeben ." trim
                    aria-required=""></b-form-input>
    </b-form-group>
  <b-form-group id="newPasswort" label="neues Passwort *" valid-feedback="Richtig" :invalid-feedback="write"
                :state="status" aria-required>
    <b-form-input type="password" v-model="new_password" placeholder="Bitte neues Passwort eingeben ( min. 6 Zeichen)." trim
                  aria-required=""></b-form-input>
  </b-form-group>
  <b-form-group id="repeatPasswort" label=" neues Passwort wiederholen*"  valid-feedback="Richtig" :invalid-feedback="confirmiswrong"
                :state="state" aria-required>
    <b-form-input type="password" v-model="confirm_password" placeholder="Bitte neues Passwort wiederholen." trim
                  aria-required=""></b-form-input>
  </b-form-group>
  </b-modal>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
         return {
           new_password:'',
           confirm_password:'',
           old_password:''
         }

  },
  computed: {
    confirmiswrong() {
      return 'Beide Passwörter stimmen nicht überein';

    },
    state() {
      if (this.confirm_password.length === 0) {
        return null;
      }

      return this.new_password === this.confirm_password
    },
    write() {
      var len = 6 - this.new_password.length;
      return 'Passwort braucht noch ' + len.toString() + ' Zeichen';

    },
    status() {
      if (this.new_password.length === 0) {
        return null;
      }
      return this.new_password.length > 5;
    },
    methods: {
      HandleOk(bvModalEvt) {
        bvModalEvt.preventDefault();
      },
      send_new_passwort(){
        axios.post('/api/auth/login', null, {
          params: {
            old_password: this.old_password,
            new_password: this.new_password
          }
        }).then(response => {
          console.log(response.data);
        }).catch(function (error) {
          console.log(error);
          this.$alert('altes Passwort ist falsch ','Fehler',"error");
        })


      }

    }
  }
}
</script>

<style scoped>

</style>