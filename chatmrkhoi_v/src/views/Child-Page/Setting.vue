<script setup>
  import menuCPN from "../../components/MainCPN/Routerview/SettingCPN/MenuSetting.vue";
  import ScreenCPN from "../../components/MainCPN/Routerview/SettingCPN/ScreenSetting.vue";
  import NotifiCPN from "../../components/ComomCPN/Notification/Upinfo_waring.vue";
</script>
<template>
   <div class="setting">
        <div class="setting__action">
            <div class="setting__action--left">

                <!-- MENU -->
                <menuCPN />
                
            </div>
            <div class="setting__action--right">

                <!-- VIEW  -->
                <ScreenCPN />

            </div>
        </div>
   </div>

   <!-- NOTIFICATION UPDATE -->
   <NotifiCPN></NotifiCPN>

</template>
<style scoped>
.setting {
    position: relative;
    padding: 20px;
    box-sizing: border-box;
    height: 100%;
}
.setting__action {
    overflow: hidden;
    border-radius:10px;
    display: grid;
    height: 100%;
    gap: 20px;
    grid-template-columns: 300px 1fr;
}
.setting__action--right,
.setting__action--left {
    background: white;
    border-radius: 10px;
}

</style>