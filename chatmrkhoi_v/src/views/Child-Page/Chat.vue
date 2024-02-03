<script setup>
 import MenuCPN from "../../components/MainCPN/Routerview/ChatCPN/Listperson.vue"
 import ScreenCPN from "../../components/MainCPN/Routerview/ChatCPN/Screenmess.vue"
 import AddGroupCPN from "../../components/MainCPN/Routerview/GroupCPN/AddGroup.vue"
 import DetailCPN from '../../components/MainCPN/Routerview/GroupCPN/DetailGroup.vue'
 import zoomimgCPN from "../../components/MainCPN/Routerview/ChatCPN/ExtrasOption/Fromzoomfile.vue";
 import NotifiCPN_kick from "../../components/ComomCPN/Notification/Kick_warning.vue";
 import NotifiCPN_rmgroup from "../../components/ComomCPN/Notification/delete_group.vue";
 import NotifiCPN_unfri from "../../components/ComomCPN/Notification/Unfriend_warning.vue";
 import NotifiCPN_usergroup from "../../components/ComomCPN/Notification/DeletegroupUser_warning.vue";
 import NotifiCPN_edtgroup from "../../components/ComomCPN/Notification/Editgroup_warning.vue";
import { computed } from "vue";
import store from "../../store";

 const detail  = computed(() => {
    if(store.state.avaible_chat.action_group) { }
    return localStorage.getItem("data-select");
 }) 


</script>
<template>
    <!-- CHAT -->
    <div class="chat">

        <!-- MENU CHAT -->
        <div class="chat__left">
            <MenuCPN />
        </div>

        <!-- SCREEN CHAT -->
        <div class="chat__right">
            <ScreenCPN v-if="$route.query.action != 'detail' && detail"/>
            <DetailCPN v-if="$route.query.action == 'detail' && detail" />
        </div>
        
    </div>


    <!-- COMPONENT -->
    <AddGroupCPN v-if="$route.query.action == 'add-group'" />
    <zoomimgCPN v-if="$route.query.mess_media != null" />
    


    <!-- NOTIFICATION  -->
    <NotifiCPN_kick></NotifiCPN_kick>
    <NotifiCPN_unfri></NotifiCPN_unfri>
    <NotifiCPN_rmgroup></NotifiCPN_rmgroup>
    <NotifiCPN_usergroup></NotifiCPN_usergroup>
    <NotifiCPN_edtgroup></NotifiCPN_edtgroup>
    
</template>
<style scoped>
.chat {
    position: relative;
    padding: 20px;
    display: grid;
    grid-template-columns: 350px 1fr;
    gap: 20px;
    height: 100% !important;
    box-sizing: border-box;
}
.chat__left {
    border-radius:10px;
    height: 100%;
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
    background: whitesmoke;
    position: relative;
    box-sizing: border-box;
}
.chat__right {
    background: white;
    border-radius: 10px;
    width: 100%;
}
</style>