<template>
  <div id="app">
    <nav-bar v-bind:forms="forms" @toggle-entry="toggleShowForms"/>
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
      } else if (showForm.toLowerCase() === "toggleController".toLowerCase()) {
        this.forms.showController = !this.forms.showController;
      }
      else if (showForm.toLowerCase() === "toggleDataManager".toLowerCase()) {
        this.forms.showDataManager = !this.forms.showDataManager;
      }
      else if (showForm.toLowerCase() === "toggleAutomatedDecision".toLowerCase()) {
        this.forms.showAutomatedDecision = !this.forms.showAutomatedDecision;
      }
      else if (showForm.toLowerCase() === "toggleChangesOfPurpose".toLowerCase()) {
        this.forms.showPurpose = !this.forms.showPurpose;
      }       
      else if (showForm.toLowerCase() === "toggleRights".toLowerCase()) {
        this.forms.showRights = !this.forms.showRights;
      }
      else if (showForm.toLowerCase() === "toggleSources".toLowerCase()) {
        this.forms.showSources = !this.forms.showSources;
      }

    },
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
        showSources: true,
      },
      filterString: "",
      entrys: []
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