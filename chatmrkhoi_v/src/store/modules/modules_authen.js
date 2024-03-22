import api from "../axios_config";
import store from "..";
const module_authen = {
    namespaced: true,
    state: {
        user: {
            fullname: "",
            gmails: "",
            passwords: "",
        },
        err: {
            fullname: "",
            gmails: "",
            passwords: "",
        },
        err_login: null,
        verify: null,
        authen: null,
        verify_code: null,
        authen_fake: null,
    },
    mutations: {
        setauthen(state, valua) {
            state.authen = valua;
        }
    },
    actions: {
        // REGISTER ACOUNT
        async SignUp ({ state }) {
             await api.post("/account/register", state.user, {}).then((e) => {
              state.verify = false;
            }).catch(e => {
                state.verify = true;
                store.state.avaible_chat.open_loader = false;
                state.err.gmails = "TextAcount.Err.SignUp.GmailUse";
            })
        },

        // LOGIN ACOUNT
        async SignIn ({ state}) {
            const data = {
                gmail: state.user.gmails,
                password: state.user.passwords
            }
            await api.post("/account/login", data, {}).then((e) => {
                localStorage.clear()
                localStorage.setItem("token",e.data.token);
                localStorage.setItem("select-menu", 1)
                state.authen_fake = e.data;
            }).catch((e) => {
                store.state.avaible_chat.open_loader = false;
                if(e.response.data == "Acount not exist") {
                    state.err_login = "TextAcount.Err.Login.GmailNotExit";
                } else {
                    state.err_login = "TextAcount.Err.Login.PassWordCorrect";
                }
            })
        },

        
        // VERIFY ACOUNT
        async verify ({ state }) {
            await api.post("/account/verify_gmail?gmail=" + localStorage.getItem("verify_gmail")).then((e) => {
                if(e.data == 'verify') {
                    state.verify = true;
                }
            })
        },

        // VERIFY CODE ACOUNT
        async verify_code ({ state }, data) {
            await api.post("/account/verify_code?gmail=" + data).then((e) => {
                localStorage.setItem("gmailverify", data);
            }).catch((e) => {
                store.state.avaible_chat.open_loader = false;
                state.verify_code = "TextAcount.Forget_Password.From.step1.err";
            })
        },
        
       // GET STATUS VERIFY ACOUNT
        async verify_code_check ({ state }, data) {
            await api.post("/account/verify_code_check?gmail=" + data.email + "&&code="+ data.code ).then((e) => {
                localStorage.setItem("gmailverify", data.email);
                localStorage.setItem("nameverify", e.data);
            }).catch((e) => {
                store.state.avaible_chat.open_loader = false;
                 if(e.response.data == "The code Invalid") {
                    state.verify_code = "TextAcount.Forget_Password.From.step2.err.Incorrect";
                 } else {
                    state.verify_code = "TextAcount.Forget_Password.From.step2.err.NoLonger";
                 }
            })
        },

        
        // CHANGE PASSWORD ACOUNT
        async change_pass ({ state }, data) {
            await api.post("/account/change_pass?gmail=" + data.email + "&&code=" + data.passnew, {}, {
                headers: {Authorization: "Bearer " + data }
            } ).then((e) => {
                 const data = {
                    img: 'change_pass.png',
                    title: 'Notification.ChangePassword.Title',
                    content: 'Notification.ChangePassword.Content',
                 }
                 store.state.avaible_chat.Notification.data = data;
                 store.state.avaible_chat.Notification.warning.changepass = true;
                 localStorage.clear();
            }).catch((e) => {
               console.log(e);
            })
        },
        
        
        //  VERIFY ACOUNT
        async verify_send ({ state }) {
            await api.post("/account/verify_gmail_send?gmail=" + localStorage.getItem("verify_gmail"));
        },

        // GET INFOMATION USER AUTHENTICATION FROM JWT
        async authen({ commit }, data) {
            await api.post("/user-center/jwt",{}, {
               headers: {Authorization: "Bearer " + data }
            }).then((e) => {
                console.log(e.data);
                commit("setauthen",e.data)
            }).catch((e) => {
                console.log(e)
            })
        } 
    }
  }
export default module_authen;
