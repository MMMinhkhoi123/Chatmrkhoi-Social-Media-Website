<template>
    <img loading="lazy" :src="'/src/assets/images/avata_org/' + name">
</template>
<script>
export default {
    props: {
        name: {
            typeof: String,
            default: ""
        }
    },
}
</script>
<style scoped>
img {
    position: relative;
    z-index: 1;
}
</style>