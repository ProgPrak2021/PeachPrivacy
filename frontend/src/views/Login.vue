<template>
  <div class="wrapper">
    <div class="section header-filter" :style="headerStyle">
      <div class="container" style="height: 100%">
        <div class="md-layout md-alignment-center-center" style="height: 100%">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto md-alignment-center-center"
          >
            <login-card header-color="red">
              <h4 slot="title" class="card-title">Login</h4>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label>Email...</label>
                <md-input v-model="email" type="email"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>Password...</label>
                <md-input v-model="password" type="password"></md-input>
              </md-field>
              <md-button v-on:click="HandleOk" slot="footer" class="md-danger">
                Login
              </md-button>
              <a v-on:click="forgotpassword" slot="inputs">
                Password vergessen
              </a>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import axios from "axios";

export default {
  components: {
    LoginCard
  },
  bodyClass: "login-page",
  data() {
    return {
      email: "",
      password: ""
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/LoginPage.png")
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
    forgotpassword() {
      this.$router.push("forgot");
    },
    login() {
      //Login Aufruf für an den Server
      console.log("Email = " + this.email + " Passwort = " + this.password);
      axios
        .post("/api/auth/login", null, {
          params: {
            email: this.email,
            password: this.password
          }
        })
        .then(response => {
          localStorage.setItem("token", response.data);
          console.log(response.data);
          this.$router.push("profile");
          this.$alert("Wilkommen zurück", "login-sucess", "success");
        })
        .catch(function(error) {
          console.log(error);
          this.$alert("Passwort oder Email ist falsch ", "Fehler", "error");
        });
        this.$alert("Passwort oder Email ist falsch ", "Fehler", "error");
      return;
    },
    HandleOk() {
      // Prevent modal from closing
      if (this.email.length >= 255 || this.password.length >= 255) {
        this.$alert("Passwort oder Email ist zu lang ", "Fehler", "info");
        return;
      }
      if (this.password.length === 0 || this.email.length === 0) {
        this.$alert(
          "Gib deine Email oder dein Passwort an",
          "Kein Passwort oder Keine Email",
          "info"
        );
        return;
      }
      if (
        !/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(
          this.email
        )
      ) {
        this.$alert("Die Email ist nicht valide", "Fehler", "error");
        return;
      }
      // Trigger submit handler
      this.login();
    }
  }
};
</script>

<style lang="css" scoped></style>
