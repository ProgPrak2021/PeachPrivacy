<template>
    <div class="tilt-block">
        <table>
            <div class="block">
                    <tr>
                        <td>
                            <h3>Controller:</h3>
                        </td>
                    </tr>
                    <tr>
                        <td>Firmenname:</td>
                        <td><input type="text" name="cpName" required></td>
                    </tr>
                    <tr>
                        <td>Bereich:</td>
                        <td><input type="text" name="division" ></td>
                    </tr>
                    <tr>
                        <td>Adresse:</td>
                        <td><p>
                            <!--CoolapseCode auf eigenes Bsp angepasst, Grundgerüst von https://getbootstrap.com/docs/4.1/components/collapse/---->
                            <a id="TableButton" class="btn btn-primary" data-toggle="collapse" href="#Adresse" role="button" aria-expanded="false"
                                aria-controls="Adresse">Adresse spezifizieren</a>
                        </p>
                        <div class="row">
                            <div class="col">
                                <div class="collapse multi-collapse" id="Adresse">
                                    <div class="card card-body">
                                        Name: <input type="text" name="adressName" required>
                                        Straße: <input type="text" name="straße" required>
                                        Hausnummer: <input type="text" name="hausnummer" required>
                                        Postleitzahl: <input type="number" name="postleitzahl" required>
                                    </div>
                                </div>
                            </div>
                        </div></td>
                    </tr>
                    <tr>
                        <td>Länderkennung:</td>
                        <td><input type="text" name="countryCode" required></td>
                    </tr>
                    <tr>
                        <td>Version:</td>
                        <td><input type="number" name="version"></td>
                    </tr>
                    <tr>
                        <td>Sprache:</td>
                        <td><input type="text" name="langCode"></td>
                    </tr>
                    <tr>
                        <td>Vertreter:</td>
                        <td>
                            <p>
                                <a id="TableButton" class="btn btn-primary" data-toggle="collapse" href="#Vertreter" role="button" aria-expanded="false"
                                    aria-controls="Vertreter">Vertreter spezifizieren</a>
                            </p>
                            <div class="row">
                                <div class="col">
                                    <div class="collapse multi-collapse" id="Vertreter">
                                        <div class="card card-body">
                                            Name: <input type="text" name="vertreterName" required>
                                            Email: <input type="email" name="vertreterEmail" required>
                                            Telefon: <input type="tel" name="vertreterTelefon" required>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </div>
        </table>
    </div>
</template>

<script>
export default {
  name: 'TiltFormController',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
