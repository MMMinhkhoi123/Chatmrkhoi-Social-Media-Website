<script setup>
import handle from "../../../../../../../handle/Screen_chat";
 import other from "../../../../../../../handle/Common";
 import options from "../../../ScreenOPtion/optionmenu.vue";
  import options2 from "../../../screenoption/optionmenu2.vue";
  const { url_img } = other();
const {data_profile, showmenufeel,array_icon,show_detail_feel,array_icon_index,authen} = handle();
const convertTimeText = (time, key) => {
      const date = new Date(time);
      return (
        date.getHours() +
        ":" +
        date.getMinutes() +
        " " +
        date.getDate() +
        key +
        (date.getMonth() + 1) +
        ", " +
        date.getFullYear()
      );
    };
</script>
<template>
   <!-- text chat -->
   <div class="frame__text">
        <div class="frame__text--content">
          <!-- text reply -->
        <div class="text__owner" v-if="item.content != '' && item.feel == null &&  authen.id != item.id_user && item.id_group != null
         && $store.state.everyone.array_friend && $store.state.everyone.array_not_friend &&  item.start == false &&  item.pin == null && item.group_status == null && data_profile(item.id_user) ">
          <img class="text__owner--img"  v-if="data_profile(item.id_user).type_img == 'rs'" :src=" '/src/assets/images/avata_org/' +  data_profile(item.id_user).images " />
            <img  class="text__owner--img" v-if="data_profile(item.id_user).type_img == 'cpt'" :src="url_img + data_profile(item.id_user).images  " />
          </div>
          <!-- value text -->
          <p class="text__main" v-if="item.content != '' && item.feel == null">
            <span class="text__main--time" :class="item.id_user == authen.id ? '' : 'your__time'" >
              {{ convertTimeText(item.time, $t('TextMain.Chat.Screen.From.Pin.Month')) }}
            </span>
            <!--show feel -->
            <span  class="text__main--feel" @mouseover="showmenufeel(item.id, 'text')"  v-if="array_icon(item.listfeel, 'text').length > 3" 
              :style="   authen.id == item.id_user  ? { left: '-10px' } : { right: '-10px' } " >
              {{ array_icon(item.listfeel, "text")[0].feel }}
            </span>

            <span class="text__main--listfeel" v-if="item.listfeel.length > 0"   :style="  authen.id == item.id_user  ? { left: '20px' }  : { right: '20px' }  " >
              <span  @click.self="show_detail_feel(item, 'text')"  v-for="itemx in array_icon_index( array_icon(item.listfeel, 'text') )" :key="itemx" >
                {{ itemx.feel }}
              </span>
            </span>

            {{ item.content }}

            <options v-if="item" :item="item" :type="'text'"></options>

            <options2 v-if="item" :item="item" :type="'text'"></options2>
          </p>
          <!-- end value text -->
        </div>
      </div>
</template>
<script>
export default {
props: {
    item: {
        typeof: Object,
        default: null,
    }
},
}
</script>
<style scoped>


.text__owner {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  font-size: 15px;
  font-weight: 900;
  color: black;
}
.text__owner--img {
  width: 40px;
  height: 40px;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
  border-radius: 50%;
}



.my_mess {
  margin-right: 10px;
}
.your_mess {
  margin-left: 10px;
}
.your__time {
  left: calc(100% + 5px) !important;
}
.my_mess > .frame__text {
  justify-content: end;
}
.your_mess > .frame__text {
  justify-content: start;
}
.your_mess > .frame__text > .frame__text--content {
  justify-content: start;
}
.my_mess > .frame__text > .frame__text--content {
  justify-content: end;
}

.my_mess >.frame__text > .frame__text--content > .text__main {
  border-radius: 10px 10px 0 10px;
}
.your_mess  > .frame__text > .frame__text--content > .text__main {
  border-radius: 10px 10px 10px 0px;
  background: white !important;
  color: black;
}

.my_mess >  .frame__text > .frame__text--content > .text__main > .option {
  right: calc(100% + 40px);
}

.your_mess >  .frame__text > .frame__text--content > .text__main > .option {
  left: calc(100% + 40px);
}

.my_mess > .frame__text > .frame__text--content > .text__main > .option > .smile > .emoji {
  display: none;
  left: 0;
}
.your_mess > .frame__text > .frame__text--content > .text__main > .option > .smile > .emoji {
  display: none;
  right: 0;
}


.file__feel {
  position: absolute;
  bottom: -8px;
  font-size: 16px;
  z-index: 10;
  border-radius: 10px;
  background: white;
  display: flex;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
}

/* mess text */
.frame__text {
  display: flex;
  width: 100% !important;
}
.frame__text--content {
  color: white;
  display: flex;
  align-items: end;
  gap: 10px;
  width: 500px;
  word-break: break-all;
}
.text__main {
  position: relative;
  min-width: 70px;
  padding: 15px 10px;
  background: #4f48ed;
  margin: 0;
}
.text__main:hover > .text__main--time {
  transition-delay: 2s;
  opacity: 1 !important;
}


.text__main--feel {
  position: absolute;
  bottom: -8px;
  font-size: 16px;
  z-index: 10;
  border-radius: 10px;
  background: white;
  display: flex;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
}
.text__main--listfeel {
  background: white;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  border-radius: 10px;
  position: absolute;
  display: flex;
  font-size: 15px;
  bottom: -8px;
}
.text__main--time {
  opacity: 0;
  background: rgb(62, 60, 60);
  padding: 10px;
  font-size: 13px;
  border-radius: 5px;
  position: absolute;
  white-space: nowrap;
  min-width: 135px !important;
  top: 50%;
  right: calc(100% + 5px);
  z-index: 99;
  color: white !important;
  display: flex;
  transform: translateY(-50%);
  pointer-events: none;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
}
</style>
   
  