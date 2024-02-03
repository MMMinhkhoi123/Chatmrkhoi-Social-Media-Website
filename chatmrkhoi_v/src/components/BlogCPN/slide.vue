<template>
     <div  id="slide">

            <!-- slide 1 -->
            <div style="background: var(--color2)" class="slide__item" id="slide__item0">
                <div class="slide__item--king">
                    <img class="item__img item__king" src="../../assets/images/thekingreal.png">
                    <img  class="item__img item__bitcoin" src="../../assets/images/btc.png">
                    <span class="item__text">         
                        <h1>Build 30 
                            <span style="color: orange;">Days</span>
                        </h1> 
                    </span>
                </div>
                <img class="slide__item--keychorn" src="../../assets/images/keychorn.png">
                <img class="slide__item--mouse" src="../../assets/images/logictech.png">
                <img class="slide__item--moniter" src="../../assets/images/monitor.png">
                <img class="slide__item--pen" src="../../assets/images/pen.png">
            </div>

            <!-- slide 2 -->
            <div style="background: var(--color1) ;" class="slide__item" id="slide__item1">
                <img class="slide__item--file" src="../../assets/images/file.png">
                <img class="slide__item--image1" src="../../assets/images/img.png">
                <img class="slide__item--image2" src="../../assets/images/img.png">
                <img class="slide__item--image3" src="../../assets/images/img.png">
                <img class="slide__item--video" src="../../assets/images/videp.png">
            </div>

            <!-- slide 3 -->
            <div style="background: var(--color3);" class="slide__item" id="slide__item2">
                <img class="slide__item--phone1" src="../../assets/images/phone.png">
                <img class="slide__item--phone2" src="../../assets/images/phone.png">
                <img class="slide__item--phone3" src="../../assets/images/phone.png">
                <img class="slide__item--phone4" src="../../assets/images/phone.png">
                <img class="slide__item--laptop1" src="../../assets/images/laptop.png">
                <img class="slide__item--laptop2" src="../../assets/images/laptop.png">
                <img class="slide__item--mess1" src="../../assets/images/mess.png">
                <img class="slide__item--mess2" src="../../assets/images/mess.png">
            </div>

        </div>
</template>
<script>
export default {
    mounted() {
        // get all slide
        const parent = document.querySelectorAll(".slide__item");
        var shows = 0;

        // method run carousel 
        const run_animate  = () => {
            var before_shows = shows - 1;
            var defaults = shows - 2;
            before_shows < 0 ?  before_shows = parent.length - 1 : before_shows;
            defaults < 0 ? defaults = (parent.length - 2) + shows : defaults;
            const show = document.getElementById("slide__item" + shows);
            show == null ?  clearInterval(m) : run_Element(show, before_shows, defaults);
        }
        
        // method set class slide
        function run_Element(show, before_shows, defaults) {
                show.classList.add("show")
                show.classList.remove("after_show")
                show.classList.remove("defaults")
                const shows_before  = document.getElementById("slide__item" + before_shows);
                shows_before.classList.add("after_show")
                shows_before.classList.remove("show")
                shows_before.classList.remove("defaults")
                const defaultv = document.getElementById("slide__item" + defaults);
                defaultv.classList.add("defaults")
                defaultv.classList.remove("after_show")
                defaultv.classList.remove("show")
        }
        
        run_animate();
        const m = setInterval(() => {
            shows = shows + 1;
            shows >= parent.length ? shows = 0: shows;
            run_animate();
        }, 5000)
    },
}
</script>
<style scoped>
/* action carousel */
.show {
    opacity: 1;
}
.after_show {
    opacity: 0;
    animation: example_before 3s ease-in forwards;
}

.defaults {
    opacity: 0;
    animation: example_after 3s ease-in forwards;
}

@keyframes example_before {
  0% {
    opacity: 0;
   }
  100% {
    opacity: 1;
  }
}

@keyframes example_after {
  0% {
    opacity: 1;
   }
  100% {
    opacity: 0;
  }
}
.slide__item {
    transition: 3s ;
    top: 100% ;
    position: absolute ;
    inset: 0 ;
    opacity: 0 ;
}

.slide__item > img {
    transition: 2s;
    position: absolute;
    object-fit: contain;
}




/* slide 1  */
.slide__item--keychorn {
    width: 300px;
    height: 300px;
    right: 350px;
    z-index: 2;
    opacity: 0;
    bottom: -300px;
    transform: rotate(90deg);
}
.slide__item--mouse {
    width: 100px;
    height: 100px;
    right: 500px;
    z-index: 6;
    bottom:  -100px;
    opacity: 0;
}
.slide__item--moniter {
    width: 300px;
    height: 300px;
    left: 300px;
    bottom: -300px;
    transform: rotate(-30deg);
    opacity: 0;
}
.slide__item--pen {
    z-index: 0;
    width: 70px;
    height: 70px;
    left: 500px;
    bottom: -200px;
    opacity: 0;
    filter: blur(1px);
    transform: rotate(180deg);
}
.slide__item--king {
    z-index: 5;
    position: absolute;
    width: 700px;
    height: 700px;
    left: calc(50% - 350px);
    opacity: 0;
    bottom: -400px;
    transition: 2s;
    position: absolute;
    object-fit: contain;
}
.slide__item--king  > .item__img {
    object-fit: contain;
    width: 100%;
    height: 100%;
    filter: drop-shadow(20px 20px 20px 39px black);
}
.slide__item--king  >.item__text {
    display: flex;
    position: absolute;
    bottom: 150px;
    color: white;
    right: 140px;
}
.slide__item--king  > .item__bitcoin {
    position: absolute;
    left: -70px !important;
    bottom: 50px;
    object-fit: cover !important;
    width: 300px !important;
    height: 300px !important;
}
.after_show > .slide__item--keychorn {
    opacity: 1 !important;
    bottom: -100px !important;
}
.after_show > .slide__item--mouse {
    bottom: 200px !important;
    opacity: 1 !important;
}
.after_show > .slide__item--moniter {
    bottom: -100px !important;
    opacity: 1 !important;
}
.after_show > .slide__item--pen {
    bottom: 300px !important;
    opacity: 1 !important;
}
.after_show > .slide__item--king {
    bottom: -150px !important;
    opacity: 1 !important;
}






/* slide 2  */
#slide__item1 > img { 
    transition: 2s;
    position: absolute;
    object-fit: contain;
}
.slide__item--file {
    width: 500px;
    height: 500px;
    left: 50px;
    bottom: -100px;
    opacity: 0;
    transform: rotate(30deg);
}
.slide__item--video {
    transition-delay: 1s !important; 
    width:300px;
    height: 300px;
    left: 600px;
    bottom: -500px;
    opacity: 0;
    transform: rotate(10deg);
}
.slide__item--image1 {
    width: 100px;
    height: 100px;
    right: 100px;
    top: -100px;
    opacity: 0;
    transform: rotate(-30deg);
}
.slide__item--image2 {
    width: 60px;
    height: 60px;
    right: 200px;
    top: -300px;
    filter: blur(4px);
    opacity: 0;
    transform: rotate(20deg);
}
.slide__item--image3 {
    width: 40px;
    height: 40px;
    right: 50px;
    top: -400px;
    opacity: 0;
    filter: blur(5px);
    transform: rotate(20deg);
}
.after_show > .slide__item--file {
    transition-delay: 1s !important;
    opacity: 1 !important;
    bottom: 100px !important; 
}
.after_show > .slide__item--video {
    opacity: 1 !important;
    bottom: -100px !important; 
}
.after_show > .slide__item--image1 {
    opacity: 1 !important;
    top: 100px !important; 
}
.after_show > .slide__item--image2 {
    opacity: 1 !important;
    top: 50px !important; 
}
.after_show > .slide__item--image3 {
    opacity: 1 !important;
    top: 50px !important; 
}






/* slide 3  */
.slide__item--phone1 {
    width: 100px;
    height: 100px;
    bottom: -100px;
    filter: blur(1px);
    opacity: 0;
    transform: rotate(0deg);
} 
.slide__item--phone2 {
    width: 100px;
    height: 100px;
    right: 200px;
    bottom: -200px;
    opacity: 0;
    filter: blur(1px);
    transform: rotate(0deg);
} 
.slide__item--phone3 {
    width: 50px;
    height: 50px;
    left: 500px;
    bottom: -220px;
    opacity: 0;
    filter: blur(5px);
} 
.slide__item--phone4{
    width: 200px;
    height: 200px;
    right: 300px;
    bottom: -400px;
    opacity: 0;
    transform: rotate(3deg);
} 
.slide__item--laptop1 {
    width: 50px;
    height: 50px;
    bottom: 10px;
    right: 10px;
    filter: blur(5px);
    opacity: 0;
    transform: rotate(0deg);
} 
.slide__item--laptop2 {
    width: 140px;
    height: 140px;
    bottom: -130px;
    left: 250px;
    opacity: 0;
    transform: rotate(0deg);
} 
.slide__item--mess1 {
    width: 180px;
    height: 180px;
    bottom: -199px;
    left: 50%;
    opacity: 0;
    transform: translateX(0%) rotate(0deg);
}
.slide__item--mess2 {
    width: 70px;
    height: 70px;
    bottom: -259px;
    right:200px;
    opacity: 0;
    filter: blur(1px);
}


.after_show > .slide__item--phone1  {
 opacity: 1 !important;
 bottom: 10px !important;
 transform: rotate(-60deg) !important;
} 
.after_show > .slide__item--phone2  {
    opacity: 1 !important;
    transform: rotate(80deg) !important;
   bottom: 200px !important;
}
.after_show > .slide__item--phone3 {
    opacity: 1 !important;
   bottom: 20px !important;
}
.after_show > .slide__item--phone4 {
    opacity: 1 !important;
   bottom: -100px !important;
}
.after_show > .slide__item--laptop1 {
    opacity: 1 !important;
    transform: rotate(30deg) !important;
   bottom: -10px !important;
}

.after_show > .slide__item--laptop2  {
    opacity: 1 !important;
    transform: rotate(30deg) !important;
   bottom: 130px !important;
}
.after_show >  .slide__item--mess1  {
    opacity: 1 !important;
    transform: translateX(-50%) rotate(-10deg) !important;
   bottom: 59px !important;
}
.after_show > .slide__item--mess1 {
    opacity: 1 !important;
   bottom: 59px !important;
}

</style>