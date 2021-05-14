import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/LoginForm'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Login',
            hidden: true,
            component: Login
        }
        ]
})