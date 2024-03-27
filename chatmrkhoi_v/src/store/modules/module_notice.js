import api from "../axios_config";
import store from "..";
const module_authen = {
    namespaced: true,
    state: {
        arrayNotice: [],
        arrayNoticeSave: [],
        statuslook: null,
    },
    getters: {
        getNoticebystatus: (state) => (array) => {
            if(state.statuslook == null) {
                return array;
            }
            return array.filter((e) => e.status == state.statuslook);
        },
        getNoticeByType: (state) => (value) => {
            if( state.arrayNotice == null) {
                return [];
            }
            return state.arrayNoticeSave.filter((e) => e.title == value);
        },
        getNoticeById: (state) => (value) => {
            if( state.arrayNotice == null) {
                return [];
            }
            return state.arrayNotice.filter((e) => e.id == value);
        },
    },
    mutations: {
        setNotice(state, valua) {
            state.arrayNoticeSave = valua;
            state.arrayNotice = valua;
        }
    },
    actions: {
        async getAllNotice({ commit }, token) {
            await api.get("/notice-center/all", {
                headers: {Authorization: "Bearer " + token}
            }).then((e) => {
                commit("setNotice", e.data)
            }).catch((err) => {
                console.log(err)
            })
        },
        async deleteNotice({ commit }, data) {
            await api.delete("/notice-center/delete/" + data, {
                headers: {Authorization: "Bearer " +  localStorage.getItem("token")}
            });
        },

        async updateNotice({ commit }, data) {
            await api.put("/notice-center/view/" + data,{}, {
                headers: {Authorization: "Bearer " +  localStorage.getItem("token")}
            });
        },
    }
  }
export default module_authen;
