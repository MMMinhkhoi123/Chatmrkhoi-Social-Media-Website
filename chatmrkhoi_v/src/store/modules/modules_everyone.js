import api from "../axios_config";
import store from "../../store/index";
const module_everyone = {
    namespaced: true,
    state: {
        data_find: [],
        array_not_friend: [],
        array_friend: [],
        array_request_friend: [],
        array_send_request_friend: [],
        chose: localStorage.getItem("select_menu_user") == null ? 1 : localStorage.getItem("select_menu_user"),
        resetrom: false,
        data_after_action: null,
        keysearch: '',
        optionselect: false,
        array_sug: [],
    },

    getters: {
        get_friend_id: (state) => (value) => {
            return state.array_not_friend.filter((e) => e.id == value);
        },
        get_friend_request_id: (state) => (value) => {
            return state.array_send_request_friend.filter((e) => e.id == value);
        },
        get_request_friend_id: (state) => (value) => {
            return state.array_request_friend.filter((e) => e.id == value);
        },
        get_your_friend_id: (state) => (value) => {

            if (state.array_friend == null) {
                return [];
            }
             const x = state.array_friend.filter((e) => e.id == value);

             if(x.length == 0) {
                if(state.array_not_friend == null) {
                    return [];
                }
                return state.array_not_friend.filter((e) => e.id == value);
             } else {
                return x;
             }
        },
    },
    mutations: {
        setdatafind(state, valua) {
            state.data_find = valua;
        },
        set_array_sendfriendrequest(state, valua) {
            state.array_send_request_friend = valua.data;
        },
        set_array_friend(state, valua) {
            state.array_friend = valua.data; 
        },
        set_array_notfriend(state, valua) {
            state.array_not_friend = valua.data; 
        },
        set_array_friendrequest(state, valua) {
            state.array_request_friend = valua.data;
        },
        set_data_after_action(state, valua){
            state.data_after_action = valua;
            store.state.chat.array_connect.push(valua);
        },
        set_data_suggest_action(state, valua) {
            state.array_sug = valua;
        }
    },

    actions: {

          // action request
          async unsendrequest({ commit } ,data) {
            await api.post("/friend-center/action-request", data, {
                headers: {Authorization: "Bearer " + localStorage.getItem("token") }
             }).then((e) => {
                console.log("unfriend success")
            }).catch(() => {
                console.log("unfriend fail")
            })
        },
  

        // define request
        async refuse({ commit } ,data) {
            await api.get("/friend-center/refuse-request/" + data, {
                headers: {Authorization: "Bearer " + localStorage.getItem("token") }
             })
        },


         // accept request
         async accept({ commit } ,data) {
            await api.get("/friend-center/accept-request/" + data, {
                headers: {Authorization: "Bearer " + localStorage.getItem("token") }
             }).then((e) => {
                   commit("set_data_after_action", e.data); 
            }).catch(() => {
                console.log("that bai")
            })
        },
        

         // unfriend friend
        async unfriend({ commit } ,id) {
            await api.get("/friend-center/delete-friend/" + id,{
                headers: {Authorization: "Bearer " + localStorage.getItem("token") }
             }).then((e) => {
                console.log("unfriend success")
            }).catch(() => {
                console.log("unfriend fail")
            })
        },


        // add friend
        async addfriend({ commit } ,data) {
            await api.post("/friend-center/add-request/" + data,{} ,  {
                headers: {Authorization: "Bearer " + localStorage.getItem("token") }
             } ).then((e) => {
                console.log("add success")
            }).catch(() => {
                console.log("that bai")
            })
        },


       // get user
        async finduser({ commit } ,data) {
            await api.get("/user-center/find-user/" + data.key + "/" + data.data,  {
                headers: {Authorization: "Bearer " + localStorage.getItem("token") }
             }).then((e) => {
                commit("setdatafind", e.data)
            }).catch(() => {
                console.log("that bai")
            })
        },

        async get_data_sug_friend({ commit }, data) {
            await api.get("/user-center/list-suggest", {
                headers: {
                    Authorization: "Bearer " + data
                }
            }).then((e) => {
               commit("set_data_suggest_action", e.data)
            }).catch(() => {
                console.log("Fail")
            })
        },


        
        async get_data_initial_friend({ commit }, type) {
            await api.get("/user-center/list-initial/" + type.key, {
                headers: {
                    Authorization: "Bearer " + type.token
                }
            }).then((e) => {

             commit("set_array_friend", { data: e.data , type: type.key })
                 
                   
            }).catch(() => {
                console.log("Fail")
            })
        },



        async get_data_initial_notfriend({ commit }, type) {
            await api.get("/user-center/list-initial/" + type.key, {
                headers: {
                    Authorization: "Bearer " + type.token
                }
            }).then((e) => {
                commit("set_array_notfriend", { data: e.data , type: type.key })
            }).catch(() => {
                console.log("Fail")
            })
        },

        async get_data_initial_sendfriendrequest({ commit }, type) {
            await api.get("/user-center/list-initial/" + type.key, {
                headers: {
                    Authorization: "Bearer " + type.token
                }
            }).then((e) => {
                commit("set_array_sendfriendrequest", { data: e.data , type: type.key })
            }).catch(() => {
                console.log("Fail")
            })
        },

        async get_data_initial_friendrequest({ commit }, type) {
            await api.get("/user-center/list-initial/" + type.key, {
                headers: {
                    Authorization: "Bearer " + type.token
                }
            }).then((e) => {
                commit("set_array_friendrequest", { data: e.data , type: type.key })
            }).catch(() => {
                console.log("Fail")
            })
        },
    }
}

export default module_everyone;