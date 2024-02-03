import store from "../../store/index"
import { useRouter } from 'vue-router'
import { ref, computed, reactive } from 'vue'
export default () => {

    const router = useRouter();

    const data_1 = ref(null);
    const data_2 = reactive({
        code: null,
        email: localStorage.getItem("gmailverify"),
    });
    const data_3 = ref(null);

    const NameAccount = computed(() => {
        return localStorage.getItem("nameverify")
    })

    const regex = /^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$/


    // LISTEN COMFIRM (STEP 1)
    const onsubmit_1 = () => {
        store.state.avaible_chat.open_loader = true;
        store.state.auth.verify_code = null;
        localStorage.removeItem("gmailverify");
        store.dispatch("auth/verify_code", data_1.value);
        const g = setInterval(() => {
            localStorage.getItem("gmailverify") != null  ? (clearInterval(g), localStorage.setItem("action", 2),
              store.state.avaible_chat.open_loader = false, router.push({ query: { action: "Forget", step: 2  } }))
            : null
        }, 1000);
    }

    // LISTEN COMFIRM (STEP 2)
    const onsubmit_2 = () => {
        store.state.auth.verify_code = null;
        store.state.avaible_chat.open_loader = true;
        localStorage.removeItem("gmailverify");
        store.dispatch("auth/verify_code_check", data_2);
        localStorage.setItem("action", 3)
        const g = setInterval(() => {
            localStorage.getItem("gmailverify") != null 
            ? (  clearInterval(g),   store.state.avaible_chat.open_loader = false, router.push({ query: { action: "Forget", step: 3  } }) )
            : null;
        }, 1000);
    }

    // LISTEN CONFIRM (STEP 3)
    const onsubmit_3 = () => {
        store.state.auth.err.passwords = '';
        regex.test(data_3.value) == false
         ? store.state.auth.err.passwords = "TextAcount.Forget_Password.From.step3.err"
        : null
        
        store.state.auth.err.passwords == "" 
        ? store.dispatch("auth/change_pass", {  email: localStorage.getItem("gmailverify"),   passnew: data_3.value })
        : null;
    }

     // GET INFO EMAIL
     const EmailVerify = computed(() => {
        var x = localStorage.getItem("gmailverify");
        var m = x.split("@")[0].substring(0, x.split("@")[0].length - (x.split("@")[0].length - 3));
        return m + "*****@" + x.split("@")[1];
    });


    // REDIRECCT INTO SIFNIN
    function GoSignIn() {
        router.push({ query: { action: 'SignIn' } })
    }

    // CLOSE
    function Close() {
        store.state.auth.verify_code = null
    }

    
    return { onsubmit_1, onsubmit_2, onsubmit_3, GoSignIn, EmailVerify, Close, data_2, data_1,data_3, NameAccount }
}