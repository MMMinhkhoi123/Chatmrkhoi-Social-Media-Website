<script setup>
import handle from "../../handle/NewUser/index";
   const { GoStep2 ,err, date } = handle();
   if(localStorage.getItem("birtday") != null) {
     date.value = localStorage.getItem("birtday");
   }
</script>
<template>
    <div class="date">
        
        <!-- MESSAGES FOR ERRORS -->
        <div class="date__err" v-if="err">
            <font-awesome-icon class="date__err--close" @click="err= null" :icon="['far', 'circle-xmark']" />
            <span>{{ $t(err) }}</span>
        </div>

        <!-- TITLE -->
        <h1 class="date__title">
            {{  $t('TextNew.Date.Title') }} 
            <font-awesome-icon :icon="['fas', 'cake-candles']" />
        </h1>

        <!-- IMAGES BIRDAY -->
        <img class="date__img" src="../../assets/images/date.png">

        <!-- COMPONENT DATE -->
        <VueCtkDateTimePicker v-model="date" only-date format="YYYY-MM-DD"/>
        
        <div class="date__option">

            <!-- BACK GENDER -->
            <button class="date__option--submit" @click="$router.go(-1)">
                {{  $t('TextNew.Date.ButtonBack') }} 
            </button>
            
            <!-- COMFIRM -->
            <button class="date__option--submit" @click="GoStep2()">
                <span>
                    {{  $t('TextNew.Date.ButtonNext') }} 
                </span> 
                <font-awesome-icon :icon="['fas', 'chevron-right']" />
            </button>
            
        </div>
    </div>
</template>
<style scoped>
.date {
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    position: relative;
    background: white;
    min-width: 400px;
    padding: 20px;
    text-align: center;
    border-radius:10px;
}
.date__err {
    position: absolute;
    top: 10px;
    left: calc(50% - 170px);
    padding: 20px;
    min-width: 300px;
    background: rgba(104, 28, 235, 0.704);
    color: white;
}
.date__err--close {
    position: absolute;
    top: 5px;
    right: 5px;
    border-radius: 50%;
    color: rgb(255, 255, 255) !important;
}
.date__title {
    padding: 50px;
}
.date__img {
    width: 100%;
    height: 200px;
    object-fit: contain;
    margin-bottom: 20px;
}
.date__option {
    padding-top: 50px;
    display: flex;
    gap: 10px;
    justify-content: space-between;
}
.date__option--submit {
    padding: 10px 20px;
    color: white;
    display: flex;
    gap: 10px;
    border: none;
    border-radius: 20px;
    background: var(--color4);
}
.date__option--submit:hover {
    background: orange;
}
</style>
