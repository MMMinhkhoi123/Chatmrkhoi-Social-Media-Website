<script setup>
    import handle from '../../../../handle/Group/index';
    import data from "../../../../handle/Screen_chat/index";
  const { authen, url } = data();
  const { selectgroup,setupout,setupremove,dates } = handle();
</script>
<template>
    <div class="list" id="list" v-if="$store.state.chat.array_mygroup">
        <div v-for="item in dates" :key="item" class="list__item">
            <div class="list__item--frame">
                <img  class="item__avata" :src="url + '/file/get-png/' + item.img" alt="">
                <span class="item__name">{{ item.name }}</span>
            </div>
            <div class="list__item--frame">
                <div class="item__icon">
                    <font-awesome-icon :icon="['fas', 'user-group']" /> {{ item.count }}
                </div>
                <div class="item__option">
                    <div class="item__option--item remove" v-if="authen.id == item.master" 
                     @click="setupremove(item)">
                        {{ $t('TextMain.Group.List.Remove') }}
                    </div>
                    <div class="item__option--item remove" v-if="authen.id != item.master"
                     @click="setupout(item)">
                     {{ $t('TextMain.Group.List.Leave') }}
                    </div>
                    <div class="item__option--item" 
                     @click="selectgroup(item)">
                        <font-awesome-icon :icon="['far', 'eye']" /> 
                        {{ $t('TextMain.Group.List.View') }}
                    </div>
                </div>
            </div>
        </div>
        <div v-if="dates.length == 0" class="empty">
            <img class="empty__img" src="../../../../assets/icon/folder.png" alt="">
        </div>
    </div>
</template>
<script>
export default {
    mounted() {
        const parent = document.getElementById("list");
        const nax = document.getElementById("filter");
        parent != null ?  parent.style.height = "calc(100% - " + nax.offsetHeight +"px)" : null;      
    },
    updated() {
        const parent = document.getElementById("list");
        const nax = document.getElementById("filter");
        parent.style.height = "calc(100% - " + nax.offsetHeight +"px)";    
    }
}
</script>
<style scoped>
.list {
    position: relative;
    padding: 10px;
}
.list__item  {
    border-radius: 10px;
    display: flex;
    justify-content: space-between;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
    padding: 10px;
    margin-bottom: 10px;
}
.list__item--frame {
    display: flex;
    align-items: center;
    gap: 20px;
}
.item__name {
    font-weight: 700;
}
.item__avata {
    width: 60px;
    height: 60px;
    border-radius: 50%;
}
.item__option {
    display: flex;
    gap: 10px;
}
.item__option--item {
    cursor: pointer;
}
.remove {
    color: red;
}



.empty  {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
}
.empty__img {
    width: 160px;
    height: 160px;
}

</style>