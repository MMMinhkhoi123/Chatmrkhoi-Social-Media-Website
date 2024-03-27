<template>
    <div  class="option" v-if="authen.id != item.id_user">
        <div class="option__smile"  @mouseover.self="showmenufeel(item.id, type)">
            <font-awesome-icon :icon="['far', 'face-smile']" />
        </div>
        <div class="option__rep" @click="reply(item.id, type)">
            <font-awesome-icon :icon="['fas', 'reply']" />
        </div>

        <div class="option__menu" @click="showmenuoption(item.id , type)">
            <div class="option__menu--list"  :id="'option_'+ type + item.id">
                <div class="menu__item" @click="check_move(item, type)">
                 {{ $t('TextMain.Chat.Screen.Mess.option.transaction') }}
                </div>
                <div class="menu__item" v-if="checkinclupin(item.id, type) == true" @click="pin_mess(item)">
                    {{ $t('TextMain.Chat.Screen.Mess.option.pin') }}
                </div>
                <div class="menu__item" @click="checkunghim(item.id)" v-if="checkinclupin(item.id, type) == false">
                    {{ $t('TextMain.Chat.Screen.Mess.option.Unpin') }}
                </div>
                <div class="menu__item" v-if="item.id_user != authen.id" @click="$store.state.avaible_chat.unmess.status = true , $store.state.avaible_chat.unmess.idmess = item.id">
                    {{ $t('TextMain.Chat.Screen.Mess.option.remove') }}
                    </div>
                <div class="menu__item" v-if="item.id_user == authen.id" @click="$store.state.avaible_chat.unmess.status = false,$store.state.avaible_chat.unmess.idmess = item.id"> 
                    {{ $t('TextMain.Chat.Screen.Mess.option.remove') }}
                </div>
                <div class="triangle-down" :id="'triangle_' + type + item.id"></div>
            </div>
            <font-awesome-icon  :icon="['fas', 'ellipsis-vertical']" />
        </div>

            <!-- menu feel -->
        <div  class="option__emoji" :id="'feel_'+ type + item.id" @mouseleave="outfeel(item.id, type)">
            <div class="child_emji">
                <div class="child_emji--item" @click.prevent="post_feel(item.id, itemx.character, type)" v-for="itemx in icon" :key="itemx">{{ itemx.character }}</div>
                <div class="menu__close child_emji--item"  v-if="array_icon(item.listfeel, type).length > 0"  >
                    <span class="menu__close--icon" @click.prevent="deletefeel(item.id, type)">
                        <font-awesome-icon :icon="['fas', 'xmark']" />
                    </span>
                </div>
            </div>
        </div>
        <!-- end menu feel -->
    </div>
</template>
<script>
import data from "../../../../../handle/Screen_chat/index";
export default {
    props: {
        item: {
            typeof: Object,
            default: null,
        },
        type: {
            typeof: String,
            default: 'me'
        }
    },
    setup() {
        const { authen, room, checkunghim,showmenufeel,reply,showmenuoption,check_move,checkinclupin,pin_mess,outfeel,post_feel,icon,array_icon,deletefeel } = data();
        return { authen,room, checkunghim,showmenufeel,reply,showmenuoption,check_move,checkinclupin,pin_mess,outfeel,post_feel,icon,array_icon,deletefeel };
    },
    created() {
        this.room = this.$route.query.rom;
    },
    updated() {
        this.room = this.$route.query.rom;
    },
}
</script>
<style scoped>

.option {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    display: none;
    gap: 15px;
    font-size: 20px;
    z-index: 20;
}
.option__menu  {
    position: relative;
    cursor: pointer;
}



.menu__item:hover:not(.triangle-down) {
    background: var(--color1); 
}
.menu__item:not(.triangle-down) {
    text-align: center;
    border-radius: 10px;
    cursor: pointer;
    padding: 10px;
    background: var(--colorBehindLess);
}


.option__menu--list{
    position: absolute;
    transition: 0.3s;
    display: block;
    padding: 5px;
    opacity: 0;
    pointer-events: none;
    min-width: 150px;
    left: 50%;
    z-index: 11;
    background: var(--colorBehindLess);
    border-radius: 10px;
    transform: translateX(-50%) translateY(-10px);
}
.menu__close  {
    display: flex;
    align-items: center;
}
.menu__close--icon {
    width: 25px;
    height: 25px;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 50%;
}
.triangle-down {
    position: absolute;
	width: 0;
	height: 0;
    left: 50%;
    transform: translateX(-50%);
	border-left: 10px solid transparent;
	border-right: 10px solid transparent;
} 
.option > div:hover:not(.option__emoji){
    background: rgb(194, 193, 193);
}
.option > div:not(.option__emoji)  {
    font-size: 17px;
    width: 30px;
    height: 30px;
    margin: 0 5px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 50%;
    cursor: pointer;
}

.file__frame:hover > .file__frame--list > .option {
    display: flex !important;
}
.frame__text:hover > .frame__text--content  > .text__main > .option {
    display: flex !important;
}

.option__smile,
.option__rep,
.option__menu {
    position: relative;
    color: var(--colorText);
}


.option__emoji {
    transform: scale(0);
    transition: 0.2s;
    opacity: 0;
    pointer-events: none;
    position: absolute;
    inset: 0;
    left: -50%;
    transform: translatex(50%);
    z-index: 11;
}


.child_emji {
    display: flex;
    position: absolute;
    background: var(--colorBehindLess);
    padding: 6px;
    border-radius: 30px;
}
.child_emji--item  {
    transition: 0.3s;
}
.child_emji--item:hover {
    transform: scale(1.3);
    cursor: pointer;
}


.showm_option {
    opacity: 1 !important;
    pointer-events: visible !important;
    transform: translateX(-50%) translateY(0px) !important;
}
.show_emoji {
    pointer-events: visible !important;
    opacity: 1 !important;
    transform: scale(1) !important;
}
</style>