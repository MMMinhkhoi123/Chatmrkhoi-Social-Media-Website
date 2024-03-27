<script setup>
    import { computed, reactive, ref} from 'vue';
    import stores from "../../../../handle/Screen_chat/index";
    import store from '../../../../store';
    import addperson from "../ChatCPN/ExtrasOption/Formaddperson.vue";
    import fromedit  from "../ChatCPN/ExtrasOption/Fromeditgroup.vue";
    import numbers from "../ChatCPN/ExtrasOption/Fromsshownumber.vue";
    import other from '../../../../handle/Common/index';
    import { useRoute, useRouter } from 'vue-router';
    const select = ref(1);      
    const { get_info_group, deletegroup, profile_friend, convertdata, outgroup, data_profile } = stores();
    const { authen, url_img, url_img_rs, url_video } = other();
    const router = useRouter();
    const route = useRoute();
    store.dispatch("chat/get_data_detail", localStorage.getItem("data-select"));

    const data = computed(() => {
        if(store.state.chat.data_detail == null) {
            return [];
        } 
        if(select.value == 2) {
            return store.state.chat.data_detail.list_video;
        }
        if(select.value == 3) {
            return store.state.chat.data_detail.list_file;
        }
        return store.state.chat.data_detail.list_img;
    })
    const selectzoom = (value, type) => {     
        localStorage.setItem("idfile", value);
        router.push({ query: { id: route.query.id, mess_media: type, idfile: value } })
    }

    function GoProfile() {
        router.push({ name: 'profile', query: { id: profile_friend(route.query.id)[0].id } })
    }

    function ViewAvata(type) {
        type == 'group' 
        ? router.push({ query: {  action: 'my', codeimg: get_info_group(route.query.idgroup)[0].img, type: 'cpt'  } })
        : router.push({ query: {  action: 'my', codeimg: data_profile(route.query.id).images, type: data_profile(route.query.id).type_img  } })
    } 

    function condition_avata(type) {
        if(data_profile(route.query.id) == null) {
            return null;
        }
       return data_profile(route.query.id).type_img == type ? true : false;
    }

    function opengroup() {
        const data = {
        img: 'delete.png',
        title: 'Notification.DeleteObj.Title',
        content: 'Notification.DeleteObj.Content'
    }
    store.state.avaible_chat.Notification.data = data;
    store.state.avaible_chat.Notification.warning.delete_group = true;
    }
</script>
<template>
    <div v-if="$store.state.chat.array_mygroup" class="detail">
        <div class="detail__frame">
            <div  class="detail__frame--top" id="detail__frame--top">
                <div  class="frame__avata"  
                 v-if="$route.query.id != null &&  $store.state.everyone.array_send_request_friend != null">
                    <img class="frame__avata--img" @click.self="ViewAvata('person')" v-if="condition_avata('rs')" :src="url_img_rs + data_profile($route.query.id).images">
                    <img class="frame__avata--img"  @click.self="ViewAvata('person')" v-if="condition_avata('cpt')" :src="url_img + profile_friend($route.query.id)[0].images">
                    <strong>{{ data_profile($route.query.id) != null ? data_profile($route.query.id).fullname : null }}</strong>
                    <div  @click="GoProfile()" class="frame__profile">
                        <img class="frame__profile--icon" src="../../../../assets/icon/investigation.png">
                        <strong>
                            {{ $t('TextMain.Group.Detail.Viewprofile') }}
                        </strong>
                    </div>
                </div>
                <div class="frame__avata" @click="ViewAvata('group')" v-if="$route.query.idgroup && get_info_group($route.query.idgroup).length > 0">
                    <img  class="frame__avata--img" :src="url_img + get_info_group($route.query.idgroup)[0].img " >
                    <strong>{{ get_info_group($route.query.idgroup)[0].name }}</strong>
                </div>
            </div>

            <div   class="detail__frame--mid" id="detail__frame--mid" v-if="$route.query.idgroup && get_info_group($route.query.idgroup).length > 0">
                <div class="frame__item">
                    <h3>
                        {{ $t('TextMain.Group.Detail.ItemSetting') }}
                    </h3>
                    <span class="frame__item--text" @click="$store.state.avaible_chat.open_editinfo = true">
                        {{ $t('TextMain.Group.Detail.ItemCustomizeinformation') }}
                    </span>
                    <span  class="frame__item--text" @click="$store.state.avaible_chat.open_addperson = true">
                        {{ $t('TextMain.Group.Detail.ItemNewUser') }}
                    </span>
                </div>
                <div class="frame__item">
                    <h3>
                        {{ $t('TextMain.Group.Detail.ItemDeails') }}
                    </h3>
                    <span class="frame__item--text" @click="$store.state.avaible_chat.open_number = true">
                        {{ $t('TextMain.Group.Detail.ItemMember') }}
                    </span>
                </div>
                <div class="frame__item" v-if="get_info_group($route.query.idgroup).length > 0">
                    <h3>
                        {{ $t('TextMain.Group.Detail.ItemAdvanted') }}
                    </h3>
                    <span  class="frame__item--text" v-if="get_info_group($route.query.idgroup)[0].master == authen.id" @click="opengroup">
                        {{ $t('TextMain.Group.Detail.ItemRemove') }}
                    </span>
                    <span class="frame__item--text" v-if="get_info_group($route.query.idgroup)[0].master != authen.id" @click="outgroup">
                        {{ $t('TextMain.Group.Detail.ItemLeave') }}
                    </span>
                </div>
            </div>
        </div>

        <div class="detail__frame--bottom" id="detail__frame--bottom" v-if="$route.query.idgroup != null || $route.query.id != null">
            <div class="frame__select" id="frame__select">
            <div class="frame__select--form">
                <div class="select__text" @click="select = 1" :class="select == 1 ? 'chose': ''">
                    {{ $t('TextMain.Group.Detail.ListImg') }}
                </div>
                <div  class="select__text" @click="select = 2"  :class="select == 2 ? 'chose': ''">
                    {{ $t('TextMain.Group.Detail.ListVideo') }}
                </div>
                <div  class="select__text" @click="select = 3"  :class="select == 3 ? 'chose': ''">
                    {{ $t('TextMain.Group.Detail.ListFile') }}
                </div>
            </div>
            </div>

            <div class="frame__list" id="frame__list">
                <div class="frame__list--img  frame__list--item" v-if="select == 1">
                    <img class="list__img" v-for="item in data" :key="item" :src="url_img + item.name" @click="selectzoom(item.id, 'img')">
                </div>
                <div class="frame__list--video  frame__list--item" v-if="select == 2">
                    <video class="list__video"  v-for="item in data" :key="item" @click="selectzoom(item.id, 'video')">
                            <source :src="url_video + item.name" type="video/mp4">
                    </video>
                </div>
                <div class="frame__list--file  frame__list--item" v-if="select == 3">         
                    <a class="list__file" v-for="item in data" :key="item" :href="'http://localhost:8081/file/filedowload/' + item.name ">
                        <img class="list__file--icon" src="../../../../assets/icon/archive.png">
                        <strong>{{ convertdata(item.name) }}</strong>
                    </a>
                </div>

                <img  class="frame__list--empty"  v-if="data.length == 0" src="../../../../assets/icon/folder.png">
            </div>

        </div>
    
    <fromedit v-if="$store.state.avaible_chat.open_editinfo == true" :name ="get_info_group($route.query.idgroup)[0].name"  :img="get_info_group($route.query.idgroup)[0].img"></fromedit>

    </div>
    <numbers v-if="$store.state.avaible_chat.open_number == true" :kinh="get_info_group($route.query.idgroup)[0]"></numbers>
    <addperson v-if="$store.state.avaible_chat.open_addperson == true"></addperson>
    
</template>
<script>
export default {
    updated() {
        const bootom = document.getElementById("detail__frame--bottom");
        const top = document.getElementById("detail__frame--top");
        const mid = document.getElementById("detail__frame--mid");
        const div = document.getElementById("frame__list");
        const parent = document.getElementById("frame__select");
        if(mid != null) {
            bootom.style.height = "calc(100% - "+ (top.offsetHeight + mid.offsetHeight)+"px)";
        } else {
            bootom.style.height = "calc(100% - "+ top.offsetHeight+"px)";    
        }
        div.style.height = "calc(100% - "+ parent.offsetHeight+"px)";
    },
}
</script>
<style scoped>
.detail {
    width: 100%;
    height: 100%;
    border-radius: 5px;
    background: var(--coloRegular);
    overflow: hidden;
    position: relative;
}
.detail__frame--top {
    padding: 10px;
}
.frame__avata {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 13px;
}
.frame__avata--img {
    width: 100px;
    height: 100px;
    object-fit: contain;
    border-radius: 50%;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}
.frame__profile {
    display: flex;
    align-items: center;
    gap: 10px;
    background:var(--color1);
    padding: 5px 10px;
    border-radius: 20px;
    cursor: pointer;
}
.frame__profile--icon {
    width: 30px;
    height: 30px;
}

.detail__frame--mid {
    padding: 10px;
    display: grid;
    grid-template-columns: repeat(3, 1fr);
}
.frame__item {
    display: flex;
    flex-direction: column;
    align-items: center;
}
.frame__item--text{
    padding: 10px;
    font-weight: 700;
    cursor: pointer;
    color: gray;
}
.frame__item--text:hover {
    color: var(--color1);
}
.detail__frame--bottom {
    border-radius: 10px;
    position: relative;
    background: var(--colorBehindLess);
}
.frame__select {
    background: var(--colorBehindLess);
    display: flex;
    justify-content: center;
} 
.frame__select--form {
    background: var(--colorMenu);
    border-radius: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
}
.select__text {
    padding: 10px;
    width: 100px;
    text-align: center;
    border-radius: 20px;
}
.select__text:hover {
    background: var(--color2);
    color: white;
}
.frame__list {
    position: relative;
}

.frame__list--item {
    max-height: 420px;
    overflow-y: scroll;
}
.frame__list--item {
    display: grid;
    gap: 2px;
    grid-template-columns: repeat(5, 1fr);
}

.frame__list--video > .list__video ,
.frame__list--img > .list__img,
.frame__list--file > .list__file {
    width: 100%;
}
 .list__img,
.list__video {
    height: 150px;
    object-fit: cover;
}
.list__file {
    text-decoration: none;
    background: var(--color1);
    color: white;
    display: flex;
    align-items: center;
    border-radius: 10px;
    padding: 5px;
    gap: 10px;
    font-size: 14px;
    box-sizing: border-box;
}
.list__file--icon {
    width: 30px;
    height: 30px;
}

.frame__list--empty {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 130px;
    height: 130px;
}
.chose {
    background: var(--color2);
    color: white;
}
</style>