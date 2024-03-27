<script setup>
import ListNotice from '../../components/MainCPN/Routerview/Notice/ListNotice.vue';
import ContentNotice from '../../components/MainCPN/Routerview/Notice/ContentNotice.vue';
import filters from "../../components/MainCPN/Routerview/Notice/FilterNotice.vue";
</script>

<template>
    <div class="notice">
        <div class="notice__list">
            <filters></filters>
            <ListNotice></ListNotice>
        </div>
        <div class="notice_screen">
            <ContentNotice></ContentNotice>
        </div>
    </div>
</template>
<style scoped>
* {
    color: var(--colorText);
}
.notice {
    display: grid;
    padding: 10px;
    grid-template-columns: 500px 1fr;
    gap: 10px;
    
}
.notice__list {
    border-radius: 10px;
    box-sizing: border-box;
    background: var( --coloRegular);
}
.notice_screen {
    border-radius: 10px;
    position: relative;
    background: var( --coloRegular);
}
</style>