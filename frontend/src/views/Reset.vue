<template>
  <div class="wrapper">
    <div class="section header-filter" :style="headerStyle">
      <div class="container" style="height: 100%">
        <div class="md-layout md-alignment-center-center" style="height: 100%">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto md-alignment-center-center"
          >
            <login-card header-color="red">
              <h4 slot="title" class="card-title">Passwort zurücksetzen</h4>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>Passwort...(min 6 Zeichen)</label>
                <md-input v-model="password" type="password"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock</md-icon>
                <label>wiederhole Passwort</label>
                <md-input v-model="confirm_password" type="password"></md-input>
              </md-field>
              <md-button v-on:click="reset" slot="footer" class="md-danger">
                Registrieren
              </md-button>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Reset",
  data() {
    return {
      user: {
        password: "",
        confirm_password: "",
        token_isright: true
      }
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
        backgroundPosition: "center",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        height: "100vh"
      };
    }
  },
  methods: {
    reset() {
      this.get();
      if (!this.token_isright) {
        this.$alert(
          "Versuchen Sie das Passwort wieder zurückzusetzen",
          "Die Adresse ist abgelaufen. ",
          "success"
        );
        this.$router.push("/forgot");
      }

      axios
        .post("/api/auth/reset", null, {
          params: {
            old_password: this.old_password,
            token: this.$route.params.token
          }
        })
        .then(response => {
          this.$alert(
            "Sie haben ihr Passwort geändert",
            "Passwort Änderung",
            "success"
          );
          console.log(response.data);
          this.$router.push("/profile");
        })
        .catch(function(error) {
          console.log(error);
          this.$alert("Versuchen Sie es nochmal  ", "Fehler", "error");
        });
    },
    get() {
      const sieben = "/api/auth/reset/" + this.$route.params.token;
      axios.get(sieben).then(response => {
        this.token_isright = response.data.data();
      });
    }
  }
};
</script>

<style scoped></style>
