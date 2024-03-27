<script setup>
 import profileCPN from "./ScreenCPN/ProfileScreen.vue";
 import securityCPN from "./ScreenCPN/SecurityScreen.vue"
 import advantedCPN from "./ScreenCPN/Advanted.vue";
</script>
<template>
   <div class="screen">
     <profileCPN v-if="$route.query.action == 'profile'"></profileCPN>
     <securityCPN v-if="$route.query.action == 'security'"></securityCPN>
     <advantedCPN v-if="$route.query.action == 'advanted'"></advantedCPN>
   </div>
</template>
<style scoped>
* {
    color: var(--colorText);
}
.screen {
    height: 100%;
    border-radius: 10px;
}
</style>