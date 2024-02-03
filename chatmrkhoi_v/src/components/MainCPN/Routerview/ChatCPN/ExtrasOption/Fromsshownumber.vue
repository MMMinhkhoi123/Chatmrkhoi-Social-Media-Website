<script>
import data from "../../../../../handle/Screen_chat/index";
import store from '../../../../../store';
import imgxs from "../../../../NewCPN/imgtake.vue";

import connects from "../../../../../socket/connect_private";
import connect_private from "../../../../../socket/handle_chat_private";
import NotifiCPn from "../../../../ComomCPN/Notification/Upload_warning.vue";
export default {
    props: {
        kinh: {
            typeof: Object,
            default : null,
        },
    },
    components: {
        imgxs,
        NotifiCPn,
    },
    setup(props) {
        store.dispatch("chat/get_groupid", Number(localStorage.getItem("iddetail")));
        const { data_profile, url, authen } = data();
        const king = data_profile(props.kinh.master);

        
        const kick = (idperson, rom) => {
            if(store.state.chat.array_groupid.length <= 3) {
                const data = {
                    img: 'alert.png',
                    title: 'KICH FAIL',
                    content: 'Group cannot have less than 3 people'
                }
                store.state.avaible_chat.Notification.data = data;
                store.state.avaible_chat.Notification.warning.upload_img = true;
            } else {
                var codenw = rom.split("&")[0] ;
            rom.split("&").forEach((e, index) => {
                if(e != idperson && index > 0) {
                    codenw = codenw + "&" + e;
                }
            })
            // update connect
            store.state.chat.array_connect.forEach((e, index) => {
                if(e.idgroup == Number(localStorage.getItem("iddetail"))) {
                    store.state.chat.array_connect[index].coderoom = codenw;
                }
            })
            connects();
            // update mess
            store.state.chat.array_all_mess.forEach((e, index) => {
                if(e.id_group == Number(localStorage.getItem("iddetail"))) {
                    store.state.chat.array_all_mess[index].room = codenw;
                }
            })

            // update groupid 
            store.state.chat.array_groupid = store.state.chat.array_groupid.filter((e) => e.id != idperson);
            store.state.chat.array_groupid.forEach((e, index) => {
                store.state.chat.array_groupid[index].coderoom = codenw;
            })
            const data = {
                idfriend: idperson,
                id: Number(localStorage.getItem("iddetail")),
                codenew: codenw,
            }
            store.state.chat.mess_after_kick = null;


            store.dispatch("chat/kick_person_group",data).then((e) => {
                const m = setInterval(() => {
                    if(store.state.chat.mess_after_kick!= null) {
                        clearInterval(m)
                        const data_reset = {
                            kick: authen.value.id,
                            code: codenw,
                            userkick: idperson,
                            code_old: localStorage.getItem("data-select"),
                            idgroup: Number(localStorage.getItem("iddetail")),
                            data: store.state.chat.mess_after_kick,
                        }
                      connect_private(data_reset ,rom);
                      localStorage.setItem("data-select", codenw)
                    }
                }, 1000)
            })

            }
        }

        return { data_profile,king, url, kick, authen }
    },
}
</script>
<template>
    <NotifiCPn></NotifiCPn>
    <div class="background" @click="$store.state.avaible_chat.open_number = false">
    </div>
    <div class="number">
        <h3 class="number__title"> {{ $t('TextMain.Chat.Screen.From.Member.Title') }}</h3>
        <h4 class="number__title--show">{{ $t('TextMain.Chat.Screen.From.Member.Content') }} {{ $store.state.chat.array_groupid.length + 1 }}</h4>
        <div class="number__list">
            <div class="number__list--item">
                <div class="list__left">
                    <img class="list__left--img"  v-if="king.type_img != 'rs'" 
                    :src="url + '/file/get-png/' + king.images" alt="">
                    <imgxs class="list__left--img"  :name="king.images" v-if="king.type_img == 'rs'"></imgxs>
                    <strong>{{ king.fullname }}</strong>
                </div>

                <div class="list__right">
                    <img class="list__right--icon" src="../../../../../assets/icon/crown.png" alt="">
                </div>
            </div>
            <div class="number__list--item" v-for="item in $store.state.chat.array_groupid" :key="item">
                <div class="list__left">
                    <img class="list__left--img"  v-if="item.type_img != 'rs'" :src="url + '/file/get-png/' + item.images">

                    <imgxs class="list__left--img"  :name="item.images" v-if="item.type_img == 'rs'"></imgxs>
                    <strong>{{ item.fullname }}</strong>
                </div>
                <div class="list__right" v-if="king.id == authen.id" >
                    <img class="list__right--icon" @click="kick(item.id, item.coderoom)" src="../../../../../assets/icon/kick.png" alt="">
                </div>
            </div>
       </div>
    </div>
</template>
<style scoped>
.number {
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


.number__title {
    background: var(--color1);
    padding: 20px;
    border-radius: 10px 10px 0 0;
    color: white;
    margin: 0;
}
.number__list {
    display: flex;
    flex-direction: column;
    height: 450px;
    overflow-y: scroll;
    gap: 10px;
    padding: 20px;
}
.number__title--show {
    margin: 0;
    padding: 10px;
    text-align: start;
}
.list__left {
    display: flex;
    align-items: center;
    gap: 10px;
}
.number__list--item {
    display: flex;
    justify-content: space-between;
}
.list__left--img {
    width: 50px;
    height: 50px;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
    border-radius: 50%;
}
.list__right--icon {
    width: 30px;
    height: 30px;
    transition: 0.3s;
    cursor: pointer;
}
.list__right--icon :hover {
    transform: scale(1.2);
}

</style>