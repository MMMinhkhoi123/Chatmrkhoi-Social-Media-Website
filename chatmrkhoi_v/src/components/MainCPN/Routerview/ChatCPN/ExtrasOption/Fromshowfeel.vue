<script  setup>
import store from '../../../../../store'
import data from "../../../../../handle/Screen_chat/index";
        const {  data_profile, authen, url } = data();
        // set up 
        const array = store.state.avaible_chat.feel.mess.listfeel;
        const array_user = () => {
            var fake_user = []
            array.forEach(element => {
                if (fake_user.includes(element.iduser) == false) {
                    fake_user.push(element.iduser)
                }
            });
            return fake_user;
        }
        const getsumemoji = (idusera) => {
            var datasum = [];
            var fake_emoji = [];
            array.forEach(element => {
                if (element.iduser == idusera && fake_emoji.includes(element.feel) == false) {
                    fake_emoji.push(element.feel)
                }
            });
            const arraynew = array.filter((e) => e.iduser == idusera)
            fake_emoji.forEach(e => {
                datasum.push(sumemoji(e, arraynew))
            })
            return datasum;
        }


        function sumemoji(character, arrasy) {
            var data = {
                character: character,
                sum: 0,
            }
            arrasy.forEach(element => {
                if (element.feel == character) {
                    data.sum += 1;
                }
            });
            return data;
        }
</script>
<template>
    <div class="backgrond" @click="$store.state.avaible_chat.feel.status = false" >
    </div>
    <div class="feel" >
        <h3 class="feel__title">
            {{ $t('TextMain.Chat.Screen.From.Feel.Title') }}
        </h3>
        <p class="feel__text">
            {{ $t('TextMain.Chat.Screen.From.Feel.Content') }}
             <strong>{{ array_user().length }}</strong></p>
        <div  class="feel__item" v-for="item in  array_user()" :key="item">
            <div class="feel__item--avata" v-if="item == authen.id">
                <img  class="item__img" v-if="authen.type_img != 'rs'"
                :src="url + '/file/get-png/' + authen.images">
                <img class="item__img" v-if="authen.type_img == 'rs'" :src="'/src/assets/images/avata_org/' + authen.images">
            </div>
            <div class="feel__item--avata" v-if="item != authen.id && $store.state.everyone.array_friend && $store.state.everyone.array_not_friend">
                <img class="item__img" v-if="data_profile(item).type_img != 'rs'"
                :src="url + '/file/get-png/' + data_profile(item).images">
                <img class="item__img" v-if="data_profile(item).type_img == 'rs'" :src="'/src/assets/images/avata_org/' + data_profile(item).images">
            </div>
            
            <div  class="feel__icon" v-for="itemx in getsumemoji(item)" :key="itemx">
                <div>
                    {{ itemx.character }}
                    <span>{{ itemx.sum }}</span>
                </div>
            </div>
        </div>
    </div>
</template>
<style scoped>
.backgrond {
    transition: 0.3s;
    position: fixed;
    inset: 0;
    background: var(--colorHideFrom);
    z-index: 100;
    filter: blur(0px);
}
.feel {
    background: var(--coloRegular);
    min-width: 500px;
    overflow: hidden;
    border-radius: 10px;
    text-align: center;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 111;
    box-shadow: 0 0 19px var(--colorshadow);
}
.feel__title {
    padding: 15px !important;
    margin: 0;
    padding: 0;
}
.feel__text {
    text-align: start;
    padding: 20px 10px !important;
    margin: 0;
    padding: 0;
}


.feel__item {
    display: flex;
    align-items: center;
    gap: 30px;
    margin-left: 10px;
    margin-bottom: 10px;
}

.item__img {
    width: 60px;
    height: 60px;
    border-radius: 50%;
    box-shadow:  var(--colorshadow) 0px 7px 29px 0px;
}
</style>