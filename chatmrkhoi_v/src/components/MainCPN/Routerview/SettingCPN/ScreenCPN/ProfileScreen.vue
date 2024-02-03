<script setup>
import imgx from "../../../../NewCPN/imgtake.vue";
import other from "../../../../../handle/Common/index";
import handle from "../../../../../handle/Setting/index";
import store from "../../../../../store";
const { up_info, data_alert, convert_date,data_img, filemix,upload } = handle();
const { url_img, authen } = other();

const posts = () =>  {
        filemix.value != null
        ? (store.dispatch("chat/upload_clone", { file: filemix.value, type: 'img' }), up_info()) 
        :  up_info();
    }
</script>

<template>
    <div v-if="authen" class="profile">

        <!-- avata -->
        <div class="profile__avata">
            <h3 class="profile__avata--title">
                {{ $t('TextMain.Setting.Screen.Profile.Title') }}
            </h3>
            <div class="profile__avata--image" v-if="authen" >
                <img class="avata__item" v-if="authen.type_img != 'rs' && data_img == null"
                :src="url_img + authen.images"
                @click="$router.push({ query: { codeimg: authen.images, type: authen.type_img  } })">
                <imgx class="avata__item" :name="authen.images" v-if="authen.type_img == 'rs' && data_img == null" 
                @click="$router.push({ query: { codeimg: authen.images, type: authen.type_img  } })">
                </imgx>
                <img class="avata__item" v-if="data_img!= null" :src="data_img" 
                @click="$router.push({ query: { codeimg: authen.images, type: authen.type_img  } })">

                <label>
                    <font-awesome-icon :icon="['fas', 'camera']" />
                    <input @change="upload" style="display: none;" type="file">
                </label>
            </div>
        </div>

        
        <!-- infomation -->
        <div class="profile__infomation">
            <!-- name -->
            <div class="profile__infomation--item">
                <strong>
                    {{ $t('TextMain.Setting.Screen.Profile.TitleName') }}
                </strong>
                <input class="infomation__input" v-model="authen.fullname"  type="text" />
            </div>

            <!-- birtday -->
            <div class="profile__infomation--item">
                <strong>
                    {{ $t('TextMain.Setting.Screen.Profile.Titlebirday') }}
                </strong>  
                <VueCtkDateTimePicker v-model="convert_date"  only-date format="YYYY-MM-DD"/>
            </div>

            <!-- gender -->
            <div class="profile__infomation--item">
                <strong>
                    {{ $t('TextMain.Setting.Screen.Profile.TitleGender') }}
                </strong>  
                <div class="infomation__gender">
                    <label class="infomation__gender--item">
                        <img class="gender__icon" src="../../../../../assets/icon/male.png">
                        <input class="gender__checkbox"  id="gender__checkbox"  
                        :checked="authen.gender == 'male' ? true: false" type="radio" value="male" name="z"/>
                        <span class="gender__icon">
                            <font-awesome-icon class="gender__icon--check" :icon="['fas', 'check']" />
                        </span>
                    </label>

                    <label class="infomation__gender--item">
                        <img class="gender__icon" src="../../../../../assets/icon/female.png" alt="">
                        <input class="gender__checkbox" id="gender__checkbox" 
                        :checked="authen.gender == 'female' ? true: false" type="radio" value="female" name="z"/>
                        <span class="gender__icon">
                            <font-awesome-icon class="gender__icon--check" :icon="['fas', 'check']" />
                        </span>
                    </label>
                </div>
            </div>

            <!-- description -->
            <div  class="profile__infomation--item">
                <strong>
                    {{ $t('TextMain.Setting.Screen.Profile.TitleDesc') }}
                </strong>
                <textarea  class="infomation__textarea" v-model="authen.desc"  rows="5"></textarea>
            </div>

        </div>

        <div class="profile__account">
            <Strong>
                {{ $t('TextMain.Setting.Screen.Profile.TitlOption') }}
            </Strong>
        </div>

        <div class="profile__option">
            <button class="profile__option--button" @click="posts()">
                {{ $t('TextMain.Setting.Screen.Profile.buttonChange') }}
            </button>
        </div>
    </div>

</template>
<style scoped>
.profile {
    display: flex;
    height: 100%;
    flex-direction: column;
}

/* avata */
.profile__avata {
    margin: 0 20px;
    padding: 20px 0;
    border-bottom: 1px solid rgb(223, 220, 220);
}
.profile__avata--image {
    width: 80px;
    display: block;
    height: 80px;
    border: 5px solid white;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
    border-radius: 50%;
    position: relative;
}
.profile__avata--image > label {
    width: 30px;
    height: 30px;
    display: flex;
    z-index: 22;
    justify-content: center;
    align-items: center;
    color: white;
    background: blue;
    border-radius: 50%;
    position: absolute;
    bottom: 0;
    right: 0;
}
.avata__item {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    object-fit: cover;
}
.profile__avata--title {
    margin: 0;
    margin-bottom: 10px;
}


/* .infomation */
.profile__infomation {
    padding: 20px 0;
    margin: 0 20px;
    border-bottom: 1px solid rgb(223, 220, 220);
}
.profile__infomation--item {
    display: grid;
    grid-template-columns: 200px 500px;
    align-items: center;
    margin: 10px 0;
}

.infomation__input {
    padding: 12px;
    border: 1px solid rgb(204, 204, 204);
    outline: none;
    font-size: 13px;
    border-radius: 5px;
}
.infomation__gender {
    padding: 13px;
    display: flex;
    gap: 30px;
}
.infomation__gender--item {
    display: flex;
    gap: 10px;
}

.gender__icon {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 20px;
    height: 20px;
    background: rgb(236, 236, 236);
    border-radius: 50%;
    border: 2px solid rgb(255, 255, 255);
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}
.gender__checkbox:checked ~ .gender__icon > .gender__icon--check {
    display: block !important;
}
.gender__checkbox:checked ~ .gender__icon {
    border: 2px solid  var(--color2);
}
.gender__checkbox {
    display: none;
}
.gender__icon--check {
    display: none;
    color: var(--color2)
}


.infomation__textarea {
  border-radius: 5px;
  resize: none;
  border: 1px solid rgb(206, 204, 204);
  outline: none;
}



.profile__option {
    padding: 20px;
    text-align: end;
    margin-top: auto;
}
.profile__option--button {
    padding: 12px 24px;
    border-radius: 5px;
    border: none;
    background: var(--color1);
    color: white;
}



/* acount */
.profile__account {
    padding: 20px 0;
    margin: 0 20px;
}
</style>