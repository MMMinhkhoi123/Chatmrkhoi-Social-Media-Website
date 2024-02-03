<script setup>
import Step1CPN from "./RightForGet/Step1.vue"
import Step2CPN from "./RightForGet/Step2.vue"
import Step3CPN from "./RightForGet/Step3.vue"
</script>
<template>
    <div class="content">
        <Step1CPN v-if="$route.query.step == 1" /> 
        <Step2CPN v-if="$route.query.step == 2" />  
        <Step3CPN v-if="$route.query.step == 3" />     
    </div>
</template>
<style scoped>
.content {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>