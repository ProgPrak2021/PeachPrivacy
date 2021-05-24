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
                _id: this.pmeta._id,
                name: this.pmeta.name,
                created: this.pmeta.created,
                modified: this.pmeta.modified,
                version:this.pmeta.version,
                language: this.pmeta.language,
                status: this.pmeta.status,
                url: this.pmeta.url,
                _hash:this.pmeta._hash
            }
       }
    },
    props: {
        pmeta: {
                _id: String,
                name: String,
                created: Date,
                modified: Date,
                version: Number,
                language: String,
                status: String,
                url: String,
                _hash: String
            }
    },
 };
</script>

<style scoped>
</style>