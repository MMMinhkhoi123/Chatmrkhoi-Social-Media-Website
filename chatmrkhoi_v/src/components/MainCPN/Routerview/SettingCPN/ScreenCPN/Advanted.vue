<script setup>
import { computed, ref } from "vue";
import common from "../../../../../handle/Common/index";
import store from "../../../../../store";
const { authen} = common();
const opendropdown = ref(false);
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
.notify__frame{
    display: block;
    width: 60px;
    background: gray;
    display: flex;
    border-radius: 20px;
}
.notify__frame--icon {
    display: block;
    width: 25px;
    height: 25px;
    border-radius: 50%;
    background: rgb(34, 33, 33);
    margin-right: auto;
    transition: 0.3s;
}
.advanted__notify--checkbox > input {
    display: none;
}
.advanted__notify--checkbox > input:checked ~ .notify__frame {
  background: rgb(211, 214, 210);
}
.advanted__notify--checkbox > input:checked ~ div > .notify__frame--icon {
    background: rgb(121, 209, 92);
    margin-left: auto !important;
    margin-right: 0 !important;
}
</style>