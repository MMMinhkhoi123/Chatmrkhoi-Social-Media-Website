<script setup>
import handle from "../../../../handle/Everyone/index";
import imgCPN from "../../../../components/NewCPN/imgtake.vue"
import store from '../../../../store';
import datas from "../../../../handle/Screen_chat/index";
import { useRouter } from "vue-router";
    const { data_profile } = datas();
    store.state.avaible_chat.open_notifi = false;
    const {
         AddFriend, 
         AcceptFriend,
         DestroyRequest,
         RefuseFriend,
         DeleteFriend,
         CheckAcceptFriend,
         CheckRefuseFriend,
         CheckDestroyRequest,
         CheckAddFriend,
         showalert,
         data,
         url,
         GoProfile,
    } = handle();
    const router = useRouter();


</script>
<template>

    <div v-if="data.length > 0" class="list">
        <div class="list__item" v-for="item in data" :key="item" @click.self="GoProfile(item.id)">
            <!-- OPTION SELECT -->
            <div class="list__item--setting">
                
                <!-- ADD FRIEND -->
                <span class="item__select"   v-if="CheckAddFriend(item)" @click="AddFriend(item.id)">
                  <img class="item__select--img" src="../../../../assets/icon/add-user.png">
                </span>

                <!-- UNFRIEND  -->
                <span class="item__select" v-if="CheckDestroyRequest(item)" @click="DestroyRequest(item.id)">
                    <img class="item__select--img" src="../../../../assets/icon/un-user.png">
                </span>

                <!-- REFUSE -->
                <span  class="item__select" v-if="CheckRefuseFriend(item)"  @click="RefuseFriend(item.id)"> 
                    
                    <img class="item__select--img" src="../../../../assets/icon/delete_user.png">
                </span>

                <!-- AGREE -->
                <span  class="item__select"  v-if="CheckAcceptFriend(item)"  @click="AcceptFriend(item.id)">
                    <img class="item__select--img" src="../../../../assets/icon/add-user-friend.png">
                </span>

                <!-- DELETE FRIEND -->
                <span  class="item__select" v-if="DeleteFriend(item)" @click="showalert(item)"> 
                    <img class="item__select--img" src="../../../../assets/icon/delete-account.png">
                </span>
            </div>

            <div class="list__item--mutual" v-if="$store.state.everyone.chose == 5 "> Mutual friends {{item.sugg == null ? 0 : item.sugg.length }}
              <ul v-if="item" class="item__list">
                 <li v-for="items in item.sugg" :key="items" class="item__list--item">
                  {{  data_profile(items).fullname }}
                </li>
              </ul>
            </div>
            <!-- INFO USER -->
            <div class="list__item--infomation">
                <span class="item__text">
                    {{  item.fullname }}
                </span>
                <img  class="item__img" v-if="item.type_img !='rs'" :src="url + '/file/get-png/' + item.images">
                <imgCPN class="item__img" :name="item.images" v-if="item.type_img == 'rs'" />
            </div>

            <div class="list__item--animate">
                <div class="item__animate">
                </div>
                <div class="item__animate">
                </div>
            </div>

        </div>
    </div>

    <!-- ICON EMPTY DATA  -->
    <div class="empty" v-if="data.length == 0">
       <img class="empty__img" src="../../../../assets/icon/folder.png" alt="">
    </div>



</template>
<style scoped>
.list__item--mutual {
    position: relative;
    font-weight: 700;
}
.list__item--mutual:hover > .item__list {
    display: block !important;
}
.item__list {
    min-height: 150px;
    overflow-y: scroll;
    list-style-type: none;
    position: absolute;
    top: 100%;
    background: var(--color1);
    color: white;
    margin: 0;
    padding: 0;
    display: none;
    padding: 10px;
    z-index: 11;
}
.item__list > li {
    padding: 5px;
    text-align: center;
}
.list {
    padding: 0px 100px;
    padding-top: 10px;
    height: 550px;
    overflow-y: scroll;
}
.list__item {
    transition: 0.3s;
    display: flex;
    cursor: pointer;
    align-items: center;
    position: relative;
    justify-content: space-between;
    width: 100%;
    background: rgb(255, 255, 255);
    padding: 6px;
    margin-bottom: 15px;
    border-radius: 10px;
    box-sizing: border-box;
    box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
}
.list__item--setting {
    display: flex;
    gap: 50px;
    margin-left: 15px;
}
.item__select {
    color: #101010;
    font-style: normal;
    font-weight: 500;
    line-height: normal;   
}
.item__select--img {
    width: 30px;
    height: 30px;
}

.list__item--infomation {
    display: flex;
    align-items: center;
    gap: 24px;
}
.item__text {
    color: #000;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}
.item__img {
    width: 60px;
    height: 60px;
    box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
    border-radius: 50%;
}

.list__item--animate {
    position: absolute;
    left: 0;
    right: 0;
    bottom: 0;
    height: 2px;
    display: grid;
    grid-template-columns: repeat(2, 50%);
}
.item__animate  {
    position: relative;
}
.item__animate::before {
    position: absolute;
    top: 0;
    bottom: 0;
    width: 0;
    content: "";
    background: var(--color1);
    transition: 0.4s;
}
.item__animate:nth-child(1)::before {
    right: 0;
}
.item__animate:nth-child(2)::before {
    left: 0;
}

.list__item:hover {
    transform: translateY(-10px);
}
.list__item:hover > .list__item--animate > .item__animate::before {
    transition: 0.3s;
    width: 100%;
}
.empty {
    position: absolute;
    inset: 0;
    display: flex;
    justify-content: center;
    align-items: center;
}
.empty__img {
    width: 160px;
    height: 160px;
}




.btn {
    width: 100px;
    padding: 10px;
    border: none;
    border-radius: 10px;
    cursor: pointer;
}
.confirm {
    color: var(--color1);
    border: 1px solid  var(--color1) ;
    background: transparent; 
}
.lose {
  background: var(--color4); 
  color: white;  
}
.lose:hover {
    background: orange;
}
.confirm:hover {
    background: var(--color1);
    color: white;
}





</style>