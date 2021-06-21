<template>
    <div class="tilt-block">
        <tr>
            <td>
                <h3>Datenschutzbeauftragter:</h3>
            </td>
        </tr>  
        <tilt-address v-bind:paddress="dataProtectionOfficer" />
    </div>
</template>

<script>
import AddressForm from './AddressForm.vue'
export default {
  name: "protectionOfficer",
  components: {
    'tilt-address':AddressForm,
  },
  data() {
    return {
        dataProtectionOfficer: {
            name: "Jane Super",
            address: "Wolfsburger Ring 2, 38440 Berlin",
            country: "DE",
            email: "contact@greencompany.de",
            phone: "0049 151 1234 5678"
        }
    }
  }
}
</script>

<style scoped>

</style>