<script >
import { reactive } from 'vue'
import store from '../../../../../store';
import data from "../../../../../handle/Screen_chat/index";
export default {
    props: {
        img: {
            typeof: String,
            default: '',
        },
        name: {
          typeof: String,
          default: '',
        }
    },
    setup(props) {
        const { url } = data();
        const dataupdate = reactive({
            newname: props.name,
            file: null,
            file_org: null,
        })
        const data_alert = reactive({
            title: '',
            content: '',
            img: '',
        })

        const upload = (event) => {
            let files = event.target.files[0];
            if(files.type.split("/")[0] != 'image') {
                data_alert.title = 'Notification.EditGoup.Title',
                data_alert.content = 'Notification.EditGoup.Content'
                data_alert.img = 'alert.png'
                store.state.avaible_chat.Notification.warning.edit_group  = true;
                store.state.avaible_chat.Notification.data = data_alert;
            } else {
                dataupdate.file_org = files;
                var reader = new FileReader();
                reader.onloadend = function() {
                    dataupdate.file =  reader.result;
                }
                reader.readAsDataURL(files);
            }

        }

        const save = (id) => {
            const datapost = {
                file: dataupdate.file_org,
                id: Number(id),
                name: dataupdate.newname,
            }
            if(dataupdate.file != null) {
                store.dispatch("chat/uploadimggroup", datapost).then((e) => {
                    store.state.avaible_chat.open_editinfo = false
                })
            }
            if(data.name != props.name) {
                store.dispatch("chat/update_namegroup", datapost).then((e) => {
                    store.state.avaible_chat.open_editinfo = false
                })
            }
    }

        return { upload,save, dataupdate,data_alert, url}
    },
}
</script>
<template>
    <div @click="$store.state.avaible_chat.open_editinfo = false" class="backgrond">
    </div>
    <form @submit.prevent="save($route.query.idgroup)" class="edit">
        <div class="edit__avata">
            <label class="edit__avata--form">
                <img class="avata__img"  v-if="dataupdate.file == null" :src="url + '/file/get-png/' + img">
                <img  class="avata__img" v-if="dataupdate.file != null" :src="dataupdate.file">
                <img class="avata__icon" src="../../../../../assets/icon/edit.png" >
                <input class="avata__input" @change="upload" type="file" />
            </label>
        </div>

        <div class="edit__name">
            <input class="edit__name--input" v-model="dataupdate.newname" type="text" required :placeholder="$t('TextMain.Chat.Screen.From.Edit.Input')" />
        </div>
        <div class="edit__setting">
            <button class="edit__setting--button">
            {{ $t('TextMain.Chat.Screen.From.Edit.Button') }}
            </button>
        </div>
    </form>
</template>
<style scoped>
.edit {
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    gap: 20px;
    background: var(--coloRegular);
    box-shadow: var(--colorshadow) 0 0 12px;
    padding: 20px;
    position: fixed;
    z-index: 2;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
}
.edit__avata {
    display: flex;
    justify-content: center;
}
.edit__avata--form  {
    position: relative;
    padding: 10px;
}

.avata__icon {
    position: absolute;
    width: 40px;
    height: 40px;
    right: 0px;
    bottom: 10px;
    cursor: pointer;
}
.avata__img {
    width: 100px;
    height: 100px;
    border: 4px solid rgb(255, 255, 255);
    border-radius: 50%;
    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
}
.avata__input {
    display: none;
}
.backgrond {
    position: fixed;
    inset: 0;
    z-index: 1;
    background: var(--colorHideFrom);
}

.edit__name {
    width:400px;
    display: flex;
}
.edit__name--input {
    outline: none;
    border-radius: 5px;
    width: 100%;
    padding: 15px;
    border: none;
    outline: none;
    background: var(--colorBehindLess);
    border: 1px solid var(--colorLine) !important;
}

.edit__setting {
    text-align: center;
}
.edit__setting--button {
    padding: 10px 20px;
    border: none;
    border-radius: 10px;
    color: var(--color1);
    transition: 0.3s;
    border: 1px solid var(--color1);
    background: transparent;
    cursor: pointer;
}
.edit__setting--button:hover {
    background: var(--color1) !important;
    color: var(--colorText);
    cursor: pointer;
}

</style>