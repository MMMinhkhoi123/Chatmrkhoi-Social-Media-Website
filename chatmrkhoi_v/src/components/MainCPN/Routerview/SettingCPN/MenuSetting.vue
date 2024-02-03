<script setup>
import { computed } from 'vue'
import store from '../../../../store'
    const select = computed(() => {
        return store.state.avaible_chat.setting.select;
    })
</script>
<template>
    <ul class="menu">
        <li class="menu__item" :class="select == 1 ? 'active': ''"
         @click="$store.state.avaible_chat.setting.select = 1, $router.push({ query: { action: 'profile' } })">
            <font-awesome-icon id="icon"  class="icon" :icon="['fas', 'user']" />
            <strong>
                {{ $t('TextMain.Setting.Menu.Profile') }}
            </strong>
        </li>
        <li  class="menu__item" :class="select == 2 ? 'active': ''"
        @click="$store.state.avaible_chat.setting.select = 2, $router.push({ query: { action: 'security' } })" >
            <font-awesome-icon :icon="['fas', 'lock']" />
            <strong>
                {{ $t('TextMain.Setting.Menu.Security') }}
            </strong>
        </li>
        <li  class="menu__item" :class="select == 3 ? 'active': ''"
        @click="$store.state.avaible_chat.setting.select = 3, $router.push({ query: { action: 'advanted' } })" >
           <font-awesome-icon :icon="['fas', 'sliders']" />
            <strong>
                {{ $t('TextMain.Setting.Menu.Advanted') }}
            </strong>
        </li>
    </ul>
</template>

<style scoped>
.menu {
    height: 100%;
    overflow: hidden;
    margin: 0;
    padding: 0;
    padding: 10px;
}

.menu__item {
    list-style-type: none;
    display: flex;
    gap: 20px;
    border-radius: 5px;
    padding: 15px;
    padding-left:20px;
    margin-bottom: 10px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
 }

 .menu__item:hover {
    background: var(--color1);
    color: white;
 }
.active {
    background: var(--color1);
    color: white;
}

</style>