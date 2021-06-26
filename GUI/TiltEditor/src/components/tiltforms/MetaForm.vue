<template>
    <div class="tilt-block">
      <tr>
        <td>
            <h3>Meta:</h3>
        </td>
      </tr>  
      <b-form id="modal-meta" title="Meta" >
        <b-form-group id="ctrl_id" label-align='right' label-for="input-_id" label="Id" >
            <b-form-input v-b-tooltip.hover :title="metaInf._id" id="input-_id" v-model="meta._id"></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlname" label="Firmenname">
            <b-form-input v-b-tooltip.hover :title="metaInf.name" :placeholder="[[meta.name]]" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlcreated" label="Erstellungsdatum">
            <b-form-input v-b-tooltip.hover :title="metaInf.created" v-model="meta.created" type= datetime aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlmodified" label="Letzte Bearbeitung">
            <b-form-input v-b-tooltip.hover :title="metaInf.modified" v-model="meta.modified" type= datetime aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlversion" label="Version">
            <b-form-input v-b-tooltip.hover :title="metaInf.version" :placeholder="[[meta.version]]" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrllanguage" label="Sprache">
            <b-form-input v-b-tooltip.hover :title="metaInf.language" :placeholder="[[meta.language]]" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlstatus" label="Status">
          <b-form-select  v-model="meta.status" :options="options"
           class="mb-3" v-b-tooltip.hover :title="metaInf.status" size="lg">
            <template #first>
              <b-form-select-option value="" disabled>-- Please select an option --</b-form-select-option>
            </template>
          </b-form-select>
        </b-form-group>
        <b-form-group id="ctrlurl" label="Url">
            <b-form-input v-b-tooltip.hover :title="metaInf.url" v-model="meta.url"  aria-required=""></b-form-input>
        </b-form-group>
         <b-form-group id="ctrl_hash" label="Hash">
            <b-form-input v-b-tooltip.hover :title="metaInf._hash" v-model="meta._hash"  aria-required=""></b-form-input>
        </b-form-group>
    </b-form>
    </div>
</template>

<script>
export default {
  name: "tilt_meta",
  data() {
    return {
      options: [
        { value: 'active',text: 'active',},
        {value: 'inactive',text: 'inactive' },
      ],
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
            },
             metaInf: {
                _id: "Hier finden Sie die ID ihres TiltDokuments. Dieses müssen Sie nicht bearbeiten.",
                name: "Der Name ihres Unternehmens tragen Sie bitte in dieses Feld ein. Die Green Company ist lediglich ein Beispiel.",
                created: "Dieses Feld beinhaltet das Datum der Erstellung dieses Templates und muss nicht von Ihnen bearbeitet werden.",
                modified: "Dieses Feld beinhaltet das Datum der letzten Bearbeitung dieses Templates und muss ebenfalls nicht von Ihnen bearbeitet werden.",
                version:"Die aktuelle Versionsnummer finden Sie in diesem Feld.",
                language: "Bestimmen Sie hier die Sprache ihres TiltDokuments.",
                status: "Der aktuelle Bearbeitungsstatus wie In Bearbeitung finden Sie in diesem Feld.",
                url: "Tragen Sie hier die Webseite ihres Unternehmens, falls vorhanden, ein. Das hilft ihren Kunden Sie über ihre Webseite zu kontaktieren.",
                _hash:"Hier befindet sich der Hashwert ihres TiltDokuments. Das benötigt PeachPrivacy für die Verarbeitung."
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