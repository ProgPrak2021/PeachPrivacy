<template>
  <footer
    class="footer"
    :class="{ [`footer-${type}`]: type }"
    :data-background-color="backgroundColor"
  >
    <div class="container">
      <nav>
        <ul>
          <li>
            <a href="https://github.com/Transparency-Information-Language/meta">
              Tilt
            </a>
          </li>
          <li>
            <a href="https://peachprivacy.dev">
              Spring Cloud
            </a>
          </li>
          <li>
            <a href="https://peachprivacy.dev">
              Template-Service
            </a>
          </li>
           <li>
            <a href="https://peachprivacy.dev">
              User-Service
            </a>
          </li>
          <li>
            <a href="https://peachprivacy.dev">
              Tilt-Service
            </a>
          </li>
          <li>
            <a href="https://www.creative-tim.com/license">
              License Tim
            </a>
          </li>
          <li>
            <a href="https://www.creative-tim.com/license">
              License PP
            </a>
          </li>
        </ul>
      </nav>
      <div class="copyright">
        &copy; {{ year }}, made with <md-icon>favorite</md-icon> by
        <a href="https://www.creative-tim.com/" target="_blank">Creative Tim</a>
        for a better web.
      </div>
    </div>
  </footer>
</template>
<script>
export default {
  props: {
    backgroundColor: String,
    type: String
  },
  data() {
    return {
      year: new Date().getFullYear()
    };
  }
};
</script>
<style></style>
