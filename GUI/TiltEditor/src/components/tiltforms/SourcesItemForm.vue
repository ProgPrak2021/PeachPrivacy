<template>
    <div  class="tilt-block" >
        <b-form >
            <b-form-group label="Datenkategorie">
                <b-form-input v-model="sourceItem.dataCategory" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group v-for="entry in sourceItem.sources" v-bind:key="entry.url">
               <tilt-sources v-bind:entry="psource"></tilt-sources> 
            </b-form-group> 
            <b-form-group>
                <button class="btn" @click="addNewSource">Add new Source</button>
            </b-form-group>
        </b-form>
    </div>
</template>

<script>
import SourcesForm from './SourcesForm.vue' 

export default {
  name: "SourcesItem",
  components: {
    'tilt-sources':SourcesForm,
  },
  data() {
    return {
         sourceItem: {
             _id: this.psourceItem._id,
            dataCategory: this.psourceItem.dataCategory,
            sources: this.psourceItem.sources,
        }
    }
  },
  props: {
     psourceItem: {
                 _id: String,
                dataCategory: String,
                sources: Array,
            }   
  },
  methods: {
      addNewSource(){
         this.$emit("added-source", this.sourceItem.sources)
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