
<script setup>
import zoomimg from "../../components/MainCPN/Routerview/ChatCPN/ExtrasOption/Fromshowimg.vue"
import store from '../../store/index.js'
import Menus from "../../components/MainCPN/Menu.vue"
import connect_private from "../../socket/connect_private";
import connect_public from '../../socket/connect_public';
import action from "../../socket/action";
import typeing from '../../socket/typeing';
import newuser from "../../socket/newuser";
import stom from "../../socket/common";


     const { stompClient } =  stom();
     stompClient.connect({login: store.state.auth.authen.id },() => {})
     
    // CONNECT ACTION EVERYONE
    connect_public();
    // CONNECT LISTEN TYPING EVERYONE
    typeing();
    // CONNECT LISTEN NEW USER 
    newuser();
    // CONNECT LISTEN ACTION EVERYONE
    action();
    // RESET LOADING;
    store.state.avaible_chat.open_loader = false;
    const f = setInterval(() => {
        store.state.chat.array_connect != null ? (clearInterval(f) ,connect_private()): null;
    }, 1000)

</script>
<template>
    <div class="main">
        <!-- menu -->
        <div class="main__menu">
            <Menus/>
        </div>
        <!-- render view page child -->
        <div class="main__center">
            <router-view></router-view>
        </div>
    </div>
     <!-- show img zoom when click on image-->
    <zoomimg v-if="$route.query.codeimg != null"></zoomimg>
</template>
<style scoped>
.main {
    position: fixed;
    inset: 0;
    display: flex;
    background: rgb(196, 205, 231);
}

.main__center {
    transition: 0.6s;
    background: rgb(196, 205, 231);
    width: 100%;
    box-sizing: border-box;
    margin-left: 200px;
}
.show_action {
    margin-left: 100px;
}
</style>