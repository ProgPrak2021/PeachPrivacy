<template>
    <div class="tilt-block">
      <tr>
        <td>
            <h3>Controller:</h3>
        </td>
      </tr>  
      <b-form id="modal-controller" title="Controller" >
        <b-form-group id="ctrlname" label="Firmenname">
            <b-form-input v-b-tooltip.hover :title="controllerInf.name" :placeholder="[[controller.name]]" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrldivision" label="Abteilung">
            <b-form-input v-b-tooltip.hover :title="controllerInf.division" :placeholder="[[controller.division]]" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrladdress" label="Adresse">
            <b-form-input  v-b-tooltip.hover :title="controllerInf.address" :placeholder="[[controller.address]]" type= datetime aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlcountry" label="Land">
            <b-form-input  v-b-tooltip.hover :title="controllerInf.country" :placeholder="[[controller.country]]" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlrepresentativename" label="Name">
            <b-form-input  v-b-tooltip.hover :title="controllerInf.representative.name" :placeholder="[[controller.representative.name]]" aria-required=""></b-form-input>
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
            name: "Green Company AG",
            division: "Product line e-mobility",
            address: "Wolfsburger Ring 2, 38440 Berlin",
            country: "DE",
            representative: {
                name: "Jane Super",
                email: "contact@greencompany.de",
                phone: "0049 151 1234 5678"
            }
        },
         controllerInf: {
            name: "Tragen Sie hier bitte den Namen ihres Unternehmens ein, damit ihre Kund*innen das Tiltdokument später ihrem Unternehmen zuordnen können.",
            division: "Die Abteilung des Controllers gehört in dieses Feld.",
            address: "Hier tragen Sie bitte die Adresse ihres Unternehmens ein.",
            country: "Wo ihr Unternehmen seinen Firmensitz hat, gehört in dieses Feld.",
            representative: {
                name: "Bei Nachfragen soll dieser Mitarbeiter kontaktiert werden. Geben Sie dafür in dieses Feld den vollständigen Namen dieses Mitarbeiters ein.",
                email: "Die Emailadresse dieses Mitarbeiters schreiben Sie bitte in diesen Feld.",
                phone: "Tragen Sie bitte hier die Telefonnummer des Mitarbeiters ein"
            }
        }
    }
  }
}
</script>

<style scoped>

</style>