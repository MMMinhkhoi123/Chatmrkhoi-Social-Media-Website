<script setup>
import { useRoute, useRouter } from 'vue-router';
    import handle from "../../../../../../../handle/Screen_chat/index";
    import options from "../../../ScreenOPtion/optionmenu.vue";
    import options2 from "../../../screenoption/optionmenu2.vue";
    import other from "../../../../../../../handle/Common";
    const {authen,url,showmenufeel,array_icon,array_icon_index,show_detail_feel,convertdata,dowloadk,data_profile } = handle();

    const router = useRouter();
    const route = useRoute();
    const { url_img } = other();
      
    const convertTimeText = (time, key) => {
      const date = new Date(time);
      return (
        date.getHours() +
        ":" +
        date.getMinutes() +
        " " +
        date.getDate() +
        key +
        date.getMonth() +
        ", " +
        date.getFullYear()
      );
    };
    const selectzoom = (value, type) => {
      localStorage.setItem("idfile", value);
      router.push({
        query: { id: route.query.id, mess_media: type, idfile: value },
      });
    };
    
</script>
<template>
    <div class="file__frame" :id="'file__frame' + item.id" :class="item.id_group != null && item.content != '' && item.id_user != authen.id ? 'file__left': ''">
      <div class="text__owner" v-if="item.content == '' && item.feel == null &&  authen.id != item.id_user && item.id_group != null && $store.state.everyone.array_friend && $store.state.everyone.array_not_friend &&  item.start == false &&  item.pin == null && item.group_status == null  ">
            <img class="text__owner--img"  v-if="data_profile(item.id_user).type_img == 'rs'" :src=" '/src/assets/images/avata_org/' +  data_profile(item.id_user).images " />
            <img  class="text__owner--img" v-if="data_profile(item.id_user).type_img == 'cpt'" :src="url_img + data_profile(item.id_user).images  " />
      </div>
      <div class="file__frame--list">

        <span class="frame__time" :class="item.id_user == authen.id ? '' : 'your__time'" v-if="item.content == ''">
          {{ convertTimeText(item.time,  $t('TextMain.Chat.Screen.From.Pin.Month')) }}
        </span>

        <span class="frame__feel" @mouseover="showmenufeel(item.id, 'file')" v-if="array_icon(item.listfeel, 'file').length > 3 && item.img.length > 0" 
          :style=" authen.id == item.id_user ? { left: '-10px' } : { right: '-10px' }">
          {{ array_icon(item.listfeel, "file")[0].feel }}
        </span>

        <span  class="frame__feel" v-if="item.listfeel.length > 0 && item.img.length > 0" 
          :style="authen.id == item.id_user ? { left: '20px' }: { right: '20px' }">
          <span @click="show_detail_feel(item, 'file')" v-for="itemx in array_icon_index(array_icon(item.listfeel, 'file'))" :key="itemx">
            <span v-if="itemx.type == 'file'">
              {{ itemx.feel }}
            </span>
          </span>
        </span>

        <options v-if="item" :item="item" :type="'file'"></options>
        <options2 v-if="item" :item="item" :type="'file'"></options2>

        <div class="frame__content" v-for="its in item.img" :key="its">
          <img class="frame__content--img"  @click="selectzoom(its.id, 'img')" loading="lazy" v-if="its.type == 'image' && its.status == true" 
            :style=" its.status == true? { height: '150px', with: '150px' }: {}"
            :src="url + '/file/get-png/' + its.namefile" />

          <video class="frame__content--video" @click.self="selectzoom(its.id, 'video')" v-if="its.type == 'video' && its.status == true"
           :style="its.status == true ? { height: '150px', width: '150px' }: {}" controls>
            <source :src="url + '/file/geturl-video/' + its.namefile" type="video/mp4"/>
          </video>

          <a class="frame__content--file" v-if="its.type == 'file' && its.status == true" :href="'http://localhost:8081/file/filedowload/' + its.namefile ">
            <img class="content__icon" src="../../../../../../../assets/icon/archive.png"/>
            <div class="content__text">
              <strong>{{ convertdata(its.namefile) }}</strong>
              <span>{{ its.size + ' kb' }}</span>
            </div>
          </a>
        </div>
      <!-- end value file -->
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
.file__left {
  margin-left: 50px;
}
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

  /* .hightlight {
    animation: animate 0.6s ease-in forwards;
  }

  @keyframes animate {
    0%{
      opacity: 0 ;
    }
    100% {
      opacity: 1 ;
    }
  } */
.my_mess {
  margin-right: 10px;
}
.your_mess {
  margin-left: 10px;
}
.your__time {
  left: calc(100% + 5px) !important;
}
.my_mess > .file__frame {
  justify-content: end;
}
.your_mess > .file__frame {
  justify-content: start;
}
.my_mess > .file__frame > .file__frame--list {
  justify-content: end;
}
.your_mess > .file__frame > .file__frame--list {
  justify-content: start;
}
.my_mess > .file__frame > .file__frame--list> .option {
  right: calc(100% + 40px);
}
.your_mess > .file__frame > .file__frame--list > .option {
  left: calc(100% + 40px);
}

.file__frame {
  position: relative;
  z-index: 0;
  display: flex;
  gap: 10px;
  align-items: end;
  transition: .4s;
}

.file__frame--list {
  display: flex;
  position: relative;
  flex-wrap: wrap;
  justify-content: end;
  align-items: end;
  max-width: 610px;
}

.file__frame--list > div:not(.option) {
  display: flex;
  margin: 1px;
  box-sizing: border-box;
}
.frame__time {
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
.frame__feel {
  position: absolute;
  bottom: -8px;
  font-size: 16px;
  z-index: 10;
  border-radius: 10px;
  background: white;
  display: flex;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
}

.frame__content {
  position: relative;
  background: var(--color1);
  padding: 5px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  gap: 10px;
  color: white;
}
.content__text {
  display: flex;
  flex-direction: column;
}
.frame__content--img {
  width: 100%;
  height: 100%;
}
.file__frame--list:hover > .frame__time {
  transition-delay: 1.4s;
  opacity: 1 !important;
}
.content__icon {
  width: 30px;
  height: 30px;
}
.frame__content--video {
  object-fit: cover;
}
.frame__content--file {
  text-decoration: none;
  color:white;
  display:flex;
  align-items: center;
  gap: 10px;
}
</style>