<script setup>
    import handle from "../../../../../../handle/Screen_chat/index";
    const {handlepin, profile_friend, authen} = handle();
</script>
<template>
         <!-- Key Mess pin -->
        <div class="pin" v-if="item.pin != null">
            <span class="pin__text" v-if="handlepin(item.pin) == 'ping'">
                <span v-if=" item.id_user == authen.id">
                    {{ $t('TextMain.Chat.Screen.Mess.ping', { user: $t('TextMain.Chat.Screen.Mess.User') })  }}
                </span>
                <span v-if=" item.id_user != authen.id && profile_friend(item.id_user).length > 0">
                    {{ $t('TextMain.Chat.Screen.Mess.ping', { user: profile_friend(item.id_user)[0].fullname }) }}
                </span>
            </span>
            <span class="pin__text" v-if="handlepin(item.pin) == 'unping'">
                <span v-if="item.id_user == authen.id ">
                    {{ $t('TextMain.Chat.Screen.Mess.unping', { user: $t('TextMain.Chat.Screen.Mess.User') })   }}
                </span>
                <span  v-if="item.id_user != authen.id &&  profile_friend(item.id_user).length >0">
                    {{ $t('TextMain.Chat.Screen.Mess.unping', { user: profile_friend(item.id_user)[0].fullname })  }}
                </span>
            </span>
            <strong class="pin__highlight" @click="$store.state.avaible_chat.open_pin = true">
               {{ $t('TextMain.Chat.Screen.Mess.PingView') }}
            </strong>
        </div>
</template>
<script>
export default {
    props: {
        item: {
            typeof: Object,
            default: null,
        }
    },
}
</script>
<style scoped>
/* mess pin */
.pin {
  font-size: 14px !important;
  display: flex;
  padding: 10px;
  justify-content: center;
}
.pin__highlight{
  color: var(--color1);
  cursor: pointer;
}
strong {
    margin-left: 5px;
}
</style>