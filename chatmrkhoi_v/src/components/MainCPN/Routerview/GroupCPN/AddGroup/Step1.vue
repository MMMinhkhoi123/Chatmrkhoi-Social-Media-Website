<script setup>
import store from '../../../../../store/index'
import imgxs from "../../../../NewCPN/imgtake.vue";
import data from "../../../../../handle/Screen_chat/index";
import handle from "../../../../../handle/Group/index";
    store.state.avaible_chat.open_notifi = false;
    const { url } = data();
    const { checknext,addgroup,namesearch,array_friend } = handle();
</script>
<template>
    <div class="form">
        <div class="form__search">
            <input class="form__search--input" v-model="namesearch" type="text" :placeholder="$t('TextMain.Group.Add.Input')" />
            <font-awesome-icon class="form__search--icon" :icon="['fas', 'magnifying-glass']" />
        </div>
        <strong class="form__text"> {{ $t('TextMain.Group.Add.Content') }} {{ $store.state.chat.array_addgroup.length }}</strong>
        <div class="form__list">
            <div class="form__list--item" @click="addgroup(item.id)" v-for="item in array_friend" :key="item.id" :class="$store.state.chat.array_addgroup.includes(item.id) == true ? 'active': ''">
                <span class="list__frame">
                    <div class="list__frame--img" v-if="item.type_img == 'rs'">
                        <imgxs class="frame__img" :name="item.images" ></imgxs>
                    </div>
                    <div class="list__frame--img" v-if="item.type_img == 'cpt'">
                        <img class="frame__img" :src="url + '/file/get-png/' + item.images" alt="">
                    </div>
                    <p class="list__frame--text">{{ item.fullname }}</p>
                </span>
            </div>
        </div>
        <div class="form__setting">
            <button class="form__setting--button" @click="checknext">
             {{ $t('TextMain.Group.Add.ButtonNext') }}
            </button>
        </div>
    </div>

</template>
<style scoped>
.form {
    padding: 10px;
    width: 450px;
    height: 500px;
    background: white;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    position: absolute;
    border-radius: 10px;
    top: 50%;
    left: 50%;
    z-index: 11;
    transform: translate(-50%, -50%);
}
.form__search {
    position: relative;
    width: 100%;
    display: flex;
    box-sizing: border-box;
    background: #EFEEFF;
    border-radius: 10px;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    overflow: hidden;
}
.form__search--icon{
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
}
.form__search--input {
    width: 100%;
    padding: 20px;
    border: none;
    padding-right: 30px;
    outline: none;
    color: #000;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
}

.form__text {
    display: block;
    margin-top: 25px;
    color: #000;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
}

.form__list {
    padding: 20px 0;
    height: 300px;
    overflow-y:scroll;
}

.form__list--item {
    background: #F4F2F2;
    border-radius: 10px;
    padding: 5px;
    display: flex;
    margin-bottom: 10px;
    transition: 0.3s;
    justify-content: space-between;
}
.form__list--item:hover {
    cursor: pointer;
    transform: translateY(-5px)
}
.list__frame {
    display: flex;
    align-items: center;
    gap: 13px;
}
.list__frame--img {
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    overflow: hidden;
}
.frame__img {
    width: 100%;
    height: 100%;
}
.list__frame--text {
    color: #000;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
}
.active {
    background: var(--color1) !important;
}
.active > .list__frame > .list__frame--text {
    color: white !important;
}
.form__setting {
    position: absolute;
    bottom: 20px;
    right: 20px;
}
.form__setting--button {
    padding: 10px 30px;
    transition: 0.3s;
    border-radius: 10px;
    border: 1px solid var(--color1);
    background:  transparent;
    color: var(--color1);
    font-style: normal;
    font-weight: 500;
    line-height: normal;
}
.form__setting--button:hover {
    background: var(--color1) !important;
    color: white !important;
}
</style>