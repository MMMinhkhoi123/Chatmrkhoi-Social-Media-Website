<script setup>
import { useRouter } from "vue-router";
import CarouselCPN from "./slide.vue";

const router = useRouter();

function GoSignUp() {
    router.push({ name: 'acount', query: { action: 'SignUp' } })
}

</script>
<template>
       <div class="carousel">
            
            <!-- CONTENT CENTER -->
            <div class="carousel__content">
                <h1>
                    {{  $t('TextBlog.CaroselTitle') }}
                </h1>
                <h2>
                    {{  $t('TextBlog.CaroselContent') }}
                </h2>
                <button class="carousel__content--link" @click="GoSignUp()">
                    {{ $t('TextBlog.ButtonSignUp') }}
                </button>
            </div>

            <!-- REAR CAROSEL -->
            <CarouselCPN />
        </div>
</template>
<style scoped>
/* carousel */
.carousel {
    position: relative;
    background: var(--);
    width: 100%;
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;
}
.carousel__content {
    font-weight: 200 !important;
    color: white;
    text-align: center;
    margin-bottom: 300px;
    position: relative;
    z-index: 50;
}
.carousel__content--link {
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
.carousel__content--link:hover {
    background: rgb(225, 108, 66);   
}
</style>