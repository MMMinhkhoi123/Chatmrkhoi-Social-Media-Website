<script setup>
import data from "../../../../../../handle/Screen_chat/index";
const { showrevoke,showrevoke2,showmenuoption,unmesschose, authen } = data();
</script>
<template>
    <div class="revoke" v-if="showrevoke(item.listrevoke) == false
        && showrevoke2(item.listrevoke) == false && item.pin == null" 
        :style="authen.id == item.id_user ? { justifyContent: 'end' } : { justifyContent: 'start'}" >
        <div class="revoke__frame">
            <div class="revoke__frame--menu">
                    <div class="frame__menu" @click="showmenuoption(item.id,'unmess')">
                        <div  class="frame__menu--revoke" @click="unmesschose(item)" :id="'option_unmess' + item.id">
                            <div>
                                {{ $t('TextMain.Chat.Screen.Mess.option.delete') }}
                            </div>
                            <div class="triangle-down"></div>
                        </div>
                        <span class="frame__menu--icon">
                            <font-awesome-icon  :icon="['fas', 'ellipsis-vertical']" />
                        </span>
                    </div>
            </div>
            {{ $t('TextMain.Chat.Screen.Mess.option.revoke') }}
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
}
}
</script>
<style scoped>
.revoke {
display: flex;
}
.revoke:hover > .revoke__frame > .revoke__frame--menu  {
display: block !important;
}
.revoke__frame {
position: relative;
background: transparent;
padding: 15px 10px;
margin: 10px 0;
border-radius:10px;
color: rgb(186, 178, 178);
border: 1px solid rgb(177, 170, 170);
}
.revoke__frame--menu {
top: 50%;
left: -40px;
transform: translateY(-50%);
position: absolute;
display: none;
color: black !important;
}

.frame__menu--revoke{
position: absolute;
transition: 0.3s;
bottom: calc(100% + 10px);
display: block;
background: white;
padding: 5px;
opacity: 0;
pointer-events: none;
min-width: 120px;
left: 50%;
z-index: 11;
border-radius: 10px;
transform: translateX(-50%) translateY(-10px);
}

.frame__menu--revoke:hover:not(.triangle-down)  {
background: var(--color1); 
color: white;
}
.frame__menu--revoke:not(.triangle-down) {
text-align: center;
border-radius: 10px;
cursor: pointer;
padding: 10px;
}
.frame__menu--icon {
width: 25px;
height: 25px;
display: flex;
justify-content: center;
align-items: center;
border-radius: 50%;
cursor: pointer;
}
.frame__menu--icon:hover {
background: rgb(201, 201, 201);
}

.triangle-down {
position: absolute;
top: 100%;
width: 0;
height: 0;
left: 50%;
transform: translateX(-50%);
border-left: 10px solid transparent;
border-right: 10px solid transparent;
border-top: 10px solid #ffffff;
} 
.showm_option {
opacity: 1 !important;
pointer-events: visible !important;
transform: translateX(-50%) translateY(0px) !important;
}
</style>