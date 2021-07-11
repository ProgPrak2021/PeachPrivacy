<template>
  <div>
    <b-modal id="modal-change-pw" title="Passwort ändern " hide-header-close @ok="HandleOk">
      <b-form-group id="oldPasswort" label="Passwort *" aria-required>
        <b-form-input type="password" v-model="old_password" placeholder="Bitte altes Passwort eingeben ." trim
                      aria-required=""></b-form-input>
      </b-form-group>
      <b-form-group id="newPasswort" label="neues Passwort *" valid-feedback="Richtig" :invalid-feedback="write"
                    :state="status" aria-required>
        <b-form-input type="password" v-model="new_password"
                      placeholder="Bitte neues Passwort eingeben ( min. 6 Zeichen)." trim
                      aria-required=""></b-form-input>
      </b-form-group>
      <b-form-group id="repeatPasswort" label=" neues Passwort wiederholen*" valid-feedback="Richtig"
                    :invalid-feedback="confirmiswrong"
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
  name: "passwordchange",
  data() {
    return {
      new_password: '',
      confirm_password: '',
      old_password: ''
    };

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
  },

  methods: {

    HandleOk(bvModalEvt) {
      if (this.old_password.length === 0) {
        bvModalEvt.preventDefault();
        this.$alert('Geben Sie ein Passwort an', 'Es gibt kein Passwort', "info");
        return;
      }
      if (this.new_password.length === 0) {
        bvModalEvt.preventDefault();
        this.$alert('Wiederholen Sie das Passwort', ' Passwort wurde nicht wiederholt', "info");
        return;
      }
      if (this.confirm_password.length === 0) {
        bvModalEvt.preventDefault();
        this.$alert('Wiederholen Sie das Passwort', ' Passwort wurde nicht wiederholt', "info");
        return;
      }
      if (this.new_password.length < 6) {
        bvModalEvt.preventDefault();
        this.$alert('Passwort muss min. 6 Zeichen enthalten', ' Passwort ist zu kurz', "info");
        return;
      }
      if (!this.state || !this.status) {
        bvModalEvt.preventDefault();
        this.$alert('Beide Passwörter stimmen nicht überein', 'Beide Passwörter stimmen nicht überein', "info")
        return;
      }
      this.send_new_passwort();


    },
    send_new_passwort() {
      axios.post('/api/auth/', null, {
        params: {
          old_password: this.old_password,
          new_password: this.new_password
        }
      }).then(response => {
        console.log(response.data);
      }).catch(function (error) {
        console.log(error);
        this.$alert('altes Passwort ist falsch ', 'Fehler', "error");
      })

      this.$alert('Sie haben ihr Passwort geändert', 'Passwort Änderung', 'success')


    }

  }

}
</script>

<style scoped>

</style>