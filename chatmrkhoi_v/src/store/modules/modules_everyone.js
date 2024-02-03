import api from "../axios_config";
import store from "../../store/index";
const module_everyone = {
    namespaced: true,
    state: {
        data_find: [],
        array_not_friend: null,
        array_friend: null,
        array_request_friend: null,
        array_send_request_friend: null,
        chose: 1,
        resetrom: false,
        data_after_action: null,
        keysearch: '',
        optionselect: false,
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
        setarray_list_send_requestfriend(state, valua) {
            state.array_send_request_friend = valua;
        },
        setarray_list_requestfriend(state, valua) {
            state.array_request_friend = valua
        },
        setarray_list_friend(state, valua) {
            state.array_friend = valua; 
        },
        setarray_not_list_friend(state, valua) {
            state.array_not_friend = valua; 
        },
        set_data_after_action(state, valua){
            state.data_after_action = valua;
            store.state.chat.array_connect.push(valua);
        }
    },

    actions: {
        // action request
        async action_request({ commit } ,data) {
            await api.post("/everyone/action-request", data, {
                headers: {Authorization: "Bearer " + localStorage.getItem("token") }
             }).then((e) => {
                if(data.status == 'friend') {
                   commit("set_data_after_action", e.data); 
                }
            }).catch(() => {
                console.log("that bai")
            })
        },
        
         // unfriend friend
        async unfriend({ commit } ,id) {
            await api.post("/everyone/unfiend/" + id,{} ,  {
                headers: {Authorization: "Bearer " + localStorage.getItem("token") }
             }).then((e) => {
                console.log("unfriend success")
            }).catch(() => {
                console.log("unfriend fail")
            })
        },


        // add friend
        async addfriend({ commit } ,data) {
            await api.post("/everyone/add-request/" + data,{} ,  {
                headers: {Authorization: "Bearer " + localStorage.getItem("token") }
             } ).then((e) => {
                console.log("add success")
            }).catch(() => {
                console.log("that bai")
            })
        },

       // get user gmail
        async finduser({ commit } ,data) {
            await api.get("/everyone/finduser/" + data,  {
                headers: {Authorization: "Bearer " + localStorage.getItem("token") }
             }).then((e) => {
                console.log(e.data)
                commit("setdatafind", e.data)
            }).catch(() => {
                console.log("that bai")
            })
        },

        
       // get user name
       async findusername({ commit } ,data) {
        await api.get("/everyone/findusername/" + data,   {
            headers: {Authorization: "Bearer " + localStorage.getItem("token") }
         }).then((e) => {
            console.log(e)
            commit("setdatafind", e.data)
        }).catch(() => {
            console.log("that bai")
        })
        },


        // get list send your request
        async get_send_friend_request({ commit }, data) {
            await api.get("/everyone/list-send-request", {
                headers: {
                    Authorization: "Bearer " + data
                }
            }).then((e) => {
                commit("setarray_list_send_requestfriend", e.data)
            }).catch(() => {
                console.log("that bai")
            })
        },

        // get list your request friend 
        async get_friend_request({ commit }, data) {
            await api.get("/everyone/list-friend-request", {
                headers: {
                    Authorization: "Bearer " + data
                }
            }).then((e) => {
                commit("setarray_list_requestfriend", e.data)
            }).catch(() => {
                console.log("that bai")
            })
        },

        // get list your friend
        async get_friend({ commit }, data) {
            await api.get("/everyone/list-friend", {
                headers: {
                    Authorization: "Bearer " + data
                }
            }).then((e) => {
                commit("setarray_list_friend", e.data)
            }).catch(() => {
                console.log("that bai")
            })
        },

        // get list not your friend
        async get_not_friend({ commit }, data) {
            await api.get("/everyone/list-not-friend", {
                headers: {
                    Authorization: "Bearer " + data
                }
            }).then((e) => {
                commit("setarray_not_list_friend", e.data)
            }).catch(() => {
                console.log("that bai")
            })
        } 
    }
  }
export default module_everyone;