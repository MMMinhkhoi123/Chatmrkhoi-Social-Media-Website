<script setup>
import genderCPN from "../../components/NewCPN/step1.vue";
import dateCPN from "../../components/NewCPN/step2.vue";
import avateCPN from "../../components/NewCPN/step3.vue";
</script>
<template>
    <div class="New">
        <genderCPN v-if="$route.query.step == 1"></genderCPN>
        <dateCPN v-if="$route.query.step == 2"></dateCPN>
        <avateCPN v-if="$route.query.step == 3"></avateCPN>
    </div>
</template>
<style scoped>
.New {
    position: fixed;
    inset: 0;
    background: white;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>