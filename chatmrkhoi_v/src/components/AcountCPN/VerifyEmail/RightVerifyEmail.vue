<script setup>
import FrameCPN from "./RightVerifyEmail/Step1.vue"
import NotifiCPN from "../../ComomCPN/Notification/verifysuccess_warning.vue";
</script>
<template>
    
    <div class="content">

       <!-- MAIN -->
       <FrameCPN v-if="$store.state.auth.verify != true" />

       <!-- PINNER -->
       <div class="piner" v-if="$store.state.auth.verify"></div>

    </div>

    <!-- NOTIFICATION -->
    <NotifiCPN v-if="$store.state.auth.verify"></notifiCPN>

</template>
<style scoped>
.content {
    padding: 40px;
    width: 100%;
    height: 100%;
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
}
.piner {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    border: 4px solid rgb(225, 218, 218);
    border-left-color: transparent !important;
    animation: spin89345 1s linear infinite;
}
@keyframes spin89345 {
    0% {
        transform: rotate(0deg);
    }

    100% {
        transform: rotate(360deg);
    }
    }
</style>