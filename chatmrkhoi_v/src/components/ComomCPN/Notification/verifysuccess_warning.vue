<script setup>
import { useRouter } from "vue-router"
    const router = useRouter();
    function verifysuccess() {
        localStorage.clear();
        router.push({query:{ action: 'SignIn'} })
    }
</script>
<template>
    <div  class="background active"></div>
    <div class="noification active2">
        <img class="noification__img" :src="'/src/assets/images/verify.png'">
        <h2>
            {{ $t('Notification.verifyEmailsucssec.Title') }}
        </h2>
        <h4>
            {{ $t('Notification.verifyEmailsucssec.Content') }}
        </h4>
        <div class="noification__option">
            <button class="noification__option--button" @click="verifysuccess()">
            {{ $t('Notification.verifyEmailsucssec.button') }}
            </button>
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
    background: rgba(10, 10, 10, 0.568);
}
.noification {
    transition: 0.2s;
    opacity: 0;
    pointer-events: none;
    text-align: center;
    border-radius: 10px;
    width: 450px;
    z-index: 120;
    background: white;
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