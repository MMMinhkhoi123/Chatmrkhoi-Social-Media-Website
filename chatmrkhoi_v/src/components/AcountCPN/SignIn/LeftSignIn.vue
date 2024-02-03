<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

function GoForget() {
    localStorage.setItem("action", 1)
    router.push({ query: { action: 'Forget', step : 1 } })
}

function GoSignUp() {
    router.push({  query: { action: 'SignUp' } })
}

</script>
<template>
    <div class="content">  
        <div class="content__avata">
            <img class="content__avata--img" src="../../../assets/images/logo.PNG">
            <strong class="content__avata--text">Chatmrkhoi</strong>
        </div>
        <h1 class="content__title">{{ $t('TextAcount.Login.TitleLeft') }}</h1>
        <button class="content__button"  @click="GoSignUp()">
            {{ $t('TextAcount.Login.ButtonMove') }}
        </button> 
        <span class="content__button default" @click="GoForget()">
            {{ $t('TextAcount.Login.LinkMove') }} ?
        </span>
    </div>
</template>
<style scoped>
.content__avata {
    display: flex;
    align-items: center;
    gap: 10px;
}
.content__avata--img {
    width: 70px;
    height: 70px;
    object-fit: cover;
    border-radius: 50%;
}
.content__avata--text {
    font-size: 50px;
    color: white;
}
.content {
    position: relative;
    z-index: 9;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 25px;
}
.content__title {
    color: #ffffff;
}
.content__button {
    border: none; 
    border-radius: 30px;
    background: var(--color4);
    color: #FFF;
    padding: 10px 30px;
    cursor: pointer;
}
.content__button:hover:not(.default) {
    background: orange;
}
.default {
    color: #ffffff;
    cursor: pointer;
}
.default {
    padding: 0;
    background: transparent;
    outline: none;
}
</style>