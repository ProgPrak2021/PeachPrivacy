<template>
  <div class="tilt-block">
    <tr>
      <td>
        <h3>Datenschutzbeauftragter:</h3>
      </td>
    </tr>
    <b-form id="modal-controller" title="Controller" >

      <b-form-group id="ctrladdress" label="Name">
        <b-form-input  v-b-tooltip.hover :title="controllerInf.representative.name" :placeholder="[[controller.representative.name]]" type= datetime aria-required=""></b-form-input>
      </b-form-group>
      <b-form-group id="ctrlcountry" label="Wohnort">
        <b-form-input  v-b-tooltip.hover :title="controllerInf.address" :placeholder="[[controller.address]]" aria-required=""></b-form-input>
      </b-form-group>
      <b-form-group id="ctrlrepresentativename" label="Land                                                                                            ">
        <b-form-input  v-b-tooltip.hover :title="controllerInf.country" :placeholder="[[controller.country]]" aria-required=""></b-form-input>
      </b-form-group>
      <b-form-group id="ctrlrepresentativeemail" label="E-Mail">
        <b-form-input  v-b-tooltip.hover :title="controllerInf.representative.email" :placeholder="[[controller.representative.email]]" aria-required=""></b-form-input>
      </b-form-group>
      <b-form-group id="ctrlrepresentativephone" label="Telefonnummer">
        <b-form-input  v-b-tooltip.hover :title="controllerInf.representative.phone" :placeholder="[[controller.representative.phone]]" aria-required=""></b-form-input>
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
        address: "Wolfsburger Ring 2, 38440 Berlin",
        country: "DE",
        representative: {
          name: "Jane Super",
          email: "contact@greencompany.de",
          phone: "0049 151 1234 5678"
        }
      },
      controllerInf: {
        address: "Hier tragen Sie bitte den Wohnort des Datenschutzbeauftragten ein",
        country: "Hier tragen Sie bitte das Land des Wohnortes ein.",
        representative: {
          name: " Geben Sie in dieses Feld den vollständigen Namen des Mitarbeiters ein, der für die Einhaltung der Datenschutzrichtlinien zuständig ist (Datenschutzbeauftragter).",
          email: "Die Emailadresse dieses Mitarbeiters schreiben Sie bitte in dieses Feld.",
          phone: "Tragen Sie bitte hier die Telefonnummer des Mitarbeiters ein"
        }
      }
    }
  }
}
</script>

<style scoped>

</style>