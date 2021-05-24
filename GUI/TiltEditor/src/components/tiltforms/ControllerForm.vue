<template>
    <div class="tilt-block">
      <tr>
        <td>
            <h3>Controller:</h3>
        </td>
      </tr>  
      <b-form id="modal-controller" title="Controller" >
        <b-form-group id="ctrlname" label="Name">
            <b-form-input v-model="controller.name" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrldivision" label="Division">
            <b-form-input v-model="controller.division" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrladdress" label="Address">
            <b-form-input v-model="controller.address" type= datetime aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlcountry" label="Country">
            <b-form-input v-model="controller.country" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlrepresentativename" label="Representative Name">
            <b-form-input v-model="controller.representative.name" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlrepresentativeemail" label="Representative E-Mail">
            <b-form-input v-model="controller.representative.email" aria-required=""></b-form-input>
        </b-form-group>
         <b-form-group id="ctrlrepresentativephone" label="Representative Phone">
            <b-form-input v-model="controller.representative.phone" aria-required=""></b-form-input>
        </b-form-group>
    </b-form>
    </div>
</template>

<script>
export default {
  name: "controller",
  data() {
    return {
        controller: {
            name: "Green Company AG",
            division: "Product line e-mobility",
            address: "Wolfsburger Ring 2, 38440 Berlin",
            country: "DE",
            representative: {
                name: "Jane Super",
                email: "contact@greencompany.de",
                phone: "0049 151 1234 5678"
            }
        }
    }
  }
}
</script>

<style scoped>

</style>