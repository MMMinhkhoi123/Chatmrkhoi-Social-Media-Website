<script setup>
import { computed } from "vue";
import { useRouter } from "vue-router";
import store from "../../../../store";

const router = useRouter();
 function convertDate(date) {
    const dates = new Date(date);
    return "Vào lúc "+ dates.getHours() + " giờ " + dates.getMinutes() + " phút  Ngày " + dates.getDate()+ ", Tháng " +  (dates.getMonth() + 1)+ " " + dates.getFullYear();
 }

 function GoView(item) {
    router.push({query: {view : item.id}})
    if(!item.status) {
        store.state.notice.arrayNotice.forEach( (element, index) => {
            if(item.id == element.id) {
                store.state.notice.arrayNotice[index].status = true; 
            }
        });   
        store.dispatch("notice/updateNotice", item.id);
    }
 }

 const convertTime = (time) => {
        const x = computed(() => {
            const date = new Date();
            const hour = (date.getTime() - time) / (60 * 60 * 1000);
            const mute = (hour - Math.floor(hour)) * 60;
            const second = (mute - Math.floor(mute)) * 60;
            
            if( Math.floor(hour) == 0 &&  Math.floor(mute) == 0  ) {
                return  Math.floor(second) + " giây trước";
            }
            if( Math.floor(hour) == 0 &&  Math.floor(mute) != 0) {
                return  Math.floor(mute) + " phút trước";
            }
            return Math.floor(hour) + " giờ trước" ;
        })
        return x.value;
        } 


        const array = computed(() => {
            return store.getters["notice/getNoticebystatus"](store.state.notice.arrayNotice);
        })
</script>
<template>
    <ul class="listnotice">
        <li class="listnotice__item" v-for="item in array" :key="item.id" @click="GoView(item)">
            <div class="listnotice__item--title">
                <h3>{{ item.title }}</h3>
                <span class="item__new" v-if="item.status == false">
                    New
                </span>
            </div>
            <p  class="listnotice__item--describe">{{ item.describe }}</p>
            <span>{{ convertTime(item.date) }}</span>
        </li>
    </ul>
</template>
<style scoped>
.listnotice {
    background: var( --coloRegular);
    min-height: 100vh;
    border-radius: 10px;
}
.listnotice__item {
    cursor: pointer;
    padding: 20px;
}
.listnotice__item:hover {
    background: var(--colorMenu);
}
.listnotice {
  list-style: none;
  margin: 0;
  padding: 0;
}
.listnotice__item--describe {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
.listnotice__item--title {
    display: flex;
    align-items: center;
    gap: 20px;
}
.item__new {
  background: orange;
  padding: 5px 20px;
  color: white;
}
</style>