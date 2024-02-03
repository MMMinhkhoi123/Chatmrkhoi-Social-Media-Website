<template>
    <div class="content">
        <ul class="content__menu">
            <li class="content__menu--item">
                <span class="item__cirleblue">1</span>
                 <p>{{ $t('TextAcount.Forget_Password.StepByStep.step1') }}</p>
            </li>
            <li class="content__menu--item">
                <span class="item__cirleblue">2</span> 
                <p>{{ $t('TextAcount.Forget_Password.StepByStep.step2') }}</p>
            </li>
            <li class="content__menu--item">
                <span class="item__cirleblue">3</span>
                <p>{{ $t('TextAcount.Forget_Password.StepByStep.step3') }}</p>
            </li>
        </ul>
    </div>
</template>
<style scoped>
 .content {
    position: relative;
    z-index: 12;
 }
.content__menu {
    list-style-type: none;
    padding: 0;
    margin: 0;
}
.content__menu--item {
    padding: 15px;
    color: #ffffff;
    display: flex;
    gap: 15px;
    justify-content: start;
    align-items: center;
 }
 .item__cirleblue {
    display: flex;
    justify-content: center;
    align-items: center;
    color:  white;
    border-radius:50%;
    width: 30px;
    height: 30px;
    background: #4F48ED;
 }
</style>