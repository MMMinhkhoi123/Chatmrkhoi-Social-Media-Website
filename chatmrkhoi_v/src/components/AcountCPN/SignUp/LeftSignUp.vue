<script setup>
import { useRouter } from "vue-router"

const router = useRouter();

// REDIRECT INTO SIGNIN
function GoSignIn() {
    router.push({ query: { action: 'SignIn' } })
}

</script>
<template>


    <div class="content">  

        <!-- LOGO -->
        <div class="content__avata">
            <img class="content__avata--img" src="../../../assets/images/logo.PNG">
            <strong class="content__avata--text">
                Chatmrkhoi
            </strong>
        </div>

        <!-- TITLE -->
        <h1 class="content__title">
            {{ $t('TextAcount.SignUp.TitleLeft') }}
        </h1>

        <!-- BUTTON NEXT -->
        <button class="content__button" @click="GoSignIn()">
            {{ $t('TextAcount.SignUp.ButtonMove') }}
        </button> 

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
    z-index: 11;
    display: flex;
    padding: 30px;
    flex-direction: column;
    align-items: center;
    gap: 25px;
}
.content__title {
    color: #ffffff;
}
.content__button {
    border: none;
    padding: 10px 30px;
    border-radius: 30px;
    background:  var(--color4);
    color: #FFF;
    cursor: pointer;
}
.content__button:hover {
    background: orange;
}
</style>