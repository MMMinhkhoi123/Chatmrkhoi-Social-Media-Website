<script setup>
import imgx from "../../../NewCPN/imgtake.vue";
import handle from "../../../../handle/Chat/index";
import store from "../../../../store";
import other from "../../../../handle/Common";
import datas from "../../../../handle/Screen_chat/index";
import { computed, ref } from "vue";

const { data_profile } = datas();
const {
    check_online,
        data,
        get_info_group,
        get_info_friend,
        url,
        move,
        addgroup,
        namedata,
        convert_online,
        namesearch,
} = handle();

const { authen } = other();

function array_mess_count(code) {
   const x = computed(() => {
        const m = store.getters["chat/get_data_room_vitral"](code);
        const m1 = m.filter((e) => e.pin == null && e.group_status == null && e.feel == null);
        const xs = m1.filter((e) => e.id_user != authen.value.id && (e.listwatch.length  == 0 || e.listwatch  ==  null )  );
        if(store.state.avaible_chat.open_change_array) {}
        if(xs.length == 0) {
            return 0
        }
        return xs.length;
    })
 return x.value;
}
function getname(code) {
    const x = computed(() => {
        const m = store.getters["chat/get_data_room_vitral"](code);
        if(m.length == 0) {
            return null;
        }
        const data = m[m.length - 1];
        if(get_info_friend(data.id_user).length == 0) {
            return "";
        } 
        const name = convertname(get_info_friend(data.id_user)[0].fullname);
        return name;
    })
 return x.value;
}


function getnamekick(code) {
    const x = computed(() => {
        const m = store.getters["chat/get_data_room_vitral"](code);
        if(m.length == 0) {
            return null;
        }
        const data = m[m.length - 1];
        const name = convertname(get_info_friend(Number(data.group_status.split("&")[1]))[0].fullname);
        return name;
    })
 return x.value;
}

function getfeel(code) {
    const x = computed(() => {
        const m = store.getters["chat/get_data_room_vitral"](code);
        if(m.length == 0) {
            return null;
        }
        const data = m[m.length - 1];
        if(data.feel != null) {
            return data.feel;
        }
        return null;
    })
 return x.value;
}



function array_mess(code) {
   const x = computed(() => {
        const m = store.getters["chat/get_data_room_vitral"](code);
        const ma = m.filter((e) => (e.listwatch == null || e.listwatch.length == 0));
        const data = ma[ma.length - 1];

        if(data != null && data != undefined) {
            if(data.listrevoke != null && data.id_user != authen.value.id) {
                if(checkinclurevoke(data.listrevoke) == false) {
                    return  "revoke";
                }
            }
            if(data.pin != null && data.id_user != authen.value.id) {
                if(convertpin(data.pin) == 'ping' ) {
                    return  "ping";
                }
                 else {
                   return "unping";
                }
            }
            if(data.group_status != null && data.id_user != authen.value.id) {
                if(data.group_status.split("&")[0] == "kich") {
                    return "kick";
                } else if(data.group_status.split("&")[0] == "out" ) {
                    return "out";
                }
                else {
                    return "add"
                }
            }
        }
        const m1 = m.filter((e) => e.pin == null && e.group_status == null);
        const xs = m1.filter((e) => e.id_user != authen.value.id && e.listwatch.length  == 0 );
        if(store.state.avaible_chat.open_change_array) {}
        if(xs.length == 0) {
            return null
        } 
        if(data.content == '' && data.img) {
            return "sendfile";
        }
        return xs[xs.length - 1].content;
    })
 return x.value;
}




function checkinclurevoke(list) {
    var check = true;
    list.forEach(element => {
        if(element.type == 'all') {
            check = false;
        }
    });
    return check;
}


function convertname(name) {
    const x = name.split(" ");
    if(x.length == 1) {
        return x[0];
    } else {
        return x[x.length - 1]
    }
}

function convertpin(name) {
    return name.split("&")[0];
}


function array_mess_old(code) {
   const x = computed(() => {
        const m = store.getters["chat/get_data_room_vitral"](code);
        if(m.length == 0) {
            return null;
        }
        const data = m[m.length - 1];
        if(data.listrevoke != null) {
            if (checkinclurevoke(data.listrevoke) == false) {
               return "revoke"
            } 
        }
        if(data.pin != null) {
            if(convertpin(data.pin) == 'ping' ) {
                return "ping";
            } else {  
                return "unping";
            }
        }
        if(data.feel != null) {
            return "feel";
        }
        if(data.content == '' && data.img.length > 0) {
            return "sendfile";
        }
        if(data.group_status != null) {
            if(data.group_status.split("&")[0] == "kich") {
                return "kick";
            } else if(data.group_status.split("&")[0] == "out" ) {
                    return "out";
                }
             else {
                return "add"
            }
        }
        return m[m.length - 1].content;
    })
 return x.value;
}

function check_typing(code) {
         const g = computed(() => {
          var x = false;
          store.state.chat.data_typing.forEach((e) => {
            if (
              e.room == code &&
              e.istyping == true
            ) {
              x = true;
            }
          });
          return x;
        });
        return g.value;
      }
</script>

<template>
    <!-- filter top -->
    <div class="filter">
        <form class="filter__form">
            <button class="filter__form--submit">
                <font-awesome-icon class="form__icon" :icon="['fas', 'magnifying-glass']" />
            </button>
            <input class="form__input" v-model="namesearch" type="text" :placeholder="$t('TextMain.Chat.Menu.Textsearch')">
        </form>
        <span class="filter__group" 
        @click="addgroup" >
            <img loading="lazy" class="filter__group--img" src="../../../../assets/icon/add-group.png" alt="">
        </span>
    </div>

  <!-- list friend -->
    <div class="list" id="list">
        <div class="list__loading" v-if="data.length == 0">
            <div class="list__loading--item" v-for="item in 10" :key="item">
                <div class="loading__avata skeleton">
                </div>
                <div class="loading__content">
                    <div class="loading__content--top skeleton">
                    </div>
                    <div class="loading__content--bottom skeleton">
                    </div>
                </div>
            </div>
        </div>
        <div class="list__item" :id="'list_item' + item.coderoom" v-for="item in data" :key="item" @click="move(item)">
            <div class="list__item--left">
                <div class="item__avata" >
                    <div  class="item__avata--img" v-if="get_info_friend(item.idfriend).length > 0 && item.idgroup == null && $store.state.everyone.array_friend && $store.state.everyone.array_not_friend">
                        <imgx class="avata__img" v-if="get_info_friend(item.idfriend)[0].type_img == 'rs'" :name="get_info_friend(item.idfriend)[0].images"></imgx>
                        <img  class="avata__img" loading="lazy" v-if="get_info_friend(item.idfriend)[0].type_img != 'rs'"  :src=" url + '/file/get-png/' + get_info_friend(item.idfriend)[0].images">
                    </div>
                    <span class="item__avata--action" v-if="get_info_friend(item.idfriend).length > 0 && item.idgroup == null && $store.state.everyone.array_friend && $store.state.everyone.array_not_friend">
                        <span class="avata__status" v-if="check_online(get_info_friend(item.idfriend)[0].id)  == true" ></span>
                    </span>
                    
                    <div  class="item__avata--img" v-if="$store.state.chat.array_mygroup && get_info_group(item.idgroup)">
                        <img class="avata__img" loading="lazy"  v-if="item.idgroup != null" :src=" url + '/file/get-png/' +  get_info_group(item.idgroup)[0].img" alt="">
                    </div>
                </div>
                <div class="item__name" v-if="$store.state.chat.array_mygroup && $store.state.everyone.array_friend && $store.state.everyone.array_not_friend">
                    <strong class="item__name--text">
                        <span class="item__name--texts">
                            <strong v-if="get_info_friend(item.idfriend).length > 0 && item.idgroup == null">
                                {{ get_info_friend(item.idfriend)[0].fullname }}
                            </strong>
                            <strong v-if="get_info_group(item.idgroup).length > 0 && item.idgroup != null">
                                {{ get_info_group(item.idgroup)[0].name }}
                            </strong>
                        </span>
                    </strong>
                    <strong class="item__name--new" >
                        {{  array_mess(item.coderoom) !=  null
                                ?    array_mess(item.coderoom) == "revoke" ?  $t("TextMain.Chat.Menu.Remove")
                                    :  array_mess(item.coderoom) == "ping" ? $t("TextMain.Chat.Menu.Ping", { user: getname(item.coderoom) }) 
                                    :  array_mess(item.coderoom) == "unping" ? $t("TextMain.Chat.Menu.UnPing", { user: getname(item.coderoom) }) 
                                    :  array_mess(item.coderoom) == "sendfile" ? $t("TextMain.Chat.Menu.SendFile", { user: getname(item.coderoom) })
                                    :  array_mess(item.coderoom) == "feel" ? $t("TextMain.Chat.Menu.YourSendFeel", { user: getname(item.coderoom), feel: getfeel(item.coderoom) }) 
                                    :  array_mess(item.coderoom) == "kick" ? $t("TextMain.Chat.Menu.KickPerson", { user: getname(item.coderoom), usernew: getnamekick(item.coderoom)})
                                    :  array_mess(item.coderoom) == "add" ?  $t("TextMain.Chat.Menu.AddPerson", { user: getname(item.coderoom), usernew: getnamekick(item.coderoom)}) 
                                    :  array_mess(item.coderoom) == "out" ? $t("TextMain.Chat.Menu.UserOut", { user: getname(item.coderoom) })
                                    :  array_mess(item.coderoom)
                                : null
                        }}  
                    </strong>
                
                    <strong class="item__name--old" v-if="array_mess(item.coderoom) == null" >
                        {{ array_mess_old(item.coderoom) == "feel"
                            ? $t("TextMain.Chat.Menu.YourSendFeel", { user: getname(item.coderoom), feel: getfeel(item.coderoom) }) 
                            : array_mess_old(item.coderoom) == "revoke" ? $t("TextMain.Chat.Menu.Remove")
                            : array_mess_old(item.coderoom) == "ping" ?  $t("TextMain.Chat.Menu.Ping", { user: getname(item.coderoom) }) 
                            : array_mess_old(item.coderoom) == "unping" ?  $t("TextMain.Chat.Menu.UnPing", { user: getname(item.coderoom) })
                            : array_mess_old(item.coderoom)  == "sendfile" ?   $t("TextMain.Chat.Menu.SendFile", { user: getname(item.coderoom) }) 
                            : array_mess_old(item.coderoom) == "kick" ? $t("TextMain.Chat.Menu.KickPerson", { user: getname(item.coderoom), usernew: getnamekick(item.coderoom)})
                            : array_mess_old(item.coderoom) == "add" ?  $t("TextMain.Chat.Menu.AddPerson", { user: getname(item.coderoom), usernew: getnamekick(item.coderoom)}) 
                            : array_mess_old(item.coderoom) == "out" ? $t("TextMain.Chat.Menu.UserOut", { user: getname(item.coderoom) })
                            : array_mess_old(item.coderoom)
                        }}
                    </strong>
                    <strong class="item__name--news" :class="check_typing(item.coderoom) == true ? 'show' : ''">
                        <div class="action">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                    </strong>
                </div>
            </div>
            <div class="list__item--right">
                <span class="item__count" v-if=" array_mess_count(item.coderoom) > 0">{{ array_mess_count(item.coderoom) }}</span>
            </div>
        </div>
    </div>
</template>
<style scoped>
  .item__name--new,
  .item__name--old {
    animation: show 2s ease forwards; 
  }
.action {
    display: flex;
    gap: 5px;
    margin-left: 10px;
  }
  @keyframes show {
    0% {
     opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }

  @keyframes animations {
    0% {
      transform: translateY(0);
    }
    70% {
      transform: translateY(1px);
      background: rgb(140, 138, 138);
    }
    100% {
      transform: translateY(0);
    }
  }
.action > span {
    background: #4f48ed;
    animation: animations 2s ease infinite;
    display: block;
    border-radius: 50%;
    width: 5px;
    height: 5px;
  }

  .item__name--news {
    opacity: 0;
    transition: 2s !important;
    position: absolute;
    top: 10px;
    right: 10px;
}
  .show {
    opacity: 1 !important;
    transition: 0.1s !important;
  }

.loading__content {
    display: flex;
    flex-direction: column;
    gap: 10px;
}
.loading__content--top {
    height: 20px;
    width: 80px;
    overflow: hidden;
    border-radius: 20px;
}
.loading__content--bottom {
    height: 20px;
    width: 170px;
    overflow: hidden;
    border-radius: 20px;
}
.list__loading--item {
    padding: 5px;
    border-radius: 10px;
    margin-bottom: 5px;
    background: white;
    display: flex;
    gap: 10px;
}
.loading__avata {
    width: 50px;
    height: 50px;
    overflow: hidden;
    border-radius: 50%;
}
.item__name--texts {
    align-items: start !important;
    margin-bottom: 5px;
}
.item__name--new,
.item__name--old {
    width: 200px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
/* filter */
.filter {
    padding: 10px;
    padding-bottom: 0;
    display: grid;
    gap: 10px;
    grid-template-columns: 1fr 80px;
    margin-bottom: 10px;
}
.filter__form {
    position: relative;
    display: flex;
    border-radius: 5px;
    overflow: hidden;
}
.filter__form--submit {
 border: none;
 background: transparent;
 position: absolute;
    left: 0;
    top: 50%;
    transform: translateY(-50%);
}
.form__icon {
    font-size: 20px;
}
.form__input {
    width: 100%;
    padding: 17px 10px;
    padding-left: 35px;
    background: #FFF;
    border: none;
    color: #000;
    outline: none;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
}

.filter__group {
    border-radius: 5px;
    display:flex;
    align-items: center;
    justify-content: center;
}
.filter__group--img {
    width: 30px;
    height: 30px;
    object-fit: contain;
}


/* menu  */
.list {
    height: 610px;
    overflow-y: scroll;
    padding: 10px;
    transition: 0.4s !important;
    box-sizing: border-box;
}
.list__item {
    cursor: pointer;
    padding: 5px;
    border-radius: 10px;
    background: #FFF;
    position: relative;
    display: flex;
    margin-bottom: 5px;
    justify-content: space-between;
    transition: 0.3s !important;
    box-shadow: rgba(17, 12, 46, 0.15) 0px 48px 100px 0px;
}
.list__item:hover {
    transform: translateY(-5px);
}
.list__item--left {
    display: flex;
    align-items: center;
    gap: 18px;
 }
 .list__item--right {
    display: flex;
    justify-content: center;
    align-items: center;
 }
 .item__count {
    width: 20px;
    height: 20px;
    background: var(--color1);
    border-radius: 50%;
    color: white;
    font-weight: 700;
 }
 .item__avata {
    position: relative;
 }
 .avata__img {
    width: 50px;
    height: 50px;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
    border-radius: 50%;
 }
 span {
    display: flex;
    background: #FFF;
    color: #000;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
}
 .avata__status {
    width: 12px;
    height: 12px;  
    background: #1CB73E;
    border: 1px solid rgb(221, 215, 215);
    position: absolute;
    bottom: 6px;
    right: 0;
    z-index: 11;
    border-radius: 50%;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
 }

 .item__name {
    display: flex;
    flex-direction: column;
    font-size: 15px;
 }
 .item__name--text {
    color: #000;
    font-style: normal;
    line-height: normal;
 }
 .item__name--old {
    font-weight: 200;
    color: rgb(137, 135, 135);
 }
 .item__name--new {
    font-weight: 700;
 } 
.item__count {
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>