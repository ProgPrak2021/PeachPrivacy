<template>
    <div >
        <b-form class="bform">
            <b-form-group label="Zweck"  >
                <b-form-input v-b-tooltip.hover :title="purposeInf.purpose" :placeholder="[[purpose]]"></b-form-input>
            </b-form-group> 
            <b-form-group label="Beschreibung">
                <b-form-input v-b-tooltip.hover :title="purposeInf.description" :placeholder="[[description]]" aria-required=""></b-form-input>
            </b-form-group>
        </b-form>
    </div>
</template>

<script>
export default {
    name: "Proposal",
    data() {
    return {
        purpose: this.ppurpose,
        description: this.pdescription,
        purposeInf: {
            purpose: "Hier den Zweck eingeben",
            description: "Hier die Beschreibung des Zweckes hinterlegen",
        }
    }
    },
    props: {
        ppurpose: String,
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