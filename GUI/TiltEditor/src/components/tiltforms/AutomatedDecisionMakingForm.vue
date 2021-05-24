<template>
    <div class="tilt-block">
      <tr>
        <td>
            <h3>Automated Decision Making:</h3>
        </td>
      </tr>  
      <b-form id="modal-controller" title="AutomatedDecisionMaking" >
        <b-form-group id="ctrlinUse" label="In Use">
            <b-form-checkbox v-model="automatedDecisionMaking.inUse" aria-required=""></b-form-checkbox>
        </b-form-group>
        <b-form-group id="ctrllogicInvolved" label="Logic Involved">
            <b-form-input v-model="automatedDecisionMaking.logicInvolved" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlscopeAndIntendedEffects" label="Scope and Intended Effects">
            <b-form-input v-model="automatedDecisionMaking.scopeAndIntendedEffects" aria-required=""></b-form-input>
        </b-form-group>
    </b-form>
    </div>
</template>

<script>
export default {
  name: "automatedDecisionMaking",
  data() {
    return {
        automatedDecisionMaking: {
            inUse: true,
            logicInvolved: "The personal data are processed as follows...",
            scopeAndIntendedEffects: "From processing follows..."
        }
    }
  }
}
</script>

<style scoped>

</style>