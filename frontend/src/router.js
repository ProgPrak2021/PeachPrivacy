import Vue from "vue";
import Router from "vue-router";
import Index from "./views/Index.vue";
import Login from "./views/Login.vue";
import Profile from "./views/Profile.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import register from "./views/Register.vue";
import Reset from "@/views/Reset";
import ForgotPassword from "@/views/ForgotPassword";
import Verify from "@/views/Verify";
import axios from "axios";
import ProjectCreate from "@/views/ProjectCreate";
import ProjectView from "@/views/ProjectView";
Vue.use(Router);

let router = new Router({
  routes: [
    {
      path: "/",
      name: "index",
      components: { default: Index, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      },
      meta: {
        auth: false
      }
    },
    {
      path: "/login",
      name: "login",
      components: { default: Login, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 }
      },
      meta: {
        auth: false
      }
    },
    {
      path: "/profile",
      name: "profile",
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      },
      meta: {
        auth: true
      }
    },
    {
      path: "/project/create",
      name: "project_create",
      components: {
        default: ProjectCreate,
        header: MainNavbar,
        footer: MainFooter
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      },
      meta: {
        auth: true
      }
    },
    {
      path: "/project/:id",
      name: "project_view",
      components: {
        default: ProjectView,
        header: MainNavbar,
        footer: MainFooter
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      },
      meta: {
        auth: true
      }
    },
    {
      path: "/register",
      name: "register",
      components: { default: register, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      },
      meta: {
        auth: false
      }
    },
    {
      path: "/forgot",
      name: "forgot",
      components: {
        default: ForgotPassword,
        header: MainNavbar,
        footer: MainFooter
      },
      props: {
        header: { colorOnScroll: 400 }
      },
      meta: {
        auth: false
      }
    },
    {
      path: "/reset/:token",
      name: "reset",
      components: { default: Reset, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 }
      },
      meta: {
        auth: false
      }
    },
    {
      path: "/verify/:token",
      name: "verify",
      components: { default: Verify, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 }
      },
      meta: {
        auth: false
      }
    }
  ],
  scrollBehavior: to => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  }
});

router.beforeEach((to, from, next) => {
  localStorage.setItem("authenticated", "false");
  localStorage.removeItem("user");
  let authenticationNeeded = to.matched.some(record => record.meta.auth);
  let token = localStorage.getItem("token");
  if (token != null) {
    axios
      .get("/api/account/info", {
        headers: {
          Authorization: "Bearer " + token
        }
      })
      .then(response => {
        localStorage.setItem("authenticated", "true");
        localStorage.setItem("user", JSON.stringify(response.data));
        next();
      })
      .catch(error => {
        if (authenticationNeeded) {
          next({
            path: "/login"
          });
        } else {
          next();
        }
      });
  } else {
    if (authenticationNeeded) {
      next({
        path: "/login"
      });
    } else {
      next();
    }
  }
});

export default router;
