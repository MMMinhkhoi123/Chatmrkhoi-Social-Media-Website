<script setup>
import data from "../../handle/Screen_chat/index";
 import socket from "../../socket/common.js";
 import listprofileCPN from "../../components/MainCPN/Routerview/ProfileCPN/Listfriend.vue";
 import ProfileTopCPN from  "../../components/MainCPN/Routerview/ProfileCPN/ProfileTop.vue";
 import ProfileBottomCPN from  "../../components/MainCPN/Routerview/ProfileCPN/ProfileBottom.vue";
 import NotifiCPN from "../../components/ComomCPN/Notification/Unfriendfl_warning.vue";
 const { authen } = data();
 socket()
</script>

<template>
    <div  class="profile" v-if="authen && $store.state.everyone.array_friend != null && $store.state.everyone.array_not_friend != null ">
        <div class="profile__action">
            <div class="profile__action--left">

                <!-- profile top -->
                <ProfileTopCPN />

                <!-- profile bottom -->
                <ProfileBottomCPN />

            </div>
            <div class="profile__action--right">

                <!-- list other profile -->
                <listprofileCPN />
            </div>
        </div>
    </div>

    <!-- NOTIFICATION  -->
    <NotifiCPN />

    
</template> 
<style scoped>
.profile {
    padding: 20px;
    position: relative;
    height: 100%;
    box-sizing: border-box;
}

.profile__action {
    box-sizing: border-box;
    height: 100%;
    border-radius: 10px;
    display: grid;
    gap: 10px;
    grid-template-columns: 1fr 350px;
}
.profile__action--left {
    background: rgb(255, 255, 255);
    border-radius: 10px;
    overflow: hidden;
}
.profile__action--right {
    border-radius: 10px;
    background:rgb(252, 252, 252);
}




.icon_img {
    width: 30px;
    height: 30px;
}
.confirm:hover {
    background: var(--color1);
    color: white;
}

.lose {
  background: var(--color4); 
  color: white;  
}
.lose:hover {
    background: orange;
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
    border: 1px solid  var(--color1);
    background: transparent; 
}
</style>