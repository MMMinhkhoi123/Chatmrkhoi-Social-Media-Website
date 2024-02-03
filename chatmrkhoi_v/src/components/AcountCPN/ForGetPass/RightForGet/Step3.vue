<script setup>

import NotifiCPN from "../../../ComomCPN/Notification/Changepass_warning.vue";
import Handle from "../../../../handle/Acount/Forget";

const { onsubmit_3, NameAccount, data_3  } = Handle();

</script>
<template>
    <form  class="form" @submit.prevent="onsubmit_3">

        <!-- MESSAGES FOR ERRORS -->
        <div class="form__errol" v-if="$store.state.auth.err.passwords != ''">
            <font-awesome-icon class="form__errol--icon"  @click="$store.state.auth.err.passwords = ''" :icon="['far', 'circle-xmark']" />
            <span> {{ $t($store.state.auth.err.passwords) }}</span>
        </div>

        <!-- TITLE -->
        <h1 class="form__title">
            {{ $t('TextAcount.Forget_Password.From.step3.Title') }}
        </h1>
        <p>
            {{ $t('TextAcount.Forget_Password.From.step3.Content') }} : {{ NameAccount }}
        </p>

        <!-- PASSWORD  -->
        <div  class="form__password">
            <input class="form__password--input" v-model="data_3" type="password" :placeholder="$t('TextAcount.Forget_Password.From.step3.TextInputPassword')" required  autocomplete="on"/>  
            <font-awesome-icon class="form__password--icon" :icon="['fas', 'key']" />
        </div> 

        <!-- CONFIRM -->
        <div class="form__setting">
            <button class="btn form__setting--submit" type="submit">
                <span>{{ $t('TextAcount.Forget_Password.From.step3.ButtonChange') }}</span>
            </button>
        </div>

    </form>

    <!-- NOTIFACATION -->
    <NotifiCPN></NotifiCPN>
    
</template>
<style scoped>
.form {
    padding: 30px 0;
    width: 100%;
    text-align: center;
}
.form__errol {
    position: absolute;
    top: 10px;
    padding: 20px;
    color: white;
    width: 350px;
    background: rgba(104, 28, 235, 0.704);
}
.form__errol--icon {
    position: absolute !important;
    right: 5px !important;
    top: 10px !important;
}

.form__title {
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    margin-bottom: 40px;
    }
.form__password {
    position: relative;
    margin-bottom: 40px;
}
.form__password--input {
    outline: none;
    width: 100%;
    padding: 20px;
    padding-left: 30px;
    border: none;
    border-bottom: 1px solid #d3d3d3; 
    font-style: normal;
    font-weight: 500;
    line-height: normal;
}
.form__password--icon {
    position: absolute;
    left: 0;
    top: 50%;
    transform: translateY(-50%);
}
.form__setting {
    display: flex;
    justify-content: center;
}

.btn {
    margin-right: 15px;
    border-radius: 20px;
    background: var(--color4);
    padding: 10px 20px;
    color: #ffffff;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    border: none;
}

.btn:hover {
    background: orange;
}
</style>