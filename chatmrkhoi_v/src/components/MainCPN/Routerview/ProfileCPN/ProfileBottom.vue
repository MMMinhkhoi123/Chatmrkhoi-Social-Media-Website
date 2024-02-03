<script setup>
  import data from "../../../../handle/Screen_chat/index";
  import other from "../../../../handle/Common/index";
  const { data_profile } = data();
  const { authen } = other();
  const convertdate = (time) =>{
    const date = new Date(time);
    return  {date: date.getDate() , month:(date.getMonth() + 1 ) ,year: date.getFullYear() };
 }
</script>
<template>
    <div class="action__bottom">
        <div class="action__bottom--gender">
            <span class="bottom__title">
            {{ $t('TextMain.Profile.Screen.Info.TitleGender') }}    
            </span>
            <strong>
                {{  $route.query.action == 'my' 
                ? (authen.gender == "male" ? $t('TextMain.Profile.Screen.Info.GenderMale') : $t('TextMain.Profile.Screen.Info.GenderFaMale') )    
                : (data_profile($route.query.id).gender == "male" ?  $t('TextMain.Profile.Screen.Info.GenderMale') : $t('TextMain.Profile.Screen.Info.GenderFaMale'))  }}
            </strong>
        </div>

        <div class="action__bottom--birday">
            <span class="bottom__title"> 
                {{ $t('TextMain.Profile.Screen.Info.Titlebirday') }}     
            </span>
            <strong>
                {{  $route.query.action == 'my' 
                ? $t('TextMain.Profile.Screen.Info.birday', convertdate(authen.briday))
                 : $t('TextMain.Profile.Screen.Info.birday',convertdate(data_profile($route.query.id).briday))  }}
            </strong>
        </div>

        <div class="action__bottom--descripstion">
            <span class="bottom__title">
                {{ $t('TextMain.Profile.Screen.Info.Desc') }}  
            </span>
            <div class="bottom__content">
                <p class="bottom__content--text" v-if="$route.query.action != 'my'">
                    {{data_profile($route.query.id).desc }}
                </p>
                <p class="bottom__content--text"  v-if="$route.query.action == 'my'" >
                    {{ authen.desc }}
                </p>
            </div>
        </div>
    </div>
</template>
<style scoped>
.action__bottom {
    padding: 20px;
    box-sizing: border-box;
    width: 100%;
    height: calc(100% - 300px);
    background: rgb(255, 255, 255);
    border-radius: 10px;
}
.action__bottom--gender,
.action__bottom--birday {
    margin: 20px;
}
.action__bottom--descripstion {
    box-sizing: border-box;
    padding: 20px;
    width: 100%;
    color: #000;
    font-weight: 500;
    border-radius: 10px;
    border: 1px solid #E8E8E8;
    background: #FFF;
}
.bottom__title {
    color: #000;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}
.bottom__content {
    margin-top: 35px;
}
</style>