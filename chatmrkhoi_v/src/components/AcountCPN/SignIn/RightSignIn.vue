<script setup>
import Handle from "../../../handle/Acount/SignIn"
import loadingCPN from "../../../components/ComomCPN/loader.vue";
import store from "../../../store/index";
const { data, onsubmit, Close, CheckErrors } = Handle();

store.state.avaible_chat.open_loader = false;

</script>
<template>

    <form class="from" @submit.prevent="onsubmit()">

        <!-- MESSAGES FOR ERRORS -->
        <div class="from__errol" v-if="CheckErrors">
            <font-awesome-icon class="from__erro--icon" @click="Close()" :icon="['far', 'circle-xmark']" />
            <span class="from__erro--text">
                {{ $t($store.state.auth.err_login) }}
            </span>
        </div>

        <!-- TITLE -->
        <h1 class="from__title">
            {{ $t('TextAcount.Login.TitleRight') }}
        </h1>

        <!-- EMAIL -->
        <input class="from__input" v-model="data.gmails" type="email" :placeholder="$t('TextAcount.Login.TextInputEmail')" required>
        
        <!-- PASSWORD -->
        <input class="from__input" v-model="data.passwords"  type="password" :placeholder="$t('TextAcount.Login.TextInputPassword')" required autocomplete="on">

        <!-- CONFIRM -->
        <button class="from__button" type="submit">
            {{ $t('TextAcount.Login.ButtonSubmit') }} <font-awesome-icon :icon="['fas', 'paper-plane']" />
        </button>

    </form>

    <!-- LOADING  -->
    <loadingCPN></loadingCPN>

</template>
<style scoped>
.from {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 512px;
    gap: 16px;
    padding: 50px;
}
.from__errol {
    position: absolute;
    top: 10px;
    padding: 20px;
    min-width: 300px;
    background: rgba(104, 28, 235, 0.704);
    color: white;
}
.from__erro--icon {
    position: absolute;
    top: 5px;
    right: 5px;
    border-radius: 50%;
    color: rgb(255, 255, 255) !important;
}
.from__erro--text {
    display: block;
    margin-bottom: 10px;
}

.from__title {
margin-bottom: 24px;
}

.from__input {
    width: 100%;
    padding: 20px;
    padding-left: 10px;
    outline: none;
    border: none;
    background: transparent;
    border-bottom: 1px solid rgb(198, 194, 194);
}
.from__button {
    margin-top: 50px;
    padding: 10px 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 20px;
    background: var(--color4);
    color: #FFF;
    gap: 12px;
    border: none;
    cursor: pointer;
}
.from__button:hover {
    background: orange;
}
</style>