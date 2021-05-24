<template>
    <div class="tilt-block">
      <tr>
        <td>
            <h3>Meta:</h3>
        </td>
      </tr>  
      <b-form id="modal-meta" title="Meta" >
        <b-form-group id="ctrt_id" label-align='right' label-for="input-_id" label="Id" >
            <b-form-input id="input-_id" v-model="meta._id"></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlname" label="Name">
            <b-form-input v-model="meta.name" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlcreated" label="Created">
            <b-form-input v-model="meta.created" type= datetime aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlmodified" label="Modified">
            <b-form-input v-model="meta.modified" type= datetime aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlversion" label="Version">
            <b-form-input v-model="meta.version" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrllanguage" label="Language">
            <b-form-input v-model="meta.language" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlstatus" label="Status">
            <b-form-input v-model="meta.status" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlurl" label="Url">
            <b-form-input v-model="meta.url"  aria-required=""></b-form-input>
        </b-form-group>
         <b-form-group id="ctrl_hash" label="Hash">
            <b-form-input v-model="meta._hash"  aria-required=""></b-form-input>
        </b-form-group>
    </b-form>
    </div>
</template>

<script>
export default {
  name: "tilt_meta",
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
        }
    };
  },
};
</script>

<style scoped>
</style>