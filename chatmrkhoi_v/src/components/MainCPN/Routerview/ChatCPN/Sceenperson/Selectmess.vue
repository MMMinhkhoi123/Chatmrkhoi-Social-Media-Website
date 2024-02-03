<script setup>
import MessActionGroupCPN from "../Sceenperson/Selectmess/MessActionGroup.vue";
import MessnewdateCPn from "../Sceenperson/Selectmess/Messnewdate.vue";
import MesspinCPN from "../Sceenperson/Selectmess/Messpin.vue";
import MessrevokeCPN from "../Sceenperson/Selectmess/messrevoke.vue";
import MessmainCPN from "../sceenperson/Selectmess/MessMain.vue";
</script>
<template>
        <MessActionGroupCPN v-if="item" :item="item"></MessActionGroupCPN>
        <MessnewdateCPn v-if="item" :item="item"></MessnewdateCPn>
        <MesspinCPN v-if="item" :item="item"></MesspinCPN>
        <MessrevokeCPN v-if="item" :item="item"></MessrevokeCPN>
        <MessmainCPN v-if="item" :item="item"></MessmainCPN>
</template>
<script>
export default {
    props: {
        item: {
            typeof: Object,
            default: null,
        }
    },
    setup() {
    },
}
</script>
<style scoped>
</style>