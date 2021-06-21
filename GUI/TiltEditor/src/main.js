import Vue from 'vue'
import 'core-js'
import App from './App.vue'
import { routes } from './Router'
import BootstrapVue from 'bootstrap-vue'
import VueSimpleAlert from "vue-simple-alert";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
Vue.use(VueSimpleAlert)
Vue.config.productionTip = false

new Vue({
   routes,
  render: h => h(App),

}).$mount('#app')
