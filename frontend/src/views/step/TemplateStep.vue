<template>
  <div>
    <h3>Erbende Projekte...</h3>
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
            :hover="false"
            :selected="value.projects.includes(project.id)"
            @select="select(project.id, $event)"
          />
        </div>
      </template>
    </div>
  </div>
</template>

<script>
import ProjectElement from "@/components/ProjectElement";
import axios from "axios";

export default {
  name: "TemplateStep",
  components: { ProjectElement },
  data() {
    return {
      projects: [],
      itemsPerRow: 4
    };
  },
  model: {
    prop: "value"
  },
  props: {
    value: {
      type: Object,
      default() {
        return {
          projects: []
        };
      }
    }
  },
  async mounted() {
    this.value.valid = true;
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
    }
  },
  methods: {
    select(id, enabled) {
      console.log(this.value);
      if (enabled) this.value.projects.push(id);
      else
        this.value.projects = this.value.projects.filter(item => item !== id);
      this.$emit("input", this.value);
    }
  }
};
</script>

<style scoped></style>
