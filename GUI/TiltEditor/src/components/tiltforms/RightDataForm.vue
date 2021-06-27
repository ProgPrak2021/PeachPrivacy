<template>
    <div>
        <b-form >
            <b-form-group label="Verfügbarkeit" >
              <b-form-select  v-model="pRightData.available" :options="options"
                              class="mb-3" v-b-tooltip.hover :title="rightDataInf.available" size="lg">
                <template #first>
                  <b-form-select-option value="" disabled>-- Please select an option --</b-form-select-option>
                </template>
              </b-form-select>
            </b-form-group> 
            <b-form-group label="Beschreibung">
                <b-form-input v-b-tooltip.hover :title="rightDataInf.description" :placeholder="[[rightData.description]]" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Url">
                <b-form-input v-b-tooltip.hover :title="rightDataInf.url" :placeholder="[[rightData.url]]" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="E-Mail">
                <b-form-input v-b-tooltip.hover :title="rightDataInf.email" :placeholder="[[rightData.email]]" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Identifikationsnachweise">
                <b-form-tags input-id="tags-basic" v-b-tooltip.hover :title="rightDataInf.identificationEvidences" :placeholder="[[rightData.identificationEvidences]]" v-model="rightData.identificationEvidences"></b-form-tags>
                <p class="mt-2">Data Categories: {{ rightData.identificationEvidences }}</p>
            </b-form-group>
        </b-form>
    </div>
</template>

<script>
export default {
  name: "RightData",
  data() {
    return {
      options: [
        { value: 'true',text: 'true',},
        {value: 'false',text: 'false' },
      ],
        rightData: {
            available: this.pRightData.available,
            description: this.pRightData.description,
            url: this.pRightData.url,
            email:this.pRightData.email,
            identificationEvidences: this.pRightData.identificationEvidences
        },
        rightDataInf: {
            available: "Ist dieses Recht verfügbar?",
            description: "Hier dem entsprechendem Recht bitte nachkommen.",
            url: "Für weitere Infos für ihre Kund*innen bitte hier den Link ihrer Webseite angeben.",
            email:"Bei Fragen zu diesem speziellen Punkt: Über welche Email können ihre Kund*innen Rückfragen stellen?",
            identificationEvidences: "Fügen Sie bitte hier entsprechende Nachweise an.",
        }
    }
  },
  props: {
        pRightData: {
            available: Boolean,
            description: String,
            url: String,
            email: String,
            identificationEvidences: Array
        }
    },
}
</script>

<style scoped>

</style>