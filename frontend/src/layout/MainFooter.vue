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
            <a href="https://www.creative-tim.com/license">
              License Creative-Tim
            </a>
          </li>
        </ul>
      </nav>
      <div class="copyright">&copy; {{ year }} PeachPrivacy</div>
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
