<script setup>
import createAlerts from "../../../../handle/Notice/index";
const { run } = createAlerts();
</script>
<template>
    <div class="listAlert">
    </div>
</template>
<style scoped>
.listAlert {
    position: absolute;
    z-index: 11;
    bottom: 0;
    left: 0;
    display: flex;
    flex-direction: column;
    gap: 10px;
    margin: 20px;
    transition: 2s;
}
.Alert {
    min-width: 200px;
    background: white;
    padding: 10px 20px;
    border-radius: 10px;
    animation: hiden 2s ease forwards;
}

.ALert__title {
    margin: 0;
    padding: 0;
}
</style>