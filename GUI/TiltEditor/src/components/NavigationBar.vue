<template>
    <div id="menue" >
        <b-navbar class="navbar" id="nbar" toggleable="lg" type="dark" variant="info">
            <b-navbar-brand href="#">
               <img src="../assets/tiltLogo.jpg" alt="" height="50">
            </b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
                <b-nav-item href="#">Home</b-nav-item>
                <b-nav-item-dropdown text="Templates verwalten" right>
                  <b-dropdown-item href="#">Neues Template</b-dropdown-item>
                  <b-dropdown-item href="#">Template öffnen</b-dropdown-item>
                  <b-dropdown-item href="#">Template speichern</b-dropdown-item>
                  <b-dropdown-item href="#">Template schließen</b-dropdown-item>
                </b-nav-item-dropdown>
                <b-nav-item-dropdown text="Building blocks" right>
                  <b-dropdown-item href="#">Meta</b-dropdown-item>
                  <b-dropdown-item href="#">Controller</b-dropdown-item>
                  <b-dropdown-item href="#">Data Protection Officer</b-dropdown-item>
                  <b-dropdown-item href="#">Adequacy decisions</b-dropdown-item>
                  <b-dropdown-item href="#">Access/Data portability</b-dropdown-item>
                  <b-dropdown-item href="#">Right to complain</b-dropdown-item>
                  <b-dropdown-item href="#">Automated decision making</b-dropdown-item>
                  <b-dropdown-item href="#">Notification on change</b-dropdown-item>
                  <b-dropdown-item href="#">Adequacy decisions</b-dropdown-item>
                  <b-dropdown-item href="#">Access/Data portability</b-dropdown-item>
                </b-nav-item-dropdown>
            </b-navbar-nav>

            <!-- Right aligned nav items -->
            <b-navbar-nav class="ml-auto">
               <!-- <b-nav-form>
                <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
                </b-nav-form>
                -->
                <b-nav-item-dropdown text="Sprache wählen" right>
                <b-dropdown-item href="#">Englisch</b-dropdown-item>
                <b-dropdown-item href="#">Deutsch</b-dropdown-item>
                </b-nav-item-dropdown>

                <b-nav-item-dropdown right>
                    <!-- Using 'button-content' slot -->
                    <template #button-content>
                        <em>Benutzer</em>
                    </template>
                    <div>
                        <b-dropdown-item href="#" v-b-modal.modal-profile size="sm">Profile</b-dropdown-item>
                        <profile-modal v-bind:user="user"></profile-modal>
                    </div>
                    <div  v-if="!loginV">
                      <b-dropdown-item href="#" v-b-modal.modal-login size="sm" >Anmelden </b-dropdown-item>
                      <login-modal @login-success="login"></login-modal>
                    </div>
                    <div v-else>
                      <b-dropdown-item href="#" @click="logout"  >Abmelden</b-dropdown-item>
                    </div>
                </b-nav-item-dropdown>
            </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
    import LoginpModal from "./LoginForm.vue";
    import ProfileModal from "./ProfileForm.vue";

    export default {
        name: "NavigationBar",
        components: {
        'login-modal':LoginpModal,
        'profile-modal':ProfileModal
        },
         data() {
            return {
                user:{
                  email: "",
                  password: ""
                },
                loginV:   false
            };
        },

        methods: {
            login: function(usr){
                //ToDo
                this.user = usr;
                this.loginV=true;
                console.log("login called: Username = " + this.user.email+ " loginV = " + this.loginV)
            },
            logout: function(){
                //ToDo
                this.loginV=false;
                console.log("call Logout" + " loginV = " + this.loginV )
            }
        }
    }
</script>

<style >
#nbar{
    background-color: whitesmoke  !important;
}
/**Menübar */
.nav-link{
    color: #649A9C !important; 
    background-color: whitesmoke !important;
}
.ml-auto{
    color: black !important; 
    background-color: red !important;
}
</style>