<script setup>
import Handle from "../../../../handle/Acount/Forget";
import store from "../../../../store";
import LoadingCPN from "../../../ComomCPN/loader.vue";

const { onsubmit_1, GoSignIn, Close, data_1 } = Handle();

</script>
<template>

    
    <form  class="from" @submit.prevent.self="onsubmit_1()">

        <!-- MESSAGES FOR ERRORS -->
        <div class="from__errol" v-if="$store.state.auth.verify_code">
            <font-awesome-icon class="from__errol--icon"  @click="Close()" :icon="['far', 'circle-xmark']" />
            <span> {{ $t($store.state.auth.verify_code) }} </span>
        </div>

        <!-- TITLE -->
        <h1 class="from__title">
            {{ $t('TextAcount.Forget_Password.From.step1.Title') }}
        </h1>

        <!-- EMAIL -->
        <div class="from__email">
            <input class="from__email--input" v-model="data_1" type="email" :placeholder="$t('TextAcount.Forget_Password.From.step1.TextInputEmail')" required autocomplete="on"/>
            <font-awesome-icon class="from__email--icon"  :icon="['fas', 'envelope']" />
        </div>

        <div class="from__setting">

            <!-- BACK SIGNIN -->
            <button class="btn from__setting--cancel" type="button"  @click="GoSignIn()">
                {{ $t('TextAcount.Forget_Password.From.step1.ButtonCancel') }}
            </button>

            <!-- CONFIRM -->
            <button class="btn from__setting--get" type="submit"> 
               {{ $t('TextAcount.Forget_Password.From.step1.ButtonGetCode') }}
            </button>
        </div>
        
    </form>

    <!-- LOADING -->
    <LoadingCPN />



</template>
<style scoped>
.from {
    text-align: center;
    width: 100%;
    padding: 50px 0;
}
.from__errol {
    position: absolute;
    top: 10px;
    padding: 20px;
    color: white;
    width: 350px;
    background: rgba(104, 28, 235, 0.704);
}
.from__errol--icon {
    position: absolute !important;
    right: 5px !important;
    top: 10px !important;
}

.from__title {
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    margin-bottom: 60px;
    }

.from__email {
    position: relative;
    margin-bottom: 23px;
}

.from__email--icon {
    position: absolute;
    left:0px;
    top: 50%;
    transform: translateY(-50%) ;
}


.from__setting {
    bottom: 15px;
    right: 0;
    display: flex;
    justify-content: space-between;
}
.from__setting--cancel {
    background: orange !important;
}
.from__setting--cancel:hover{
    background: var(--color4) !important;
}

.btn {
    border-radius: 20px;
    padding: 10px 20px;
    background: var(--color4);
    color: #ffffff;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    cursor: pointer;
    border: none;
}
.btn:hover {
    background: orange;
}

.from__email--input {
    margin: 20px 0;
    width: 100%;
    padding: 20px;
    padding-left: 35px;
    font-style: normal;
    font-weight: 500;
    outline: none;
    border: none;
    border-bottom: 1px solid #CBC6C6 ;
    line-height: normal;
}

</style>