<template>
    <div class="listreply"  :class="item.id_group != null && item.id_user != authen.id  ? 'file__left': ''">
        <div class="listreply__frame" @click="introview(item.reply, 'img')"
            v-if="item.reply != null && item.typereply == 'file'">
            <img class="listreply__frame--img" v-if="reply_mess_show(item.reply)[0].img[0].type == 'image'"  :src="url + '/file/get-png/' +  reply_mess_show(item.reply)[0].img[0].namefile" />
            
            <video class="listreply__frame--video" v-if="reply_mess_show(item.reply)[0].img[0].type == 'video'">
                <source :src="url + '/file/geturl-video/' + reply_mess_show(item.reply)[0].img[0].namefile" type="video/mp4">
            </video>

            <span class="listreply__frame--file" v-if="reply_mess_show(item.reply)[0].img[0].type == 'file'" > 
                <img class="frame__icon" src="../../../../../assets/icon/archive.png">
                <strong class="frame__text">{{ convertdata( reply_mess_show(item.reply)[0].img[0].namefile) }}</strong>
            </span>
        </div>

        <p @click="introview(item.reply, 'text')" v-if="item.reply != null && item.typereply == 'text'" class="reply_mess" 
        :class="reply_mess_show(item.reply)[0].id_user != authen.id ? 'hin' : ''" >
            {{ reply_mess_show(item.reply)[0].content}}
        </p>
</div>
</template>
<script>
import data from "../../../../../handle/Screen_chat/index";
export default {
    props: {
        item: {
            typeof: Object,
            default: null,
        }
    },
    setup() {
        const { introview,room, authen, reply_mess_show,convertdata,url } = data();
        return { introview,room,authen, reply_mess_show,convertdata,url }
    },
    created() {
        this.room = this.$route.query.rom;
    },
    updated() {
    this.room = this.$route.query.rom;
    },
}
</script>
<style scoped>
.file__left {
    margin-left: 50px;
}
.row__left {
    justify-items: start;
}
 .listreply {
    display: flex;
    justify-content: end;
    max-width: 400px;
    word-break: break-all;
}

.listreply__frame--img,

.listreply__frame--video  {
    width: 150px;
    height: 150px;
    object-fit: cover;
    border-radius: 10px;
}
.listreply__frame {
    object-fit: cover;
    opacity: 0.6;
    transform: translateY(10px);
    max-height: 150px;
    cursor: pointer;
}


.listreply__frame--file {
    background: var(--color1);
    color: white;
    display: block;
    padding: 5px;
    display: flex;
    position: relative;
    align-items: center;
    gap: 10px;
    font-size: 14px;
    border-radius: 10px;
    box-sizing: border-box;
    width: 100%;
    padding-right: 30px;
}

.frame__icon {
    width: 30px;
    height: 30px;
}

.hin {
    background: white !important;
    color: #000 !important;
}
.reply_mess {
    opacity: 0.6;
    border-radius:10px;
    background: #4F48ED;
    color: white;
    padding: 10px;
    transform: translateY(10px);
    margin: 0;
}


</style>