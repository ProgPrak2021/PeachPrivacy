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

export default {
  name: "ProjectCreate",
  bodyClass: "profile-page",
  components: { TemplateStep, NameStep },
  data() {
    return {
      step: 0,
      steps: [],
      stepCount: 10,
      state: {
        name: "",
        description: "",
        projects: [],
        valid: false
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
    pageUpdate(add) {
      if (this.step === 1 && add === 1) {
        console.log("request data for");
        console.log(this.state);
      } else if (this.step === this.steps.length && add === 1) {
      } else {
        this.step += add;
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
