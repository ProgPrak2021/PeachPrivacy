<template>
    <div>
        <b-form class="bform">
            <b-form-group label="Beschreibung">
                <b-form-input v-b-tooltip.hover :title="six.description" v-model="source.description" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Url">
                <b-form-input v-b-tooltip.hover :title="six.url" v-model="source.url" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Öffentliche Verfügbarkeit">
              <b-form-select  v-model="source.publiclyAvailable" :options="options"
                              class="mb-3" v-b-tooltip.hover :title="six.pubavai" size="lg">
                <template #first>
                  <b-form-select-option value="" disabled>-- Please select an option --</b-form-select-option>
                </template>
              </b-form-select>
            </b-form-group>
        </b-form>
    </div>
</template>

<script>
export default {
  name: "Sources",
  data() {
    return {
      options: [
        { value: 'false',text: 'false',},
        {value: 'true',text: 'true' }
      ],

      six:{
        description :" Beschreibung voher die Information kommen ",
        url :" Die Url woher die Daten stammen",
        pubavai :"Sind diese Daten öffentlich verfügbar "

      },
        source: {
            description: "Diese Informationen können abgerufen werden von...1",
            url: "https://blue1Company.org",
            publiclyAvailable: false
        },
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