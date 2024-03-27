<script setup>
import { computed, ref } from "vue";
import { useRouter } from "vue-router";
import common from "../../../../../handle/Common/index";
import store from "../../../../../store";
const { authen } = common();
const opendropdown = ref(false);
const router = useRouter();
const datachonse = computed(() => {
    if(opendropdown.value != opendropdown.value) {};
    return localStorage.getItem("language");
})
function setlanguage(value) {
    localStorage.setItem("language",value);
    opendropdown.value != opendropdown.value;
}
function ChangeNotify() {
        setTimeout(() => {
            store.dispatch("chat/updatenotify",document.querySelector(".notify__checkbox").checked);
            authen.value.notify = !authen.value.notify;
        }, 500); 
}

function ChangeDarkMode() {
    authen.value.darkMode = !authen.value.darkMode;
    setTimeout(() => {
            store.dispatch("chat/updatetheme",document.querySelector(".darkmode__checkbox").checked);
        }, 200);
    if (authen.value.darkMode == false) {
        document.documentElement.style.setProperty('--colorMenu', '#44318D');
        document.documentElement.style.setProperty('--colorBehind', 'rgb(196, 205, 231)');
        document.documentElement.style.setProperty('--coloRegular', 'white');
        document.documentElement.style.setProperty('--colorText', 'black');
        document.documentElement.style.setProperty('--colorBehindLess', 'white');
        document.documentElement.style.setProperty('--colorshadow', 'gray');
        document.documentElement.style.setProperty('--colorLine', 'white');
        document.documentElement.style.setProperty('--colorScreenChat', ' #dedfff');
    } else {
        document.documentElement.style.setProperty('--colorMenu', '#313b4b');
        document.documentElement.style.setProperty('--colorBehind', '#15191d');
        document.documentElement.style.setProperty('--coloRegular', '#212529');
        document.documentElement.style.setProperty('--colorText', 'white');
        document.documentElement.style.setProperty('--colorBehindLess', '#262626');
        document.documentElement.style.setProperty('--colorshadow', '#100f0f');
        document.documentElement.style.setProperty('--colorLine', '#6f6f6f');
        document.documentElement.style.setProperty('--colorScreenChat', ' #3b3b3b');
    }
}
</script>
<template>
    <div class="advanted">
        <div class="advanted__language">
            <h3 class="advanted__language--title">
                {{ $t('TextMain.Setting.Screen.Advanted.TitleLanguage') }}
            </h3>
            <div class="advanted__language--dropdown" @click="opendropdown = !opendropdown" >
                <font-awesome-icon :icon="['fas', 'caret-down']" /> 
                <div class="language__choice">
                    <img class="language__choice--img" :src="'/src/assets/icon/' +  (datachonse != null ? datachonse : $root.$i18n.locale) +'.png'">
                </div>
                <div class="language__menu" v-if="opendropdown">
                    <div class="language__menu--item" v-for="locale in $i18n.availableLocales" :key="`locale-${locale}`" @click="$root.$i18n.locale = locale, setlanguage(locale)">
                        <img class="menu__img" :src="'/src/assets/icon/' + locale + '.png'">
                        <strong>{{ locale }}</strong>
                    </div>
                </div>
            </div>
        </div>
        <div class="advanted__notify">
            <h3 class="advanted__notify--title">Thông báo tin nhắn khi ngoại tuyến</h3>
            <label class="advanted__notify--checkbox">
                <input @click.self="ChangeNotify()" class="notify__checkbox" type="checkbox" :checked="authen.notify">
                <div class="notify__frame">
                    <span class="notify__frame--icon"></span>
                </div>
            </label>
        </div>
        <div class="advanted__darkmode">
            <h3 class="advanted__darkmode--title">Giao diện tối</h3>
            <label class="advanted__darkmode--checkbox">
                <input @click="ChangeDarkMode()" class="darkmode__checkbox" type="checkbox" :checked="authen.darkMode">
                <div class="darkmode__frame">
                    <span class="darkmode__frame--icon"></span>
                </div>
            </label>
        </div>
    </div>
</template>
<style scoped>
.advanted {
    margin: 20px;
}
.advanted__language--dropdown {
    position: relative;
    display: flex;
    align-items: center;
    gap: 10px;
}
.language__menu {
    background: rgb(230, 229, 229);
    position: absolute;
    color: black;
    padding: 10px;
    border-radius: 10px;
    top: 100%;
}
.language__menu--item{
    display: flex;
    align-items: center;
    gap: 10px;
}
.menu__img {
    width: 40px;
    height: 40px;
}
.language__choice--img {
    width: 40px;
    height: 40px;
}


.notify__frame,
.darkmode__frame{
    display: block;
    width: 60px;
    background: gray;
    display: flex;
    border-radius: 20px;
}
.darkmode__frame{ 
    background: var(--color3);
}

.notify__frame--icon,
.darkmode__frame--icon {
    display: block;
    width: 25px;
    height: 25px;
    border-radius: 50%;
    background: rgb(34, 33, 33);
    margin-right: auto;
    transition: 0.3s;
}

.darkmode__frame--icon {
    background: var(--color1);
}

.advanted__notify--checkbox > input,
.advanted__darkmode--checkbox > input {
    display: none;
}
.advanted__notify--checkbox > input:checked ~ .notify__frame {
  background: rgb(211, 214, 210);
}

.advanted__darkmode--checkbox > input:checked ~ .darkmode__frame {
 background: rgb(40, 37, 37);
 border: 1px solid rgb(72, 69, 69);
}

.advanted__notify--checkbox > input:checked ~ div > .notify__frame--icon {
    background: rgb(121, 209, 92);
    margin-left: auto !important;
    margin-right: 0 !important;
}
.advanted__darkmode--checkbox > input:checked ~ div > .darkmode__frame--icon  {
    background: rgb(176, 176, 176);
    margin-left: auto !important;
    margin-right: 0 !important;
}
</style>