<template>
    <div class="card">
        <div class="card__header">
            <div class="card__header--img skeleton">
                <img  src="../../assets/bg4k.jpg">
            </div>
            <h1  class="card__header--title skeleton" >Mobiphone</h1>
        </div>
        <div class="card__footer skeleton">
            <p>hom nay toi vudwqedwqifewu8fewhuju
              hweufhufhewuhfuewhuifheuiwhufhuehwui</p>
        </div>
    </div>
</template>
<style scoped>
.card {
    background: white;
    border-radius: 10px;
    padding: 10px;
    max-width: 350px;
}
.card__header {
    display: grid;
    grid-template-columns: 140px 1fr;
    grid-template-rows: 140px;
}

.card__header--img  {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
img {
    width: 100%;
    height: 100%;
}
.skeleton {
    position: relative;
}
.skeleton::before {
    content:"";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 10;
    background: linear-gradient(90deg , #eee , #d3d0d0 , #eee);
    background-size: 200%;
    animation: identifierx 2s infinite linear;
}

@keyframes identifierx {
    0% {
        background-position: -100%;
    }
    100% {
        background-position-x: 100%;
    }
}
</style>