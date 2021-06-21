<template>
  <div class="vue-tempalte">
    <form @submit.prevent="forgetPassword">
      <h3>Forgot Password</h3>

      <div class="form-group">
        <label>Passwort</label>
        <input type="password" class="form-control form-control-lg" v-model="user.password" />
      </div>

      <div class="form-group">
        <label>Passwort bestätigen</label>
        <input type="password" class="form-control form-control-lg" v-model="user.confirm_password" />
      </div>

      <button type="submit" class="btn btn-dark btn-lg btn-block">Reset password</button>
    </form>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "Reset",
  data() {
    return {
      user: {
        password: '',
        confirm_password: ''
      }
    };
  },  methods: {
    forgetPassword() {
      axios.post('/api/auth/reset', null, {
        params: {
          old_password: this.old_password,
          token: this.$route.params.token
        }
      }).then(response => {
        console.log(response.data);
      }).catch(function (error) {
        console.log(error);
        this.$alert('Versuchen Sie es nochmal  ','Fehler',"error");
      })

      this.$alert('Sie haben ihr Passwort geändert','Passwort Änderung','success')

    }
  }
};
</script>

<style scoped>

</style>