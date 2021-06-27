<template>
  <div>
    <b-modal id="modal-open" title="Template öffnen" hide-header-clos>
      <b-row id="open_actions">
        <div class="col-4">
          <b-btn class="open_actions_btn" @click="openTemplate_1" href="#" v-b-modal.modal-template1 >Template 1</b-btn>
        </div>
        <div class="col-4">
          <b-btn class="open_actions_btn" href="#" v-b-modal.modal-change-template2 >Template 2</b-btn>
        </div>
        <div class="col-4">
          <b-btn class="open_actions_btn" href="#" v-b-modal.modal-template3> Template 3</b-btn>
        </div>
      </b-row>
    </b-modal>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  name: "OpenTemplateModal",
  data() {
    return {
       data: Array 
    };
  },
  methods: {
    openTemplate_1() {
      console.log("Open Template 1");
      axios({ method: "GET", "url": "http://35.246.192.209:30001/api/tilt/projects" }).then(result => {
                 console.log(result.data)
                 this.data = result.data
            }, error => {
                console.error(error);
            });
      console.log("Data = " + this.data);
      return;
    },
  }
}
</script>

<style scoped>
 #modal-open{
    background-color: whitesmoke;
  }
  .open_actions_btn{
    margin: 20px 10px 10px 10px;
    background-color: #FF876C;
    border-color:#D9304F;
    border-style: solid;
    border-radius: 5px 5px 5px 5px;
    border-width: 100%;
    /*color: #D9304F; */
  }
</style>