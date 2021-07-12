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
        name: "",
        description: "",
        projects: [],
        valid: false,
        schema: [
          {
            path: "accessAndDataPortability/description",
            question: "AccessAndDataPortability",
            description:
              "Description of the requirements according to Art. 20 GDPR.",
            type: "string",
            definition: {
              schema: "10f240c4-f0bc-4108-ac65-eb1ea1c6cd3c"
            }
          },
          {
            path: "accessAndDataPortability/URL",
            question: "Beschreibungdjanfijadnifnjad",
            description: "fdakfmnadlkfad",
            type: "string",
            definition: {
              schema: "10f240c4-f0bc-4108-ac65-eb1ea1c6cd3c"
            }
          },
          {
            path: "accessAndDataPortability/available",
            question: "AccessAndDataPortability - Available",
            description:
              "Defining the right to access and data portability. - The information is subject to the requirements of Art. 20 (right to data portability) GDPR.",
            type: "boolean",
            definition: {
              schema: "10f240c4-f0bc-4108-ac65-eb1ea1c6cd3c"
            }
          },
          {
            path: "accessAndDataPortability/identificationFeatures",
            question: "Beschreibungdjanfijadnifnjad",
            description: "fdakfmnadlkfad",
            type: "string",
            definition: {
              schema: "10f240c4-f0bc-4108-ac65-eb1ea1c6cd3c"
            }
          },
          {
            path: "accessAndDataPortability/email",
            question: "Beschreibungdjanfijadnifnjad",
            description: "fdakfmnadlkfad",
            type: "string",
            definition: {
              schema: "10f240c4-f0bc-4108-ac65-eb1ea1c6cd3c"
            }
          },
          {
            path: "accessAndDataPortability/dataFormats",
            question: "Beschreibungdjanfijadnifnjad",
            description: "fdakfmnadlkfad",
            type: "string",
            definition: {
              schema: "10f240c4-f0bc-4108-ac65-eb1ea1c6cd3c"
            }
          },
          {
            path: "accessAndDataPortability/administrativeFee/amount",
            question: "Beschreibungdjanfijadnifnjad",
            description: "fdakfmnadlkfad",
            type: "double",
            definition: {
              schema: "10f240c4-f0bc-4108-ac65-eb1ea1c6cd3c"
            }
          },
          {
            path: "accessAndDataPortability/administrativeFee/currency",
            question: "Beschreibungdjanfijadnifnjad",
            description: "fdakfmnadlkfad",
            type: "string",
            definition: {
              schema: "10f240c4-f0bc-4108-ac65-eb1ea1c6cd3c"
              // value: "Lorem Ipsum..."
            }
          },
          {
            path: "test/feld1",
            question: "Feld 1",
            description: "das ist feld 1",
            type: "string",
            definition: {
              schema: "10f240c4-f0bc-4108-ac65-eb1ea1c6cd3c"
            }
          },
          {
            path: "test/feld2",
            question: "Feld 2",
            description: "das ist feld 2",
            type: "string",
            definition: {
              schema: "10f240c4-f0bc-4108-ac65-eb1ea1c6cd3c"
            }
          }
        ]
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
        await this.createProject();
        new Set(
          this.state.schema.map(field => field.path.split("/")[0])
        ).forEach(prefix => {
          this.steps.push({
            type: "FormStep",
            prefix: prefix
          });
        });
        this.stepCount = this.steps.length;
        this.step += add;
      } else if (this.step === this.steps.length - 1 && add === 1) {
        console.log("request");
        console.log(this.state);
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
        console.log(projectResponse);
        const template = {
          project: {
            id: projectResponse.headers.location.split("/").slice(-1)[0]
          },
          version: 1,
          changelog: "Initial",
          parents: []
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
      } catch (error) {
        await this.$alert(error, "Projekt kann nicht erstellt werden");
        console.error(error);
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
