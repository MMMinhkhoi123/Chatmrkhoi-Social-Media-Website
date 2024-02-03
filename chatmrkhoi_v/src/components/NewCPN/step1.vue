<script setup>
import handle from "../../handle/NewUser/index"
const { GoStep1 } = handle();
</script>
<template>
    <div class="gender">

        <!-- TITLE -->
        <h1 class="gender__title">
            {{ $t('TextNew.Gender.Title') }} 
            <font-awesome-icon :icon="['fas', 'venus-mars']" />
        </h1>

        <div class="gender__frame">

            <!-- FAMALE -->
            <label class="gender__frame--item">
                <img class="frame__img" src="../../assets/images/girls.png" >
                <input class="frame__input" id="ip" type="radio" name="x" value="female" checked>      
                <div class="frame__gender">
                    <span class="frame__gender--icon">
                        <font-awesome-icon :icon="['fas', 'check']" />
                    </span>
                    <strong class="frame__text">
                        {{ $t('TextNew.Gender.TextFaMale') }}
                    </strong>
                </div>
            </label>
            
            <!-- MALE -->
            <label class="gender__frame--item">
                <img  class="frame__img"  src="../../assets/images/boys.png">
                <input  class="frame__input" id="ip" type="radio" value="male" name="x">
                <div class="frame__gender">
                    <span class="frame__gender--icon">
                        <font-awesome-icon :icon="['fas', 'check']" />
                    </span>
                    <strong class="frame__text">
                        {{ $t('TextNew.Gender.TextMale') }}
                    </strong>
                </div>
            </label>

        </div>

        <!-- CONFIRM -->
        <div class="gender__option">
            <button class="gender__option--submit" @click="GoStep1()">
                <span>
                    {{ $t('TextNew.Gender.ButtonNext') }}
                </span>
             <font-awesome-icon :icon="['fas', 'chevron-right']" /></button>
        </div>
    </div>
</template>
<script>
export default {
    mounted() {
        const div = document.querySelectorAll("#ip");
        div.forEach((e) => {
                if(localStorage.getItem("gender") != null) {
                    if(e.value == localStorage.getItem("gender")) {
                        e.checked = true;
                    } else {
                        e.checked = false;
                    }
                }
            })
    },
}
</script>
<style scoped>
.gender__title {
    margin-bottom: 50px;
}

.gender {
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    padding: 20px 40px;
    background: white;
    border-radius: 10px;
    text-align: center;
    color: rgb(3, 3, 3);
}
.gender__frame {
    display: flex;
    gap: 30px;
}
.gender__frame--item {
    display: block;
    width: 200px;
}
.frame__img {
    width: 100%;
}

.frame__input:checked ~ .frame__gender > .frame__gender--icon  {
    display: flex;
}

.frame__input {
    display: none;
}
.frame__gender {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px;
}
.frame__gender--icon {
    display: none;
    background: rgb(12, 227, 12);
    width: 30px;
    height: 30px;
    justify-content: center;
    align-items: center;
    border-radius: 50%;
    color: rgb(255, 255, 255) !important;
}
.frame__gender--icon  {
    color: rgb(18, 249, 14);
}




.gender__option {
    padding-top: 50px;
    display: flex;
    justify-content: end;
}
.gender__option--submit {
    padding: 10px 20px;
    color: white;
    display: flex;
    gap: 10px;
    border: none;
    border-radius: 20px;
    background: var(--color4);
}
.gender__option--submit:hover {
    background: orange;
}
</style>