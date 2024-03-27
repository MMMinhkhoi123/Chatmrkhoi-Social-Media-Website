<script setup>
import filterCPN from "../../components/MainCPN/routerview/EveryoneCPN/FilterPerson.vue"
import itemCPN from "../../components/MainCPN/Routerview/EveryoneCPN/ItemPerson.vue"
import menuCPN from "../../components/MainCPN/Routerview/EveryoneCPN/MenuSelect.vue";
import NotifiCPN from "../../components/ComomCPN/Notification/Unfriendfl_warning.vue";
import store from "../../store/index"

    store.dispatch("everyone/get_data_initial_friend",{key :"FRIEND", token: localStorage.getItem("token")})
    store.dispatch("everyone/get_data_initial_friendrequest", {key:"FRIEND_REQUEST",  token: localStorage.getItem("token")});
    store.dispatch("everyone/get_data_initial_notfriend", {key:"NOT_FRIEND", token: localStorage.getItem("token")});
    store.dispatch("everyone/get_data_initial_sendfriendrequest", {key:"SEND_FRIEND_REQUEST",  token: localStorage.getItem("token") });
    store.dispatch("chat/get_all_action", localStorage.getItem("token"));
    store.dispatch("everyone/get_data_sug_friend",localStorage.getItem("token"));

</script>
<template>
    
    <div class="everyone">

        <!-- MENU SELECT -->
        <div class="everyone__left">
            <menuCPN />
        </div>

        <div class="everyone__right">

            <!-- FILTER TOP (SEARCH, OPION) -->
            <div class="everyone__right--filter">
                <filterCPN></filterCPN>
            </div>

            <!-- LIST DATA AFTER SEARCH  -->
            <div id="everyone__right--list" class="everyone__right--list">
                <itemCPN ></itemCPN>
            </div>
            
        </div>
    </div>

    <!-- NOTIFICATION  -->
    <NotifiCPN></NotifiCPN>


</template>
<script>
export default {
    mounted() {
       const x = document.getElementById("everyone__right--list");
       const filter = document.querySelector(".everyone__right--filter");
       x.style.height = "calc(100% - "+ filter.offsetHeight +"px)";
    },
}
</script>

<style scoped>
* {
    color: var(--colorText);
}
.everyone {
    padding: 20px;
    position: relative;
    box-sizing: border-box;
    height: 100%;
    display: grid;
    gap: 40px;
    grid-template-columns: 400px 1fr;
}
.everyone__right {
    background: var(--coloRegular);
    border-radius: 10px;
    overflow: hidden;
}
.everyone__right--list {
    position: relative;
}
</style>
