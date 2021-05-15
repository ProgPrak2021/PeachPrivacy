<template>
  <div id="app">
    <nav-bar @filter-entry="updateFilterString" />
    <tilt-formular id="tildForm" />
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
    updateFilterString(filter) {
      this.filterString = filter;
    },
  },
  data() {
    return {
      idCounter: 8,
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