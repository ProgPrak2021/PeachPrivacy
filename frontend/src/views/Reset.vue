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
                <label>Passwort</label>
                <md-input v-model="password" type="password"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>Passwort bestätigen</label>
                <md-input v-model="confirmPassword" type="password"></md-input>
              </md-field>
              <md-button v-on:click="reset" slot="footer" class="md-danger">
                Zurücksetzen
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
import { LoginCard } from "@/components";
export default {
  name: "Reset",
  components: {
    LoginCard
  },
  data() {
    return {
      password: "",
      confirmPassword: ""
    };
  },
  async created() {
    try {
      const response = await axios.get(
        "/api/auth/reset/" + this.$route.params.token,
        {}
      );
      if (response.data !== true) {
        console.log(response.data);
        await this.$router.push("/login");
      }
    } catch (error) {
      console.error(err);
    }
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
      if (this.password.length < 6) {
        this.$alert(
          "Passwort muss min. 6 Zeichen enthalten",
          "Passwort ist zu kurz",
          "error"
        );
        return;
      }
      if (this.password !== this.confirmPassword) {
        this.$alert("Die Passwörter stimmen nicht überein", "Fehler", "error");
        return;
      }

      axios
        .post("/api/auth/reset", null, {
          params: {
            password: this.password,
            token: this.$route.params.token
          }
        })
        .then(response => {
          this.$alert(
            "Sie haben Ihr Passwort geändert",
            "Passwort geändert",
            "success"
          );
          this.$router.push("/login");
        })
        .catch(function(error) {
          console.log(error);
        });
    }
  }
};
</script>

<style scoped></style>
