<template>
    <div class="tilt-block">
        <div>
            <h3>{{help.title}}</h3>
        </div>
        <div>
            {{help.data_1}}
        </div>
        <div>
            {{help.data_2}}
        </div>
        <div>
            {{help.data_3}}
        </div>
    </div>
</template>

<script>
export default {
  name: "help",
  props: {
        help: {
                title : String,
                data_1 : String,
                data_2 : String,
                data_3 : String,
                countLines : Number,
            }
    },
 };
</script>
<style scoped>
#userStory{
    padding: 20px 20px 20px 20px;
    margin: 10px 10px 10px 10px;
}
</style>