<template>
  <div>
    <md-card>
      <md-card-media-cover>
        <md-card-media>
          <img :src="image" alt="-" />
        </md-card-media>
        <md-card-area class="media-cover">
          <md-card-content class="text-center" style="margin-top: auto;">
            <h1 v-if="title === '+'">{{ title }}</h1>
            <h3 v-else>{{ title }}</h3>
            <p>{{ description }}</p>
          </md-card-content>
        </md-card-area>
      </md-card-media-cover>
    </md-card>
  </div>
</template>

<script>
export default {
  name: "ProjectElement",
  props: {
    image: {
      type: String,
      default: require("@/assets/img/examples/studio-5.jpg")
    },
    title: {
      type: String,
      default: "+"
    },
    description: {
      type: String,
      default: "Neues Projekt erstellen"
    }
  },
  computed: {
    backgroundImage() {
      return {
        backgroundImage: `url(${this.image})`
      };
    }
  }
};
</script>

<style scoped>
.media-cover {
  position: absolute;
  min-height: 100%;
  min-width: 100%;
  content: "";
  z-index: 100;
  cursor: pointer;
  background: linear-gradient(
    to bottom,
    rgba(0, 0, 0, 0) 20%,
    rgba(0, 0, 0, 0.6) 66%,
    rgba(0, 0, 0, 0.6) 100%
  );
}
.media-cover:hover {
  background: linear-gradient(
    to bottom,
    rgba(0, 0, 0, 0) 20%,
    rgba(0, 0, 0, 0.6) 66%,
    rgba(0, 0, 0, 0.8) 100%
  );
}
</style>
