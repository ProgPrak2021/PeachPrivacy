<template>
    <div >
        <b-form class="bform">
            <b-form-group label="Recipientunternehmen"  >
                <b-form-input v-b-tooltip.hover :title="recipientInf.name" :placeholder="[[recipient.name]]"></b-form-input>
            </b-form-group> 
            <b-form-group label="Abteilung">
                <b-form-input v-b-tooltip.hover :title="recipientInf.division" :placeholder="[[recipient.division]]" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Adresse">
                <b-form-input v-b-tooltip.hover :title="recipientInf.address" :placeholder="[[recipient.address]]" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Land">
                <b-form-input v-b-tooltip.hover :title="recipientInf.country" :placeholder="[[recipient.country]]" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Repräsentant Name">
                <b-form-input v-b-tooltip.hover :title="recipientInf.representative.name" :placeholder="[[precipient.representative.name]]" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Repräsentant EMail">
                <b-form-input v-b-tooltip.hover :title="recipientInf.representative.email" :placeholder="[[precipient.representative.email]]" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Repräsentant Telefon">
                <b-form-input v-b-tooltip.hover :title="recipientInf.representative.phone" :placeholder="[[precipient.representative.phone]]" aria-required=""></b-form-input>
            </b-form-group>
        </b-form>
    </div>
</template>

<script>
export default {
    name: "Recipient",
    data() {
        return {
            recipient:
            {
                name: this.precipient.name,
                division: this.precipient.division,
                address: this.precipient.address,
                country: this.precipient.country,
                representative: {
                    name: this.precipient.representative.name,
                    email: this.precipient.representative.email,
                    phone: this.precipient.representative.phone,
                },
                category:this.precipient.category,
            },
            recipientInf: {
                name: "Hier bitte das Unternehmen des Empfängers angeben.",
                division: "Die entsprechende Abteilung ist in diese Feld einzutragen.",
                address: "Die Adresse des Unternehmens gehört in dieses Feld.",
                country: "Das Land hier notieren.",
                representative: {
                    name: "Name des repräsentierenden Mitarbeiters angeben.",
                    email: "Die Email dieses Mitarbeites ist hier zu notieren.",
                    phone: "Die Telefonnummer des Mitarbeiters bitte in dieses Feld"
                },
                category: "Die Kategory hier notieren.",
            }
        }
    },
    props: {
        precipient:
        {
            name: String,
            division: String,
            address: String,
            country: String,
            representative: {
                name: String,
                email: String,
                phone: String,
            },
            category: String,
        },
    }
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