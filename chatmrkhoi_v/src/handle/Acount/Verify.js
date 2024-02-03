import store from "../../store/index"
import { useRouter } from 'vue-router'
import { ref, computed } from 'vue'
export default () => {

    const router = useRouter();
    const next = ref(Number(localStorage.getItem("times")));

    // COUNT TIMES (20s )
    const CountTime = computed(() => {
        return next.value;
    });

    // ENCODE INFOMATION
    const Emailverify = computed(() => {
        var x = localStorage.getItem("verify_gmail");
        var m = x
          .split("@")[0]
          .substring(0, x.split("@")[0].length - (x.split("@")[0].length - 3));
        return m + "*****@" + x.split("@")[1];
    });
  
      // RUNTIME
    function RunTime() {
        const x = setInterval(() => {
          store.dispatch("auth/verify");
          if (next.value != 20) {
            next.value += new Date().getSeconds() - (new Date().getSeconds() - 1);
            localStorage.setItem("times", next.value);
            if (next.value == 20 || store.state.auth.verify == true) {
              clearInterval(x);
            }
          }
        }, 1000);
    }

    // CONFIRM
    const onsubmit = () => {
        localStorage.setItem("times", 0);
        next.value = 0;
        store.dispatch("auth/verify_send");
        RunTime();
    };

    // REDIRECT SIGNUP
    function GoSignUp() {
        router.push({ query: { action: 'SignUp' } })
    }

    return { onsubmit, RunTime, CountTime, Emailverify, GoSignUp }
}