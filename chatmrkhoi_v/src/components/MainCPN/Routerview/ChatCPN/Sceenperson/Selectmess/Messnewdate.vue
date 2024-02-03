<script setup>
    import handle from "../../../../../../handle/Screen_chat/index";
    const {convet_date_start} = handle();
</script>
<template>
        <!-- Key Acount roup -->
        <div class="new__date" v-if="item.start == true">
            <strong>{{ convet_date_start(item.time, $t('TextMain.Chat.Screen.From.Pin.Month')) }}</strong>
            <span></span>
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
 .new__date {
  position: relative;
  text-align: center;
  font-size: 13px;
  padding: 20px;
  color: var(--color1);
}
</style>