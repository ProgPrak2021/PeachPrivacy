<template>
  <div class="wrapper">
    <div>
      <div class="main main-raised" style="margin-top: 50px; z-index: unset;">
        <div class="section profile-content">
          <div class="container">
            <h1 class="text-center">{{ project.name }}</h1>
            <div class="description text-center">
              <p>
                {{ project.baseDescription }}
              </p>
            </div>
            <div class="text-center">
              <template v-for="version in project.versions">
                <div :key="version">
                  <md-button class="md-info" @click="rawTilt(version)"
                    >TILT Schema für {{ version }} </md-button
                  ><br />
                </div>
              </template>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ProjectView",
  bodyClass: "profile-page",
  components: {},
  data() {
    return {
      project: {
        name: ""
      }
    };
  },
  async created() {
    try {
      const response = await axios.get(
        "/api/tilt/projects/" + this.$route.params.id,
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token")
          }
        }
      );
      this.project = response.data;
      console.log(this.project);
    } catch (error) {
      await this.$router.push("/profile");
    }
  },
  methods: {
    rawTilt(id) {
      this.$router.push("/api/tilt/templates/" + id); // TODO ../schema to only display schema
    }
  },
  computed: {}
};
</script>

<style scoped></style>
