<script setup>
import store from '../../../../store';
import other from "../../../../handle/Common/index";
    const { url_img } = other();
    const check_online = (id) => {
        var check = false;
        store.state.chat.data_online.forEach((e) => {
            e.id == id && e.status == 'online' ? check = true: null;
        })
        return check;
    }
</script>
<template>
    <div class="profileother">
        <h3 class="profileother__title">
            {{ $t('TextMain.Profile.Listprofile.Title')  }}
        </h3>
        <div class="profileother__list">
            <div class="profileother__list--item" v-for="item in $store.state.everyone.array_friend" :key="item" @click="$router.push({ name: 'profile', query: { id: item.id } })">
                <div class="list__left">
                    <img class="list__left--img" v-if="item.type_img != 'rs'"
                    :src="url_img + item.images" alt="">
                    <img class="list__left--img"  v-if="item.type_img == 'rs'" loading="lazy" :src="'/src/assets/images/avata_org/' + item.images">
                    <div class="list__left--content">
                        <strong>{{ item.fullname }}</strong>
                        <span>{{ check_online(item.id) == true ?  $t('TextMain.Profile.Listprofile.Online') : $t('TextMain.Profile.Listprofile.offline') }}</span>
                    </div>
                </div>
                <div class="list__right">
                    <font-awesome-icon :icon="['fas', 'angle-right']" />
                </div>
            </div>
        </div>
    </div>
</template>
<style scoped>
.profileother {
    width: 100%;
    height: 100%;
    box-sizing: border-box;
}
.profileother__title {
    font-weight: 700;
    margin-left: 10px;
}
.profileother__list {
    padding: 10px;
    height: 400px;
    overflow-y: scroll;
    overflow-x: hidden;
}
.profileother__list--item {
    display: flex;
    justify-content: space-between;
    margin: 2px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    border-radius: 20px;
    overflow: hidden;
    transition: 0.3s;
    background: white;
    margin-bottom: 5px;
}
.profileother__list--item:hover {
    transform: translateX(10px);
}

.list__left {
    display: flex;
    gap: 10px;
    padding: 10px;
}
.list__right {
    display: flex;
    align-items: center;
    font-size: 20px;
    padding: 10px;
    background: rgb(239, 246, 245);
}
.list__left--content {
    display: flex;
    justify-content: center;
    flex-direction: column;
}
.list__left--img {
    object-fit: cover;
    width: 50px;
    height: 50px;
    border-radius: 50%;
}

</style>