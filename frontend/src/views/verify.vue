<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
              class="md-layout-item md-size-50 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="red">
              <h2 slot="title" class="card-title">Sie haben sich erfolreich registriert</h2>
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
              <md-button v-on:click="HandleOk" slot="footer" class="md-danger"  >
                Login
              </md-button>

            </login-card>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import axios from 'axios';

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
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },
  methods: {
    forgotpassword(){
      this.$router.push('forgot');
    },
    login() {
      //Login Aufruf für an den Server
      console.log("Email = " + this.user.email + " Passwort = " + this.user.password);
      axios.post('/api/auth/login', null, {
        params: {
          email: this.email,
          password: this.password
        }
      }).then(response => {
        if(response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
          console.log(response.data);
          this.$router.push('profile');
          this.$alert('Wilkommen zurück', 'login-sucess', "success");
        }
      }).catch(function (error) {
        console.log(error);
        this.$alert('Passwort oder Email ist falsch ','Fehler',"error");
      })
      return;
    },
    HandleOk() {
      // Prevent modal from closing
      if(this.email.length>=255||this.password.length>=255) {
        this.$alert('Passwort oder Email ist zu lang ','Fehler',"info");
        return;
      }
      if(this.password.length===0 ||this.email.length===0) {
        this.$alert('Gib deine Email oder dein Passwort an',"Kein Passwort oder Keine Email", "info");
        return;
      }
      if(!(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.email))){
        this.$alert('Die Email ist nicht valide','Fehler','error');
        return;
      }
      // Trigger submit handler
      this.login();
    },
  },
};
</script>

<style lang="css"></style>
