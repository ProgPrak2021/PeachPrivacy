<template>
  <div id="app">
    <nav-bar v-bind:forms="forms" @toggle-entry="toggleShowForms" />
    <tilt-formular id="tildForm" v-bind:forms="forms" />
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
     if (showForm.equals("toggleMeta")){
       this.forms.showMeta = !this.forms.showMeta;
     } else if (showForm.equals("toggleController")){
       this.forms.showController = !this.forms.showController;
     } 
    },
  },
  data() {
    return {
      forms : {
        showMeta:true,
        showController:false,
      },
      filterString: "",
      entrys: [
      ] 
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
    background-color:#FF876C;
}
</style>