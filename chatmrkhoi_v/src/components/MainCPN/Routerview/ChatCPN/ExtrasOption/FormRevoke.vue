<template>
    <div  class="bgwhite">
    </div>
    <div  class="fromrevoke">
        <h2>Remove on your side</h2>
        <p>
            We'll remove this message for you. Other members of the chat can still see it.
        </p>
        <div class="option">
            <button @click="$store.state.avaible_chat.unmess.status = null">Cancel</button>
            <button @click="unmesschose">Delete, remove</button>
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
    background: rgba(255, 255, 255, 0.612);
    z-index: 100;
    filter: blur(20px);
  }
  .fromrevoke {
    background: white;
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
    color: rgb(0, 0, 0);
    width: 300px;
    height: 50px;
    cursor: pointer;
    transition: 0.4s;
  }
  .option > button:nth-child(2) {
    background: white;
    font-size: 15px;
    border: 1px solid  var(--color1);
    color: var(--color1);

  }
  .option > button:nth-child(2):hover {
    background: var(--color1);
    color: white;
  }
  .option > button:nth-child(1):hover  {
    
  }
</style>