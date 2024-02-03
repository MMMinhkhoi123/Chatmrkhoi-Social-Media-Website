<script setup>
import imgCPN from '../../components/NewCPN/imgtake.vue';
import Handle from "../../handle/NewUser/index"
const { GoStep3, ChoiceImg, ChoiceAvatadefault ,img_load, img_ref , err,array_img } = Handle();
</script>
<template>
    <div class="avata">

        <!-- TITLE -->
        <h1 class="avata__title">
            {{  $t('TextNew.Avata.Title') }}
            <font-awesome-icon :icon="['fas', 'image']" />
        </h1>

        <!-- MESSAGES FOR ERRORS -->
        <div  class="avata__err" v-if="err">
            <font-awesome-icon class="avata__err--close" @click="err = null" :icon="['far', 'circle-xmark']" />
            <span>{{ $t(err) }}</span>
        </div>

        <!-- AVATA CHOICE -->
        <div class="avata__img">
            <imgCPN v-if="img_ref != null" :name="img_ref"/>
            <imgCPN v-if="img_ref == null && img_load.url == null" />
            <img v-if="img_load.url != null" :src="img_load.url">
        </div>

        <!-- LIST AVATA SELECT -->
        <div class="avata__select">
            <label  class="avata__select--item" @click="ChoiceAvatadefault(item)" v-for="item in array_img" :key="item">
                <imgCPN :name="item" />
                <input class="select__radio" type="radio" name="x" />
                <span class="select__icon"></span>
            </label>
            <label class="avata__select--item chose__system">
                <font-awesome-icon  :icon="['fas', 'image']" />
                <input class="select__file" @change="ChoiceImg" type="file">
                <span class="select__icon"></span>
            </label>
        </div>

        
        <div class="avata__option">

            <!-- BACK BIRDAY -->
            <button  class="avata__option--button" @click="$router.go(-1)">
                {{ $t('TextNew.Avata.ButtonBack') }}
            </button>

            <!-- COMFIRM -->
            <button class="avata__option--button" @click="GoStep3()">
                <span>
                    {{ $t('TextNew.Avata.ButtonFish') }}
                </span>
            <font-awesome-icon :icon="['fas', 'chevron-right']" /></button>
        </div>
    </div>
</template>
<style scoped>
.avata {
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    background: rgb(255, 255, 255);
    border-radius: 10px;
    padding: 20px;
    display: flex;
    flex-direction: column;
    position: relative;
    align-items: center;
}
.avata__err {
    position: absolute;
    top: 10px;
    left: calc(50% - 170px);
    padding: 20px;
    min-width: 300px;
    background: rgba(104, 28, 235, 0.704);
    color: white;
    text-align: center;
}
.avata__err--close {
    position: absolute;
    top: 5px;
    right: 5px;
    border-radius: 50%;
    color: rgb(255, 255, 255) !important;
}
.avata__title {
    padding: 40px;
}
.avata__img {
    margin-bottom: 50px;
}
.avata__img > img {
    border-radius: 50%;
    border: 2px solid rgb(180, 180, 180);
    background: rgb(209, 201, 216);
    object-fit: contain;
    width: 200px;
    height: 200px;
}

.avata__select {
    width: 500px;
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
    padding: 20px;
    border-radius: 10px;
    background: rgb(180, 177, 177);
}
.chose__system {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: 30px;
}
.avata__select--item {
    overflow: hidden;
    position: relative;
    width: 50px;
    background: white;
    height: 50px;
    border: 1px solid rgb(117, 117, 117);
    border-radius: 10px;
    cursor: pointer;
}
.avata__select--item:hover {
    transform: scale(1.2);
}
.avata__select--item > img {
    width: 100%;
    height: 100%;
}
.avata__select--item:nth-child(2) {
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 30px;
    color: var(--color3);
}
.select__file {
    display: none;
}
.select__radio {
    display: none;
}
.select__radio:checked ~ .select__icon {
    position: absolute;
    inset: 0;
    background: var(--color4);
}

.option__file {
    display: none;
}
.option__icon {
    position: relative;
    z-index: 1;
}

.avata__option{
    padding-top: 50px;
    width: 100%;
    display: flex;
    gap: 10px;
    justify-content: space-between;
}
.avata__option--button {
    padding: 10px 20px;
    color: white;
    display: flex;
    gap: 10px;
    border: none;
    border-radius: 20px;
    background: var(--color4);
}
.avata__option--button:hover {
    background: orange;
}
</style>