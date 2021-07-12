<template>
  <div>
    <h3>{{ prefix }}</h3>
    <template
      v-for="field in value.fields"
      v-if="field.path.join('/').startsWith(prefix)"
    >
      <div :key="field.path.join('/')" class="md-layout">
        <div class="md-layout-item">
          <md-field v-if="field.type === 'string' || field.type === 'array'">
            <label>{{ field.question }}</label>
            <md-input
              type="text"
              v-model="field.definition.value"
              @input="update"
            ></md-input>
          </md-field>
          <md-field v-if="field.type === 'integer' || field.type === 'number'">
            <label>{{ field.question }} number</label>
            <md-input
              type="number"
              pattern="\d+(,\d{2})?"
              v-model="field.definition.value"
              @input="update"
            ></md-input>
          </md-field>
          <md-switch
            v-if="field.type === 'boolean'"
            v-model="field.definition.value"
            style="margin-top: 15px"
            @input="update"
          >
            {{ field.question }}
          </md-switch>
        </div>
        <div class="md-layout-item md-size-10">
          <v-popover offset="8" placement="right">
            <md-button class="md-primary md-simple">?</md-button>
            <template slot="popover">
              <h3 class="popover-header">{{ field.question }}</h3>
              <div class="popover-body">
                {{ field.description }}
              </div>
            </template>
          </v-popover>
        </div>
      </div>
    </template>
  </div>
</template>

<script>
export default {
  name: "FormStep",
  mounted() {
    this.value.valid = true;
  },
  props: {
    value: {
      type: Object
    },
    prefix: {
      type: String
    }
  },
  methods: {
    update() {
      this.$emit("input", this.value);
    }
  }
};
</script>

<style scoped></style>
