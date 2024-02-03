<script setup>
 import Step1 from "./AddGroup/Step1.vue"
 import Step2 from "./AddGroup/Step2.vue"
 import NotifiCPN from "../../../ComomCPN/Notification/Addgroup_warning.vue";
 import { useRoute, useRouter } from 'vue-router'
        const route = useRoute();
        const router = useRouter();
        const out = () => {
            if (route.query.step == '1') {
                router.go(-1);
            } else {
                router.go(-2);
            }
        }
</script>
<template>
    <div @click="out()" class="background"></div>
    <Step1 v-if="$route.query.step == 1" />
    <Step2 v-if="$route.query.step == 2" />
    <NotifiCPN></NotifiCPN>
</template>
<style>
.background {
    position: fixed;
    z-index: 11;
    inset: 0;
    background: rgba(250, 250, 250, 0.345);
}
</style>