<template>
          <div class="container-fluid">
              <div class="row">
                  <div class="col-12 col-md-6 col-lg-4" v-if="forms.showMeta">
                       <tilt-meta id="tildMetaForm" v-bind:pmeta="meta" />
                  </div>
                  <div class="col-12 col-md-6 col-lg-4" v-if="forms.showController" >
                       <tilt-controller id="tildController" />
                  </div>
                  <div class="col-12 col-md-6 col-lg-4" v-if="forms.showDataManager">
                       <tilt-protection-officer id="tild-protection-officer" />
                  </div>
                  <div class="col-12 col-md-6 col-lg-4" v-if="forms.showAutomatedDecision">
                       <tilt-dutomated-decision-making id="tilt-dutomated-decision-making" />
                  </div>
                  <div class="col-12 col-md-6 col-lg-4" v-if="forms.showPurpose">
                       <tilt-changes-of-purpose id="tilt-changes-of-purpose" />
                  </div>
              </div>    
          </div>    
</template>

<script>
import MetaForm from './tiltforms/MetaForm.vue'
import ChangesOfPurposeForm from './tiltforms/ChangesOfPurposeForm.vue'
import ControllerForm from './tiltforms/ControllerForm.vue'
import ProtectionOfficerForm from './tiltforms/ProtectionOfficerForm'
import AutomatedDecisionMakingForm from './tiltforms/AutomatedDecisionMakingForm'



export default {
  name: 'TiltFormular',
  components: {
    'tilt-meta':MetaForm,
    'tilt-changes-of-purpose':ChangesOfPurposeForm,
    'tilt-controller': ControllerForm,
    'tilt-protection-officer':ProtectionOfficerForm,
    'tilt-dutomated-decision-making': AutomatedDecisionMakingForm
  },
  props: {
    msg: String,
    forms : {
        showMeta:Boolean,
        showController:Boolean,
        showDataManager: Boolean,
        showAutomatedDecision: Boolean,
        showPurpose: Boolean
    }
  },
  data() {
    return {
    meta: {
        _id: "f1424f86-ca0f-4f0c-9438-43cc00509931",
        name: "Green Company",
        created: "2020-04-03T15:53:05.929588",
        modified: "2020-04-03T15:53:05.929588",
        version: 2,
        language: "de",
        status: "active",
        url: "https://green-bikes.de/privacy",
        _hash: "be81d309088dde861ab5fc4d62d4bbfe0aeef3e3baf2f5362c1086f451f0a1e7"
    },
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
    dataProtectionOfficer: {
        name: "Jane Super",
        address: "Wolfsburger Ring 2, 38440 Berlin",
        country: "DE",
        email: "contact@greencompany.de",
        phone: "0049 151 1234 5678"
    },
    dataDisclosed: [
        {
        _id: "f1424f86-ca0f-4f0c-9438-43cc00509931",
        category: "E-mail address",
        purposes: [
            {
            purpose: "Marketing",
            description: "Newsletter will be sent out once a month."
            }
        ],
        legalBases: [
            {
            reference: "GDPR-99-1-a",
            description: "The data are processed on the basis of Art. 99 GDPR which states..."
            },
            {
            reference: "BDSG-42-5",
            description: "BDSG-42-5 refers to the processing of personal data within..."
            }
        ],
        legitimateInterests: [
            {
            exists: false,
            reasoning: "There is an legitimate interest based on ... and is not overwritten because ..."
            }
        ],
        recipients: [
            {
            name: "Yellow Company AG",
            division: "Product line e-mobility",
            address: "Triana 123, 9999 Seville",
            country: "ES",
            representative: {
                name: "Jane Super",
                email: "contact@yellowcompany.de",
                phone: "0049 151 1234 9876"
            },
            category: "Marketing content provider"
            },
            {
            category: "Responsible Statistical Institutes"
            }
        ],
        storage: [
            {
            temporal: [
                {
                description: "Creating backups.",
                ttl: "P3Y6M4DT12H30M5S"
                },
                {
                description: "Finishing ordering process.",
                ttl: "2020-08-09T18:31:42P3Y6M4DT12H30M17S"
                }
            ],
            purposeConditional: [
                "Data is stored until the end of the ordering process."
            ],
            legalBasisConditional: [
                "SGB-100-42"
            ],
            aggregationFunction: "max"
            }
        ],
        nonDisclosure: {
            legalRequirement: false,
            contractualRegulation: false,
            obligationToProvide: false,
            consequences: "If the data is not disclosed, the shipment cannot be delivered."
        }
        }
    ],
    thirdCountryTransfers: [
        {
        country: "ES",
        adequacyDecision: {
            available: true,
            description: "An adequacy decision was made on the 23rd April 2020 by..."
        },
        appropriateGuarantees: {
            available: true,
            description: "Here the appropriate guarantee was formulated by..."
        },
        presenceOfEnforceableRightsAndEffectiveRemedies: {
            available: true,
            description: "These rights are given because of..."
        },
        standardDataProtectionClause: {
            available: true,
            description: "The standard data protection clause which applies here can be found here: ..."
        }
        }
    ],
    accessAndDataPortability: {
        available: true,
        description: "Data access is possible through...",
        url: "https://green-bikes.de/access",
        email: "access@greencompany.de",
        identificationEvidences: [
        "ID card copy",
        "Email verification"
        ],
        administrativeFee: {
        amount: 0,
        currency: "EUR"
        },
        dataFormats: [
        "json",
        "xml"
        ]
    },
    sources: [
        {
        _id: "f1423cc00509931",
        dataCategory: "Creditworthiness",
        sources: [
            {
            description: "This information could be retrieved from...",
            url: "https://blueCompany.org",
            publiclyAvailable: false
            }
        ]
        }
    ],
    rightToInformation: {
        available: true,
        description: "For the right to information please use this contact form and...",
        url: "https://greencompany.org/rightToInformation",
        email: "contact@greencompany.de",
        identificationEvidences: [
        "ID card copy",
        "Email verification"
        ]
    },
    rightToRectificationOrDeletion: {
        available: true,
        description: "For the right to rectification please use this contact form and...",
        url: "https://greencompany.org/rights",
        email: "contact@greencompany.de",
        identificationEvidences: [
        "ID card copy",
        "Email verification"
        ]
    },
    rightToDataPortability: {
        available: false,
        description: "Data portability is only possible when...",
        url: "https://greencompany.org/rights",
        email: "contact@greencompany.de",
        identificationEvidences: [
        "ID card copy"
        ]
    },
    rightToWithdrawConsent: {
        available: true,
        description: "For the right to withdraw consent please use this contact form and...",
        url: "https://greencompany.org/rights",
        email: "contact@greencompany.de",
        identificationEvidences: [
        "Email verification"
        ]
    },
    rightToComplain: {
        available: true,
        description: "For the right to complain please use this contact form and...",
        url: "https://greencompany.org/rights",
        email: "contact@greencompany.de",
        identificationEvidences: [
        "ID card copy",
        "Email verification"
        ],
        supervisoryAuthority: {
        name: "Commissioner for Data Protection",
        address: "Friedrichstrasse 219, 10969 Berlin",
        country: "DE",
        email: "mailbox@privacy-berlin.de",
        phone: "0049 444 222 111"
        }
    },
    automatedDecisionMaking: {
        inUse: true,
        logicInvolved: "The personal data are processed as follows...",
        scopeAndIntendedEffects: "From processing follows..."
    },
    changesOfPurpose: [
        {
        description: "Due to technical requirements...",
        affectedDataCategories: [
            "Email adress",
            "Credit score"
        ],
        plannedDateOfChange: "2020-08-20",
        urlOfNewVersion: "https://greencomp.de/privacypolicy/2"
        }
    ]
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<!--<style scoped> -->
<style>
    #tildForm{
        background-color: white !important;
    }
   
    .tilt-block {
      background-color: #FF876C !important;
      border-style: solid;
      border-color: #D9304F;
      padding:5px 5px 5px 5px;
      margin: 5px 5px 5px 5px;
    }
    
    h3{
        letter-spacing: 1px;
        padding: 15px;
        color: #D9304F;
    }

    .form-group{
        text-align: left;
        color: #D9304F;
    }
    .form-control{
        border-color:#D9304F;
        border-radius: 5px 5px 5px 5px;
        border-width: 100%;
        color: #D9304F;
        padding:5px 5px 5px 5px;
        margin: 5px 5px 5px 0px;
    }
</style>
