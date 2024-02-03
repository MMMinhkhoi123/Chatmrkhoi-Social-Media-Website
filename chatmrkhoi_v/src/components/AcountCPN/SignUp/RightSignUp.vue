<script setup>
import LoadingCPN from "../../ComomCPN/loader.vue";
import Handle from "../../../handle/Acount/SignUp"
const  { onsubmit,show, data, Close,CheckErrors } = Handle();
</script>
<template>

    <form  class="form" @submit.prevent="onsubmit" >
        
        <!-- MESSAGES FOR ERRORS -->
        <div class="form__errol" v-if="!CheckErrors()">
            <font-awesome-icon class="form__errol--icon" @click="Close()" :icon="['far', 'circle-xmark']" />
            <span>{{ $t($store.state.auth.err.fullname) }}</span>
            <span>{{ $t($store.state.auth.err.gmails) }}</span>
            <span>{{ $t($store.state.auth.err.passwords) }}</span>
        </div>

        <!-- TITLE -->
        <h1 class="form__title">
            {{ $t('TextAcount.SignUp.TitleRight') }}
        </h1>

        <!-- FULLNAME -->
        <div class="form__fullname form__part">
            <input class="form__part--input" v-model="data.fullname" type="text" :placeholder="$t('TextAcount.SignUp.TextInputName')" required>
        </div>

        <!-- EMAIL -->
        <div class="form__email form__part">
            <input class="form__part--input" v-model="data.gmails" type="email" :placeholder="$t('TextAcount.SignUp.TextInputEmail')" required>
        </div>

        <!-- PASSWORD -->
        <div class="form__password form__part">
            <input class="form__part--input" v-model="data.passwords" type="password" :placeholder="$t('TextAcount.SignUp.TextInputPassword')" required autocomplete="on">
        </div>

        <!-- CONFIRM -->
        <div class="form__setting">
            <button class="form__button--submit" type="submit">
                {{  $t('TextAcount.SignUp.ButtonSubmit')   }}
            </button>
        </div>
    </form>

    <!-- LOADING  -->
    <LoadingCPN />
</template>
<style scoped>
.form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 16px;
    padding: 30px;
    width: 100%;
    margin-top: 50px;
}
.form__errol {
    position: absolute;
    top: 10px;
    padding: 20px;
    min-width: 300px;
    max-width: 100%;
    background: rgba(104, 28, 235, 0.704);
    color: white;
}
.form__errol--icon {
    position: absolute;
    top: 5px;
    right: 5px;
    border-radius: 50%;
    color: rgb(255, 255, 255) !important;
}
.form__part:not(.form__errol) {
    width: 100%;
    display: flex;
    flex-direction: column;
}
.form__part--input {
    width: 100%;
    padding: 20px;
    padding-left: 10px;
    color: #000000;
    font-weight: 500;
    outline: none;
    border: none;
    transition: 0.5s;
    border-bottom: 1px solid rgb(198, 194, 194);
}
.form__setting {
    display: flex;
    align-items: center;
}
.form__button--submit {
    margin-top: 50px;
    border-radius: 20px;
    padding: 10px 20px;
    background:  var(--color4);
    color: #FFF;
    border: none;
    cursor: pointer;
}
.form__button--submit:hover {
    background: orange;
}
</style>