<template>
    <div class="tilt-block">
      <tr>
        <td>
            <h3>Automatisierte Entscheidungsfindung:</h3>
        </td>
      </tr>  
      <b-form id="modal-controller" title="AutomatedDecisionMaking" >
        <b-form-group id="ctrlinUse" label="In Benutzung">
            <b-form-checkbox :placeholder="[[automatedDecisionMaking.inUse]]" aria-required=""></b-form-checkbox>
        </b-form-group>
        <b-form-group id="ctrllogicInvolved" label="Eingesetzte Logik">
            <b-form-input :placeholder="[[automatedDecisionMaking.logicInvolved]]" aria-required=""></b-form-input>
        </b-form-group>
        <b-form-group id="ctrlscopeAndIntendedEffects" label="Umfang und beabsichtigte Wirkung">
            <b-form-input :placeholder="[[automatedDecisionMaking.scopeAndIntendedEffects]]" aria-required=""></b-form-input>
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
            logicInvolved: "Die personenbezogenen Daten werden wie folgt verarbeitet...",
            scopeAndIntendedEffects: "Aus der Verarbeitung folgt..."
        }
    }
  }
}
</script>

<style scoped>

</style>