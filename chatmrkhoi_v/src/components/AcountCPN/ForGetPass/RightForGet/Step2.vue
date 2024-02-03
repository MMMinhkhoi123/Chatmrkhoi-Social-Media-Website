<script setup>
import loaderCPN from "../../../ComomCPN/loader.vue";
import Handle from "../../../../handle/Acount/Forget";
const { Close, onsubmit_2, data_2 } = Handle();
</script>

<template>

    
    <form class="from" @submit.prevent.self="onsubmit_2">

        <!-- MESSAGES FOR ERRORS -->
        <div  class="from__errol" v-if="$store.state.auth.verify_code">
            <font-awesome-icon class="from__errol--icon"  @click="Close()" :icon="['far', 'circle-xmark']" />
            <span> 
                {{ $t($store.state.auth.verify_code) }}
            </span>
        </div>

        <!-- TITLE -->
        <h1 class="from__title">
            {{ $t('TextAcount.Forget_Password.From.step2.Title') }}
        </h1>

        <!-- TEXT EMAIL -->
        <p>{{ $t('TextAcount.Forget_Password.From.step2.Content')}}
             {{ EmailVerify }}
        </p>

        <!-- CODE VERIFY -->
        <div class="from__code">
            <input class="from_code--input" v-model="data_2.code" type="number" :placeholder="$t('TextAcount.Forget_Password.From.step2.TextInputCode')" required autocomplete="on"/>
            <font-awesome-icon class="from_code--icon" :icon="['fas', 'code']" />
        </div>

        <div class="from__setting">
            
            <!-- BACK -->
            <button class="btn from__setting--cancel" type="button"  @click="$router.go(-1)">
                {{ $t('TextAcount.Forget_Password.From.step2.ButtonCancel') }}
            </button>

            <!-- CONFIRM -->
            <button class="btn from__setting--submit" type="submit">
                {{ $t('TextAcount.Forget_Password.From.step2.ButtonNext') }} <font-awesome-icon :icon="['fas', 'angle-right']" />
            </button>
        </div>

    </form>

    <!-- LOADING -->
    <loaderCPN></loaderCPN>
    
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
    margin-bottom: 40px;
    }
.from__code {
    position: relative;
    margin-bottom: 23px;
}

.from_code--input {
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

.from__setting{
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

.from_code--icon {
    position: absolute;
    left:0px;
    top: 50%;
    transform: translateY(-50%) ;
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
    display: flex;
    gap: 10px;
    align-items: center;
}
.btn:hover {
    background: orange;
}

</style>