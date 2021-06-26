<template>
    <div >
        <b-form class="bform">
            <b-form-group label="Vorhanden?"  >
              <b-form-select  v-model="pexists" :options="options"
                              class="mb-3" v-b-tooltip.hover :title="legitimateInterestInf.exists" size="lg">
                <template #first>
                  <b-form-select-option value="" disabled>-- Please select an option --</b-form-select-option>
                </template>
              </b-form-select>
            </b-form-group>
            <b-form-group label="Grund">
                <b-form-input v-b-tooltip.hover :title="legitimateInterestInf.reasoning" :placeholder="[[reasoning]]" aria-required=""></b-form-input>
            </b-form-group>
        </b-form>
    </div>
</template>

<script>
export default {
    name: "LegitimateInterest",
    data() {
    return {
      options: [
        { value: 'true',text: 'true',},
        {value: 'false',text: 'false' },
      ],
        exists: this.pexists,
        reasoning: this.preasoning,
        legitimateInterestInf: {
            exists: "Existiert hier ein berechtigtes Interesse?",
            reasoning: "Warum existiert hier ein berechtigtes Interesse",
        }
    }
    },
    props: {
        pexists: Boolean,
        preasoning: String,
    },
}
</script>

<style scoped>
 .bform{
        border-top-color: #D9304F;
        border-top-style: solid;
        border-top-width: 2px;
        margin-top: 20px;
    }
</style>