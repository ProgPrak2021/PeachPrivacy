<template>
    <div  class="tilt-block" >
       <tr>
        <td>
            <h3>Sources:</h3>
        </td>
      </tr>  
      <b-form-group v-for="entry in sources" v-bind:key="entry._id">
               <tilt-sources v-bind:psourceItem="entry"></tilt-sources> 
      </b-form-group> 
      <button class="btn" v-on:click='addNewItem'>Hinzufügen</button>
      <button class="btn" v-on:click='removeItem'>Entfernen</button>
</div>
</template>

<script>
import SourcesItemForm from './SourcesItemForm.vue' 

export default {
  name: "SourcesItems",
  components: {
    'tilt-sources':SourcesItemForm,
  },
  data() {
    return {
       sources: [
        {
          _id: "f1423cc00509931",
          dataCategory: "Creditworthiness",
          sources: [
          ]
        },
        {
          _id: "f1423cc00509932",
          dataCategory: "Creditworthiness 2",
          sources: [
          ]
        }    
        ],
    }
  },
  props: {
     psourceItems: {
                sources: Array,
            }   
  },
  methods: {
     addNewItem: function () {
         console.log("addNewItem"); 
         this.sources.push(
         {
          _id: "f1423cc00509932",
          dataCategory: "Creditworthiness 2",
          sources: [
          ]
        });
      },
      removeItem: function () {
         this.sources.pop();
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