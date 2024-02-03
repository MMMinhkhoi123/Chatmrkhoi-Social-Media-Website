<template>
    <div class="bg" 
         :class="$store.state.avaible_chat.open_loader == true ? ' showbg' : ''"
    >
    </div>
    <div class="loader" 
    :class="$store.state.avaible_chat.open_loader == true ? ' showbg' : ''"
    ></div>
</template>
<script>
export default {
    setup() {},
}
</script>
<style scoped>
.showbg {
    opacity: 1 !important;
    pointer-events: visible !important;
}
.bg {
    pointer-events: none;
    position: fixed;
    inset: 0;
    opacity: 0;
    z-index: 200;
    transition: 2s;
    background: rgba(26, 28, 27, 0.768);
}
    .loader {
        transition: 1.4s;
        position: fixed;
        top: 50%;
        left: 50%;
        z-index: 229;
        opacity: 0;
        transform: translate(-50%, -50%);
        width: 36px;
        height: 36px;
        border-radius: 50%;
        border: 4px solid var(--color4);
        border-left-color: transparent;
        animation: spin89345 1s linear infinite;
    }

    @keyframes spin89345 {
    0% {
        transform: rotate(0deg);
    }

    100% {
        transform: rotate(360deg);
    }
    }
</style>