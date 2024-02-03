<script setup>
    import handle from "../../../../../../handle/Screen_chat/index";
    const {convert_group_status, data_profile} = handle();
    function nameuser(item) {
        if(data_profile(item.id_user) == null)  {
            return ""
        }
        const user =  data_profile(item.id_user).fullname.split(" ");
        const userx = user[user.length - 1]
        return userx;
    }
    function nameuserkick(item) {
        const user =  data_profile(item.group_status.split("&")[1]).fullname.split(" ");
        const userx = user[user.length - 1]
        return userx;
    }
</script>
<template>
        <div class="action__group" v-if="item.group_status != null">
            <strong>
                {{convert_group_status(item.group_status) == "kick" 
                ? $t("TextMain.Chat.Screen.Mess.KickPerson", { user: nameuser(item), usernew: nameuserkick(item)  }) 
                : convert_group_status(item.group_status) == "add" 
                ? $t("TextMain.Chat.Screen.Mess.AddPerson", { user: nameuser(item), usernew: nameuserkick(item)  })
                : $t("TextMain.Chat.Screen.Mess.UserOut", { user: nameuser(item) })
                }}
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
.action__group {
    padding: 10px;
  font-size: 14px;
  text-align: center;
}

</style>