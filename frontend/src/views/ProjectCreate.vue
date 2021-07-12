<template>
  <div class="wrapper">
    <div>
      <div class="main main-raised" style="margin-top: 50px; z-index: unset;">
        <div class="section profile-content">
          <md-progress-bar
            class="md-primary"
            :md-value="(step / stepCount) * 100"
            style="margin-top: -70px"
          ></md-progress-bar>
          <div class="container">
            <NameStep
              v-if="steps[step].type === 'NameStep'"
              v-model="state"
            ></NameStep>
            <TemplateStep
              v-if="steps[step].type === 'TemplateStep'"
              v-model="state"
            ></TemplateStep>
            <FormStep
              v-if="steps[step].type === 'FormStep'"
              v-model="state"
              :prefix="steps[step].prefix"
            ></FormStep>
            <br />
            <div class="md-layout md-alignment-center">
              <div class="md-layout-item text-left">
                <md-button class="md-primary" @click="cancel()"
                  >Abbrechen
                </md-button>
              </div>
              <div class="md-layout-item text-center">
                <md-button
                  class="md-primary"
                  v-if="backward"
                  @click="pageUpdate(-1)"
                  >Zurück
                </md-button>
              </div>
              <div class="md-layout-item text-right">
                <md-button
                  :disabled="!forward"
                  class="md-primary"
                  @click="pageUpdate(1)"
                  >Weiter
                </md-button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NameStep from "@/views/step/NameStep";
import TemplateStep from "@/views/step/TemplateStep";
import axios from "axios";
import FormStep from "@/views/step/FormStep";
import DefaultSchema from "@/assets/default-schema.json";

export default {
  name: "ProjectCreate",
  bodyClass: "profile-page",
  components: { FormStep, TemplateStep, NameStep },
  data() {
    return {
      step: 0,
      steps: [],
      stepCount: 10,
      state: {
        templateId: "",
        name: "",
        description: "",
        schema: JSON.stringify(DefaultSchema),
        projects: [],
        valid: false,
        fields: []
      }
    };
  },
  created() {
    this.steps.push({
      type: "NameStep"
    });
    this.steps.push({
      type: "TemplateStep"
    });
  },
  methods: {
    nameStepUpdate() {
      this.update = true;
    },
    async pageUpdate(add) {
      if (this.step === 1 && add === 1) {
        if (!(await this.createProject())) return;
        if (!(await this.resolveFields())) return;
        new Set(this.state.fields.map(field => field.path[0])).forEach(
          prefix => {
            this.steps.push({
              type: "FormStep",
              prefix: prefix
            });
          }
        );
        this.stepCount = this.steps.length;
        this.step += add;
      } else if (this.step === this.steps.length - 1 && add === 1) {
        console.log("request");
        console.log(this.state);
        if (!(await this.pushFields())) return;

        await this.$router.push("/profile");
      } else {
        this.step += add;
      }
    },
    async createProject() {
      try {
        const project = {
          name: this.state.name,
          baseDescription: this.state.description,
          authority: "default"
        };
        const projectResponse = await axios.post(
          "/api/tilt/projects",
          project,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token")
            }
          }
        );
        const template = {
          project: {
            id: projectResponse.data.id
          },
          version: 1,
          changelog: "Initial",
          parents: [],
          schema: this.state.schema
        };
        this.state.projects.forEach(templateId => {
          template.parents.push({ id: templateId });
        });
        console.log(template);
        const templateResponse = await axios.post(
          "/api/tilt/templates",
          template,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token")
            }
          }
        );
        this.state.templateId = templateResponse.data.id;
        return true;
      } catch (error) {
        console.error(error);
        await this.$alert(error, "Projekt kann nicht erstellt werden");
        return false;
      }
    },
    async resolveFields() {
      try {
        const resolveResponse = await axios.get(
          "/api/tilt/templates/" + this.state.templateId + "/resolve",
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token")
            }
          }
        );
        this.state.fields = resolveResponse.data.filter(field => {
          if (field.definition === null) {
            field.definition = {};
          }
          return field.definition.value === undefined;
        });
        console.log(this.state.fields);
        return true;
      } catch (error) {
        console.error(error);
        await this.$alert(error, "Template kann nicht erstellt werden");
        return false;
      }
    },
    async pushFields() {
      try {
        const push = {};
        this.state.fields.forEach(field => {
          if (
            field.definition.value !== undefined &&
            field.definition.value !== null &&
            field.definition.value !== ""
          ) {
            push[field.path.join("/")] = field.definition.value;
          }
        });
        const pushResponse = await axios.post(
          "/api/tilt/templates/" + this.state.templateId + "/resolve",
          push,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token")
            }
          }
        );
        console.log(pushResponse.data);
        return true;
      } catch (error) {
        console.error(error);
        await this.$alert(error, "Template kann nicht geupdated werden");
        return false;
      }
    },
    cancel() {
      this.$router.push("/profile");
    }
  },
  computed: {
    backward: function() {
      return this.step !== 0 && this.step !== 2;
    },
    forward: function() {
      return this.state.valid;
    }
  }
};
</script>

<style scoped></style>
