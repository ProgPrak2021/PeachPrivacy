<template>
   <md-toolbar class="md-primary">
            <div class="md-toolbar-row">
                <img src="../assets/tiltLogo.jpg" alt="" width="250">
                <div class="md-toolbar-section-start">
                  <!--<h3 class="md-title">Primary Color</h3>-->
                </div>
                <div class="md-toolbar-section-end">
                  <md-button class="md-just-icon md-simple md-white md-toolbar-toggle">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </md-button>
                  <div class="md-collapse">
                    <md-list>
                      <md-list-item href="#/">
                        <md-icon>explore</md-icon>
                        <p>Suche</p>
                      </md-list-item>
                      <li class="md-list-item">
                            <a href="javascript:void(0)" class="md-list-item-router md-list-item-container md-button-clean dropdown">
                              <div class="md-list-item-content">
                                <drop-down direction="down">
                                  <md-button slot="title" class="md-button md-button-link md-white md-simple dropdown-toggle" data-toggle="dropdown">
                                  <md-icon>article</md-icon>
                                  <p>Template</p>
                                  </md-button>
                                  <ul
                                    class="dropdown-menu"
                                    :class="{ 'dropdown-menu-right': responsive }"
                                  >
                                    <li class="dropdown-header">Templateverwaltung</li>
                                  <div v-if="!loginV">
                                      <li>
                                        <a href="#pablo" class="dropdown-item">Neues Template</a>
                                      </li>
                                    </div>
                                    <li>
                                      <a href="#pablo" class="dropdown-item">Template laden</a
                                      >
                                    </li>
                                    <li>
                                      <a href="#pablo" class="dropdown-item" >Template speichern</a>
                                    </li>
                                    <li>
                                      <a href="#pablo" class="dropdown-item">Template löschen</a
                                      >
                                    </li>
                                  </ul>
                                </drop-down>
                              </div>
                          </a>
                      </li>
                      <li class="md-list-item">
                            <a href="javascript:void(0)" class="md-list-item-router md-list-item-container md-button-clean dropdown">
                              <div class="md-list-item-content">
                                <drop-down direction="down">
                                  <md-button slot="title" class="md-button md-button-link md-white md-simple dropdown-toggle" data-toggle="dropdown">
                                  <md-icon>account_circle</md-icon>
                                  <p>Benutzer</p>
                                  </md-button>
                                  <ul
                                    class="dropdown-menu"
                                    :class="{ 'dropdown-menu-right': responsive }"
                                  >
                                    <li class="dropdown-header">Benutzerverwaltung</li>
                                  <div v-if="!loginV">
                                      <li>
                                        <a href="#pablo" class="dropdown-item">Profile</a>
                                      </li>
                                    <!-- <profile-modal v-bind:user="user"></profile-modal>
                                      <profile-card></profile-card>-->
                                    </div>
                                    <li>
                                      <a href="#pablo" class="dropdown-item"
                                        >Anmelden</a
                                      >
                                    </li>
                                    <li>
                                      <a href="#pablo" class="dropdown-item"
                                        >Registrieren</a
                                      >
                                    </li>
                                    <li class="dropdown-divider"></li>
                                    <li>
                                      <a href="#pablo" class="dropdown-item"
                                        >Abmelden</a
                                      >
                                    </li>
                                  </ul>
                                </drop-down>
                              </div>
                          </a>
                      </li>
                      <md-list-item href="javascript:void(0)">
                            <md-icon>settings</md-icon>
                            <p>Einstellungen</p>
                          </md-list-item>
                      </md-list>
                  </div>
                </div>
            </div>
   </md-toolbar>
</template>

<script>
let resizeTimeout;
function resizeThrottler(actualResizeHandler) {
  // ignore resize events as long as an actualResizeHandler execution is in the queue
  if (!resizeTimeout) {
    resizeTimeout = setTimeout(() => {
      resizeTimeout = null;
      actualResizeHandler();

      // The actualResizeHandler will execute at a rate of 15fps
    }, 66);
  }
}

import MobileMenu from "@/layout/MobileMenu";
import ProfileModal from "@/components/ProfileForm.vue";
//import ProfileCard from '../components/cards/ProfileCard.vue';
export default {
  components: {
    MobileMenu,
    'profile-modal':ProfileModal,
    //'profile-card': ProfileCard

  },
  props: {
    type: {
      type: String,
      default: "white",
      validator(value) {
        return [
          "white",
          "default",
          "primary",
          "danger",
          "success",
          "warning",
          "info"
        ].includes(value);
      }
    },
    colorOnScroll: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      extraNavClasses: "",
      toggledClass: false,
       user:{
                  email: "",
                  password: ""
                },
      loginV:   false
    };
  },
  computed: {
    showDownload() {
      const excludedRoutes = ["login", "landing", "profile"];
      return excludedRoutes.every(r => r !== this.$route.name);
    }
  },
  methods: {
    bodyClick() {
      let bodyClick = document.getElementById("bodyClick");

      if (bodyClick === null) {
        let body = document.querySelector("body");
        let elem = document.createElement("div");
        elem.setAttribute("id", "bodyClick");
        body.appendChild(elem);

        let bodyClick = document.getElementById("bodyClick");
        bodyClick.addEventListener("click", this.toggleNavbarMobile);
      } else {
        bodyClick.remove();
      }
    },
    toggleNavbarMobile() {
      this.NavbarStore.showNavbar = !this.NavbarStore.showNavbar;
      this.toggledClass = !this.toggledClass;
      this.bodyClick();
    },
    handleScroll() {
      let scrollValue =
        document.body.scrollTop || document.documentElement.scrollTop;
      let navbarColor = document.getElementById("toolbar");
      this.currentScrollValue = scrollValue;
      if (this.colorOnScroll > 0 && scrollValue > this.colorOnScroll) {
        this.extraNavClasses = `md-${this.type}`;
        navbarColor.classList.remove("md-transparent");
      } else {
        if (this.extraNavClasses) {
          this.extraNavClasses = "";
          navbarColor.classList.add("md-transparent");
        }
      }
    },
    scrollListener() {
      resizeThrottler(this.handleScroll);
    },
    scrollToElement() {
      let element_id = document.getElementById("downloadSection");
      if (element_id) {
        element_id.scrollIntoView({ block: "end", behavior: "smooth" });
      }
    }
  },
  mounted() {
    document.addEventListener("scroll", this.scrollListener);
  },
  beforeDestroy() {
    document.removeEventListener("scroll", this.scrollListener);
  }
};
</script>
