<script setup>
import { computed, reactive, ref } from 'vue'
import store from '../../../../../store'
import imgx from "../../../../NewCPN/imgtake.vue";
import connect from "../../../../../socket/handle_chat_private";
import datas from "../../../../../handle/Screen_chat/index";
const { authen } = datas();
const unmesschose = () => {
const datapost = {
    type: 'one',
    idmess:  store.state.avaible_chat.unmess.idmess,
    iduser: store.state.auth.authen.id
}
store.dispatch("chat/unmess", datapost);
    store.state.avaible_chat.unmess.status = null
}


const data = computed(() => {
    if(datasearch.value != '') {
        const arrayreturn = [];
        store.state.chat.array_connect.forEach(element => {
            if(element.idgroup != null) {
                if(upper(get_info_group(element.idgroup)[0].name).search(upper(datasearch.value)) != -1) {
                   arrayreturn.push(element);
                }
            }else {
                if(upper(get_info_friend(element.idfriend)[0].fullname).search(upper(datasearch.value)) != -1) {
                arrayreturn.push(element);
                }
            }
         });
        console.log(arrayreturn)
        return arrayreturn;
    }
    return store.state.chat.array_connect;
})



function upper(text) {
    return text.toUpperCase();
}
const url = import.meta.env.VITE_API_URL;
const get_info_group = (id)=> {
    return store.getters["chat/get_info_group"](id)
}
const get_info_friend = (id) => {
        return store.getters["everyone/get_your_friend_id"](id)
}

const sendmesmove = (item, key) => {
    store.state.chat.data_after_send = null;
        const datasend = reactive({
        type: store.state.avaible_chat.save_move.type,
        idmess: store.state.avaible_chat.save_move.data.id,
        idgroup:  item.idgroup,
        idfriend: item.idfriend,
    })
    store.dispatch("chat/movemess", datasend).then(() => {
        const x = setInterval(() => {
            if( store.state.chat.data_after_send != null) {
                clearInterval(x);
                connect({ move: authen.value.id, list:store.state.chat.data_after_send },  item.coderoom );
            }
        }, 1000)
    });

    const div = document.getElementById("send" + item.coderoom);
            div.classList.add('sendted')
            div.innerText = key;
    }

const datasearch = ref("");
</script>
<template>
    <div class="background" @click="$store.state.avaible_chat.open_send = false" >
    </div>
    <div class="trantition">
        <h3 class="trantition__title">
            {{ $t('TextMain.Chat.Screen.From.Transitions.Title') }}
        </h3>
        <div class="trantition__search">
            <input class="trantition__search--input" v-model="datasearch" type="text" :placeholder="$t('TextMain.Chat.Screen.From.Transitions.Input')" />
            <button class="trantition__search--icon">
                <font-awesome-icon  class="search__icon"  :icon="['fas', 'magnifying-glass']" />
            </button>
        </div>
        <div class="transition__list">
            <div class="transition__list--item" v-for="item in data" :key="item" >
                <div class="list__content">
                    <div class="list__content--avata" v-if="item.idgroup != null">
                        <img  class="content__img" :src="url + '/file/get-png/' +  get_info_group(item.idgroup)[0].img">   
                    </div>
                    <div class="list__content--avata"  v-if="item.idfriend != null">
                        <imgx class="content__img" v-if="get_info_friend(item.idfriend)[0].type_img == 'rs'" :name="get_info_friend(item.idfriend)[0].images"></imgx>
                        <img class="content__img" v-if="get_info_friend(item.idfriend)[0].type_img != 'rs'"  :src="url + '/file/get-png/' +  get_info_friend(item.idfriend)[0].images">            
                    </div>
                    <strong>{{ item.idgroup != null ? get_info_group(item.idgroup)[0].name : get_info_friend(item.idfriend)[0].fullname}}</strong>
                </div>
                <div class="list__setting">
                    <button class="list__setting--button" @click="sendmesmove(item, $t('TextMain.Chat.Screen.From.Transitions.Sended'))" :id="'send'+ item.coderoom">
                        {{ $t('TextMain.Chat.Screen.From.Transitions.send') }}
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>
<style scoped>
.background {
    position: fixed;
    inset: 0;
    background: rgba(123, 121, 121, 0.612);
    z-index: 100;
    filter: blur(20px);
}
.trantition {
    background: white;
    border-radius: 10px;
    text-align: center;
    position: fixed;
    top: 50%;
    left: 50%;
    min-width: 500px;
    display: flex;
    flex-direction: column;
    transform: translate(-50%, -50%);
    z-index: 111;
    box-shadow: 0 0 19px rgba(0, 0, 0, 0.253);
  }
.trantition__search {
    display: flex;
    position:relative;
    margin: 20px;
}
.trantition__title {
    margin: 0;
    padding: 20px;
    border-radius: 10px 10px 0px 0px;
    color: white;
    background: var(--color1);
}
.trantition__search--icon {
    position: absolute;
    background: transparent;
    border: none;
    font-size: 25px;
    right: 0;
    top: 50%;
    transform: translateY(-50%);
}
.trantition__search--input {
    width: 100%;
    padding: 15px;
    border: 1px solid black;
    border-radius: 10px;
    padding-right: 25px;
    outline: none;
    border: none;
    border: 1px solid gray;
}

.transition__list {
    margin: 0 20px;
    height: 300px;
    overflow-y: scroll;
}

.transition__list--item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    margin-top: 10px;
}
.list__content {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px;
    font-size: 17px;
}
.content__img {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
}
.list__setting {
    display: flex;
    align-items: center;
}
.list__setting--button {
    transition: 0.3s;
    padding: 10px 20px;
    border: none;
    background: var(--color1);
    color: white;
    border-radius: 5px;
}
.list__setting--button:hover {
    background: var(--color2);
}

.sendted {
    border: 1px solid var(--color1) !important;
    background: white !important;
    color: var(--color1) !important;
}
</style>