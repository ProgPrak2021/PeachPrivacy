<template>
    <div >
        <b-form class="bform">
            <b-form-group label="Legale Basis"  >
                <b-form-input v-b-tooltip.hover :title="legalBasisInf.reference" :placeholder="[[reference]]"></b-form-input>
            </b-form-group> 
            <b-form-group label="Beschreibung">
                <b-form-input v-b-tooltip.hover :title="legalBasisInf.description" :placeholder="[[description]]" aria-required=""></b-form-input>
            </b-form-group>
        </b-form>
    </div>
</template>

<script>
export default {
    name: "LegalBasis",
    data() {
    return {
        reference: this.preference,
        description: this.pdescription,
        legalBasisInf: {
            reference: "Hier bitte die entsprechende Referenz angeben.",
            description: "Hier die Beschreibung der legalen Basis hinterlegen",
        }
    }
    },
    props: {
        preference: String,
        pdescription: String,
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