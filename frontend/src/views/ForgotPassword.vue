<template>
  <div class="wrapper">
    <div class="section header-filter" :style="headerStyle">
      <div class="container" style="height: 100%">
        <div class="md-layout md-alignment-center-center" style="height: 100%">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto md-alignment-center-center"
          >
            <login-card header-color="red">
              <h4 slot="title" class="card-title">Passwort vergessen</h4>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label>Email...</label>
                <md-input v-model="email" type="email"></md-input>
              </md-field>
              <md-button v-on:click="HandleOk" slot="footer" class="md-danger">
                Passwort zurücksetzen
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
  name: "forgotpassword",
  bodyClass: "login-page",
  components: {
    LoginCard
  },
  data() {
    return {
      email: ""
    };
  },
  methods: {
    forgot_password() {
      axios
        .post("/api/auth/reset/request", null, {
          params: {
            email: this.email
          }
        })
        .then(response => {
          this.register_response = response.data;
          this.$alert(
            "Wir schicken Ihnen eine E-Mail mit weiteren Instruktionen",
            "Passwort zurückgesetzt",
            "success"
          );
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    HandleOk() {
      if (
        !/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(
          this.email
        )
      ) {
        this.$alert(
          "Geben Sie eine valide Email an",
          "Email existiert nicht",
          "error"
        );
        return;
      }
      this.forgot_password();
      this.$router.push("/");
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
  }
};
</script>

<style scoped></style>
