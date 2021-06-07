<template>
  <div>
  <b-modal id="modal-change-pw" title="Passwort ändern " hide-header-close @ok="handleOk">
    <b-form-group id="oldPasswort" label="Passwort *"  aria-required>
      <b-form-input type="password" v-model="old_password" placeholder="Bitte Passwort eingeben (10 Zeichen)." trim
                    aria-required=""></b-form-input>
    </b-form-group>
  <b-form-group id="newPasswort" label="neues Passwort *"  aria-required>
    <b-form-input type="password" v-model="new_password" placeholder="Bitte Passwort eingeben (10 Zeichen)." trim
                  aria-required=""></b-form-input>
  </b-form-group>
  <b-form-group id="repeatPasswort" label=" neues Passwort wiederholen*"  valid-feedback="Richtig" :invalid-feedback="confirmiswrong"
                :state="state" aria-required>
    <b-form-input type="password" v-model="confirm_password" placeholder="Bitte Passwort wiederholen." trim
                  aria-required=""></b-form-input>
  </b-form-group>
  </b-modal>
  </div>
</template>

<script>
export default {
  data() {
         return {
           name: "passwordchange",
           new_password:'',
           confirm_password:'',
           old_password:''


         }

  },


  methods: {
    HandleOk(bvModalEvt) {
      bvModalEvt.preventDefault();
    }

  }
}
</script>

<style scoped>

</style>