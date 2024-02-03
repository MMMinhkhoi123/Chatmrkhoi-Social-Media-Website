<script setup>
import { defineAsyncComponent } from 'vue'

const LeftSignUpCPN = defineAsyncComponent(() => import('../../components/AcountCPN/SignUp/LeftSignUp.vue'))
const RightSignUpCPN = defineAsyncComponent(() => import('../../components/AcountCPN/SignUp/RightSignUp.vue'))

const RignSignInCPN = defineAsyncComponent(() => import('../../components/AcountCPN/SignIn/RightSignIn.vue'))
const LeftSignInCPN = defineAsyncComponent(() => import('../../components/AcountCPN/SignIn/LeftSignIn.vue'))

const LeftForGetCPN = defineAsyncComponent(() => import('../../components/AcountCPN/ForGetPass/LeftForGet.vue'))
const RightForGetCPN = defineAsyncComponent(() => import('../../components/AcountCPN/ForGetPass/RigntForGet.vue'))

const LeftVerifyEmailCPN = defineAsyncComponent(() => import('../../components/AcountCPN/VerifyEmail/LeftVerifyEmail.vue'))
const RightVerifyEmailCPN = defineAsyncComponent(() => import('../../components/AcountCPN/VerifyEmail/RightVerifyEmail.vue'))

import { useRouter } from "vue-router"

const router = useRouter();

// REDIRECT HOME
function GoHome() {
    router.push({ name: 'blog'})
}

</script>
<template>
    <div class="acount">
        <div class="acount__background"></div>
        <div class="acount__frame">
            <div class="acount__frame--left">

                <!-- TEXT BACK HOME -->
                <span @click=" GoHome()" class="frame__back">
                    <font-awesome-icon :icon="['fas', 'chevron-left']" />
                    <span>
                        {{ $t('TextAcount.Common.Back') }}
                    </span>
                </span>

                <!-- COMPONENTS LEFT -->
                <LeftSignUpCPN v-if="$route.query.action == 'SignUp'" />

                <LeftVerifyEmailCPN v-if="$route.query.action == 'VerifyEmail'" />

                <LeftSignInCPN v-if="$route.query.action == 'SignIn'" />

                <LeftForGetCPN v-if="$route.query.action == 'Forget'" />

            </div>
            
            <div class="acount__frame--right"> 

                <!-- COMPONENTS RIGHT -->
                <RightSignUpCPN v-if="$route.query.action == 'SignUp'"/>

                <RignSignInCPN v-if="$route.query.action == 'SignIn'" />

                <RightForGetCPN v-if="$route.query.action == 'Forget'" />

                <RightVerifyEmailCPN v-if="$route.query.action == 'VerifyEmail'" />
            </div>
        </div>
    </div>
</template>
<style scoped>
.acount {
    position: fixed;
    inset: 0;
    background-image: url('../../assets/images/connectreal.jpg');
    background-repeat: no-repeat;
    background-size: cover;
}
.acount__background {
    content: "";
    position: absolute;
    inset: 0;
    background: rgba(4, 4, 4, 0.636);
}
.frame__back {
    position: absolute;
    left: 0;
    top: 0;
    display: flex;
    gap: 10px;
    cursor: pointer;
    z-index: 11;
    color: white;
}
.acount__frame {
    position: fixed;
    inset: 0;
    display: grid;
    grid-template-columns: 1fr 550px;
    border-radius: 10px;
    overflow: hidden;
    padding: 20px;
    box-sizing: border-box;
}
.acount__frame--left, .acount__frame--right {
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
}
.acount__frame--left{
    padding: 70px;
    position: relative;
}
.acount__frame--right {
    padding: 70px;
    background: white;
    border-radius: 10px;
}
</style>