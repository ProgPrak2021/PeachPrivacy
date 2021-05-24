<template>
    <div class="tilt-block">
      <tr>
        <td>
            <h3>Protection Officer:</h3>
        </td>
      </tr>  
      <b-form id="modal-controller" title="ProtectionOfficer" >
        <b-form-group id="ctrlname" label="Name">
            <b-form-input v-model="dataProtectionOfficer.name" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrladdress" label="Address">
            <b-form-input v-model="dataProtectionOfficer.address" type= datetime aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlcountry" label="Country">
            <b-form-input v-model="dataProtectionOfficer.country" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlrepresentativeemail" label="E-Mail">
            <b-form-input v-model="dataProtectionOfficer.email" aria-required=""></b-form-input>
        </b-form-group>
         <b-form-group id="ctrlrepresentativephone" label="Phone">
            <b-form-input v-model="dataProtectionOfficer.phone" aria-required=""></b-form-input>
        </b-form-group>
    </b-form>
    </div>
</template>

<script>
export default {
  name: "protectionOfficer",
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