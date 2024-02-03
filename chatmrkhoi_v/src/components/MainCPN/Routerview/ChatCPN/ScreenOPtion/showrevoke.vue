<template>
        <div v-if="showrevoke(item.listrevoke) == false && showrevoke2(item.listrevoke) == false && item.pin == null" class="unemss"
                     :style="authen.id == item.id_user ? { justifyContent: 'end' } : { justifyContent: 'start'}" >
                        <div class="ele_unmes">
                            <div  class="option2">
                                    <div @click="showmenuoption(item.id,'unmess')"  class="menu">
                                        <div @click="unmesschose(item)" :id="'option_unmess' + item.id" class="revoke">
                                            <div>Xóa bỏ</div>
                                            <div class="triangle-down"></div>
                                        </div>
                                        <span class="elem">
                                            <font-awesome-icon  :icon="['fas', 'ellipsis-vertical']" />
                                        </span>
                                    </div>
                           </div>
                            You've recalled a message
                        </div>
                    </div>
</template>
<script>
import data from "../../../../../handle/Screen_chat/index";
export default {
    props: {
        item: {
            typeof: Object,
            default: null,
        }
    },
    setup() {
        const { showrevoke,showrevoke2,showmenuoption,unmesschose, authen } = data();
        return {  showrevoke,showrevoke2,showmenuoption,unmesschose, authen }
    },
}
</script>
<style scoped>
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
.unemss:hover > .ele_unmes > .option2  {
    display: block !important;
}
.elem {
    width: 25px;
    height: 25px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 50%;
    cursor: pointer;
}
.elem:hover {
    background: rgb(201, 201, 201);
}
.unemss {
    display: flex;
}



.ele_unmes {
    position: relative;
    background: transparent;
    padding: 15px 10px;
    margin: 10px;
    border-radius:10px;
    color: rgb(186, 178, 178);
    border: 1px solid rgb(177, 170, 170);
}
.option2 {
    top: 50%;
    left: -40px;
    transform: translateY(-50%);
    position: absolute;
    display: none;
    color: black !important;
}

.option2 > .menu > .revoke > div:hover:not(.triangle-down)  {
    background: var(--color1); 
    color: white;
}
.option2 > .menu > .revoke > div:not(.triangle-down) {
    text-align: center;
    border-radius: 10px;
    cursor: pointer;
    padding: 10px;
}

.option2 > .menu > .revoke {
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
</style>