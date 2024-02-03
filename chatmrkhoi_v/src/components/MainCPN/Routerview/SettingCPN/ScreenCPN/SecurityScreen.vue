<script setup>
import { useRouter } from 'vue-router';
import store from '../../../../../store';
import data from "../../../../../socket/common";
    const router = useRouter();
    const { stompClient } = data();
    const logout = () => {
        // SEND LOGOUT
         stompClient.connect({ login: store.state.auth.authen.id }, (e) => {
         }, () => {});
        const handleSend = () => {
        if ( stompClient.connected == true) {
            stompClient.disconnect();
            router.push({ name: 'blog' })
            localStorage.clear();
        } else {
            setTimeout(() => { handleSend() }, 200)
        }
    }
    handleSend()
    }
</script>
<template>
    <div class="security">
        <div class="security__infomation">
            <h3>
                {{ $t('TextMain.Setting.Screen.Security.Title') }}
            </h3>
            <div class="security__infomation--item"> <font-awesome-icon :icon="['fas', 'envelope']" /> clas*****clan@gmail.com </div>
            <div class="security__infomation--item"> <font-awesome-icon :icon="['fas', 'key']" /> ***********</div>
        </div>
        <span class="security__item"> {{ $t('TextMain.Setting.Screen.Security.ChangePass') }} (Later updates)</span>
        <span class="security__item"> {{ $t('TextMain.Setting.Screen.Security.ChangeEmail') }} (Later updates)</span>
        <span class="security__item logout" @click="logout">
            {{ $t('TextMain.Setting.Screen.Security.Logout') }}
        </span>
    </div>
    
</template>
<style scoped>
.security__infomation--item {
    padding: 15px;
    opacity: 0.8;
}
.security__infomation {
    margin: 20px;
    border-bottom: 1px solid rgb(206, 204, 204);
}
.security__item {
    padding: 15px;
    display: block;
    cursor: pointer;
}
.logout {
    color: red;
    font-weight: 700;
}
</style>