<template>
    <div class="menu">
        <div v-if="authen" @click.self="collap()"  class="menu__action" id="menu__action">

            <!-- infomation user -->
            <div class="menu__action--auth">
                <div class="action__frame">
                    <img class="action__avata" id="action__avata"
                    :src="url + '/file/get-png/' + authen.images"
                    v-if="authen.type_img != 'rs'">
                    <imgx class="action__avata" id="action__avata" :name="authen.images" v-if="authen.type_img == 'rs'"></imgx>
                </div>

                <h3 id="action__text" class="action__name action__item--text">
                    {{ authen.fullname }}
                </h3>
            </div>

            <!-- list item top-->
            <ul class="menu__action--list">
                <li class="action__item" :class="select == 3 ? 'active': ''"
                @click="$router.push({ name: 'profile', query: { action: 'my'} }),selects(3)" >
                    <font-awesome-icon id="action__item--icon"  class="action__item--icon" :icon="['fas', 'user']" />
                    <span  id="action__text" class="action__item--text">
                        {{ $t('TextMain.Menu.ItemProfile') }}
                    </span>
                </li>
                
                <li class="action__item" :class="select == 1 ? 'active': ''"
                @click="select_chat(), selects(1)" >
                    <font-awesome-icon id="action__item--icon"  class="action__item--icon" :icon="['fas', 'comment']" />
                    <span  id="action__text" class="action__item--text" >
                        {{ $t('TextMain.Menu.ItemChats') }}
                    </span>
                </li>
                <li class="action__item"  :class="select == 5 ? 'active': ''"
                @click="$router.push({ name: 'group', query: {  action: 'all'}}),selects(5)">
                    <font-awesome-icon  id="action__item--icon" class="action__item--icon" :icon="['fas', 'people-group']" />
                    <span  id="action__text" class="action__item--text" >
                        {{ $t('TextMain.Menu.ItemGroup') }}
                    </span>
                </li>
                <li class="action__item"  :class="select == 2 ? 'active': ''"
                 @click="this.$router.push({ name: 'everyone' }), selects(2)" >
                    <font-awesome-icon id="action__item--icon"  class="action__item--icon" :icon="['fas', 'people-arrows']" />
                    <span  id="action__text" class="action__item--text">
                        {{ $t('TextMain.Menu.ItemEveryone') }}
                    </span>
                </li>
            </ul>
             <!-- list item bottom-->
            <ul class="menu__action--list default">
                <li class="action__item" :class="select == 4 ? 'active': ''"
                  @click="$router.push({ name: 'setting', query: { action: 'profile' }}),selects(4)" >
                    <font-awesome-icon id="action__item--icon"  class="action__item--icon" :icon="['fas', 'gear']" />
                    <span id="action__text" class="action__item--text">
                        {{ $t('TextMain.Menu.ItemSetting') }}
                    </span>
                </li>
            </ul>
            
        </div>
    </div>
</template>
<script>
import { computed, ref } from 'vue'
import imgx from "../../components/NewCPN/imgtake.vue";
import store from '../../store/index';
import data  from "../../handle/Screen_chat/index";
import { useRouter } from 'vue-router';
export default {
    components: {
        imgx,
    },
    updated() {
        localStorage.getItem("select-menu") != null ? this.select = Number(localStorage.getItem("select-menu")): null;
    },
    setup() {
        const { url, authen } = data();
        const select = computed(() => {
            if(choice.value != choice.value) {}
            return localStorage.getItem("select-menu")
        } );
        const choice = ref(false);
        const router = useRouter();
        const run_collap = ref(false);

        // method run collap
        const collap = () => {
            run_collap.value = ! run_collap.value;
            const main_center = document.querySelector(".main__center") 
            const menu_action = document.getElementById("menu__action")
            const text = document.querySelectorAll("#action__text")
            const icon = document.querySelectorAll("#action__item--icon")
            const avata = document.getElementById("action__avata");

            run_collap.value == true && main_center != null 
            ? show(main_center, menu_action, text, icon, avata) 
            : main_center != null 
              ? hide(main_center, menu_action, text, icon, avata)
              : null;
        }

        // method set class 1
        function show(main_center, menu_action, text, icon, avata) {
            main_center.classList.add("show_action")
            menu_action.classList.add("show");
            avata.classList.add("show_avata")
            text.forEach(e => {
                e.classList.add("show_text")
            })
            icon.forEach((e) => {
                e.classList.add("show_icon")
            })
        }

        // method set class 2
        function hide(main_center, menu_action, text, icon, avata) {
            main_center.classList.remove("show_action")
            avata.classList.remove("show_avata")
            menu_action.classList.remove("show");
            text.forEach(e => {
                e.classList.remove("show_text")
            })
            icon.forEach((e) => {
                e.classList.remove("show_icon")
            })
        }

        // method config  item chat when click
        const select_chat = () => {
            const data = store.state.chat.array_connect[0];
            data == null 
            ? null 
            :  (localStorage.setItem("data-select",data.coderoom)
                , data.idgroup == null 
                   ?  router.push({ name: "chat", query: { id: data.idfriend }})
                   : router.push({ name: "chat", query: { idgroup: data.idgroup }})
                );
        }

        // method select only item
        const selects = (value) => {
            localStorage.setItem("select-menu", value);
            choice.value = !choice.value;
        }

        if(authen.value.images == null) {
            store.dispatch("auth/authen",localStorage.getItem("token"));
        }

        return { authen, url, collap, run_collap, select_chat,select,selects}
    },
}
</script>
<style scoped>
.menu {
    position: fixed;
    top: 0;
    left: 0;
    bottom: 0;
    box-sizing: border-box;
    transition: 0.8s;
    width: 200px;
}
.menu__action {
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
    transition: 0.6s;
    background: var(--color3);
} 
.menu__action--auth {
    white-space: nowrap;
    position: relative;
    display: flex;
    align-items: center;
    gap: 10px;
    margin: 0 20px;
    padding: 30px 0;
    padding-left: 50px;
    overflow: hidden;
    border-bottom: 2px solid rgb(255, 255, 255);
 }
 .action__frame {
    display: flex;
    align-items: center;
}
 .action__avata {
    position: absolute;
    left: 0 ;
    width: 40px;
    height: 40px;
    background: white;
    object-fit: cover;
    border-radius: 50%;
}
.action__avata ,
.action__item--icon {
    transition-duration: 0.6s;
    z-index: 1;
}


.action__name {
    color: #FFF;
    font-weight: 700;
    font-size: 13px;
 }

 .action__name, .action__item--text {
    transition: 1s;
    position: relative;
    z-index: 0;
    white-space: nowrap;
}

.menu__action--list:not(.default) {
    border-bottom: 2px solid rgb(255, 255, 255);
    
}
.menu__action--list  {
    list-style-type: none;
    margin: 0 20px;
    padding: 0;
    padding: 20px 0;
}

.action__item {
    position: relative;
    display: flex;
    gap: 27px;
    padding: 10px 0;
    padding-left: 40px;
    border-radius: 30px;
    align-items: center;
    color: #FFF;
    cursor: pointer;
}
.action__item :hover {
    color: var(--color1);
}
.active {
    color: var(--color1);
}

.action__item--icon {
    position: absolute;
    left: 0;
    font-size: 15px;
}



/* value config */
.show_icon {
    transition-duration: 0.6s !important;
    left: calc(50% - 7.5px) !important;
}
.show_avata {
    transition-duration: 0.6s !important;
    left: calc(50% - 25px) !important;
}
.show {
    right: 100px !important;
}
.show_text {
    opacity: 0;
    transform: translateX(-100px);
}

</style>