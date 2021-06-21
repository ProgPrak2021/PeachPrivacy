<template>
    <div class="tilt-block" >
            <b-form-group v-for="pentry in recipients" v-bind:key="pentry.category">
              <tilt-recipient v-bind:precipient="pentry" ></tilt-recipient> 
            </b-form-group>
            <button class="btn" v-on:click='addNewRecipient' >+</button>
            <button class="btn" v-on:click='removeRecipient'>-</button>
    </div>
</template>

<script>
import RecipientForm from './RecipientForm.vue' 

export default {
  name: "recipients",
  components: {
    'tilt-recipient':RecipientForm,
  },
  data() {
    return {
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
                category: "Anbieter von Marketing-Inhalten"
            },
          ],
    }
  },
  props: {
     precipients:  Array,
  },
  methods: {
     addNewRecipient: function () {
          this.recipients.push( {
                name: "Yellow Company AG",
                division: "Product line e-mobility",
                address: "Triana 123, 9999 Seville",
                country: "ES",
                representative: {
                    name: "Jane Super",
                    email: "contact@yellowcompany.de",
                    phone: "0049 151 1234 9876"
                },
                category: "Anbieter von Marketing-Inhalten"
        });
      },
       removeRecipient: function () {
                this.recipients.pop();
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