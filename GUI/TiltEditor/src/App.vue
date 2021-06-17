<template>
  <div id="app">
    <nav-bar v-bind:forms="forms" @toggle-entry="toggleShowForms" @set-user-data="setUserData" />
    <tilt-formular id="tildForm" v-bind:forms="forms"/>
  </div>
</template>

<script>
import NavigationBar from './components/NavigationBar.vue'
import TiltFormular from './components/TiltContainer.vue'


export default {
  name: 'App',
  components: {
    'nav-bar': NavigationBar,
    'tilt-formular': TiltFormular
  },
  methods: {
    toggleShowForms(showForm) {
      if (showForm.toLowerCase() === "toggleMeta".toLowerCase()) {
        this.forms.showMeta = !this.forms.showMeta;
        if (this.forms.showMeta) {
          this.forms.showIntro = false;
        }else if (this.forms.showHelp){
          this.forms.showIntro = true;
        }
      } else if (showForm.toLowerCase() === "toggleController".toLowerCase()) {
        this.forms.showController = !this.forms.showController;
         this.forms.showIntro = !this.forms.showIntro;
      }
      else if (showForm.toLowerCase() === "toggleDataManager".toLowerCase()) {
        this.forms.showDataManager = !this.forms.showDataManager;
        this.forms.showIntro = !this.forms.showIntro;
      }
      else if (showForm.toLowerCase() === "toggleAutomatedDecision".toLowerCase()) {
        this.forms.showAutomatedDecision = !this.forms.showAutomatedDecision;
         this.forms.showIntro = !this.forms.showIntro;
      }
      else if (showForm.toLowerCase() === "toggleChangesOfPurpose".toLowerCase()) {
        this.forms.showPurpose = !this.forms.showPurpose;
        this.forms.showIntro = !this.forms.showIntro;
      }       
      else if (showForm.toLowerCase() === "toggleRights".toLowerCase()) {
        this.forms.showRights = !this.forms.showRights;
        this.forms.showIntro = !this.forms.showIntro;
      }
      else if (showForm.toLowerCase() === "toggleSources".toLowerCase()) {
        this.forms.showSources = !this.forms.showSources;
        this.forms.showIntro = !this.forms.showIntro;
      }
      else if (showForm.toLowerCase() === "toggleDataDisclosed".toLowerCase()) {
        this.forms.showDataDisclosed = !this.forms.showDataDisclosed;
        this.forms.showIntro = !this.forms.showIntro;
      }
      else if (showForm.toLowerCase() === "toggleShowHelp".toLowerCase()) {
        this.forms.showHelp = !this.forms.showHelp;
        if(!this.forms.showMeta && this.forms.showHelp){
            this.forms.showIntro = true;
        }else{
            this.forms.showIntro = false;
        }
      }else{
        // wenn nix zu sehen aber Hilfe aktiviert  
        if (this.forms.showHelp){
          this.forms.showIntro= true;
        }
      }
     

    },
    setUserData(user, bLogin) {
      this.userData.user= user;
      this.userData.bLogin= bLogin;
       console.log("AppVue: Email = " + this.userData.user.email + " Passwort = " + this.userData.user.password);
    }
  },
  data() {
    return {
      forms: {
        showMeta: false,
        showController: false,
        showDataManager: false,
        showAutomatedDecision: false,
        showPurpose: false,
        showRights: false,
        showSources: false,
        showDataDisclosed: false,
        showIntro: true,
        showHelp: true,
      },
      userData: {
                user:{
                  email: "",
                  password: ""
                },
                bLogin:   false
      },
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-color: #FF876C;
}
body{
  background-color: #FF876C;
}
</style>