<template>
<div class="tilt-block">
      <tr>
        <td>
            <h3>Changes Of Purpose</h3>
        </td>
      </tr>  
      <b-form id="modal-meta" title="Meta" >
        <b-form-group id="ctrtdescription" label="Description">
            <b-form-input v-model="changesOfPurpose.description" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlaffectedDataCategories" label="AffectedDataCategories">
            <b-form-tags input-id="tags-basic" v-model="changesOfPurpose.affectedDataCategories"></b-form-tags>
            <p class="mt-2">Data Categories: {{ changesOfPurpose.affectedDataCategories }}</p>
        </b-form-group>
        <b-form-group id="ctrlplannedDateOfChange" label="Planned Date Of Change">
            <b-form-input v-model="changesOfPurpose.plannedDateOfChange" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlurlOfNewVersion" label="Url of New Version">
            <b-form-input v-model="changesOfPurpose.urlOfNewVersion" aria-required=""></b-form-input>
        </b-form-group>
    </b-form>
  </div>
</template>

<script>

export default {
  name: "changesOfPurpose",
  data() {
    return {
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
    };
  },
};
</script>

<style scoped>
</style>