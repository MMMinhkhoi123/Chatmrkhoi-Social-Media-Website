<script setup>
import groups from "../../components/MainCPN/Routerview/GroupCPN/ListGrorp.vue";
import filterCPN from "../../components/MainCPN/Routerview/GroupCPN/FilterGroup.vue";
import NotifiCPN from "../../components/ComomCPN/Notification/delete_group.vue";
</script>
<template>
    <div class="group">
        <div class="group__frame">
            
            <!-- FILTER -->
            <filterCPN></filterCPN>

            <!-- LIST GROUP  -->
            <groups></groups>
        </div>
    </div>

    <!-- NOTIFICATION -->
    <NotifiCPN></NotifiCPN>

</template>
<style scoped>
.group {
    padding: 20px;
    position: relative;
    height: 100%;
    box-sizing: border-box;
}
.group__frame {
    background: rgb(255, 255, 255);
    height: 100%;
    border-radius: 10px;
    overflow: hidden;
}



/* alert */
.option {
    width: 100%;
    display: flex;
    justify-content: space-between;
}
.option > button {
    border: none;
    color: white;
    width: 100px;
    background: var(--color4);
    transition: 0.3s;
    border-radius: 10px;
}
.option > button:nth-child(1):hover {
    background: orange;
}
.option > button:nth-child(2) {
    border: 1px solid var(--color1);
    color: var(--color1);
    background: transparent;
}
.option > button:nth-child(2):hover {
    color: white;
    background: var(--color1);
}

.option > button {
    padding: 10px;
}
.option {
    display: flex;
    gap: 10px;
}
</style>