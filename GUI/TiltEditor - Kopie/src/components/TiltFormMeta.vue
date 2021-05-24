<template>
    <div class="tilt-block">
        <table>
                    <tr>
                        <td>
                            <h3>Meta:</h3>
                        </td>
                    </tr>
                    <tr>
                        <td>Identifikationsnummer:</td>
                        <td><input type="number" name="idNum" required autofocus></td> 
                    </tr>
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" name="name" required></td>
                    </tr>
                    <tr>
                        <td>Erstellungsdatum:</td>
                        <td><input type="date" name="crDate" required></td>
                    </tr>
                    <tr>
                        <td>Letzte Änderung:</td>
                        <td><input type="date" name="modDate"></td>
                    </tr>
                    <tr>
                        <td>Version:</td>
                        <td><input type="number" name="version"></td>
                    </tr>
                    <tr>
                        <td>Länderkennung:</td>
                        <td><input type="text" name="langCode" ></td>
                    </tr>
                    <tr>
                        <td>Status:</td>
                        <td><input type="text" name="status" list="stati"></td>
                        <datalist id="stati">
                            <option value="fertiggestellt"></option>
                            <option value="in Bearbeitung"></option>
                        </datalist>
                    </tr>
                    <tr>
                        <td>URL:</td>
                        <td><input type="url" name="url"></td>
                    </tr>
                    <tr>
                        <td>Hash:</td>
                        <td><input type="text" name="hash"></td>
                    </tr>
        </table>
    </div>
</template>

<script>
export default {
  name: 'TiltFormMeta',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
