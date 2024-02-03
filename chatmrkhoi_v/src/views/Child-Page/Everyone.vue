<script setup>
import filterCPN from "../../components/MainCPN/routerview/EveryoneCPN/FilterPerson.vue"
import itemCPN from "../../components/MainCPN/Routerview/EveryoneCPN/ItemPerson.vue"
import menuCPN from "../../components/MainCPN/Routerview/EveryoneCPN/MenuSelect.vue";
import NotifiCPN from "../../components/ComomCPN/Notification/Unfriendfl_warning.vue";
import store from "../../store/index"

    // CALL APPLICATION DATA SERVER 
    store.dispatch("everyone/get_send_friend_request", localStorage.getItem("token"));
    store.dispatch("everyone/get_friend_request", localStorage.getItem("token"));
    store.dispatch("everyone/get_not_friend",  localStorage.getItem("token"));
    store.dispatch("everyone/get_friend", localStorage.getItem("token"));
    store.dispatch("chat/get_all_action", localStorage.getItem("token"));

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
    background: #ffffff;
    border-radius: 10px;
    overflow: hidden;
}
.everyone__right--list {
    position: relative;
}
</style>
