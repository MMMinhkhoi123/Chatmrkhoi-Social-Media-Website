<script setup>
import { ref } from "vue"
import data from "../../../../handle/Everyone/index";
const { search } = data();
 const open_option = ref(false)
</script>
<template>
      <div class="filter">
        
        <!-- INPUT -->
        <form @submit.prevent="search" class="filter__form">
            <font-awesome-icon class="filter__form--icon" :icon="['fas', 'magnifying-glass']" />
            <input class="filter__form--input" :type="$store.state.everyone.optionselect == false ? 'email': 'text'" v-model="$store.state.everyone.keysearch" 
                :placeholder="$store.state.everyone.optionselect == false 
                ?  $t('TextMain.Everyone.Filter.InputSeachGmail') 
                : $t('TextMain.Everyone.Filter.InputSeachName')" 
                required />
            <span class="filter__form--clear" v-if="$store.state.everyone.keysearch!= ''" @click="$store.state.everyone.keysearch = ''">
                {{ $t('TextMain.Everyone.Filter.Clear') }}
            </span>
        </form>

        <div class="filter__option">

            <!-- TEXT RESULT -->
            <div class="filter__option--item">
                <strong class="option__text">
                    {{$t('TextMain.Everyone.Filter.Result')  }}
                </strong>
                <strong class="option__text">
                    {{ $store.state.avaible_chat.count_user }}
                </strong>
            </div>

            <!-- DROPDOWN OPTION -->
            <div  class="filter__option--item" @click.self="open_option = !open_option" >
                {{ $store.state.everyone.optionselect == true 
                ? $t('TextMain.Everyone.Filter.DropDownChoiceName')
                : $t('TextMain.Everyone.Filter.DropDownChoiceEmail') }} 
                <font-awesome-icon :icon="['fas', 'caret-down']" />
                <div v-if="open_option == true" class="option__menu">
                    <div class="option__menu--select" @click="open_option = false, $store.state.everyone.optionselect = false"
                        :class="$store.state.everyone.optionselect == false ? 'active': ''" >
                        {{ $t('TextMain.Everyone.Filter.DropDownEmail') }}
                    </div>
                    <div class="option__menu--select" @click="open_option = false, $store.state.everyone.optionselect = true"  
                    :class="$store.state.everyone.optionselect == true ? 'active': ''">
                    {{ $t('TextMain.Everyone.Filter.DropdownName') }}
                </div>
                </div>
            </div>
        </div>
    </div>

</template>
<style scoped>
.filter {
    background: white;
    display: flex;
    flex-direction: column;
    justify-content: end;
    position: relative;
    border-bottom: 1px solid rgb(224, 218, 218);
}
.filter__form {
    position: relative;
    display: block;
    align-items: center;
    display: flex;
    padding-left: 45px;
    padding-right: 45px;
    border-bottom: 1px solid rgb(224, 218, 218);
}
.filter__form--icon {
    position: absolute;
    left: 10px;
    top: 50%;
    font-size: 25px;
    transform: translateY(-50%);
    color: rgb(181, 177, 177);
}
.filter__form--clear {
    position: absolute;
    right: 10px;
    cursor: pointer;
}
.filter__form--input {
    width: 100%;
    padding: 20px;
    color: #000;
    font-size: 16px;
    border: none;
    outline: none;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
}

/* option */
.filter__option {
    display: flex;
    justify-content: space-between;
    padding: 20px;
}
.filter__option--item {
    min-width: 140px;
    position: relative;
    cursor: pointer;
}


.filter__option--item:nth-child(1) {
    display: flex;
    align-items: center;
    gap: 10px;
}
.option__text:nth-child(2) {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 25px;
    height: 25px;
    background: rgb(221, 221, 221);
    border-radius: 50%;
}
.active {
    background: var(--color4) !important;
    color: white;
}

.option__menu {
    position: absolute;
    left: 0;
    right: 0;
    z-index: 4;
    top: 100%;
    background: white;
    box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;
}
.option__menu--select {
    transition: 0.3s;
    padding: 10px 20px;
    cursor: pointer;
    text-align: center;
    background: white;
}
.option__menu--select:hover {
    background: var(--color4);
    color: white;
    transform: scale(1.1);
}


</style>