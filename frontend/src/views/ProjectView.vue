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
                  <md-button class="md-info" @click="tiltSchema(version)"
                    >TILT Schema für {{ version }}
                  </md-button>
                  <md-button
                    v-if="available.includes(version)"
                    class="md-success"
                    @click="tilt(version)"
                    >TILT für {{ version }} </md-button
                  ><br />
                </div>
              </template>
              <br />
              <md-button class="md-danger" @click="deleteProject"
                >Löschen</md-button
              >
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
      },
      available: []
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
      for (const version of this.project.versions) {
        try {
          const response = await axios.get(
            "/api/tilt/templates/" + version + "/resolve/object"
          );
          if (response.status === 200) {
            this.available.push(version);
          }
        } catch (error) {}
      }
      console.log(this.project);
    } catch (error) {
      await this.$router.push("/profile");
    }
  },
  methods: {
    tiltSchema(id) {
      window.location = "/api/tilt/templates/" + id;
    },
    tilt(id) {
      window.location = "/api/tilt/templates/" + id + "/resolve/object";
    },
    async deleteProject() {
      try {
        const response = await axios.delete(
          "/api/tilt/projects/" + this.$route.params.id,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token")
            }
          }
        );
        await this.$router.push("/profile");
      } catch (error) {
        await this.$alert(
          "Projekt konnte nicht gelöscht werden! " + error,
          "Fehler"
        );
      }
    }
  },
  computed: {}
};
</script>

<style scoped></style>
