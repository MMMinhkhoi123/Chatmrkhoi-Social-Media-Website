<script setup>
  import MessCPN from "../../Routerview/ChatCPN/Sceenperson/Selectmess.vue"
  import revoke from "./ExtrasOption/FormRevoke.vue";
  import revoleother from "./ExtrasOption/FromRevokeother.vue";
  import showfeel from "./ExtrasOption/Fromshowfeel.vue";
  import showpin from "./ExtrasOption/Fromshowpin.vue";
  import frommove from "./ExtrasOption/Formmove.vue";
  import inputCPN from "../ChatCPN/ScreenOPtion/input.vue";
  import headers from "../ChatCPN/screenoption/header.vue";
  import showatch from "./ExtrasOption/Fromshowatch.vue";

  import handle_method from "../../../../handle/Screen_chat/index";
  import { computed } from "vue";
  import store from "../../../../store";

  import send_connect_private from "../../../../socket/handle_chat_private";
  
  const {
        checkscrooll,
        authen,
        array_mess,
        adddrop,
      } = handle_method();
      
      store.state.chat.runscroll = false;

      function check_typing() {
         const g = computed(() => {
          var x = false;
          store.state.chat.data_typing.forEach((e) => {
            if (
              e.room == localStorage.getItem("data-select") &&
              e.istyping == true
            ) {
              x = true;
            }
          });
          return x;
        });
        return g.value;
      }


      const enddrag = () => {
        const x = document.getElementById("drag");
        x.style.display = "none";
      };
  
      const showdrag = () => {
        const x = document.getElementById("drag");
        x.style.display = "flex";
      };

      const newmess = computed(() => {
            const m = store.getters["chat/get_data_room"](localStorage.getItem("data-select"));
            const m1 = m.filter((e) => e.pin == null && e.group_status == null);
            const x = m1.filter((e) => e.id_user != authen.value.id && e.listwatch.length  == 0 );
            if(store.state.avaible_chat.open_change_array) {
            }
            if(x.length == 0) {
                return []
            }
            return x;
        })

      function scrollcheck() {
        const div = document.querySelector(".form__row" + array_mess.value[array_mess.value.length -1].id);
        div.scrollIntoView({ behavior: "smooth" });
      }
</script>
<template>
    <div class="screen" v-if="$store.state.everyone.array_friend != null && $store.state.everyone.array_not_friend != null" >
      <!-- Top Header  -->
      <headers></headers>
  
      <div class="screen__form" id="screen__form" @dragover.prevent="showdrag()">

        <!-- content chat -->
        <div  class="screen__form--content" id="screen__form--content" v-if="authen && array_mess" @scroll="checkscrooll">
          <!-- mess chat -->
          <div v-for="item in array_mess" :key="item"  class="form__row" :class="'form__row' + (item == null ? null : item.id)" :id="'form__row' +  (item == null ? null : item.id)" :style="(item == null ? null : item.feel) == null ? { marginBottom: '5px'} : {}"   >  
            <MessCPN v-if="item" :item="item" ></MessCPN>
          </div>
          <!-- Acction typeing -->
          <div class="action_parent" :class="check_typing() == true ? 'show' : ''">
            <div class="action">
              <span></span>
              <span></span>
              <span></span>
            </div>
          </div>
        </div>
  
        <div  class="dropfile" id="drag" @dragleave.prevent="enddrag()" @drop.prevent="adddrop" >
          <div class="drop">
            {{ $t('TextMain.Chat.Drop') }}
          </div>
        </div>

        <inputCPN></inputCPN>
      </div>

        <div class="form__suggest" :class="newmess.length > 0 ? 'newmess': ''" @click.self="scrollcheck()">
            <font-awesome-icon class="form__suggest--icon" :icon="['fas', 'chevron-down']" />
            <strong class="form__suggest--text">
              {{ $t('TextMain.Chat.Screen.Mess.newmess')  }}
            </strong>
            <img class="form__suggest--img" src="../../../../assets/icon/bell-ring.png">
        </div>
       </div>
  
    <revoke v-if="$store.state.avaible_chat.unmess.status == true"></revoke>
    <revoleother
      v-if="$store.state.avaible_chat.unmess.status == false"
    ></revoleother>
    <showfeel v-if="$store.state.avaible_chat.feel.status == true"></showfeel>
    <showpin v-if="$store.state.avaible_chat.open_pin == true"></showpin>
    <frommove v-if="$store.state.avaible_chat.open_send == true"></frommove>
    <showatch v-if="$store.state.avaible_chat.open_watch == true"></showatch>
  
  </template>
  
  <script>
  import { computed } from "vue";
  import store from "../../../../store";
  import send_connect_private from "../../../../socket/handle_chat_private";
  import handle_method from "../../../../handle/Screen_chat/index";
  export default {
    updated() {
      const {
        array_mess,
      } = handle_method();

      if(store.state.chat.data_index_pin != null) {
         const div = document.querySelector(".form__row" + store.state.chat.data_index_pin.idmess);
         if(localStorage.getItem("pin") != null) {
          div.scrollIntoView();
         }
      }

      function handlewatch(id) {
        const x = store.state.chat.array_all_mess.filter((e) => e.id == id)[0];
        if(store.state.auth.authen != null && x != null) {
          if (x.id_user != store.state.auth.authen.id) {
            if (x.listwatch != null) {

              var check = false;
              x.listwatch.forEach((e) => {
                if (e.id == store.state.auth.authen.id) {
                  check = true;
                }
              });
              if (check == false) {
                const data_fake = {
                  timetamp: new Date().getTime(),
                  id: store.state.auth.authen.id,
                };
                store.state.chat.array_all_mess.forEach((e, index) => {
                  if (e.id == x.id) {
                    store.state.chat.array_all_mess[index].listwatch.push(
                      data_fake
                    );
                  }
                });
                const data = {
                  idperson: store.state.auth.authen.id,
                  idmess: x.id,
                };

                store.dispatch("chat/addwatch", data);

                data_fake.idmess = x.id;
                data_fake.watch = store.state.auth.authen.id;

                // send mess
                send_connect_private(
                  data_fake,
                  localStorage.getItem("data-select")
                );
              }
            }
          }
        }
      }


      const observer = new IntersectionObserver((entry) => {
        entry.forEach((e) => {
          if (e.isIntersecting == true) {
            handlewatch(Number(e.target.getAttribute("id").split("form__row")[1]));
          } 
        });
      });

      const list = document.querySelectorAll(".form__row");
      list.forEach((img) => {
        observer.observe(img);
      });


        array_mess.value.forEach((element) => {
        const div = document.querySelector("#form__row" + element.id);
        const div2 = document.querySelector("#file__frame" + element.id);

        if (element.listfeel != null) {
          if (element.listfeel.length > 0) {
              element.listfeel.forEach((e) => {
                if (e.type != "text" && div2 != null) {
                  div2.style.marginBottom = "20px";
                }
                if (e.type == "text" && div != null) {
                  div.style.marginBottom = "20px";
                }
              });
          }
        }
      });
      this.room = localStorage.getItem("data-select");
  
       if(store.state.chat.runscroll == false) {
          const parent = document.querySelector(".form__row" + array_mess.value.length);
            if ( array_mess.value.length > 0 &&  array_mess.value != null) {
              const parent2 = document.querySelector(
                ".form__row" +  array_mess.value[ array_mess.value.length - 1].id
              );
              if (parent2 != null) {
                parent2.scrollIntoView();
              }
            }
            if (parent != null) {
              parent.scrollIntoView({ behavior: "smooth" });
            }
       }
    },
  };
  </script>
  
  <style scoped>
  .form__suggest--text {
    pointer-events: none;
  }
  .newmess {
     opacity: 1 !important;
  }
   .form__suggest:hover {
     background: var(--color1) ;
   }
  .form__suggest {
    position: absolute;
    bottom: 100px;
    right: 10px;
    cursor: pointer;
    display: flex;
    align-items: center;
    gap: 10px;
    opacity: 0;
    z-index: 13;
    transition: 0.3s;
    background: rgba(255, 255, 255, 0.548);
    border-radius:30px;
    padding: 5px 20px;
  }
  .form__suggest--text {
  }
  .form__suggest--img {
    width: 40px;
    height: 40px;
    animation: bell 0.6s ease-in-out infinite;
  }

  @keyframes bell {
    0%{
      transform: rotate(0deg);
    }
    25% {
      transform: rotate(10deg);
    }
    50% {
      transform: rotate(0deg);
    }
    75% {
      transform: rotate(15deg);
    } 
    100% {
      transform: rotate(0deg);
    } 
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
  .screen {
    width: 100%;
    height: 100%;
    border-radius: 10px;
    background: #dedfff;
    position: relative;
    display: flex;
    flex-direction: column;
  }
  .screen__form {
    position: relative;
    z-index: 11;
    height: 100%;
  }
  .screen__form--content {
    display: flex;
    flex-direction: column;
    position: absolute;
    inset: 0;
    overflow-y: scroll;
    overflow-x: hidden;
    padding: 10px;
    padding-bottom: 130px;
}

  .form__row {
    animation: hiden 1.2s ease-in-out forwards ;
    position: relative;
    justify-content: space-between;
    width: 100%;
    transition: .4s;
  }
  
  @keyframes hiden {
    0%{
      opacity: 0;
    }
    100%{
      opacity: 1;
    }
  }
  
  /* mess main */
  #form__row--main {
    display: flex;
    flex-direction: column;
  }
  .my_mess > .row__item {
    justify-content: end;
  }
  .your_mess > .row__item {
    justify-content: start;
  }
  .your_mess > .row__item > .item_row_text {
    justify-content: start;
  }
  .my_mess > .row__item > .item_row_text {
    justify-content: end;
  }
  
  .my_mess > .row__item > .item_row_text > p {
    border-radius: 10px 10px 0 10px;
  }
  .your_mess > .row__item > .item_row_text > p {
    border-radius: 10px 10px 10px 0px;
    background: white !important;
    color: black;
  }
  
  .my_mess > .row__item > .row__file--list> .option {
    right: calc(100% + 40px);
  }
  .your_mess > .row__item > .row__file--list > .option {
    left: calc(100% + 40px);
  }
  .my_mess > .row__item > .item_row_text > p > .option {
    right: calc(100% + 40px);
  }
  
  .your_mess > .item_row > .item_row_text > p > .option {
    left: calc(100% + 40px);
  }
  
  .my_mess > .item_row > .item_row_text > p > .option > .smile > .emoji {
    display: none;
    left: 0;
  }
  
  .your_mess > .item_row > .item_row_text > p > .option > .smile > .emoji {
    display: none;
    right: 0;
  }
  
  /* 
  reply */
  .row__reply {
    display: flex;
  }
  .listreply {
    display: flex;
    width: 400px;
    word-break: break-all;
  }
  
  .your_mess > .row__reply > .listreply {
    justify-content: start;
  }
  .my_mess > .row__reply > .listreply {
    justify-content: end;
  }
  .row__left {
    margin-left: 70px;
  }
  
  
  /* mess file */
  .row__file {
    position: relative;
    z-index: 0;
    display: flex;
  }
  
  .row__file--list {
    display: flex;
    position: relative;
    flex-wrap: wrap;
    justify-content: end;
    max-width: 610px;
  }
  
  .row__file--list > div:not(.option) {
    display: flex;
    margin: 1px;
    box-sizing: border-box;
  }
  .my_mess > .row__item > .row__file--list {
    justify-content: end;
  }
  .your_mess > .row__item > .row__file--list {
    justify-content: start;
  }
  
  
  /* time send */
  .file__time {
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
  
  .row__file--list:hover > .file__time {
    transition-delay: 1.4s;
    opacity: 1 !important;
  }
  .your__time {
    left: calc(100% + 5px) !important;
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
  
  
  
  /* mess text */
  .row__text {
    display: flex;
    width: 100% !important;
  }
  .row__text--content {
    color: white;
    display: flex;
    align-items: center;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
    width: 500px;
    word-break: break-all;
  }
  /* owner send */
  .text__owner {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px;
    padding: 10px;
    font-size: 15px;
    font-weight: 900;
    color: black;
  }
  .text__owner--img {
    width: 50px;
    height: 50px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    border-radius: 50%;
  }
  
  /* main text */
  .text__main {
    position: relative;
    min-width: 70px;
    padding: 15px 10px;
    background: #4f48ed;
    margin: 0;
  }
  .text__main:hover > .time {
    transition-delay: 1.4s;
    opacity: 1 !important;
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
  .watchprivate {
    margin-left: auto;
  }
  
  .row__watch--user {
    position: relative;
  }
  .row__watch--user:hover > .showtime {
    opacity: 1 !important;
  }
  .row__watch--user:hover > .showtimegroup {
    opacity: 1;
  }
  .img_watch {
    width: 20px;
    height: 20px;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
    border-radius: 50%;
  }
  
  
  .myshowtimegroup > .row__watch--user > .showtimegroup {
    left: 0 !important;
  }
  .yourshowtimegroup > .row__watch--user > .showtimegroup {
    right: -8px !important;
  }
  
  .yourshowtimegroup > .row__watch--user > .showtimegroup > .triangle-up {
    right: 8px;
  }
  
  .triangle-up {
    width: 0;
    height: 0;
    bottom: 100%;
    position: absolute;
    border-left: 5px solid transparent;
    border-right: 5px solid transparent;
    border-bottom: 7px solid rgb(62, 60, 60);
  }
  
  .showtimegroup {
    position: absolute;
    background: rgb(62, 60, 60);
    color: white;
    padding: 10px;
    top: calc(100% + 5px);
    opacity: 0;
    border-radius: 5px;
    white-space: nowrap;
    z-index: 11;
    font-size: 13px;
  }
  .myshowtimegroup {
    justify-content: start;
  }
  .yourshowtimegroup {
    justify-content: end;
  }
  
  .showtime {
    position: absolute;
    background: rgb(62, 60, 60);
    color: honeydew;
    opacity: 0;
    font-size: 13px;
    padding: 10px;
    border-radius: 5px;
    top: 100%;
    right: 0;
    white-space: nowrap;
    max-width: 299px;
  }
  .text {
    font-size: 13px;
    margin: 0;
    padding: 0;
  }
  
  .statusMess {
    font-size: 13px;
  }
  
  .show {
    opacity: 1 !important;
    transition: 0.1s !important;
  }
  
  
  
  
  
  
  
  .my_mess {
    margin-right: 10px;
  }
  .your_mess {
    margin-left: 10px;
  }
  
  
  
  
  .add_emoji {
    width: 40px;
    height: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
    background: rgb(205, 203, 203);
    border-radius: 50%;
  }
  .filereply {
    opacity: 0.5;
  }
  
  
  .dropfile {
    position: absolute;
    inset: 0;
    display: none;
    justify-content: center;
    align-items: center;
    background: rgba(126, 126, 126, 0.637);
  }
  .dropfile > div {
    font-size: 20px;
    color: rgb(243, 243, 243);
    font-weight: 700;
    padding: 50px;
    border-style: dashed;
  }
  
  .addupload {
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 50px;
    color: #1c1c1d;
  }
  
  .action_parent {
    padding: 20px;
    opacity: 0;
    transition: 2s;
  }
  
  .action {
    display: flex;
    gap: 5px;
    margin-left: 10px;
  }
  
  @keyframes animations {
    0% {
      transform: translateY(0);
    }
    70% {
      transform: translateY(1px);
      background: rgb(140, 138, 138);
    }
    100% {
      transform: translateY(0);
    }
  }
  .action > span {
    animation: animations 1s ease infinite;
    display: block;
    border-radius: 50%;
    width: 7px;
    height: 7px;
    background: #4f48ed;
  }
  .highttext {
    padding-left: 10px;
  }
  .content-text {
    width: 700px;
    gap: 2px;
  }
  .tabb {
    width: 2px;
    height: 20px;
    background: #000;
  }
  
  @keyframes hidenshow {
    0% {
      opacity: 0;
    }
    80% {
      opacity: 1;
    }
  }
  .status > span:nth-child(2) {
    width: 30px;
    height: 30px;
    background: #4f48ed;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 50%;
    margin-right: 10px;
  }
  .status > span:nth-child(1) {
    color: black;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
  }
  
  
  </style>
  