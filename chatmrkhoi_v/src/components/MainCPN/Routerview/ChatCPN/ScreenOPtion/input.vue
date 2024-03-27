<template>
    <form  class="form" @submit.prevent="send">
        <div class="form__upload">
            <div class="form__upload--item" v-for="(item, index) in  $store.state.chat.array_img" :key="item"  :style="$store.state.chat.array_img.length > 0 ? { padding: '10px' } : {}">
                <div class="upload__img"  v-if="item.type == 'image'">
                    <font-awesome-icon class="upload__close" :icon="['fas', 'xmark']" @click="close(index)" />
                    <img class="upload__img--image"  :src="url + '/file/get-png/' + item.namefile">
                </div>
                
                <span class="upload__file" v-if="item.type == 'file'"> 
                    <font-awesome-icon class="upload__close" :icon="['fas', 'xmark']" @click="close(index)" />
                    <img class="upload__file--icon" src="../../../../../assets/icon/archive.png" alt="">
                    <div class="upload__file--text">
                        <p class="file__name">{{ item.namefile }}</p>
                    </div>
                </span>

                <div class="upload__video" v-if="item.type == 'video' && $store.state.chat.array_img.length > 0">
                    <font-awesome-icon class="upload__close" :icon="['fas', 'xmark']" @click="close(index)" />
                    <video class="upload__video" >
                        <source :src="url + '/file/geturl-video/' +  item.namefile" type="video/mp4">
                    </video>
                </div>
            </div>
        </div>
        <div  class="form__rep" v-if="$store.state.chat.data_rep != null">
            <div class = "form__rep--close"  @click="outrep()" >
                <font-awesome-icon :icon="['fas', 'xmark']" />
            </div>
            <strong v-if="$store.state.everyone.array_friend && $store.state.everyone.array_not_friend" >
                {{ $store.state.chat.data_rep[0].id_user == authen.id 
                ? $t('TextMain.Chat.Screen.Mess.Myreply') : $t('TextMain.Chat.Screen.Mess.Myreply', {user: profile_friend($store.state.chat.data_rep[0].id_user)[0].fullname }) }}
            </strong>
            <span>
                <p>{{ $store.state.chat.data_rep[0].type == 'file'  ? $t('TextMain.Chat.Screen.Mess.Replyfile') : $store.state.chat.data_rep[0].content }}</p>
            </span>
        </div> 

        <div class="form__input">
            <input class="form__input--enter" v-model="$store.state.chat.data_input" @keyup="opentyping" @keydown="closetyping" type="text" id="ipmain'"  :placeholder="$t('TextMain.Chat.Screen.Mess.input')"/>  
            <div class="input__emoji">
                <div  class="input__emoji--move" v-if="open_emoji ==  true"  @mouseleave="open_emoji = false">
                    <emoji style="width: 300px; height: 300px;"></emoji>
                </div>   
                <font-awesome-icon class="emoji__icon" @click="open_emoji = true" :icon="['far', 'face-smile']" />
            </div>

            <div class="input__option">
                <div class="input__option--item">
                    <label>
                    <svg  xmlns="http://www.w3.org/2000/svg" width="30" height="30" viewBox="0 0 31 39" fill="none">
                        <path d="M28.6731 15.2356L16.9055 32.5189C14.644 35.8403 10.1222 36.6988 6.80077 34.4374C3.47937 32.1759 2.62085 27.6541 4.88229 24.3327L17.6732 5.54649C19.0853 3.4725 21.9146 2.93532 23.9886 4.34744C26.0626 5.75956 26.5998 8.58887 25.1877 10.6629L14.4433 26.4433C13.8805 27.2699 12.7437 27.4857 11.9171 26.9229C11.0905 26.3601 10.8747 25.2233 11.4375 24.3967L21.1586 10.1192L18.9043 8.5843L9.18318 22.8618C7.77106 24.9358 8.30823 27.7651 10.3822 29.1772C12.4562 30.5894 15.2855 30.0522 16.6977 27.9782L27.442 12.1978C29.7035 8.87638 28.8449 4.35453 25.5235 2.0931C22.2021 -0.16834 17.6803 0.690183 15.4189 4.01158L2.62794 22.7978C-0.482813 27.3666 0.697058 33.581 5.26586 36.6917C9.83466 39.8025 16.049 38.6226 19.1598 34.0538L30.9274 16.7705L28.6731 15.2356Z" fill="black"/>
                    </svg>
                    <input class="option__files" @change="uploadfiles" type="file" id="files" >
                </label>
                </div>
                <button class="input__option--send input__option--item">
                    <font-awesome-icon :icon="['fas', 'paper-plane']" />
                </button>
            </div>
        </div>
    </form>

    <NotificationCPN></NotificationCPN>
</template>
<script>
import data from "../../../../../handle/Screen_chat/index";
import emoji from "../../ChatCPN/screenoption/emoji.vue";
import runtyping from "../../../../../socket/runtyping";
import NotificationCPN from "../../../../ComomCPN/Notification/Upload_warning.vue";
import store from '../../../../../store';
export default {
    components: {    
        emoji,
        NotificationCPN,
    },
    updated() {
        this.room = this.$route.query.rom;
        if (this.$route.query.idgroup != undefined) {
            this.id_obtans = this.$route.query.idgroup;
        } else {
            this.id_obtans = this.$route.query.id;
        }
    },
    setup() {
        const { send, room ,close, id_obtans, url,authen, open_emoji, uploadfiles,profile_friend } = data();
        const datas = { }
        const outrep = () => {
            store.state.chat.data_rep = null;
        }
      const opentyping = () => {
        datas.istyping= true;
        
        runtyping({user_id: authen.value.id,
            room: localStorage.getItem("data-select"),
            istyping: false });
        }

      const closetyping = (datas) => {
        datas.istyping = false;
        runtyping({
            user_id: authen.value.id,
            room: localStorage.getItem("data-select"),
            istyping: true
        });
      }
        return {
            send,
            outrep,
            authen,
            url,
            close,
            room,
            profile_friend,
            id_obtans,
            open_emoji,
            uploadfiles,
            opentyping,
            closetyping,
        }
    },
}
</script>
<style scoped>
.form {
    flex-direction: column;
    background: var(--colorMenu);
    display: flex;
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    margin: 15px;
    border-radius:10px;
    justify-content: space-between;
    z-index: 111;
}


.form__upload {
    width: 100%;
    display: flex;
    gap: 10px;
}
.form__upload--item {
    object-fit: cover;
    position: relative;
    display: flex;
    align-items: end;
}
.upload__img {
    position: relative;
    width: 100px;
    border-radius: 10px;
    height: 100px; 
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}
.upload__img--image {
    width: 100%;
    height: 100%;
}

.upload__video {
    position: relative;
    object-fit: cover;
    width: 100px;
    border-radius: 10px;
    height: 100px; 
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}

.upload__close {
    position: absolute;
    top: -12px;
    right: -12px;
    z-index: 11;
    width: 15px;
    height: 15px;
    border-radius: 50%;
    padding: 5px;
    background: rgb(209, 209, 209);
}
.upload__file {
    display: flex;
    width: 100%;
    height: 30px;
    padding: 10px;
    border-radius: 5px;
    gap: 10px;
    background: var(--color1);
    position: relative;
}


.upload__file--text {
    display: flex;
    align-items: center;
}
.file__name {
    margin: 0;
    padding: 0;
    width: 100px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis; 
}
.upload__file--icon {
    width: 30px;
    height: 30px;
}



.form__rep {
    box-sizing: border-box;
    width: 100%;
    padding: 10px;
    border-radius: 10px 10px 0px 0px;
    background: var(--colorBehindLess);
} 
.form__rep > strong {
    font-size: 16px;
}
.form__rep--close {
    position: absolute;
    z-index: 111;
    right: 10px;
    top: 10px;
    font-size: 16px;
}

.form__input {
        position: relative;
        background: var(--colorBehindLess);
        border-radius: 10px;
        display: flex;
        align-items: center;
        top: 50%;
        width: 100%;
        z-index: 20;
        flex-wrap: wrap;
    }
.form__input--enter {
    width: 100%;
    padding: 25px;
    outline: none;
    border: none;
    font-size: 17px;
    border-radius: 5px;
    background: var(--colorBehindLess);
    color: var(--colorText);
    font-weight: 500;
    padding-left: 60px;
    padding-right: 120px;
}

.input__emoji, 
.input__option {
    position: absolute;
    z-index: 20;
    top: 50%;
    transform: translateY(-50%);
}
.input__emoji {
    display: flex;
    left: 10px;
    font-size: 30px;
}
.input__emoji--move {
    bottom: 30px;
    right: 50%;
    transform: translateX(50%);
    position: absolute;
}
.input__option {
    display: flex;
    right: 10px;
    gap: 10px;
    justify-content: center;
    align-items: center;
    top: 50%;
}
.input__option--send {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 50px;
        height: 50px;
        font-size: 20px;
        background: #4F48ED;
        border-radius: 50%;
        border: none;
    }
.option__files {
    display: none;
}
</style>