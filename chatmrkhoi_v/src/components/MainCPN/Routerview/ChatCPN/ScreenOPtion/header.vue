<template>
        <div class="top" id="header">
            <div class="left">
                <div v-if="$route.query.id" class="option_info">
                    <div class="img">
                        <img class="avata" v-if="profile_friend($route.query.id)[0].type_img != 'rs'" :src="url + '/file/get-png/' + profile_friend($route.query.id)[0].images">
                        <imgx  class="avata" v-if="profile_friend($route.query.id)[0].type_img == 'rs'" :name="profile_friend($route.query.id)[0].images" ></imgx>
                        <span v-if="$route.query.idgroup == null">
                            <span v-if="check_online(profile_friend($route.query.id)[0].id) == true" class="status"></span>
                        </span>
                    </div>
                    <div class="option">
                        <span class="name" >{{ profile_friend($route.query.id)[0].fullname }}</span>
                        <span  class="action" v-if="convertTimeOffline($route.query.id) != null"> {{ check_online($route.query.id) == true 
                        ? $t("TextMain.Chat.Screen.Header.Online") 
                        : $store.state.avaible_chat.status_time == 'second' ? $t("TextMain.Chat.Screen.Header.OfflineSecond", { time: convertTimeOffline($route.query.id) })
                        : $store.state.avaible_chat.status_time == 'minu' ? $t("TextMain.Chat.Screen.Header.OfflineMiNu", { time: convertTimeOffline($route.query.id) }) 
                        : $store.state.avaible_chat.status_time == 'hour' ? convertTimeOffline($route.query.id) > 24 ? null : $t("TextMain.Chat.Screen.Header.OfflineHour", { time: convertTimeOffline($route.query.id) }) 
                        : $store.state.avaible_chat.status_time }}
                        </span>
                    </div>
                    
                </div>
                <div v-if="$route.query.idgroup && $store.state.chat.array_mygroup" class="option_info">
                    <img class="avata" :src="url + '/file/get-png/' + ( get_info_group($route.query.idgroup).length == 0 ? null : get_info_group($route.query.idgroup)[0].img)">
                    <span class="name"> {{ ( get_info_group($route.query.idgroup).length == 0 ? null : get_info_group($route.query.idgroup)[0].name) }}</span>
                </div>
            </div>
            <div @click="showdetail" class="right">
                <font-awesome-icon :icon="['fas', 'ellipsis']" />
            </div>
        </div>
</template>
<script>
import data from "../../../../../handle/Screen_chat/index";
import imgx from "../../../../../components/NewCPN/imgtake.vue";
import store from '../../../../../store';
import { computed, ref } from 'vue';
export default {
    components: {
        imgx,
    },
    setup() {
        const { profile_friend , get_info_group, url,showdetail } = data();
        const check_online = (id) => {
                    var check = false;
                    store.state.chat.data_online.forEach((e) => {
                    if(e.id == id && e.status == 'online') {
                        check = true;
                    }
                    });
            return check;
        }
        
        const convertTimeOffline = (id) => {
            const x = computed(() => {
                if(store.state.chat.data_online) {}
                const date = new Date();
                const dataGet = store.state.chat.data_online.filter((e) => e.id == id)
                if(dataGet.length == 0) {
                    return null
                
                }
                const hour = (date.getTime() - dataGet[0].timetamp) / (60 * 60 * 1000);
                const mute = (hour - Math.floor(hour)) * 60;
                const second = (mute - Math.floor(mute)) * 60;
                
                if( Math.floor(hour) == 0 &&  Math.floor(mute) == 0  ) {
                    store.state.avaible_chat.status_time = "second";
                    return  Math.floor(second);
                }
                if( Math.floor(hour) == 0 &&  Math.floor(mute) != 0) {
                    store.state.avaible_chat.status_time = "minu";
                    return  Math.floor(mute);
                }
                store.state.avaible_chat.status_time =  "hour"
                return Math.floor(hour);
            })
            return x.value;
        } 



        return { 
            showdetail,profile_friend, get_info_group, url,check_online,convertTimeOffline
        }
    },
    mounted() {
        if(this.$route.query.id != null) {
            this.convertTimeOffline(this.profile_friend(this.$route.query.id)[0].id);
        } 
    }
}
</script>
<style scoped>
.status {
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
.img {
    position: relative;
}
.action {
    font-size: 13px !important;
}
.option{
    display: flex;
    flex-direction: column;
    gap: 5px;
}
.right {
    display: flex;
    gap: 13px;
    cursor: pointer;
}
.right > p {
    color: #000;
font-style: normal;
font-weight: 500;
line-height: normal;
}
.right > span {
width: 20px;
height: 20px;
background: rgba(28, 183, 62, 1);
border-radius: 50%;
}
.name {
    color: #000;
    font-weight: 700;
    font-style: normal;
    line-height: normal;
}

.avata {
    width: 45px;
    height: 45px;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
    border-radius: 50%;
    }
.top {
    padding: 10px;
    position: relative;
    background: #FFF;
    display: flex;
    z-index: 0;
    justify-content: space-between;
    align-items: center;
    border-radius: 10px 10px 0 0;
}
.option_info {
    height: 50px;
    display: flex;
    gap: 10px;
    align-items: center;
    font-size: 15px;
}
</style>