<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
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
          <div class="description text-center">
            <p>
              Hier findest du deine Projekt übersicht. Angezeigt werden alle
              Projekte, welche du bearbeiten kannst.
            </p>
          </div>

          <div class="md-layout">
            <div
              class="md-layout-item md-size-25 ml-auto"
              v-for="group in groupedProjects"
              :key="group"
            >
              <div v-for="project in group" :key="project.id">
                <ProjectElement
                  :title="project.name"
                  :description="project.baseDescription"
                />
              </div>
            </div>
            <div class="md-layout-item md-size-25 mr-auto">
              <ProjectElement />
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
  created() {
    axios
      .get("/api/tilt/projects", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token")
        }
      })
      .then(response => {
        this.projects = response.data;
      });
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg")
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
        backgroundImage: `url(${this.header})`
      };
    },
    user() {
      return JSON.parse(localStorage.getItem("user"));
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
