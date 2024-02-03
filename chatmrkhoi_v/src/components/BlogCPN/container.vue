<script setup>
import { useRouter } from "vue-router";
const router = useRouter();

// REDIRECT SIGNUP
function GoSignUP() {
    router.push({ name: 'acount', query: { action: 'SignUp' } })
}
</script>
<template>
      <div class="container">
            <!-- INTRODUCE -->
            <div class="container__introduce">
                <div class="container__introduce--text">
                    <h1>{{ $t('TextBlog.IntroduceTitle') }}</h1>
                    <h3>{{ $t('TextBlog.Introducecontent') }}</h3>
                </div>
                <img class="container__introduce--img" src="../../assets/images/person2.png">
            </div>

            <!-- INTRODUCE TWO -->
            <div  class="container__introduce" style="background: var(--color4)">
                <img  class="container__introduce--img" src="../../assets/images/person1.png">
                <div  class="container__introduce--text" style="color:white;">
                    <h1>{{ $t('TextBlog.IntroduceTitle2') }}</h1>
                    <h3>{{  $t('TextBlog.IntroduceContent2')  }}</h3>
                </div>
            </div>
            
            <!-- OVERVIEW -->
            <div  class="container__overview">
                <div class="container__overview--text">
                    <h1>{{ $t('TextBlog.OverViewTitle') }}</h1>
                    <h3>{{ $t('TextBlog.OverViewContent') }}</h3>
                    <button class="overview__button" @click="GoSignUP()">
                        {{ $t('TextBlog.ButtonSignUp') }}
                    </button>
                </div>
                <span class="container__overview--background"></span>
            </div>
        </div>
</template>
<style scoped>
/*  introduce  */
.container__introduce {
    background: rgb(237, 236, 236);
    padding: 100px 100px;
    position: relative;
    display: grid;
    grid-template-columns: 500px 1fr;
}

.container__introduce--img {
    width: 100%;
    height: 400px;
    object-fit: contain;
}
/*  overview  */
.container__overview {
    position: relative;
    height: 600px;
    display: flex;
    justify-content: center;
    align-items: center;
}.container__introduce--text {
    color: rgb(21, 21, 21);
}
.container__overview--text {
    width: 500px;
    position: relative;
    z-index: 11;
    text-align: center;
    color: white;
}
.container__overview--background {
    background-image: url('../../assets/images/connectreal.jpg') ;
    background-repeat: no-repeat;
    background-size: cover;
    position: absolute;
    inset: 0;
    filter: brightness(30%);
}
.overview__button {
    margin-top: 20px;
    font-size: 15px;
    border:none;
    background: var(--color4);
    position: relative;
    color: white;
    padding: 10px 20px;
    border-radius: 5px; 
    cursor: pointer;
}
.overview__button:hover {
    background: rgb(225, 108, 66);
}
</style>