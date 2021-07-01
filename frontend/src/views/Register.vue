<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
              class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="red">
              <h4 slot="title" class="card-title">Registrierung</h4>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label>Email...</label>
                <md-input v-model="email" type="email"></md-input>
              </md-field>
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
              <md-button v-on:click="handleOk" slot="footer" class="md-danger"  >
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
import { LoginCard } from "@/components";
import axios from 'axios';

export default {
  components: {
    LoginCard
  },
  bodyClass: "register-page",
  data() {
    return {
      email: "",
      password: "",
      confirm_password:""
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
    register() {
      //Login Aufruf für an den Server

      axios.post('/api/auth/register', null, {
        params: {
          email: this.email,
          password: this.password
        }
      }).then(response => {
        console.log(this.register_response);
        this.$alert('Sie erhlaten eine Bestätigungsemail','Registrierung erfolgreich',"success");
        this.register_response = response.data
      }).catch(function (error) {
        this.$alert('Etwas ist schiefgelaufen, versuchen Sie es nochmal','Fehler',"info");
        console.log(error)
      })
    },
    handleOk() {
      // Prevent modal from closing
      if(this.email.length>=255||this.password.length>=255) {
        this.$alert('Passwort oder Email ist zu lang ','Fehler',"info");
        return;
      }
      if(this.email.length===0) {
        this.$alert('Geben Sie eine Email  ein','Fehler',"info");
        return;
      }
      if(this.password.length===0) {
        this.$alert('Geben Sie ein Passwort ein','Fehler',"info");
        return;
      }
      if(this.password.length ===0){
        this.$alert('Geben Sie ein Passwort an','Es gibt kein Passwort',"info");
        return;
      }
      if(this.confirm_password.length ===0){
        this.$alert('Wiederholen Sie das Passwort',' Passwort wurde nicht wiederholt',"info");
        return;
      }
      if(this.confirm_password.length <6){
        this.$alert('Passwort muss min. 6 Zeichen enthalten',' Passwort ist zu kurz',"info");
        return;
      }
      if(!(this.confirm_password===this.password)) {
        this.$alert('Beide Passwörter stimmen nicht überein','Beide Passwörter stimmen nicht überein', "info")
        return;
      }
      if(!(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.email))){
        this.$alert(' Geben Sie eine valide Email an','Email existiert nicht',"error");
        return;
      }
      // Trigger submit handler
      this.register();
    },
  },
};
</script>

<style lang="css"></style>