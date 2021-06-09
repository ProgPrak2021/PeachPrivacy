<template>
    <div  class="tilt-block" >
            <b-form-group label="Datenkategorie">
                <b-form-input v-model="sourceItem.dataCategory" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group v-for="entry in sourceItem.sources" v-bind:key="entry.url">
               <tilt-sources v-bind:entry="psource"></tilt-sources> 
            </b-form-group> 
            <button class="btn" v-on:click='addNewSource' >Hinzufügen</button>
            <button class="btn" v-on:click='removeSource'>Entfernen</button>
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
     addNewSource: function () {
          this.sourceItem.sources.push( {
            description: "This information could be retrieved from...3",
            url: "https://blue3Company.org",
            publiclyAvailable: false
        });
      },
       removeSource: function () {
                this.sourceItem.sources.pop();
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