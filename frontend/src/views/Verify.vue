<template>
  <div class="wrapper">
    <div class="section header-filter" :style="headerStyle">
      <div class="container" style="height: 100%"></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  components: {},
  bodyClass: "login-page",
  data() {
    return {
      email: "",
      password: ""
    };
  },
  props: {},
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
        backgroundPosition: "center",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        height: "100vh"
      };
    }
  },
  async created() {
    try {
      const response = await axios.get(
        "/api/auth/verify/" + this.$route.params.token,
        {}
      );
      if (response.data === true) {
        await this.$alert("E-Mail bestätigt!", "Bestätigt", "success");
      }
      await this.$router.push("/login");
    } catch (error) {
      console.error(err);
    }
  }
};
</script>

<style lang="css"></style>
