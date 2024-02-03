<template>
    <div class="menu">
            <div class="menu__select" :class="$store.state.everyone.chose == 1 ? 'active': ''"
            @click="$store.state.everyone.chose = 1">
                <span class="menu__select--text">
                    <font-awesome-icon :icon="['fas', 'user-plus']" />
                    {{  $t('TextMain.Everyone.Menu.ItemAddFriend')  }}
                </span>
            </div>
            <div class="menu__select" :class="$store.state.everyone.chose == 2 ? 'active': ''"
            @click="$store.state.everyone.chose = 2">
                <span class="menu__select--text">
                    <font-awesome-icon :icon="['fas', 'user-plus']" />
                    {{  $t('TextMain.Everyone.Menu.ItemSendRequest')  }}
                </span>
                <span  class="menu__select--count"  v-if="$store.state.everyone.array_send_request_friend"> 
                    {{  $store.state.everyone.array_send_request_friend.length }}
                </span>
            </div>
            <div class="menu__select" :class="$store.state.everyone.chose == 3 ? 'active': ''"
            @click="$store.state.everyone.chose = 3">
                <span class="menu__select--text">
                    <font-awesome-icon :icon="['fas', 'person-circle-question']" />
                    {{  $t('TextMain.Everyone.Menu.ItemFriendRequest')  }}
                 </span>
                <span class="menu__select--count" v-if="$store.state.everyone.array_request_friend" >
                    {{ $store.state.everyone.array_request_friend.length }}
                </span>
            </div>
            <div class="menu__select" :class="$store.state.everyone.chose == 4 ? 'active': ''"
            @click="$store.state.everyone.chose = 4">
                <span class="menu__select--text">
                    <font-awesome-icon :icon="['fas', 'user-group']" />
                    {{  $t('TextMain.Everyone.Menu.ItemListFriend')  }}
                </span>
                <span  class="menu__select--count" v-if="$store.state.everyone.array_friend">
                    {{ $store.state.everyone.array_friend.length }}
                </span>
            </div>
    </div>
</template>
<script>
export default {
    setup() {
        
    },
}
</script>
<style scoped>
.menu {
    background: white;
    display: flex;
    box-sizing: border-box;
    flex-direction: column;
    padding: 10px;
    height: 100%;
    border-radius: 10px;
}
.menu__select {
    box-sizing: border-box;
    width: 100%;
    border-radius: 5px;
    padding: 15px 0;
    padding-left: 10px;
    margin-bottom: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background: #FFF;
    box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
}
.menu__select:hover {
    background: var(--color1) !important;
}
.menu__select:hover  > .menu__select--text  {
    color: white !important;
}
.active {
    background: var(--color1) !important;
}

.active > .menu__select--text {
    color: white !important;
}
.menu__select--text  {
    color: #2F2929;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}
.menu__select--count {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 20px;
    height: 20px;
    margin-right: 10px;
    border-radius: 50%;
    background: var(--color2);
    color: #FFF;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
}
</style>