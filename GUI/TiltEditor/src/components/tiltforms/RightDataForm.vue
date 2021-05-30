<template>
    <div>
        <b-form >
            <b-form-group label="Available" >
                <b-form-input v-model="rightData.available"></b-form-input>
            </b-form-group> 
            <b-form-group label="Description">
                <b-form-input v-model="rightData.description" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Url">
                <b-form-input v-model="rightData.url" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="E-Mail">
                <b-form-input v-model="rightData.email" aria-required=""></b-form-input>
            </b-form-group>
            <b-form-group label="Identification Evidences">
                <b-form-tags input-id="tags-basic" v-model="rightData.identificationEvidences"></b-form-tags>
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
        rightData: {
            available: this.pRightData.available,
            description: this.pRightData.description,
            url: this.pRightData.url,
            email:this.pRightData.email,
            identificationEvidences: this.pRightData.identificationEvidences
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