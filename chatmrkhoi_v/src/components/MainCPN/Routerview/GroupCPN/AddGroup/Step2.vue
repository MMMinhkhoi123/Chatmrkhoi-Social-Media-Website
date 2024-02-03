<template>
    <form @submit.prevent="creategroup" class="content">
        <div class="img">
            <img :src="img == null ? 'https://i.pinimg.com/originals/ca/19/18/ca191826dd9f6156634faf94d06bc57c.jpg': img" alt="">
            <label class="chonse_img">
                <span>
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="18" viewBox="0 0 20 18" fill="none">
                      <path d="M18 2H15L13 0H7L5 2H2C0.9 2 0 2.9 0 4V16C0 17.11 0.9 18 2 18H18C19.11 18 20 17.11 20 16V4C20 2.9 19.11 2 18 2ZM3 9H5.1C5.65 6.29 8.29 4.55 11 5.1C11.76 5.25 12.43 5.59 13 6L11.56 7.45C11.11 7.17 10.58 7 10 7C8.74 7 7.6 7.8 7.18 9H9L6 12L3 9ZM14.91 11C14.36 13.71 11.72 15.45 9 14.9C8.25 14.74 7.58 14.41 7 14L8.44 12.55C8.9 12.83 9.43 13 10 13C11.27 13 12.41 12.2 12.83 11H11L14 8L17 11H14.91Z" fill="white"/>
                    </svg>
                </span>
                <strong>{{ $t('TextMain.Group.Add.ChoiceImg') }}</strong>
                <input @change="upload"  type="file">
            </label>
        </div>
        <div class="name">
            <input v-model="namegroup" type="text" :placeholder="$t('TextMain.Group.Add.InputNameGroup')" required>
        </div>
        <div class="setting">
            <button type="button" @click="this.$router.go(-1)">
                {{ $t('TextMain.Group.Add.ButtomBack') }}
            </button>
            <button type="submit">
                {{ $t('TextMain.Group.Add.ButtomCreate') }}
            </button>
        </div>
    </form>
</template>
<script>
import { reactive, ref } from 'vue';
import store from '../../../../../store/index';
import { useRouter } from 'vue-router';
import connect from "../../../../../socket/connect_private";

import connect_public from "../../../../../socket/handle_everyone";
export default {
    created() {
        if(store.state.chat.array_addgroup.length == 0) {
           this.$router.push({ name: 'chat' });
        }
    },
    setup() {
        const data = reactive({
            title: '',
            content: '',
            img: '',
        })
        store.state.avaible_chat.open_notifi = false;
        const img = ref(null)
        const namegroup = ref('')
        const route = useRouter();

        const upload = (event) => {
            let files = event.target.files[0];
            if(checkfile(files) == true) {
                var reader = new FileReader();
                reader.onloadend = function() {
                    img.value = reader.result;
                }
                const data_upload = reactive({
                    file: files,
                    iduser: store.state.auth.authen.id,
                    type: 'img',
                 })
                 store.dispatch("chat/uploadgroup",data_upload)
                // upload
                reader.readAsDataURL(files);
            } else {
                data.title = 'Notification.Addgroup.Step2.Title'
                data.content = 'Notification.Addgroup.Step2.ContentFormat'
                data.img = 'alert.png'
                store.state.avaible_chat.Notification.data = data;
                store.state.avaible_chat.Notification.warning.addgroup = true;
            }
        }

        
        function checkfile(value) {
            if(value.type.substring(0,5) != "image") {
                return false;
            } else {
              return true;
            }
        }

        const creategroup  = () => {
            if (img.value == null) {

                data.title = 'Notification.Addgroup.Step2.Title'
                data.content = 'Notification.Addgroup.Step2.ContentEmpty'
                data.img = 'alert.png'

                store.state.avaible_chat.Notification.data = data;
                store.state.avaible_chat.Notification.warning.addgroup = true;
            } else
            {
                const datapost = reactive({
                    idimg: store.state.chat.data_img_group != null ? store.state.chat.data_img_group.id : null,
                    namegroup: namegroup.value,
                    arrayperson: store.state.chat.array_addgroup,
                    idmaster: store.state.auth.authen.id,
                    coderoom: gerneration_code(store.state.chat.array_addgroup)
                })
            // CREATE GROUP
            store.dispatch("chat/addgroup", datapost).then(() => {

                const x = setInterval(() => {
                    if(store.state.chat.data_group_after != null) {
                      clearInterval(x);
                      console.log(store.state.chat.data_group_after);
                      connect();
                    }
                }, 1000);
                route.push('/main/chat?idgroup='+ store.state.chat.data_mygroup_after.id);  
                localStorage.setItem("data-select",  store.state.chat.data_mygroup_after.coderoom)
                store.state.avaible_chat.open_change_array = !store.state.avaible_chat.open_change_array;
                store.state.avaible_chat.action_group = !store.state.avaible_chat.action_group;
            // SEND CONNECT PUBLIC
                connect_public(
                   { person: store.state.chat.array_addgroup,
                    mygroup: store.state.chat.data_mygroup_after,
                    object_connect: store.state.chat.data_group_after,
                    connect : store.state.chat.data_group_after.coderoom,
                  } )

            });
  
            }
        }

        function gerneration_code(array) {
            var x = '';
            array.forEach(element => {
                x =x + "&" + element;
            });
            return x + "&" + store.state.auth.authen.id;
        }



        return {
            upload,
            img,
            creategroup,
            namegroup,
            data,
        }
    },
}
</script>
<style scoped>
.name {
    padding-top: 60px;
    display: flex;
}
.name > input {
    padding-left: 22px;
    width: 100%;
    outline: none;
    padding: 20px;
    border-radius: 10px;
    border: 1px solid #D9D9D9;
    background: #FFF;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
}
.chonse_img {
    display: flex;
    align-items: center;
    gap: 5px;
}
span {
    width: 40px;
    height: 40px;
    display: block;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    background: #4F48ED;
}
strong {
    color: #000;
    cursor: pointer;
    font-style: normal;
    line-height: normal;
    }
.chonse_img > input {
    display: none;
}
img {
    object-fit: cover;
    width: 189px; 
    height: 148px;  
    border-radius: 20px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
}
.img {
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    gap: 12px;
}
.content {
    padding: 30px;
    width: 450px;
    min-height: 500px;
    background: white;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    position: absolute;
    border-radius: 10px;
    top: 50%;
    left: 50%;
    z-index: 11;
    transform: translate(-50%, -50%);
}
.setting {
    position: absolute;
    bottom: 20px;
    right: 20px;
    display: flex;
    gap: 10px;
}
.setting > button {
    padding: 10px 30px;
    border-radius: 10px;
    background: var(--color3);
    border: none;
    color: #ffffff;
    font-style: normal;
    font-weight: 500;
    cursor: pointer;
    transition: 0.3s;
    line-height: normal;
}

.setting > button:nth-child(1):hover {
    background: var(--color2) !important;
    color: white !important;
}
.setting > button:nth-child(2) {
    border: 1px solid var(--color1);
    background: transparent;
    color: var(--color1);
}
.setting > button:nth-child(2):hover {
    background: var(--color1) !important;
    color: white !important;
}


</style>