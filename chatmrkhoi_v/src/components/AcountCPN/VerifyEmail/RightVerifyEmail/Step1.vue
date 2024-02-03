<script setup>
import Handle from "../../../../handle/Acount/Verify";
const { RunTime, onsubmit, CountTime, Emailverify, GoSignUp } = Handle();

RunTime();

</script>
<template>
  <form class="form"  @submit.prevent="onsubmit">
    <div class="form__content">

      <!-- TITLE -->
      <h1 class="form__content--title">
        {{ $t('TextAcount.verify_Email.From.Title') }}
      </h1>

      <!-- TEXT EMAIL VERIFY -->
      <p>
        {{ $t('TextAcount.verify_Email.From.TextContent') }}{{ Emailverify }}
      </p>

      <!-- TIME RESEND -->
      <strong>
        {{ $t('TextAcount.verify_Email.From.TexTime', { time: CountTime }) }}
      </strong>

    </div>

    <div class="form__setting">
      
      <!-- BACK SIGNUP -->
      <button class="form__setting--cancel btn" @click="GoSignUp()" type="button">
        {{ $t('TextAcount.verify_Email.From.ButtonCancel') }}
      </button>

      <!-- CONFIRM -->
      <button class="form__setting--resend btn" v-if="CountTime == 20" style="background: orange">
        {{ $t('TextAcount.verify_Email.From.ButtonResend') }}
      </button>
    </div>

  </form>

</template>
<style scoped>
.form__content--title {
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  margin: 0;
  white-space: nowrap;
}
.form__content {
  text-align: center;
  justify-content: center;
  padding: 50px 0;
}
.form__setting {
  display: flex;
  gap: 10px;
  justify-content: center;
}
.form__setting--cancel:hover {
  background: orange;
}
.form__setting--resend:hover {
  background: var(--color4) !important;
}

.btn {
  border-radius: 20px;
  padding: 10px 30px;
  color: #fefefe;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  border: none;
  background: var(--color4);
}

</style>
