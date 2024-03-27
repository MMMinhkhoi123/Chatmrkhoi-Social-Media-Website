<script setup>
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import store from '../../../store/index'
    const route = useRouter();
    const out = () => {
        store.state.avaible_chat.Notification.warning.changepass  = false;    
            route.push({ query: { action: 'SignIn' } })
    }
    const data_content = computed(() => {
        return store.state.avaible_chat.Notification.data;
    })
    const data = computed(() => {
        return store.state.avaible_chat.Notification.warning.changepass ;
    })
</script>
<template>
    <div  class="background" @click="store.state.avaible_chat.Notification.warning.changepass = false" :class="data == true ? ' active' : ''"></div>
    <div class="noification" :class="data == true ? ' active2' : ''">
        <img class="noification__img" :src="'/src/assets/images/'+ data_content.img">
        <h2>{{  $t(data_content.title) }}</h2>
        <h4>{{  $t(data_content.content) }}</h4>
        <div class="noification__option">
            <button class="noification__option--button" @click="out">{{ $t('Notification.ChangePassword.button') }}</button>
        </div>
    </div>
</template>
<style scoped>
.active {
    opacity: 1 !important;
    pointer-events: visible !important;
}
.active2 {
    opacity: 1 !important;
    pointer-events: visible !important;
    transform: translate(-50%, -50%) scale(1) !important;
}
.background {
    transition: 0.5s;
    position: fixed;
    inset: 0;
    opacity: 0;
    pointer-events: none;
    z-index: 119;
    background: var(--colorHideFrom);
}
.noification {
    transition: 0.2s;
    opacity: 0;
    pointer-events: none;
    text-align: center;
    border-radius: 10px;
    width: 450px;
    z-index: 120;
    background: var(--coloRegular);
    width: 350px;
    position: fixed;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%) scale(0);
    padding: 10px;
}

.noification__img {
    width: 100%;
    height: 150px;
    object-fit: contain;
    padding-top: 30px;
}

.noification__option {
    justify-content: center;
    display: flex;
    gap: 10px;
    padding-bottom: 10px;
}
.noification__option--button:nth-child(1):hover {
    background: orange;
}
.noification__option--button:nth-child(2):hover {
    background: var(--color4) !important;
}
.noification__option--button {
    padding: 10px 30px;
    border: none;
    background: var(--color4);
    color: white;
    border-radius: 20px;
}
</style>