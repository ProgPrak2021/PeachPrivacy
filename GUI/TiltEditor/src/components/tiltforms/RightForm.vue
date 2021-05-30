<template>
    <div class="row"> 
        <div class="col-12 col-md-6 col-lg-4">
            <div class="tilt-block">
                <tr>
                    <td>
                        <h3>Rights to Information:</h3>
                    </td>
                </tr>  
                <tilt-right-data v-bind:pRightData="rightToInformation" />
            </div>
        </div>
        <div class="col-12 col-md-6 col-lg-4">
            <div class="tilt-block">
                <tr>
                    <td>
                        <h3>Rights to Rectification Or Deletion:</h3>
                    </td>
                </tr>  
                <tilt-right-data v-bind:pRightData="rightToRectificationOrDeletion" />
          </div>
        </div>
        <div class="col-12 col-md-6 col-lg-4">
            <div class="tilt-block">
                <tr>
                    <td>
                        <h3>Rights to Data Portability:</h3>
                    </td>
                </tr>  
                <tilt-right-data v-bind:pRightData="rightToDataPortability" />
            </div>
        </div>
        <div class="col-12 col-md-6 col-lg-4">
            <div class="tilt-block">
                <tr>
                    <td>
                        <h3>Rights to Withdraw Consent:</h3>
                    </td>
                </tr>  
                <tilt-right-data v-bind:pRightData="rightToWithdrawConsent" />
            </div>
        </div>
        <div class="col-12 col-md-6 col-lg-4">
            <div class="tilt-block">
                <tr>
                    <td>
                        <h3>Rights to Complain:</h3>
                    </td>
                </tr>  
                <tilt-right-data v-bind:pRightData="rightToComplain" />
            </div>
        </div>
        <div class="col-12 col-md-6 col-lg-4">
            <div class="tilt-block">
                <tr>
                    <td>
                        <h3>Rights to Complain: Supervisory Authority </h3>
                    </td>
                </tr>  
                <tilt-address v-bind:paddress="rightToComplain.supervisoryAuthority" />
            </div>
        </div>
    </div>
</template>

<script>
import AddressForm from './AddressForm.vue'
import RightDataForm from './RightDataForm.vue'

export default {
  name: "tilt_right",
  components: {
    'tilt-address':AddressForm,
    'tilt-right-data':RightDataForm,
  },
  data() {
    return {
        rightToInformation: {
            available: this.pRightToInformation.available,
            description: this.pRightToInformation.description,
            url: this.pRightToInformation.url,
            email:this.pRightToInformation.email,
            identificationEvidences: this.pRightToInformation.identificationEvidences
        },
        rightToRectificationOrDeletion: {
            available: this.pRightToRectificationOrDeletion.available,
            description: this.pRightToRectificationOrDeletion.description,
            url: this.pRightToRectificationOrDeletion.url,
            email:this.pRightToRectificationOrDeletion.email,
            identificationEvidences: this.pRightToRectificationOrDeletion.identificationEvidences
        },
        rightToDataPortability: {
            available: this.pRightToDataPortability.available,
            description: this.pRightToDataPortability.description,
            url: this.pRightToDataPortability.url,
            email:this.pRightToDataPortability.email,
            identificationEvidences: this.pRightToDataPortability.identificationEvidences
        },
        rightToWithdrawConsent: {
            available: this.pRightToWithdrawConsent.available,
            description: this.pRightToWithdrawConsent.description,
            url: this.pRightToWithdrawConsent.url,
            email:this.pRightToWithdrawConsent.email,
            identificationEvidences: this.pRightToWithdrawConsent.identificationEvidences
        },
        rightToComplain: {
            available: this.pRightToComplain.available,
            description: this.pRightToComplain.description,
            url: this.pRightToComplain.url,
            email:this.pRightToComplain.email,
            identificationEvidences: this.pRightToComplain.identificationEvidences,
            supervisoryAuthority: {
                name: this.pRightToComplain.supervisoryAuthority.name,
                address: this.pRightToComplain.supervisoryAuthority.address,
                country: this.pRightToComplain.supervisoryAuthority.country,
                email: this.pRightToComplain.supervisoryAuthority.email,
                phone: this.pRightToComplain.supervisoryAuthority.phone
            }
        },
       }
    },
    props: {
        pRightToInformation: {
            available: Boolean,
            description: String,
            url: String,
            email: String,
            identificationEvidences: Array
        },
        pRightToRectificationOrDeletion: {
            available: Boolean,
            description: String,
            url: String,
            email: String,
            identificationEvidences: Array
        },
        pRightToDataPortability: {
            available: Boolean,
            description: String,
            url: String,
            email: String,
            identificationEvidences: Array
        },
        pRightToWithdrawConsent: {
            available: Boolean,
            description: String,
            url: String,
            email: String,
            identificationEvidences: Array
        },
        pRightToComplain: {
            available: Boolean,
            description: String,
            url: String,
            email: String,
            identificationEvidences: Array,
            supervisoryAuthority: {
                name: String,
                address: String,
                country:String,
                email: String,
                phone: String
            }
        }
    },
 };
</script>

<style scoped>
</style>