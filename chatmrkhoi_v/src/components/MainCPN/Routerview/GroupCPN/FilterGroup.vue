<script setup>
import { ref } from "vue";
   const open = ref(false);
</script>
<template>
    <!-- filter -->
    <div class="filter" id="filter">
        <div class="filter__top">
             <!-- search filter -->
            <div class="filter__top--search">
                <input class="top__input" v-model="$store.state.chat.data_search_group" type="text" :placeholder=" $t('TextMain.Group.Filter.InputSeach')" />
                <font-awesome-icon class="top__icon" :icon="['fas', 'magnifying-glass']" />
            </div>
        </div>

        <div  class="filter__bottom" v-if="$store.state.chat.array_mygroup">
            <div class="filter__bottom--item">
                <strong>{{  $t('TextMain.Group.Filter.Result') }}</strong>
                <span class="bottom__count">{{ $store.state.avaible_chat.count_group  }}</span>
            </div>
            <!-- dropdrown filter -->
            <div  class="filter__bottom--item  filter__bottom--dropdown" @click="open = !open" >
                <span class="bottom__text"> {{ $route.query.action == "all" ? $t('TextMain.Group.Filter.DropDownChoiceAll') : $route.query.action == "my" 
                ?  $t('TextMain.Group.Filter.DropDownChoiceMy') : $t('TextMain.Group.Filter.DropDownChoiceYour')  }}</span>
                <font-awesome-icon :icon="['fas', 'caret-down']" />
                <div class="bottom__menu" v-if="open == true">
                    <span  class="bottom__menu--item" @click="$router.push({ query: { action: 'all' } })" :class="$route.query.action == 'all' ? 'active': ''">
                        {{ $t('TextMain.Group.Filter.DropdownAll') }}
                    </span>
                    <span  class="bottom__menu--item" @click="$router.push({ query: { action: 'my' } })"  :class="$route.query.action == 'my' ? 'active': ''">
                        {{ $t('TextMain.Group.Filter.DropDownMy') }}
                    </span>
                    <span  class="bottom__menu--item" @click="$router.push({ query: { action: 'your' } })" :class="$route.query.action == 'your' ? 'active': ''">
                        {{ $t('TextMain.Group.Filter.DropDownYour') }}
                    </span>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.filter__top {
    background: var(--coloRegular);
    padding: 10px;
    display: flex;
    border-bottom: 1px solid  var(--colorLine);
}
.filter__top--search {
    width: 100%;
    display: flex;
    position: relative;
}
.top__icon {
    position: absolute;
    top: 50%;
    font-size: 20px;
    transform: translateY(-50%);
    color: gray;
}
.top__input  {
    width: 100%;
    padding: 10px;
    outline: none;
    border: none;
    font-size: 16px;
    padding-left: 30px;
    color: var(--colorText);
    background: var(--coloRegular);
}
.filter__bottom {
    display: flex;
    padding: 10px;
    border-bottom: 1px solid  var(--colorLine);
}
.filter__bottom--item {
    display: flex;
    align-items: center;
    gap: 10px;
}
.bottom__count {
    width: 25px;
    height: 25px;
    background: rgb(196, 193, 193);
    display: flex;
    font-weight: 700;
    justify-content: center;
    align-items: center;
    border-radius: 50%;
}
.filter__bottom--dropdown {
    position: relative;
    cursor: pointer;
    gap: 10px;
    margin-left: 20px;
    padding: 15px;
    width: 200px;
    display: flex;
    justify-content: center;
    align-items: center;
}
.bottom__menu {
    display: flex;
    flex-direction: column;
    position: absolute;
    top: 100%;
    left: 0;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
    min-width: 100%;
    z-index: 11;
}
.bottom__menu--item{
    padding: 10px;
    background: var(--coloRegular);
    transition: 0.3s;
}
.bottom__menu--item:hover {
    transform: scale(1.2);
    background: var(--color4);
    color: white;
}
.active {
    background: var(--color4) !important;
    color: white !important;
}
</style>