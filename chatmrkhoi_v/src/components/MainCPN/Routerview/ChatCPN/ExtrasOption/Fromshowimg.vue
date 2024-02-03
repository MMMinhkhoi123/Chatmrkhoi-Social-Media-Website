<template>
    <div class="imgzoom">
        <img v-if="$route.query.type == 'cpt'" :src="url + '/file/get-png/' + $route.query.codeimg" alt="">
        <img v-if="$route.query.type == 'rs'" :src="'/src/assets/images/avata_org/'+ $route.query.codeimg " alt="">
    </div>
</template>
<script>
import data from "../../../../../handle/Screen_chat/index";
export default {
    setup() {
        const { url } = data();
        return { url }
    },
}
</script>
<style scoped>
.imgzoom {
    position: fixed;
    inset: 0;
    z-index: 11;
    background: black;
    display: flex;
    justify-content: center;
    align-items: center;
}
img {
    max-width: 1000px;
    max-height: 600px;
}
</style>
