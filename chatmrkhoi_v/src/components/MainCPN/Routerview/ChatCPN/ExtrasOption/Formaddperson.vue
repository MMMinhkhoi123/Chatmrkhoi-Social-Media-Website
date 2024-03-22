<script setup>
import { computed, ref } from 'vue'
import store from '../../../../../store'
import connect_private from "../../../../../socket/handle_chat_private";
import storess from "../../../../../handle/Screen_chat/index";
import imgxs from "../../../../NewCPN/imgtake.vue";
import connects from "../../../../../socket/connect_private";
const {  get_info_group,profile_friend, url, authen  } = storess();
const data_search = ref('');
function upper(text) {
    return text.toUpperCase();
}

store.dispatch("chat/get_groupid", Number(localStorage.getItem("iddetail")));

const data = computed(() => {
    if(data_search.value != '') {
        const array = [];
        store.state.everyone.array_friend.forEach(element => {
            if(upper(profile_friend(element.id)[0].fullname).search(upper(data_search.value)) != -1) {
                array.push(element);
            }
        });
        return array;
    }
    return store.state.everyone.array_friend;
})
const check_incudes_group = (id) => {
    var x = true;
    if(get_info_group(Number(localStorage.getItem("iddetail")))[0].master == id) {
            return false;
    }
    store.state.chat.array_groupid.forEach(element => {
        if(element.id == id) {
            x = false;
        }
    }); 
    return x;
}


const addgroup = (id, romuser) => {
    store.state.chat.data_after_addperson = null;
    const data = {
        idfriend: id,
        id: Number(localStorage.getItem("iddetail")),
        roomuser: romuser
    }

    var connet = null;
    var group = null;

    store.state.chat.array_mygroup.forEach((e, index) => {
        if(e.id == Number(localStorage.getItem("iddetail"))) {
             group = store.state.chat.array_mygroup[index];
        }
    })

    // update connect
    store.state.chat.array_connect.forEach((e, index) => {
        if(e.idgroup ==  Number(localStorage.getItem("iddetail"))) {
            store.state.chat.array_connect[index].coderoom = localStorage.getItem("data-select") + "&" + id;
            connet = store.state.chat.array_connect[index];
            connects();
        }
    })
    // update mess old
    store.state.chat.array_all_mess.forEach((e, index) => {
        if(e.id_group ==  Number(localStorage.getItem("iddetail"))) {
           store.state.chat.array_all_mess[index].room = localStorage.getItem("data-select") + "&" + id;
        }
    })
    // post server
    store.dispatch("chat/add_person_group", data).then((e) => {  
          // my new connect
    const xcode = localStorage.getItem("data-select") + "&" + id;


    const x = setInterval(() => {
            if(store.state.chat.data_after_addperson != null) {
                clearInterval(x);

                const data_mate = {
                    statusgroup: authen.value.id,
                    idgroup: Number(localStorage.getItem("iddetail")),
                    code: localStorage.getItem("data-select") + "&" + id,
                    code_old: localStorage.getItem("data-select"),
                    data: store.state.chat.data_after_addperson
                }
                // send old usergroup
                connect_private(data_mate, localStorage.getItem("data-select"));

                group.coderoom = localStorage.getItem("data-select") + "&" + id;

                // send new user
                const data_mate2 = {
                    newuser: authen.value.id,
                    useraply: id,
                    idgroup: Number(localStorage.getItem("iddetail")),
                    newconnect: connet,
                    newgroup: group, 
                    data: store.state.chat.data_after_addperson,
                    code: localStorage.getItem("data-select") + "&" +id,
                }
                connect_private(data_mate2, romuser)
                localStorage.setItem("data-select", localStorage.getItem("data-select") + "&"+ id)
            }
        })
    })
}



</script>
<template>
    <div class="background" @click="$store.state.avaible_chat.open_addperson = false">
    </div>
    <div class="add" > 
        <h3 class="add__title">
            {{ $t('TextMain.Chat.Screen.From.AddGroup.Title') }}
        </h3>
        <div class="add__search">
            <input class="add__search--input" v-model="data_search" type="text" :placeholder="$t('TextMain.Chat.Screen.From.AddGroup.Input')" />
            <font-awesome-icon class="add__search--icon" :icon="['fas', 'magnifying-glass']" />
        </div>
        <div class="add__list">
            <div  class="add__list--item" v-for="item in data" :key="item">
                <div class="list__content">
                    <imgxs class="list__content--img" v-if="profile_friend(item.id)[0].type_img == 'rs'" :name="profile_friend(item.id)[0].images"></imgxs>
                    <img class="list__content--img" v-if="profile_friend(item.id)[0].type_img != 'rs'"  :src=" url + '/file/get-png/' + profile_friend(item.id)[0].images">
                    <strong class="list__content--text">{{ item.fullname }}</strong>
                </div>
                <div class="add__setting" v-if="$store.state.chat.array_groupid">
                    <button  class="add__setting--button"  v-if="check_incudes_group(item.id) == true"  @click="addgroup(item.id, item.coderoom)">
                        {{ $t('TextMain.Chat.Screen.From.AddGroup.Add') }}
                    </button>
                    <button class="add__setting--joined" v-if="check_incudes_group(item.id) == false" disabled>
                        {{ $t('TextMain.Chat.Screen.From.AddGroup.Joined') }}
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
    background: rgba(255, 255, 255, 0.348);
    z-index: 100;
  }
  .add {
    background: white;
    border-radius: 10px;
    text-align: center;
    position: fixed;
    top: 50%;
    left: 50%;
    min-width: 500px;
    min-height: 450px;
    display: flex;
    flex-direction: column;
    transform: translate(-50%, -50%);
    z-index: 111;
    box-shadow: 0 0 19px rgba(0, 0, 0, 0.253);
  }
  .add__search {
    display: flex;
    margin: 0 20px;
    position:relative;
}
.add__title {
    margin: 0;
    padding: 20px;
    background: var(--color1);
    color: white;
    border-radius: 10px 10px 0px 0px;
}
.add__search--icon {
    position: absolute;
    right: 10px;
    top: 50%;
    font-size: 20px;
    transform: translateY(-50%);
}
.add__search--input {
    width: 100%;
    padding: 20px;
    background: whitesmoke;
    outline: none;
    border: none;
    padding-right: 30px;
    border-radius: 10px;
}
.add__list {
    padding: 20px;
    height: 350px;
    overflow-y: scroll;
}
.add__list--item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    margin-top: 10px;
}
.list__content{
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px;
    font-size: 17px;
}

.list__content--img {
    width: 50px;
    height: 50px;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
    border-radius: 50%;
}
.add__setting--button,
.add__setting--joined {
    min-width: 70px;
    padding: 5px;
}
.add__setting--button {
    border: none;
    border-radius: 10px;
    border: 1px solid var(--color1);
    color: var(--color1);
    transition: 0.3s;
    background: transparent;
}
.add__setting--button:hover {
    background: var(--color1);
    color: white;
}

.add__setting--joined {
    opacity: 0.4;
    border: 1px solid var(--color1);
    color: var(--color1);
    border-radius:10px;
}
</style>