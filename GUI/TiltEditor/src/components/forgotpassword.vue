<template>
  <div>
  <b-modal id="modal-forgot-pw" title="Passwort vergessem " hide-header-close @ok="HandleOk">
      <b-form-group id="ctrtEmail" label="E-Mail *">
        <b-form-input type="email" v-model="email" placeholder="Bitte Email eingeben." trim
                      aria-required=""></b-form-input>
      </b-form-group>
  </b-modal>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: "forgotpassword",
  data() {
    return {
      email: ""
    };
  },
  methods: {
    forgot_password(){
      axios.post('/api/auth/reset/request', null, {
        params: {
          email: this.email
        }
      }).then(response => {
        this.register_response = response.data
        this.$alert("Sie erhalten eine Email, wenn die Email im System existier","Email","success")
      }).catch(function (error) {
        console.log(error)

      })

    },
    HandleOk(bvModalEvt) {
      if(!(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.email))){
        bvModalEvt.preventDefault();
        this.$alert(' Geben Sie eine valide Email an','Email existiert nicht',"error");
        return;
      }
      this.forgot_password();
      this.$alert('Sie erhalten eine Email um ihr Passwort zu verändern ',"Email existiert ",'success');


    },


  },
};

</script>

<style scoped>

</style>