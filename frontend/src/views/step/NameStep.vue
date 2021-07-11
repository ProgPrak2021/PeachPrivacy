<template>
  <div>
    <h2>Neues Projekt</h2>
    <br />
    <md-field :class="nameValid ? 'md-valid' : ''">
      <label>Projekt Name</label>
      <md-input type="text" v-model="value.name" @input="update"></md-input>
    </md-field>
    <md-field :class="descriptionValid ? 'md-valid' : ''">
      <label>Projekt Beschreibung</label>
      <md-input
        type="text"
        v-model="value.description"
        @input="update"
      ></md-input>
    </md-field>
  </div>
</template>

<script>
export default {
  name: "NameStep",
  mounted() {
    this.update();
  },
  props: {
    value: {
      type: Object,
      default() {
        return {
          name: "",
          description: ""
        };
      }
    }
  },
  computed: {
    nameValid: function() {
      return this.value.name !== "";
    },
    descriptionValid: function() {
      return this.value.description !== "";
    }
  },
  methods: {
    update() {
      this.value.valid = this.nameValid && this.descriptionValid;
      this.$emit("input", this.value);
    }
  }
};
</script>

<style scoped></style>
