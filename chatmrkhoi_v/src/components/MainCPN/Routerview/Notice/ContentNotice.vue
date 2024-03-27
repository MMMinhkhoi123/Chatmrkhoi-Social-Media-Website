<script setup>
import { computed } from "vue";
import {  useRoute } from "vue-router";
import store from "../../../../store";
import SubScreenNotice from "./SubScreenNotice.vue";
const useRoutes = useRoute();
const noticeView = computed(() => {
    return store.getters["notice/getNoticeById"](useRoutes.query.view);
})
function convertDate(date) {
    const dates = new Date(date);
    return "Vào lúc "+ dates.getHours() + " giờ " + dates.getMinutes() + " phút  Ngày " + dates.getDate()+ ", Tháng " +  (dates.getMonth() + 1)+ " " + dates.getFullYear();
 }

 function deleteById(id) {
    store.state.notice.arrayNotice = store.state.notice.arrayNotice.filter((e) => e.id != id);
    store.dispatch("notice/deleteNotice", id);
 }


</script>
<template>
    <div v-if="$route.query.view">
        <div class="viewnotice" v-if="noticeView.length > 0">
            <div class="viewnotice__option">
                <span style="font-weight: 700;">
                   <font-awesome-icon id="action__item--icon" class="action__item--icon" :icon="['fas', 'bell']" />
                    Notification
                </span>
                <div class="viewnotice__option--button">
                    <button class="option--remove" @click="deleteById(noticeView[0].id)">
                        Xóa tin
                    </button>
                </div>
            </div>
            <div class="viewnotice__content">
                <div class="viewnotice__content--top">
                    <h1 class="content__title" >
                        {{ noticeView[0].title }}
                    </h1>
                    <span class="content__date">
                        {{ convertDate(noticeView[0].date) }}
                    </span>
                </div>
                <p class="viewnotice__content--describe">{{ noticeView[0].describe }}</p>
            </div>
            <SubScreenNotice :item="noticeView[0]"></SubScreenNotice>
        </div>
    </div>
    <div class="viewnotice__empty" v-else>
        <img src="../../../../assets/icon/notification-bell.png" >
    </div>
</template>
<style scoped>
.viewnotice__empty {
    position: absolute;
    inset: 0;
    display: flex;
    align-items: center;
    justify-content: center;
}
.viewnotice__empty > img {
    width: 150px;
    height: 150px;

}
.viewnotice {
    padding: 20px;
}
.viewnotice__option {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.option--specific {
    margin: 10px;
    padding: 10px 40px;
    border: none;
    color: white;
    background: var(--color2);
    border-radius: 5px;
}
.option--remove:hover {
    background: blue;
    color: white;
}

.option--remove {
    margin: 10px;
    padding: 10px 40px;
    border: none;
    border: 1px solid red;
    color: red;
    background: transparent;
    border-radius: 5px;
}
.option--remove:hover {
    background: red;
    color: white;
}
.viewnotice__content--top {
    display: flex;
    align-items: center;
    gap: 20px;
    padding: 10px 0;
}
.viewnotice__content--describe {
  font-weight: 700;
  font-size: 20px;
}
.content__title {
    margin: 0;
}
.content__date {
    color: var(--color1);
    font-weight: 500;
}
</style>