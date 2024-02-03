<script setup>
import { computed, ref } from 'vue';
import data from "../../../../../handle/Screen_chat/index";
import store from '../../../../../store';
const { url } = data();
const dataimg = ref(null);
const datavideo = ref(null);

const checkleft = computed(() => {
    var checkx = true;
    if(check.value != check.value) {}
    store.state.chat.data_detail_zoom.forEach((item , index) => {
        if(item.id == Number(localStorage.getItem("idfile"))) {
            if(index == 0) {
                checkx = false;
            }
        }
    })
    return checkx;
})

const checright = computed(() => {
    var checkx = true;
    if(check.value != check.value) {
    }
    store.state.chat.data_detail_zoom.forEach((item , index) => {
        if(item.id == Number(localStorage.getItem("idfile"))) {
            if(index ==  store.state.chat.data_detail_zoom.length -1 ) {
                checkx = false;
            }
        }
    })
    return checkx;
})

store.dispatch("chat/get_data_detail_zoom", localStorage.getItem("data-select"));

const next = () => {
    var code = null;
    store.state.chat.data_detail_zoom.forEach((item , index) => {
        if(item.id == Number(localStorage.getItem("idfile"))) {
            code = store.state.chat.data_detail_zoom[index + 1].id;
            check.value = store.state.chat.data_detail_zoom[index + 1].id;
            if(store.state.chat.data_detail_zoom[index + 1].type == 'image') {
                datavideo.value = null;
                dataimg.value = store.state.chat.data_detail_zoom[index + 1].name;
            } else {
                dataimg.value = null;
                datavideo.value =store.state.chat.data_detail_zoom[index + 1].name;
            }
        }
    })
    localStorage.setItem("idfile", code);
}

const pre = () => {
    datam.value.forEach((item , index) => {
        if(item.id == Number(localStorage.getItem("idfile"))) {
            check.value = datam.value[index - 1].id;
            localStorage.setItem("idfile", datam.value[index - 1].id)
            if(datam.value[index - 1].type == 'image') {
                datavideo.value = null;
                dataimg.value = datam.value[index - 1].name;
            } else {
                dataimg.value = null;
                datavideo.value = datam.value[index - 1].name;
            }
        }
    })
}

const check = ref(null);


const datam = computed(() => {
    if(check.value != check.value) {
    }
    // get index chon
    const array_parent =  store.state.chat.data_detail_zoom;
    var index = 0;
    if(array_parent != null) {
        for(var i = 0; i < array_parent.length ; i ++ ) {
            if(array_parent[i].id == Number(localStorage.getItem("idfile"))) {
                index = i;
            }
        }
        if (array_parent.length > 10) {
        var indexstart =  index - 5 < 0 ? 0 : index - 5 ;
        var indexend = index + 5 > (array_parent.length - 1) ? array_parent.length :  index + 5;

        const array_use = array_parent.filter((e, index) => index >= indexstart && index <= indexend);
            if(array_use.length < 10) {
                if(indexstart == 0)  {
                    // add index end
                    array_parent.forEach((element, index)  => {
                        if(array_use.length < 10 && index > indexend) {
                            array_use.push(element);
                        }
                    });
                } else {

                    // add index start
                    array_parent.forEach((element, index) => {
                        if(array_use.length < 10 && index < indexstart) {
                            array_use.unshift(element);
                        }
                    });
                }
            }
        return array_use;
        }
         else {
        var indexstart =  index - 5 < 0 ? 0 : index - 5 ;
        var indexend = index + 5 > (array_parent.length - 1) ? array_parent.length :  index + 5;
        const array_use = array_parent.filter((e, index) => index >= indexstart && index <= indexend);
            if(array_use.length < array_parent.length) {
                if(indexstart == 0)  {
                    // add index end
                    array_parent.forEach((element, index)  => {
                        if(array_use.length < array_parent.length && index > indexend) {
                            array_use.push(element);
                        }
                    });
                } else {
                    // add index start
                    array_parent.forEach((element, index) => {
                        if(array_use.length < array_parent.length && index < indexstart) {
                            array_use.unshift(element);
                        }
                    });
                }
            }
        return array_use;
        }
    }
    
    return [];
})

const searchfile = (value) => {
    if(store.state.chat.data_detail_zoom == null) {
        return null;
    }
    return store.state.chat.data_detail_zoom.filter((e) => e.id == value)[0].name
}

const checkactive = (id) => {
    if(id == Number(localStorage.getItem("idfile"))) {
        return true;
    }
    return false;
}

const mex = computed(() => {
    if(check.value != check.value) {
    }
    return datam.value.filter((e) => e.id == Number(localStorage.getItem("idfile")));
});
const select = (value) => {
    if(value.type == 'image') {
        datavideo.value = null;
        dataimg.value = value.name;
    } else {
        dataimg.value = null;
        datavideo.value = value.name;
    }
    check.value = value;
    localStorage.setItem("idfile", value.id);
}

const dowload = () => {
    store.dispatch("chat/dowloadid");
}
</script>
<template>
    <div class="background">       
    </div>
    <div class="zoomfile">
        <div class="zoomfile__select" v-if="$store.state.chat.data_detail_zoom != null" >
            <div class="zoomfile__select--pre">
                <font-awesome-icon v-if="checkleft == true" @click="pre" :icon="['fas', 'chevron-left']" />
            </div>
            <div class="zoomfile__select--next">
                <font-awesome-icon v-if="checright == true"  @click="next" :icon="['fas', 'chevron-right']" />
            </div>
        </div>
        <div class="zoomfile__choice" v-if="$store.state.chat.data_detail_zoom != null">
            <img class="zoomfile__choice--img" v-if="($route.query.mess_media == 'img' || dataimg != null) && datavideo == null" :src="url + '/file/get-png/' + (dataimg != null ? dataimg : searchfile($route.query.idfile))">
            <video class="zoomfile__choice--video" id="video" v-if="($route.query.mess_media == 'video' || datavideo != null) && dataimg == null"  controls>
                <source :src="url + '/file/geturl-video/' + (datavideo == null ?  searchfile($route.query.idfile) : datavideo)" type="video/mp4">
            </video>
        </div>
<!--     
        <div class="zoomfile__close">
            <a v-if="dataimg != null || $route.query.mess_media == 'img'" class="zoomfile__close--dow" :href="url + '/file/filedowloadid/' +  (mex[0] != null ? mex[0].name : null)">
                <font-awesome-icon class="close__icon" :icon="['fas', 'download']" />
            </a>
            <font-awesome-icon class="close__icon" @click="$router.go({ query: { id: $route.query.id } })" :icon="['fas', 'xmark']" />
        </div> -->

        <div class="zoomfile__option">
            <div class="zoomfile__option--slide" id="slide">
                <div  class="option__item" v-for="item in datam" :key="item" :id="item.name + '##' + item.type" :class="checkactive(item.id) == true ? 'active': ''" @click="select(item)">
                    <img class="option__item--img" v-if="item.type == 'image'" :src="url + '/file/get-png/' + item.name">
                    <video class="option__item--video" id="video" v-if="item.type == 'video'" >
                        <source :src="url + '/file/geturl-video/' +item.name" type="video/mp4">
                    </video>
                </div>
            </div>
        </div>
    </div>



</template>
<script>
export default {
    created() {
        localStorage.setItem("idfile", this.$route.query.idfile);
    },
}
</script>
<style scoped>


.zoomfile__select {
    position: fixed;
    top: 50%;
    transform: translateY(-50%);
    color: white;
    display: flex;
    font-size: 40px;
    justify-content: space-between;
    left: 0;
    right: 0;
}
.zoomfile__select > div {
    padding: 20px;
    z-index: 13;
}

.background {
    position: fixed;
    background: rgba(0, 0, 0, 0.977);
    inset: 0;
    z-index: 11;
}
.zoomfile {
    position: fixed;
    inset: 0;
    z-index: 12;
}
.zoomfile__choice {
    position: fixed;
    top: 50%;
    left: 50%;
    z-index: 33;
    transform: translate(-50%,-50%) translateY(-50px);
    min-width: 300px;
    max-width: 1000px;
    height: 500px;
}

.zoomfile__choice--img , 
.zoomfile__choice--video {
    width: 100%;
    height: 100%;
    object-fit: contain;
}
.zoomfile__close {
    position: fixed;
    display: flex;
    gap: 50px;
    top: 0;
    right: 0;
    font-size: 35px;
    padding: 20px;
    z-index: 14;
    color: aliceblue;
}
.zoomfile__close--dow {
    color: white;
    font-size: 30px;
} 

.zoomfile__option {
    position: absolute;
    display: flex;
    z-index: 13;
    justify-content: center;
    bottom: 0;
    left: 0;
    padding: 20px;
    right: 0;
    gap: 10px;
}

.zoomfile__option--slide {
    bottom: calc(100% + 10px);
    display: flex;
    justify-content: center;
    gap: 10px;
}
.option__item {
   opacity: 0.5;
   border-radius: 10px;
   border: 1px solid rgb(152, 151, 151);
   overflow: hidden;
   left: 50%;
   cursor: pointer;
}



.active {
    opacity: 1 !important;
}

.option__item--img,
.option__item--video {
    width: 50px;
    height: 50px;
}
</style>