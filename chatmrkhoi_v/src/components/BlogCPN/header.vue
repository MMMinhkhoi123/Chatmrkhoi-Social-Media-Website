<script setup>
import { computed, ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
const opendropdown = ref(false);

// GE THE LANGUAGE YOU ARE USING
const DataChoice = computed(() => {
    if(opendropdown.value != opendropdown.value) {};
    return localStorage.getItem("language");
})

// SET LANGUAGE
function Setlanguage(value) {
    localStorage.setItem("language",value);
    opendropdown.value != opendropdown.value;
}

// REDIRECT INTO SIGNUP
function GoSignUp() {
    router.push({ name: 'acount', query: { action: 'SignIn' } });
}

// REDIRECT INTO SIGNIN
function GoSingIn() {
    router.push({ name: 'acount', query: { action: 'SignUp' } })
}

</script>

<template>


        <div class="header">

            <!-- LOGO -->
            <div class="header__logo">
                <img class="header__logo--img" src="../../assets/images/logo.png">
                <Strong id="header__logo--text">
                    Chatmrkhoi
                </Strong>
            </div>

            <div class="header__option">

                <div  class="header__option--link" id="select-signin">

                    <!-- CHOICE LANGUAGE -->
                    <div class="option__dropdown" @click="opendropdown = !opendropdown" >
                        <font-awesome-icon id="option__dropdown--icon" :icon="['fas', 'caret-down']" /> 
                        <div class="option__dropdown--chonse">
                            <img class="dropdown__img" :src="'/src/assets/icon/' +  (DataChoice != null ? DataChoice : $root.$i18n.locale) +'.png'">
                        </div>
                        <div v-if="opendropdown" class="option__dropdown--menu">
                            <div class="dropdown__item" v-for="locale in $i18n.availableLocales" :key="`locale-${locale}`" @click="$root.$i18n.locale = locale, Setlanguage(locale)">
                                <img class="dropdown__item--img" :src="'/src/assets/icon/' + locale + '.png'">
                                <strong>{{ locale }}</strong>
                            </div>
                        </div>
                    </div>

                      <!-- SIGNIN -->
                    <button class="option__button" @click="GoSignUp()">
                        <span class="option__button--text">
                            <p>{{ $t('TextBlog.ButtonLogin')  }}</p>
                        </span>
                    </button>
                </div>

                <!-- SIGNUP -->
                <div class="header__option--link" id="select-signup">
                    <button class="option__button" @click="GoSingIn()">
                        <span>{{ $t('TextBlog.ButtonSignUp') }}</span>
                    </button>
                </div>

            </div> 
        </div>


</template>

<style scoped>
.option__dropdown {
    position: relative;
    display: flex;
    align-items: center;
    gap: 10px;
}
.option__dropdown--menu {
    background: rgb(230, 229, 229);
    position: absolute;
    color: black;
    padding: 10px;
    border-radius: 10px;
    top: 100%;
}
.dropdown__item {
    display: flex;
    align-items: center;
    gap: 10px;
}
/* header */
.header {
    position: fixed;
    left: 0;
    right: 0;
    z-index: 99;
    min-height: 64px;
    display: flex;
    justify-content: space-between;
}

.header__logo {
    position: relative;
    z-index: 100;
    padding: 10px 20px;
    display: flex;
    align-items: center;
    gap: 10px;
    color: white;
    font-size: 20px;
}
.header__logo--img {
    border-radius: 50%;
    width: 50px;
    height: 50px;
    object-fit: cover;
    overflow: hidden;
}
.header__option {
    display: flex;
}
.header__option--link {
    display: flex;
    align-items: center;
    justify-content: center;
}

#select-signin {
    position: relative;
    z-index: 10;
    padding: 10px 20px;
    justify-content: center;
    display: flex;
    align-items: center;
    font-size: 16px;
    font-weight: 600;
    cursor: pointer;
    color: white;
}
#select-signin > .option__button {
    border: none;
    background: transparent;
    font-size: 15px;
    outline: none;
}
#select-signup {
    position: absolute;
    left: 0;
    right: 0;
    padding-right: 10px;
    height: 100%;
    transition: 0.4s;
    bottom: 100%;
    background: white;
    display: flex;
    z-index: 2;
    justify-content: end;
}
.header__option > span:hover {
    color: var(--color4) !important;
}
.option__button {
    background: var(--color4);
    border: none;
    cursor: pointer;
    border-radius: 5px;
    border:none;
    z-index: 11;
    position: relative;
    padding: 10px 20px;
    color: white;
}
.option__button:hover {
    background: rgb(249, 131, 88);
}
.option__button--text:hover  {
    color: var(--color4) !important;
}
.dropdown__img {
    width: 40px;
    height: 40px;
}
.dropdown__item--img {
    width: 40px;
    height: 40px;
}
</style>