<template>
  <form method="GET">
          <div class="container-fluid">
              <div class="row">
                  <div class="col-12 col-md-6 col-lg-4">
                       <tilt-meta id="tildMetaForm" />
                  </div>
                  <div class="col-12 col-md-6 col-lg-4">
                       <tilt-controller id="tildMetaController" />
                  </div>
              </div>    
          <!-- <input type="submit" value="Speichern"> -->
          </div>    
  </form>    
</template>

<script>
import TiltMetaFormular from './TiltFormMeta.vue'
import TiltControllerFormular from './TiltFormController.vue'

export default {
  name: 'TiltFormular',
  components: {
    'tilt-meta': TiltMetaFormular,
    'tilt-controller': TiltControllerFormular
  },
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<!--<style scoped> -->
<style>
.tilt-tr{
    border-style: none;
}
.tilt-h1,.tilt-h2{
    color: #2775aa;
    background-color: whitesmoke;
    padding: 15px 15px 15px 15px;
    margin:0px;
}
.tilt-h2{
    margin-bottom: 20px;
    border-bottom-width: thin;
    border-bottom: #2775aa;
    border-bottom-style: solid;
}

.tilt-h3{
    letter-spacing: 1px;
}
.tilt-h3::first-letter{
    color: #2775aa;
}


h1,h2{
    color: #2775aa;
    background-color: whitesmoke;
    padding: 15px 15px 15px 15px;
    margin:0px;
}

h2{
    margin-bottom: 20px;
}

h3{
    letter-spacing: 1px;
    padding: 15px;
}

td{
    padding: 5px;  
}

input{
    border-color:#D9304F;
    border-radius: 5px 5px 5px 5px;
    border-width: 100%;
    color: #D9304F;
}
#invite:hover, input[type="submit"]:hover{   
    background-color: whitesmoke;
}
h2{
    border-bottom-width: thin;
    border-bottom: #D9304F;
    border-bottom-style: solid;
}

input[type="submit"]{
    margin: 0px 30px 0px 30px;
}
table{
    background-color: whitesmoke;
    color: #D9304F;
    border-color: #D9304F; 
    border-style: solid;
    border-width: 3px;
    margin: 3px 3px 3px 3px;
}
.btn{
    background-color:#D9304F; 
    border-color:#D9304F;
    color: whitesmoke;
}
.btn:active {
    background-color:#D9304F; 
    border-color:#D9304F;
    color: whitesmoke;
}
.btn:hover {
    background-color:#D9304F; 
    border-color:#D9304F;
    color: whitesmoke;
}

.btn-primary:hover {
    background-color:#D9304F; 
    border-color:#D9304F;
    color: whitesmoke;
}
.btn-primary:active {
    background-color:#D9304F; 
    border-color:#D9304F;
    color: whitesmoke;
    box-shadow: 0 0 0 .2rem rgba(255, 38, 74, 0.5);
}

.btn-primary:focus {
    background-color:#D9304F; 
    border-color:#D9304F;
    color: whitesmoke;
    box-shadow: 0 0 0 .2rem rgba(255, 38, 74, 0.5);
}

.dropdown-item {
    color: #649A9C
}
.dropdown-item:hover {
    background-color: #649A9C;
    color: whitesmoke;
}
</style>
