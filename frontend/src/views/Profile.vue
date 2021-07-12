<template>
  <div class="wrapper">
    <div>
      <div class="main main-raised" style="margin-top: 200px">
        <div class="section profile-content">
          <div class="container">
            <div class="md-layout">
              <div class="md-layout-item md-size-50 mx-auto">
                <div class="profile">
                  <div class="avatar">
                    <img
                      :src="
                        `https://eu.ui-avatars.com/api/?name=${user.email.substr(
                          0,
                          1
                        )}`
                      "
                      alt="Circle Image"
                      class="img-raised rounded-circle img-fluid"
                    />
                  </div>
                  <div class="name">
                    <h3 class="title">{{ user.email }}</h3>
                    <h6>Mitglied</h6>
                  </div>
                </div>
              </div>
            </div>

            <div class="md-layout md-alignment-center">
              <template v-for="group in groupedProjects">
                <div
                  v-for="project in group"
                  :key="project.id"
                  class="md-layout-item md-size-25"
                >
                  <ProjectElement
                    :title="project.name"
                    :description="project.baseDescription"
                    @click="viewProject(project.id)"
                  />
                </div>
              </template>
              <div class="md-layout-item md-size-25">
                <ProjectElement @click="newProject" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProjectElement from "@/components/ProjectElement";
import axios from "axios";

export default {
  components: { ProjectElement },
  bodyClass: "profile-page",
  data() {
    return {
      projects: [],
      itemsPerRow: 2
    };
  },
  async created() {
    try {
      const response = await axios.get("/api/tilt/projects", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token")
        }
      });
      this.projects = response.data;
    } catch (error) {
      console.error(err);
    }
  },
  props: {
    image: {
      type: String,
      default: require("@/assets/LandingPage.png")
    }
  },
  computed: {
    groupedProjects: function() {
      return this.projects.reduce((accumulator, project, index) => {
        if (index % this.itemsPerRow === 0) {
          accumulator.push([project]);
        } else {
          accumulator[accumulator.length - 1].push(project);
        }

        return accumulator;
      }, []);
    },
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
        top: "0"
      };
    },
    user() {
      return JSON.parse(localStorage.getItem("user"));
    }
  },
  methods: {
    newProject() {
      this.$router.push("/project/create");
    },
    viewProject(id) {
      this.$router.push("/project/" + id);
    }
  }
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}

.profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*="tab-pane-"] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }
}
</style>
