<script setup>
import headerCPN from "../../components/BlogCPN/header.vue";
import footerCPN from "../../components/BlogCPN/footer.vue";
import carouselCPN from "../../components/BlogCPN/carousel.vue";
import containerCPN from "../../components/BlogCPN/container.vue";

// LISTEN SCROLL 
const check_scroll = (event) => {
    const text_logo = document.getElementById("header__logo--text");
    const text_button = document.querySelector(".option__button--text");
    const elm_signin = document.getElementById("select-signin");
    const elm_signup = document.getElementById("select-signup");
    const icon = document.getElementById("option__dropdown--icon");
    event.target.scrollTop > 0 ? 
         show(text_logo,text_button,elm_signin, elm_signup ,icon) 
        : hide(text_logo,text_button,elm_signin, elm_signup, icon) ;
}

// DOM SHOW HEADER
function show(text_logo, text_button, elm_signin,elm_signup, icon) {
    text_logo.style.color = 'black';
    text_button.style.color = 'black';
    elm_signin.style.marginRight = '200px';
    icon.style.color = "black";
    elm_signup.style.bottom = 0;
}

// DOM HIDEN HEADER
function hide(text_logo, text_button, elm_signin,elm_signup, icon) {
    text_logo.style.color = 'white';
    text_button.style.color = 'white';
    elm_signin.style.marginRight = '0';
    icon.style.color = "white";
    elm_signup.style.bottom = '100%';
}
</script>

<template>
    <div class="blog" @scroll="check_scroll">
       
        <!-- HEADER -->
        <headerCPN></headerCPN>

        <!-- CAROSEL -->
        <carouselCPN></carouselCPN>

        <!-- PART -->
        <containerCPN></containerCPN>

        <!-- FOOTER -->
        <footerCPN></footerCPN>
    </div>
</template>

<style scoped>
.blog {
    width: 100%;
    height: 100vh;
    overflow-y: scroll;
    inset: 0;
    z-index: 100;
}
</style>
