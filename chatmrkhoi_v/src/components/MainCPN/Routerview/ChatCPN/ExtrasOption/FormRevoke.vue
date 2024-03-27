<template>
    <div  class="bgwhite">
    </div>
    <div  class="fromrevoke">
        <h2>{{  $t('TextMain.Chat.Screen.From.Revoke2.Title')  }}</h2>
        <p>
          {{  $t('TextMain.Chat.Screen.From.Revoke2.decribe')  }}
        </p>
        <div class="option">
            <button @click="$store.state.avaible_chat.unmess.status = null">
              {{  $t('TextMain.Chat.Screen.From.Revoke2.button1')  }}
            </button>
            <button @click="unmesschose">
              {{  $t('TextMain.Chat.Screen.From.Revoke2.button2')  }}
            </button>
        </div>
    </div>
</template>
<script>
import store from '../../../../../store'
export default {
    setup() {
        const unmesschose = () => {
          const datapost = {
            type: 'one',
            idmess:  store.state.avaible_chat.unmess.idmess,
            iduser: store.state.auth.authen.id
          }
          store.dispatch("chat/unmess", datapost);
          store.state.avaible_chat.unmess.status = null
        }
        return {
          unmesschose,
        }
    },
}
</script>
<style scoped>
h2 {
    margin: 0;
}
p {
  padding: 20px 0;
}
  .bgwhite {
    position: fixed;
    inset: 0;
    background: var(--colorHideFrom);
    z-index: 100;
    filter: blur(20px);
  }
  .fromrevoke {
    background: var(--coloRegular);
    padding: 20px;
    border-radius: 10px;
    text-align: center;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 111;
    box-shadow: 0 0 19px rgba(0, 0, 0, 0.253);
  }
  .option {
    display: flex;
    gap: 10px;
  }
  .option > button {
    border: none;
    width: 300px;
    height: 50px;
    cursor: pointer;
    transition: 0.4s;
    background: transparent;
    border: 1px solid var(--colorText);
  }
  .option > button:nth-child(1):hover {
    background: var(--color3);
  }
  .option > button:nth-child(2) {
    background: transparent;
    font-size: 15px;
    border: 1px solid  var(--color1);
    color: var(--color1);
  }
  .option > button:nth-child(2):hover {
    background: var(--color1);
    color: white;
  }
  .option > button:nth-child(1):hover  { }
</style>