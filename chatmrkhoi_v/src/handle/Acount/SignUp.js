import { computed } from "vue"
import store from "../../store/index"
import { useRouter } from "vue-router"
export default () => {
    
    const router = useRouter();
    const data = computed(() => store.state.auth.user )
    const err = computed(() => store.state.auth.err)

    // LISTEN COMFIRM
    const onsubmit = () => {
        err.value.gmails = "";
        const regex = /^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$/
        data.value.fullname == "" ? err.value.fullname = "Fullname is not empty" 
        : data.value.fullname.length > 35 
        ? err.value.fullname = "TextAcount.Err.SignUp.FullNameLengh" : err.value.fullname  = ""       
        data.value.gmails == "" ? err.value.gmails = "TextAcount.Err.SignUp.GmailEmpty" : err.value.gmails = ""
        data.value.passwords == "" ? err.value.passwords  = "TextAcount.Err.SignUp.PassWordEmpty" : regex.test(data.value.passwords) == "" ?
        err.value.passwords  = "TextAcount.Err.SignUp.PassWordValid" :  err.value.passwords  =  "" 
        
         if ( err.value.passwords == "" && err.value.fullname == "" && err.value.passwords == "") {
            
            store.state.avaible_chat.open_loader = true;

            store.dispatch("auth/SignUp")

            if(err.value.gmails == "") {
                const x = setInterval(() => {
                    if(store.state.auth.verify == false) {
                        clearInterval(x);
                        store.state.auth.verify = null;
                        store.state.avaible_chat.open_loader = false;
                        localStorage.setItem("times", 0);
                        localStorage.setItem("Action", 'verify');
                        localStorage.setItem("verify_gmail", data.value.gmails);
                        router.push("/acount?action=VerifyEmail")
                    }
                }, 2000)
           }
        }
    }



    // CHECK INCLUDES ERRORS
    const CheckErrors = () => {
        if (err.value.fullname != "" || err.value.passwords != "" || err.value.gmails != ""  ) {
            return false;
        }
        return true;
    }

    // CLOSE MESSAGES FOR ERROR
    const Close = () => {
        err.value.fullname = "";
        err.value.passwords = "";
        err.value.gmails = "";
    }

    return  { onsubmit, data, CheckErrors , Close}
}