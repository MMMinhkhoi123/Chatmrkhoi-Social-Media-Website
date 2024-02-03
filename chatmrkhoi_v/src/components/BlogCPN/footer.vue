<template>
    <div class="footer">

        <!-- INDIVITUAL -->
        <section class="footer__individual">
            <div class="footer__individual--item">
                <span class="individual__circleorange">
                    <font-awesome-icon :icon="['fas', 'phone']" />
                </span>
                <strong>+849162803</strong>
            </div>
            <div class="footer__individual--item">
                <span class="individual__circleorange">
                    <font-awesome-icon :icon="['fas', 'envelope']" />
                </span>
                <strong>khoinguyenminh188@gmail.com</strong>
            </div>
            <div class="footer__individual--item">
                <span class="individual__circleorange">
                    <font-awesome-icon :icon="['fas', 'location-dot']" />
                </span>
                <strong>Phan Văn Bạch p15 Q Tân Bình TP HCM</strong>
            </div>
        </section>

        <!-- INFOMATION -->
        <section class="footer__infomation">
            <div class="footer__infomation--item">
                <h3>
                    {{ $t('TextBlog.FooterTitle1') }}
                </h3>
                <span class="infomation__item"> 
                    {{ $t('TextBlog.FooterItem1TT1') }} 
                </span>
                <span class="infomation__item">
                     {{ $t('TextBlog.FooterItem2TT1') }}
                </span>
            </div>
            <div class="footer__infomation--item">
                <h3>
                    Community
                </h3>
                <span class="infomation__item">
                    chatmrkhoi
                </span>
            </div>
            <div class="footer__infomation--item">
                <h3>
                    {{ $t('TextBlog.FooterTitle3') }}
                </h3>
                <span class="infomation__item">
                    {{ $t('TextBlog.FooterItem1TT3') }}
                </span>
            </div>
        </section>

        <!-- URL -->
        <section class="footer_url">
            <span class="footer_url--text">
                <font-awesome-icon :icon="['fas', 'link']" />
                chatmrkhoi.com
            </span>
        </section>

    </div>
</template>
<style scoped>
.footer {
    background:#1d2b34;
}
.footer__individual{
    display: grid;
    grid-template-columns: repeat(3 , 1fr);
}
.footer__individual--item {
    padding: 50px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 10px;
    align-items: center;
    color: white;
}
.individual__circleorange {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    background: var(--color4);
}
/* infomation */
.footer__infomation {
    display: flex;
    gap: 50px;
    color: white;
    margin: 0 100px;
    justify-content: center;
    padding-bottom: 50px;
    border-bottom: 1px solid rgb(102, 100, 100);
}
.footer__infomation--item {
    display: flex;
    flex-direction: column;
}
.infomation__item {
    color: rgb(184, 184, 184);
}


/* url */
.footer_url {
    text-align: center;
    padding: 10px;
    margin: 0 100px;
}
.footer_url--text {
    color: rgb(237, 237, 237);
}
</style>