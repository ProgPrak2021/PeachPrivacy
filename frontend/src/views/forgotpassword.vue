 <template>
    <div class="wrapper">
      <div class="section page-header header-filter" :style="headerStyle">
        <div class="container">
          <div class="md-layout">
            <div
                class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
            >
              <login-card header-color="red">
                <h4 slot="title" class="card-title">Passwort vergessen</h4>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>email</md-icon>
                  <label>Email...</label>
                  <md-input v-model="email" type="email"></md-input>
                </md-field>
                <md-button v-on:click="HandleOk" slot="footer" class="md-danger"  >
                 Passwort zurücksetzten
                </md-button>
              </login-card>

            </div>
          </div>
        </div>
      </div>
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
    HandleOk() {
      if(!(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.email))){
        this.$alert(' Geben Sie eine valide Email an','Email existiert nicht',"error");
        return;
      }
      this.forgot_password();
      this.$alert('Sie erhalten eine Email um ihr Passwort zu verändern ',"Email existiert ",'success');
      this.$router.push("landing");
    },
  },
};
</script>

<style scoped>

</style>