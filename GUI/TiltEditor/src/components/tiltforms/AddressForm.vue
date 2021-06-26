<template>
  <div>
    <b-form >
      <b-form-group label="Name">
        <b-form-input v-model="address.name" aria-required=""></b-form-input>
      </b-form-group>
      <b-form-group label="Address">
        <b-form-input v-model="address.address" aria-required=""></b-form-input>
      </b-form-group>
      <b-form-group label="Country">
        <b-form-input v-model="address.country" aria-required=""></b-form-input>
      </b-form-group>
      <b-form-group label="E-Mail">
        <b-form-input v-model="address.email" aria-required=""></b-form-input>
      </b-form-group>
      <b-form-group label="Phone">
        <b-form-input v-model="address.phone" aria-required=""></b-form-input>
      </b-form-group>
    </b-form>
  </div>
</template>

<script>
export default {
  name: "Address",
  data() {
    return {
      address: {
        name: this.paddress.name,
        address: this.paddress.address,
        country: this.paddress.country,
        email: this.paddress.email,
        phone: this.paddress.phone,
      }
    }
  },
  props: {
    paddress: {
      name: String,
      address: String,
      country:String,
      email:String,
      phone: String,
    }
  },
}
</script>

<style scoped>
</style>