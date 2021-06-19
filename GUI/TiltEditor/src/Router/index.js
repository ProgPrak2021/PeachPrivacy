import Vue from 'vue'
import Router from 'vue-router'
//import Reset from "@/components/Reset";
import verify from "@/components/verify";
//import NavigationBar from "@/components/NavigationBar";


Vue.use(Router)

export const routes =  new Router({
    mode: 'history',
    routes: [
        {path:'/', name: verify,component: verify},
        {path:'/verify',
            name : verify,
            component: verify },

        ]});