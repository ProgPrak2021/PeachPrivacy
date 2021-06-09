<template>
    <div  class="tilt-block" >
            <b-form-group v-for="pentry in purposes" v-bind:key="pentry.purpose">
              <tilt-purpose v-bind:ppurpose="pentry.purpose"  v-bind:pdescription="pentry.description" ></tilt-purpose> 
            </b-form-group>
            <button class="btn" v-on:click='addNewPurpose' >+</button>
            <button class="btn" v-on:click='removePurpose'>-</button>
    </div>
</template>

<script>
import PurposeForm from './PurposeForm.vue' 

export default {
  name: "Purposes",
  components: {
    'tilt-purpose':PurposeForm,
  },
  data() {
    return {
           purposes: [
            {
              purpose: "Marketing",
              description: "Newsletter will be sent out once a month."
            }
        ],
    }
  },
  props: {
     ppurposes:  Array,
  },
  methods: {
     addNewPurpose: function () {
          this.purposes.push( {
             purpose: "Marketing",
            description: "Newsletter will be sent out once a month."
        });
      },
       removePurpose: function () {
                this.purposes.pop();
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