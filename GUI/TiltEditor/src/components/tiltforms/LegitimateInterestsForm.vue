<template>
    <div  class="tilt-block" >
            <b-form-group v-for="pentry in legitimateInterests" v-bind:key="pentry.reasoning">
              <tilt-interest v-bind:pexits="pentry.exists"  v-bind:preasoning="pentry.reasoning" ></tilt-interest > 
            </b-form-group>
            <button class="btn" v-on:click='addNewInterest' >+</button>
            <button class="btn" v-on:click='removeInterest'>-</button>
    </div>
</template>

<script>
import LegitimateInterestForm from './LegitimateInterestForm.vue' 

export default {
  name: "LegitimateInterests",
  components: {
    'tilt-interest':LegitimateInterestForm,
  },
  data() {
    return {
           legitimateInterests: [
            {
              exists: false,
              reasoning: "There is an legitimate interest based on ... and is not overwritten because ...."
            }
        ],
    }
  },
  props: {
     pinterests:  Array,
  },
  methods: {
     addNewInterest: function () {
          this.legitimateInterests.push( {
             exists: false,
            reasoning: "There is an legitimate interest based on ... and is not overwritten because ..."
        });
      },
       removeInterest: function () {
                this.legitimateInterests.pop();
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