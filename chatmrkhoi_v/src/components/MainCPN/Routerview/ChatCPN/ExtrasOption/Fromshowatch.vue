<template>
    <div @click="$store.state.avaible_chat.open_watch = false" class="bgwhite">
    </div>
    <div class="watch">
        <h3 class="watch__title">
            {{ $t('TextMain.Chat.Screen.From.watch.title') }}
        </h3>
        <div class="watch__list">
            <div class="parent" v-for="item in array()" :key="item" >
                <div class="left">
                    <div class="img">
                        <img v-if="data_profile(item.id).type_img != 'rs'" class="img" id="iconx" :src="url + '/file/get-png/' + data_profile(item.id).images" alt="">
                        <img  v-if="data_profile(item.id).type_img == 'rs'"  class="img" loading="lazy" :src="'/src/assets/images/avata_org/' + data_profile(item.id).images">
                    </div>
                    <div class="name">
                        <strong>{{  data_profile(item.id).fullname}}</strong>
                    </div>
                </div>
                <div class="right">
                    <strong>
                        {{ convertimewatch(item, $t('TextMain.Chat.Screen.From.watch.text')) }}
                    </strong>
                </div>
        </div>
        </div>
    </div>
</template>
<script>
import store from '../../../../../store'
import data from "../../../../../handle/Screen_chat/index";
export default {
    setup() {
        const  { authen, url, data_profile } = data();
        const array = () => {
            return store.state.chat.array_all_mess.filter((e) => e.id == Number(localStorage.getItem("messwatch")))[0].listwatch
        }
        const convertimewatch = (item, key) => {  
        const date = new Date(item.timetamp);
        return  key + date.getHours() + ":" + date.getMinutes()
    }
        return {
            convertimewatch,
            array,
            authen,
            url,
            data_profile
        }
    },
}
</script>
<style scoped>
.watch {
    position: fixed;
    text-align: center;
    border-radius: 10px;
    min-width: 450px;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    z-index: 11;
    background: white;
    overflow: hidden;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}
.watch__title {
    margin: 0;
    color: white;
    background: var(--color1);
    padding: 15px;
}
.watch__list {
    padding: 10px;
    height: 400px;
    overflow-y: scroll;
}




.parent {
    display: flex;
    justify-content: space-between;
}
.right {
    display: flex;
    align-items: center;
    font-size: 13px;
}
.left {
    display: flex;
    align-items: center;
    gap: 10px;
}
.left > div {
    margin-bottom: 10px;
}
.img {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}
.bgwhite  {
    position: fixed;
    inset: 0;
    background: rgba(255, 255, 255, 0.552);
    z-index: 11;
}

</style>