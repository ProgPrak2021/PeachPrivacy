<template>
<div class="tilt-block">
      <tr>
        <td>
            <h3>Zwecksänderungen</h3>
        </td>
      </tr>  
      <b-form id="modal-meta" title="Meta" >
        <b-form-group id="ctrtdescription" label="Beschreibung">
            <b-form-input :placeholder="[[changesOfPurpose.description]]" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlaffectedDataCategories" label="Betroffene Datenkategorien">
            <b-form-tags input-id="tags-basic" :placeholder="[[changesOfPurpose.affectedDataCategories]]"></b-form-tags>
            <p class="mt-2">Data Categories: {{ changesOfPurpose.affectedDataCategories }}</p>
        </b-form-group>
        <b-form-group id="ctrlplannedDateOfChange" label="Geplantes Datum der Veränderung">
            <b-form-input :placeholder="[[changesOfPurpose.plannedDateOfChange]]" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlurlOfNewVersion" label="Url der neuen Version">
            <b-form-input :placeholder="[[changesOfPurpose.urlOfNewVersion]]" aria-required=""></b-form-input>
        </b-form-group>
    </b-form>
  </div>
</template>

<script>

export default {
  name: "Zwecksänderungen",
  data() {
    return {
        changesOfPurpose: [
            {
                description: "Aufgrund der technischen Anforderungen...",
                affectedDataCategories: [
                    "Emailadresse",
                    "Kreditwürdigkeit"
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