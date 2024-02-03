import { computed } from 'vue'
import store from "../../store/index"
import { useRouter } from 'vue-router'

export default () => {
   
    const router = useRouter();
    const data = computed(() => store.state.auth.user)



   //  LISTEN CONFIRM
    const onsubmit = () => {
      store.state.auth.err_login  = null;

      store.state.avaible_chat.open_loader  = true;

       store.dispatch("auth/SignIn");
       var time = 5000;
       const x = setInterval(() => {
         if(localStorage.getItem("token") && store.state.auth.authen_fake) {

            clearInterval(x)

            if (store.state.auth.err_login == null) {
            if (store.state.auth.authen_fake.profile == "not") {
                  localStorage.setItem("action", 1);
                  router.push("/New?step=1")
               } else {
                  router.push("/main/chat")
               }
            }
         } else if(time <= 0) {
            clearInterval(x)
         }
         time =  time - 1000;
       }, 1000)
    }


   // CLOSE 
   function Close() {
      store.state.auth.err_login = null;
   }

   // CHECK INCLUDES ERRORS
   const CheckErrors = computed(() => store.state.auth.err_login);

    return { data, onsubmit, CheckErrors, Close }
}