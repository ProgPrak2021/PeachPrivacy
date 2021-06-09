<template>
    <div  class="tilt-block" >
       <tr>
        <td>
            <h3>Data Disclosed:</h3>
        </td>
      </tr> 
      <b-form-group v-for="entry in sources" v-bind:key="entry._id">
           <h4>Item</h4>
           <!--    <tilt-sources v-bind:psourceItem="entry"></tilt-sources> --> 
      </b-form-group>  
      <button class="btn" v-on:click='addNewItem'>Hinzufügen</button>
      <button class="btn" v-on:click='removeItem'>Entfernen</button>
</div>
</template>

<script>
//import SourcesItemForm from './SourcesItemForm.vue' 

export default {
  name: "DataDisclosedItems",
  components: {
  //  'tilt-sources':SourcesItemForm,
  },
  data() {
    return {
    dataDisclosed: [
        {
        _id: "f1424f86-ca0f-4f0c-9438-43cc00509931",
        category: "E-mail address",
        purposes: [
            {
            purpose: "Marketing",
            description: "Newsletter will be sent out once a month."
            }
        ],
        legalBases: [
            {
            reference: "GDPR-99-1-a",
            description: "The data are processed on the basis of Art. 99 GDPR which states..."
            },
            {
            reference: "BDSG-42-5",
            description: "BDSG-42-5 refers to the processing of personal data within..."
            }
        ],
        legitimateInterests: [
            {
            exists: false,
            reasoning: "There is an legitimate interest based on ... and is not overwritten because ..."
            }
        ],
        recipients: [
            {
            name: "Yellow Company AG",
            division: "Product line e-mobility",
            address: "Triana 123, 9999 Seville",
            country: "ES",
            representative: {
                name: "Jane Super",
                email: "contact@yellowcompany.de",
                phone: "0049 151 1234 9876"
            },
            category: "Marketing content provider"
            },
            {
            category: "Responsible Statistical Institutes"
            }
        ],
        storage: [
            {
            temporal: [
                {
                description: "Creating backups.",
                ttl: "P3Y6M4DT12H30M5S"
                },
                {
                description: "Finishing ordering process.",
                ttl: "2020-08-09T18:31:42P3Y6M4DT12H30M17S"
                }
            ],
            purposeConditional: [
                "Data is stored until the end of the ordering process."
            ],
            legalBasisConditional: [
                "SGB-100-42"
            ],
            aggregationFunction: "max"
            }
        ],
        nonDisclosure: {
            legalRequirement: false,
            contractualRegulation: false,
            obligationToProvide: false,
            consequences: "If the data is not disclosed, the shipment cannot be delivered."
        }
        }
    ],
    }
  },
  props: {
     pDataDisclosedItems: Array,
  },
  methods: {
     addNewItem: function () {
         console.log("addNewItem"); 
      },
      removeItem: function () {
         console.log("removeItem"); 
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