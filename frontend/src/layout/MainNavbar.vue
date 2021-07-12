<template>
  <md-toolbar class="md-primary">
    <div class="md-toolbar-row">
      <img
        :src="require('@/assets/peachPrivacy.png')"
        width="250"
        style="filter: drop-shadow(2px 2px 2px #954f18)"
      />
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
            <li class="md-list-item">
              <a
                href="javascript:void(0)"
                class="md-list-item-router md-list-item-container md-button-clean dropdown"
              >
                <div class="md-list-item-content">
                  <drop-down direction="down">
                    <md-button
                      slot="title"
                      class="md-button md-button-link md-white md-simple dropdown-toggle"
                      data-toggle="dropdown"
                    >
                      <md-icon>account_circle</md-icon>
                      <p>Benutzer</p>
                    </md-button>
                    <ul
                      class="dropdown-menu"
                      :class="{ 'dropdown-menu-right': responsive }"
                    >
                      <li class="dropdown-header">Benutzerverwaltung</li>
                      <li v-if="!authenticated">
                        <router-link to="/login" class="nav-link"
                          >Anmelden</router-link
                        >
                      </li>
                      <li v-if="!authenticated">
                        <router-link to="/register" class="nav-link"
                          >Registrieren</router-link
                        >
                      </li>
                      <li v-if="authenticated">
                        <router-link to="/profile" class="nav-link"
                          >Profil</router-link
                        >
                      </li>
                      <li v-if="authenticated">
                        <a
                          href="/"
                          v-on:click.stop="logout"
                          class="dropdown-item"
                          >Abmelden</a
                        >
                      </li>
                    </ul>
                  </drop-down>
                </div>
              </a>
            </li>
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

export default {
  components: {},
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
      loginV: false
    };
  },
  computed: {
    authenticated() {
      return localStorage.getItem("authenticated") === "true";
    },
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
    },
    logout() {
      localStorage.setItem("authenticated", "false");
      localStorage.removeItem("user");
      localStorage.removeItem("token");
      this.$router.push("/");
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
