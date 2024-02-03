<template>
    <div class="backgound"  @click="$store.state.avaible_chat.open_pin = false"  >
    </div>
    <div class="pin">
        <h3 class="pin__title">
          {{ $t('TextMain.Chat.Screen.From.Pin.Title') }}
        </h3>
        <p  class="pin__content">
          {{ $t('TextMain.Chat.Screen.From.Pin.Content') }}
          <strong>{{  array_pin_room.length }}</strong></p>
        <div class="pin__list">
          <div class="pin__list--item" v-for="(item, index) in  array_pin_room" :key="item.id">

            <div class="pin__time">
              <div class="pin__time--date">
               {{ convertdate(item, index, $t('TextMain.Chat.Screen.From.Pin.Month')) }}
              </div>
              <strong class="pin__time--time">
                {{ data_profile(item.iduser).fullname + $t('TextMain.Chat.Screen.From.Pin.menu.PinAt') + converttime(item).getHours() +  " : " + converttime(item).getMinutes()  }}
              </strong>
            </div>

            <div class="list__avata" v-if="item.iduser == authen.id">
                <img class="list__avata--img" v-if="authen.type_img != 'rs'"
                :src="url + '/file/get-png/' + authen.images">
                <img class="list__avata--img"   v-if="authen.type_img == 'rs'" :src="'/src/assets/images/avata_org/' + authen.images">
            </div>
            <div class="list__avata" v-if="item.iduser != authen.id">
                <img class="list__avata--img" v-if="data_profile(item.iduser).type_img != 'rs'" 
                :src="url + '/file/get-png/' + data_profile(item.iduser).images" alt="">
                <img class="list__avata--img"  v-if="data_profile(item.iduser).type_img == 'rs'" :src="'/src/assets/images/avata_org/' + data_profile(item.iduser).images">
            </div>

            <div class="pin__list--content">
                <div  class="list__option"  @click.self="show(item.id)">
                  <div class="list__option--menu" :id="'menu' + item.id">
                    <span class="option__item" @click.self="watch(item)">
                      {{ $t('TextMain.Chat.Screen.From.Pin.menu.View') }}
                    </span>
                    <span class="option__item" v-if="authen.id == item.iduser" @click.self="checkunghim(item.idmess)">
                      {{ $t('TextMain.Chat.Screen.From.Pin.menu.Unpin') }}
                    </span>
                    <div class="triangle-down"></div>
                  </div>
                    <font-awesome-icon :icon="['fas', 'ellipsis-vertical']" />
                </div>

                <listrep :item="getinfomess(item.idmess)[0]" ></listrep>


                <div class="list__file" v-if="getinfomess(item.idmess)[0].img.length > 0">
                  <div class="list__item" v-for="its in getinfomess(item.idmess)[0].img" :key="its" >
                      <img class="list__item--img"  v-if="its.type == 'image'" :src="url + '/file/get-png/' + its.namefile" >
                      <video class="list__item--video" v-if="its.type == 'video' && its.status == true">
                        <source :src="url + '/file/geturl-video/' + its.namefile" type="video/mp4">
                      </video>
                      <span class="list__item--file" v-if="its.type == 'file'" > 
                        <img class="item__icon" src="../../../../../assets/icon/archive.png">
                        <strong>{{ convertdata(its.namefile) }}</strong>
                      </span>
                  </div>
                </div>

                <div class="list__text">
                    <div  class="list__text--item" v-if="$store.state.chat.array_all_mess">
                    <p  class="text__content" item v-if="getinfomess(item.idmess)[0].content != ''">{{ getinfomess(item.idmess)[0].content }}</p>
                  </div>
                </div>
            </div>
        </div>
        </div>
    </div>
</template>
<script>
import store from '../../../../../store/index'
import data from "../../../../../handle/Screen_chat/index";
import listrep from "../ScreenOPtion/listreply.vue";
export default {
  components: {
    listrep,
  },
  updated() {
        this.room = this.$route.query.rom;
    },
    created() {
        this.room = this.$route.query.rom;
    },
    setup() {
      const { room, array_pin_room, url,authen,convertdata,data_profile,checkunghim } = data();



      function getinfomess(value) {
        return store.getters["chat/get_mess_id"](Number(value));
      }
      const show = (id) => {
        const div = document.getElementById("menu" + id);
        if(div.getAttribute("class").includes("showmenu") == true) {
          div.classList.remove("showmenu")
        } else {
          div.classList.add("showmenu")
        }
      }

      const watch = (item) => {
        store.state.avaible_chat.open_pin = false;
        store.state.chat.runscroll = true;
        localStorage.setItem("pin", "action")

        const div = document.querySelector(".form__row" + item.idmess);

        if(div == null) {
          store.state.chat.data_index_pin = null;
          const m = store.getters["chat/get_data_room"](localStorage.getItem("data-select"));
           m.forEach((element, index) => {
            if(element.id == item.idmess) {
              store.state.chat.data_index_pin = { index: index, idmess:  item.idmess }
            }
           });
        } else {
          store.state.chat.data_index_pin = null;
          const m = store.getters["chat/get_data_room"](localStorage.getItem("data-select"));
           m.forEach((element, index) => {
            if(element.id == item.idmess) {
              store.state.chat.data_index_pin = { index: index, idmess:  item.idmess }
            }
           });
          hightlight(div);
          div.scrollIntoView();
        }
      }


      function hightlight(div) {
        div.getAttribute("class").includes("hightlight") == true 
        ?  (div.classList.remove("hightlight") , setTimeout(() => {  div.classList.add("hightlight") }, 300))  
        :   div.classList.add("hightlight") 
    }

      const converttime = (value) => {
          const date = new Date(value.time);
          return date;
      }


      const convertdate = (value, index, key) => {
        if(index == 0) {
           return  timdate(value.time, key);
        } else {
          const datefind = new Date(array_pin_room.value[index - 1].time);
          if(datefind.getDate() < new Date(value.time).getDate()) {
            return timdate(value.time, key)
          }
        }
      }

      function timdate(value, key) {
        const dates = new Date(value);
        return dates.getDate() + key +(dates.getMonth() + 1) + ", " + dates.getFullYear()
      }


      return { room, array_pin_room,converttime,getinfomess,show, url,convertdata,data_profile,watch, checkunghim, authen ,convertdate }
    },
}
</script>
<style scoped>
.backgound {
  position: fixed;
  inset: 0;
  background: rgba(203, 202, 202, 0.612);
  z-index: 100;
  filter: blur(20px);
} 
.pin__time--date {
  font-size: 14px;
  color: var(--color1);
  text-align: end;
  font-weight: 700;
}
.pin__time--time {
  font-size: 15px;
} 

.pin {
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
  background: rgb(244, 244, 244);
  min-width: 750px;
  border-radius: 10px;
  text-align: center;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 111;
  box-shadow: 0 0 19px rgba(0, 0, 0, 0.253);
}
.pin__title {
  margin: 0;
  padding: 20px;
  background: var(--color1);
  color: white;
  border-radius: 10px 10px 0 0 ;
}
.pin__content {
  text-align: start;
  padding: 20px;
}
.pin__list {
  padding: 40px 10px; 
  min-width: 600px;
  overflow-y: scroll;
  max-height: 400px;
}
.pin__list--item {
  padding-top: 40px;
  display: flex;
  gap: 10px;
  margin-bottom: 10px;
  align-items: end;
  position: relative;
}
.pin__time {
  bottom: calc(100% - 35px);
  left: 0;
  right: 0;
  position: absolute;
}
.list__avata {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  overflow: hidden;
  border: 2px solid white;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}
.list__avata--img {
  width: 100%;
  height: 100%;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
}
.pin__list--content {
  position: relative;
}
.pin__list--item:hover  > .pin__list--content > .list__option  {
  display: flex !important;
  cursor: pointer;
}

.list__option:hover {
  background: rgb(199, 199, 199);
}
.list__option {
  position: absolute;
  left: calc(100% + 40px);
  top: 50%;
  transform: translateY(-50%);
  background: transparent;
  width: 30px;
  height: 30px;
  display: none;
  border-radius: 50%;
  justify-content: center;
  align-items: center;
}
.list__option--menu {
  background: white;
  padding: 5px;
  border-radius: 10px;
  width: 140px;
  opacity: 0;
  position: absolute;
  left: calc(100% + 30px);
  top: 50%;
  pointer-events: none;
  transition: 0.3s;
  transform: translateY(-50%) translateX(10px);
}
.option__item {
  padding: 10px;
  display: block;
  cursor: pointer;
  border-radius: 5px;
}

.option__item:hover {
  background: var(--color1);
  color: white;
}



.list__item {
  display: flex;

}

.list__item--img,
.list__item--video {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 10px;
  border: 5px solid var(--color1);

}
.list__file {
    position: relative;
    padding: 5px;
    border-radius: 10px;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    gap: 5px;
    color: white;
    max-width: 400px;
}
.list__item--file {
  background: var(--color1);
  display: flex;
  border-radius: 10px;
  align-items: center;
  gap: 10px;
  padding: 5px;
}
.item__icon {
    width: 30px;
    height: 30px;
}


.list__text {
  display: flex;
  max-width: 300px;
}
.list__text--item  {
  max-width: 500px;
  word-break: break-all;
}
.text__content  {
  background: white;
  color: black;
  border-radius: 10px 10px 10px 0px;
  padding: 10px;
  display: flex;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  position: relative;
  z-index: 11;
  margin: 0;
  min-width: 70px;
}

.reply_item {
  opacity: 0.7;
}


.showmenu {
  opacity: 1 !important;
  pointer-events: visible !important;
  transform: translateY(-50%) translateX(0px) !important;
}

.triangle-down {
    position: absolute;
    top: 50%;
    width: 0;
    height: 0;
    right: 100%;
    transform: translateY(-50%);
    border-right: 10px solid #ffffff;
    border-bottom: 10px solid transparent;
    border-top: 10px solid transparent;
} 


</style>