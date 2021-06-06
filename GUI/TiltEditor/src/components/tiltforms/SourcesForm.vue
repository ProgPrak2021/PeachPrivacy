<template>
    <div>
        <b-form >
            <b-form-group label="Beschreibung">
                <b-form-input v-model="source.description" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Url">
                <b-form-input v-model="source.url" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Öffentliche Verfügabrkeit">
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
            description: "This information could be retrieved from...1",
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

</style>