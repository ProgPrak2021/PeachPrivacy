<template>
    <div>
        <b-form class="bform">
            <b-form-group label="Beschreibung">
                <b-form-input v-model="source.description" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Url">
                <b-form-input v-model="source.url" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Öffentliche Verfügbarkeit">
                <b-form-input v-model="source.publiclyAvailable" aria-required=""></b-form-input>
            </b-form-group>
        </b-form>
    </div>
</template>

<script>
export default {
  name: "Sources",
  data() {
    return {
        source: {
            description: "Diese Informationen können abgerufen werden von...1",
            url: "https://blue1Company.org",
            publiclyAvailable: false
        }
    }
  },
  props: {
        psource: {
                description: String,
                url: String,
                publiclyAvailable:Boolean,
            }
    },
}
</script>

<style scoped>
    .bform{
        border-top-color: #D9304F;
        border-top-style: solid;
        border-top-width: 2px;
        margin-top: 20px;
    }
</style>