<template>
    <div class="tilt-block" >
            <b-form-group v-for="pentry in legalBases" v-bind:key="pentry.reference">
              <tilt-legalbase v-bind:preference="pentry.reference"  v-bind:pdescription="pentry.description" ></tilt-legalbase> 
            </b-form-group>
            <button class="btn" v-on:click='addNewBasis' >+</button>
            <button class="btn" v-on:click='removeBasis'>-</button>
    </div>
</template>

<script>
import LegalBasisForm from './LegalBasisForm.vue' 

export default {
  name: "LegalBases",
  components: {
    'tilt-legalbase':LegalBasisForm,
  },
  data() {
    return {
           legalBases: [
            {
              reference: "GDPR-99-1-a",
              description: "Die Verarbeitung der Daten erfolgt auf der Grundlage von Art. 99 GDPR, der besagt..."
            }
        ],
    }
  },
  props: {
     plegalBases:  Array,
  },
  methods: {
     addNewBasis: function () {
          this.legalBases.push( {
             reference: "GDPR-99-1-a",
            description: "Die Verarbeitung der Daten erfolgt auf der Grundlage von Art. 99 GDPR, der besagt..."
        });
      },
       removeBasis: function () {
                this.legalBases.pop();
      }
  }
}
</script>

<style scoped>
.btn{
    margin: 20px 10px 10px 10px;
    background-color: #D9304F;
    border-color:#D9304F;
    border-style: solid;
    border-radius: 5px 5px 5px 5px;
    border-width: 100%;
    /*color: #D9304F; */
  }

</style>