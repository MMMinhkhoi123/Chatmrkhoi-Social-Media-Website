<script setup>
import other from "../../../../../../../handle/Common/index";
import handle from "../../../../../../../handle/Screen_chat/index";
const { authen, url_img } = other();
const { data_profile  } = handle();

const convertimewatch = (item, key) => {
  const date = new Date(item.timetamp);
  return (
    data_profile(item.id).fullname +
    key +
    date.getHours() +
    ":" +
    date.getMinutes()
  );
};

const showwatch = (id) => {
  localStorage.setItem("messwatch", id);
};


const convert_array = (array) => {
  return array.filter((e, index) => index < 2);
};
</script>
<template>
      <!--end detail mess chat -->
    <div  class="row__watch" :class="$route.query.idgroup == null ? 'watchprivate' : 'watchpublic'" 
        v-if="item.feel == null && item.id_user == authen.id && $route.query.idgroup == null &&  item.id == array_mess[array_mess.length - 1].id "  >
        <!-- genegarion  -->
        <div  class="row__watch--user" v-for="itemm in convert_array(item.listwatch)" :key="itemm" >
            <img  class="watch__img" v-if="data_profile(itemm.id).type_img == 'rs'"  :src="'/src/assets/images/avata_org/' + data_profile(itemm.id).images" />
            <img class="watch__img" v-if="data_profile(itemm.id).type_img == 'cpt'"  :src="url_img + data_profile(itemm.id).images" />
            <span class="watch__time">
              <div class="triangle-up"></div>
              {{ convertimewatch(itemm, $t('TextMain.Chat.Screen.Mess.watch')) }}
            </span>
        </div>
    </div>



    <div  class="row__watch"  :class="item.id_user == authen.id ? 'myshowtimegroup' : 'yourshowtimegroup'" v-if="
        $route.query.idgroup != null &&
        item.start == false &&
        item.pin == null &&
        item.feel == null &&
        item.group_status == null">
      <!-- genegarion  -->
      <div class="row__watch--user"  v-for="itemm in convert_array(item.listwatch)" :key="itemm"  @click="($store.state.avaible_chat.open_watch = true), showwatch(item.id) " >
        <img class="watch__img"  v-if="data_profile(itemm.id).type_img == 'rs'"  :src="'/src/assets/images/avata_org/' + data_profile(itemm.id).images"  />
        <img  class="watch__img" v-if="data_profile(itemm.id).type_img == 'cpt'" :src="url_img + data_profile(itemm.id).images" />
        <span class="watch__time">
          <div class="triangle-up"></div>
          {{ convertimewatch(itemm,$t('TextMain.Chat.Screen.Mess.watch')) }}
        </span>
      </div>
      <span v-if="item.listwatch.length > 2">
        +{{ item.listwatch.length - 2 }}</span
      >
      <p class="text" v-if="item.listwatch.length > 2">{{ $t('TextMain.Chat.Screen.Mess.audience') }}</p>
    </div>
</template>
<script>
export default {
    props: {
        item: {
            typeof: Object,
            default: null,
        },
        array_mess: {
            typeof: Array,
            default: [],
        }
    }
}
</script>
<style scoped>

/* watch */
.row__watch {
  margin-top: 4px;
  gap: 2px;
  display: flex;
  align-items: center;
  padding: 0 20px;
}
.parent_watch > span {
  width: 20px;
  height: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 13px;
  background: white;
  font-weight: 700;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}
.row__watch--user {
  position: relative;
}
.row__watch--user:hover > .showtime {
  opacity: 1 !important;
}
.row__watch--user:hover > .watch__time {
  opacity: 1;
}
.watch__img {
  width: 20px;
  height: 20px;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  border-radius: 50%;
}

.myshowtimegroup > .row__watch--user > .watch__time {
  left: 0 !important;
}
.yourshowtimegroup > .row__watch--user> .watch__time {
  right: -8px !important;
}

.yourshowtimegroup > .row__watch--user > .watch__time > .triangle-up {
  right: 8px;
}
.yourshowtimegroup {
  display: flex;
  justify-content: end;
}
.myshowtimegroup {
  display: flex;
  justify-content: start;
}
.watchprivate {
  display: flex;
  justify-content: end;
}
.watchprivate > .row__watch--user > .watch__time {
  right: -8px;
}
.watchprivate > .row__watch--user > .watch__time > .triangle-up {
  right: 8px;
} 

.triangle-up {
  width: 0;
  height: 0;
  top: 100%;
  position: absolute;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-top: 7px solid rgb(62, 60, 60);
}

.watch__time {
  position: absolute;
  background: rgb(62, 60, 60);
  color: white;
  padding: 10px;
  bottom: calc(100% + 5px);
  opacity: 0;
  border-radius: 5px;
  white-space: nowrap;
  pointer-events: none;
  font-size: 13px;
}

</style>