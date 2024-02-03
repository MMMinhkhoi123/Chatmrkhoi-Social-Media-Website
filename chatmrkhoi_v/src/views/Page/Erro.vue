<template>
   <div class="erro">
      <h1 class="erro__title">404 :></h1>
      <strong  class="erro__content">WE ARE SORRY, BUT THE PAGE REQUESTED WAS NOT FOUND</strong>
      <button class="erro__button" @click="this.$router.go(-1)">Back</button>
   </div>
</template>
<style>
.erro {
   display: flex;
   justify-content: center;
   align-items: center;
   flex-direction: column;
   position: fixed;
   inset: 0;
   background: var(--color1);
   margin: 0;
   padding: 0;
   color: white;
}
.erro__title {
   font-size: 70px;
   font-weight: 700;
   margin: 0;
   padding: 0;
   color: white;
}
.erro__button {
   cursor: pointer;
   margin: 30px;
   font-size: 20px;
   padding: 10px 30px;
   border-radius: 30px;
   border: none;
   font-weight: 700;
}
</style>