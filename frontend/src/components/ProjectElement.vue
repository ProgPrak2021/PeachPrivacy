<template>
  <div @click="onClick">
    <md-card>
      <md-card-media-cover>
        <md-card-media>
          <img :src="image" alt="-" />
        </md-card-media>
        <md-card-area :class="hover ? 'media-cover-hover' : 'media-cover'">
          <div class="media-cover-selected" v-if="selected"></div>
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
    },
    hover: {
      type: Boolean,
      default: true
    },
    selected: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    backgroundImage() {
      return {
        backgroundImage: `url(${this.image})`
      };
    }
  },
  methods: {
    onClick() {
      if (this.hover) {
        this.$emit("click");
      } else {
        this.selected = !this.selected;
        this.$emit("select", this.selected);
      }
    }
  }
};
</script>

<style scoped>
.media-cover {
  user-select: none;
  position: absolute;
  min-height: 100%;
  min-width: 100%;
  content: "";
  z-index: 50;
  cursor: pointer;
  background: linear-gradient(
    to bottom,
    rgba(0, 0, 0, 0) 20%,
    rgba(0, 0, 0, 0.6) 66%,
    rgba(0, 0, 0, 0.6) 100%
  );
}

.media-cover-hover {
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

.media-cover-hover:hover {
  background: linear-gradient(
    to bottom,
    rgba(0, 0, 0, 0) 20%,
    rgba(0, 0, 0, 0.6) 66%,
    rgba(0, 0, 0, 0.8) 100%
  );
}

.media-cover-selected {
  user-select: none;
  position: absolute;
  min-height: 100%;
  min-width: 100%;
  content: "";
  z-index: 100;
  cursor: pointer;
  background: linear-gradient(
        to top left,
        transparent 50%,
        rgb(255, 135, 108) 0
      )
      top left/60px 60px no-repeat,
    transparent;
  box-shadow: 5px 5px 0 0 rgb(255, 135, 108) inset,
    -5px -5px 0 0 rgb(255, 135, 108) inset;
}
</style>
