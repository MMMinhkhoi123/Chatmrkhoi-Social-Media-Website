<script setup>
import handle from "../../../../../../handle/Screen_chat/index";
import replyCPN from "../../../ChatCPN/ScreenOPtion/listreply.vue";
import ListfileCPN from "../Selectmess/MessMain/MessMainfile.vue";
import ListtextCPN from "../Selectmess/MessMain/MessMaintext.vue";
import WachCPN from "../Selectmess/MessMain/MessWatch.vue";
import orther from "../../../../../../handle/Common/index";
const {
  checkshowmess,
  array_mess,
  data_profile,
  authen,
} = handle();

const {  url_img  } = orther();
</script>
<template>
  <div class="form__row--main"  :class="authen.id == item.id_user ? 'my_mess' : 'your_mess'" :id="'mess'+ item.id"   v-if="checkshowmess(item.listrevoke) == true && item.pin == null" >
        <div  class="row__reply row__item"  :class=" item.id_user != authen.id ? 'row__left' : '' " >
          <replyCPN v-if="item" :item="item" />
        </div>
        <ListfileCPN :item="item"></ListfileCPN>
        <ListtextCPN :item="item"></ListtextCPN>

    <WachCPN :item="item" :array_mess="array_mess"></WachCPN>
    
  </div>
</template>
<script>
export default {
  props: {
    item: {
      typeof: Object,
      default: null,
    },
  },
};
</script>
<style scoped>




.myshowtimegroup {
  justify-content: start;
}
.yourshowtimegroup {
  justify-content: end;
}



/* mess main */
.form__row--main {
  position: relative;
}

/* 
reply */
.row__reply {
  display: flex;
  justify-content: end;
}
.listreply {
  display: flex;
  word-break: break-all;
}

/* mess feel show  */
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

.file__content {
  position: relative;
  background: var(--color1);
  padding: 5px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  gap: 10px;
  color: white;
}
.file__content--img {
  width: 100%;
  height: 100%;
}
.content__icon {
  width: 30px;
  height: 30px;
}
.content__download {
  position: absolute;
  right: 10px;
  top: 50%;
  transition: 0.2s;
  transform: translateY(-50%);
}
.content__download:hover {
  transform: translateY(-50%) scale(1.2);
}
.row__left {
justify-content: start;
}
</style>
