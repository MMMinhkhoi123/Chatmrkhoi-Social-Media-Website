<script setup>
import { computed, ref } from "vue";

const opendropdown = ref(false);
const datachonse = computed(() => {
    if(opendropdown.value != opendropdown.value) {};
    return localStorage.getItem("language");
})
function setlanguage(value) {
    localStorage.setItem("language",value);
    opendropdown.value != opendropdown.value;
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
        <div class="advanted__theme">
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
</style>