import store from "..";
import api from "../axios_config";
import axios from "axios";
const module_mess = {
    namespaced: true,
    state: {
        array_all_mess: null,
        array_fileupload: [],
        array_emoji: null,
        array_img: [],
        data_after_send: null,
        data_after_update: null,
        data_pin_after: null,
        data_input: '',
        data_after_watch: null,

        array_addgroup: [],
        array_groupid: [],
        data_img_group: null,
        array_connect: null, 
        array_mygroup: null, 
        array_pin: null,
        id_detail: null,
        group_new: null,
        data_detail: null,

        mess_after_kick: null,
        data_after_upload: null,
        data_rep: null,
        data_group_after: null,
        data_mygroup_after: null,
        data_group_status_after: null,
        data_after_addperson: null,
        data_detail_zoom: null,
        data_typing: [],
        data_online: [],
        runscroll: false,
        data_search_group: "",
        data_index_pin: null,
        data_uploadx: null,

    },
    getters: {
        get_mess_id: (state) => (valua) => {
            if(valua == null || state.array_all_mess == null) {
                return [];
            }
            return state.array_all_mess.filter((e) => e.id == valua);
        },
        get_data_room: (state) => (value) => {
            if(value == null || state.array_all_mess == null) {
                return [];
            }
            return state.array_all_mess.filter((e) => e.room == value && e.feel == null);
        },
        get_data_room_vitral: (state) => (value) => {
            if(value == null || state.array_all_mess == null) {
                return [];
            }
            return state.array_all_mess.filter((e) => e.room == value);
        },
        get_emoji_number: (state) => () => {
            if( state.array_emoji == null) {
                return [];
            }
            const check = [ "1F44D","2764 FE0F","1F606","1F62E","1F62D","1F621"]
            return state.array_emoji.filter((e , index) => check.includes(e.codePoint) == true);
        },

        get_pin_room: (state) => (value) => {
            if( state.array_pin == null) {
                return [];
            }
            return state.array_pin.filter((e) => e.coderoom == value);
        },
        get_info_group: (state) => (value) => {
            if( state.array_mygroup == null) {
                return [];
            }
            return state.array_mygroup.filter((e) => e.id == value );
        },
    },

    mutations: {
        setactionall(state, valua) {
            state.data_online = valua;
        },
        setdata_detail(state, valua){
            state.data_detail = valua;
        },
        setarrayall(state, valua) {
            state.array_all_mess = valua;
        },

        setarrayfile(state, valua) {
            state.data_uploadx = valua;
            state.array_fileupload.push(valua);
        },

        setarrayimg(state, valua) {
            state.array_img.push(valua);
        },

        
        set_emoji(state, valua) {
            state.array_emoji = valua
        },
        set_data_after_send(state, valua) {
            state.data_after_send = valua;
            valua.forEach((e) => {
                if(store.state.avaible_chat.array_new.length > 0) {
                    store.state.avaible_chat.array_new.push(e)
                }
                state.array_all_mess.push(e);
            })
        },
        set_data_after_watch(state, valua) {
            state.data_after_watch = valua;
            state.array_all_mess.forEach((e, index) => {
                if(e.id == valua.id) {
                    if(store.state.avaible_chat.array_new.length > 0) {
                        store.state.avaible_chat.array_new.forEach((xm, index) => {
                            if(xm.id == e.id) {
                                store.state.avaible_chat.array_new[index] = valua;
                            }
                        })
                    }
                    state.array_all_mess[index] = valua;
                }
            })
        },
        set_data_after_update(state, valua) {
            state.data_after_update = valua;
            state.array_all_mess.filter((e, index) => {
                if(e.id == valua.id) {
                    if(store.state.avaible_chat.array_new.length > 0) {
                        store.state.avaible_chat.array_new.forEach((x, index) => {
                            if(x.id == e.id) {
                                store.state.avaible_chat.array_new[index] = valua;
                            }
                        })
                    }
                    state.array_all_mess[index] = valua;
                }
            })
        },

        
        set_data_after_update_feel(state, valua) {
            state.data_after_update = valua;
            state.array_all_mess.filter((e, index) => {
                if(e.id == valua.messupdate.id) {
                    if(store.state.avaible_chat.array_new.length > 0) {
                        store.state.avaible_chat.array_new.forEach((x, index) => {
                            if(x.id == e.id) {
                                store.state.avaible_chat.array_new[index] = valua.messupdate;
                            }
                        })
                    }
                    state.array_all_mess[index] = valua.messupdate;
                }
            })
            if(store.state.avaible_chat.array_new.length > 0) {
                store.state.avaible_chat.array_new.push(valua.messnew)
            }
            state.array_all_mess.push(valua.messnew);
        },


        set_data_group(state, valua) {
        state.data_img_group = valua;
        },
        set_data_connect(state, valua) {
            state.array_connect = valua;
            },

        set_data_mygroup(state, valua) {
        state.array_mygroup = valua;
        },
        set_update_namegroup(state, valua) {
            state.array_mygroup.forEach((e, index) => {
                if(e.id == valua.id) {
                    state.array_mygroup[index] = valua;
                }
            })
        },
    
        defaultarrayfile(state) {
            state.array_fileupload = [];
        },
        set_array_pin(state, valua) {
            state.array_pin = valua;
        },
        set_after_pin(state, valua) {
            state.array_pin.push(valua)
            state.data_pin_after = valua;
        },
        set_group_id(state, valua) {
            state.array_groupid = valua;
        },
        set_array_connect_after(state, valua) {
            state.data_group_after = valua.connect; 
            state.data_mygroup_after = valua.mygroup;
            state.array_mygroup.push(valua.mygroup)
            state.array_connect.push(valua.connect);
        },
        set_add_person(state, valua) {
            state.data_after_addperson = valua;
            // Them nguoi dung vao danh sach nguoi dung nhom
            state.array_groupid.push(valua.getfriend_reponse);
            // Them tin nhan moi
            state.array_all_mess.push(valua.mess_reponse);
        },
        set_kick_person(state, valua) {
            state.mess_after_kick = valua.mess_reponse;
            if(store.state.avaible_chat.array_new.length > 0) {
                store.state.avaible_chat.array_new.push(valua.mess_reponse)
            }
            state.array_all_mess.push(valua.mess_reponse);
        },
        set_data_detail_zoom(state, valua) {
            state.data_detail_zoom = valua;
        }
    },
    actions: {

        async get_all_mess({ commit }, data) {
            await api.get("/mess-center/messall",{
                headers: {
                    Authorization: "Bearer " + data
                }
            }).then((e) => {
                commit("setarrayall", e.data);
            }).catch((err) => {
                console.log(err)
            })
        },

        async get_all_action({ commit }, data) {
            await api.get("/action-center/actionall", {
                headers: {
                    Authorization: "Bearer " + data
                }
            }).then((e) => {
                commit("setactionall", e.data);
            }).catch((err) => {
                console.log(err)
            })
        },

        async sendmess({ commit }, data) {
            await api.post("/mess-center/sendmess", data, {
                headers: {Authorization: "Bearer " + localStorage.getItem("token")}
            }).then((e) => {
                commit("set_data_after_send", e.data)
                commit("defaultarrayfile")
            }).catch((err) => {
                console.log(err)
            })
        },
        async notifyMess({ commit }, data) {
            await api.post("/mess-center/notifymess", data, {
                headers: {Authorization: "Bearer " + localStorage.getItem("token")}
            });
        },

        async movemess({ commit }, data) {
            await api.post("/mess-center/move", data,{
                headers: {Authorization: "Bearer " + localStorage.getItem("token")}
            }).then((e) => {
                const data = [];
                data.push(e.data);
                commit("set_data_after_send", data)
            }).catch((err) => {
                console.log(err)
            })
        },
    
        
    async upload({ commit }, data) {
          var form = new FormData();
          for (var key in data) {
              form.append(key, data[key])
            }
          await api.postForm("/file-center/upload", form, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            commit("setarrayfile", e.data.id);
            commit("setarrayimg", e.data)
          }).catch((err) => {
              console.log(err)
          })
    },

    async upload_clone({ state, commit }, data) {
        var form = new FormData();
        for (var key in data) {
            form.append(key, data[key])
        }
        await api.postForm("/file-center/upload", form, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            console.log(e.data);
            state.data_after_upload = e.data;
        }).catch((err) => {
            console.log(err);
        })
    },

    async update_profile({ state, commit }, data) {
        await api.put("/user-center/profile", data, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            store.state.auth.authen = e.data;
        }).catch((err) => {
            console.log(err);
        })
    },



    async upload_profile({ state }, data) {
        await api.post("/info-center/update-info", data, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            state.data_uploadx = 1;
        }).catch((err) => {
            console.log(err);
        })
    },
    async dowload({ state }, name) {
        await api.get("/file/filedowload/" + name, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        });
    },

    async get_data_detail({ commit }, code) {
        await api.get("/file-center/data-detail/" + code,{
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            commit('setdata_detail', e.data);
        });
    },

    async get_data_detail_zoom({ commit }, code) {
        await api.get("/file-center/data-detail-zoom/" + code, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            commit("set_data_detail_zoom", e.data);
        });
    },

    async uploadimggroup({ commit }, data) {
        var form = new FormData();
        for (var key in data) {
            form.append(key, data[key])
          }
        await api.postForm("/group-center/upload-group", form, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            commit("set_update_namegroup", e.data)
        }).catch((err) => {
            console.log(err)
        })
    },

    async uploadgroup({ commit }, data) {
        var form = new FormData();
        for (var key in data) {
            form.append(key, data[key])
          }
        await api.postForm("/file-center/upload", form, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            commit("set_data_group", e.data);
        }).catch((err) => {
            console.log(err)
        })
    },

    async get_groupid({ commit }, id) {
        await api.get("/user-center/groupid/" + id, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
           commit("set_group_id",e.data);
        }).catch(() => {
            console.log("hahah")
        })
      },

    async get_emoji({ commit }) {
        await axios.get("https://emoji-api.com/emojis?access_key=abf6d22a32adac97c59c8c6e9dc00677f9d1ae1d").then((e) => {
            commit("set_emoji", e.data)
        });
      },


      async addfeel({ commit }, data) {
        await api.post("/feel-center/feel", data, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            commit("set_data_after_update_feel", e.data)
        }).catch(() => {
            console.log("hahah")
        })
      },

      async addwatch({ commit }, data) {
        await api.post("/watch-center/watch", data,{
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            commit("set_data_after_update", e.data)
        }).catch(() => {
            console.log("hahah")
        })
      },

      async deletefeel({ commit }, data) {
        await api.delete("/feel-center/feel/" + data.id  + "/" + data.type,{
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            commit("set_data_after_update", e.data)
        }).catch(() => {
            console.log("hahah")
        })
      },

      async addgroup({ commit }, data) {
        await api.post("/group-center/group", data,{
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            commit("set_array_connect_after", e.data);
        }).catch(() => {
            console.log("add group fail")
        })
      },


      async delete_group({ commit }, data) {
        await api.delete("/group-center/group/" + data, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
        }).catch(() => {
            console.log("delete group fail")
        })
      },

      async out_group({ commit }, data) {
        console.log(data.code);
        await api.post("/group-center/out-group/" + data.id + "/" + data.code,{}, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            const array = [];
            array.push(e.data);
            commit("set_data_after_send", array)
        }).catch(() => {
            console.log("delete group fail")
        })
      },



      async add_person_group({ commit }, data) {
        await api.post("/group-center/group-person", data, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
           commit("set_add_person", e.data);
        }).catch(() => {
            console.log("hahah")
        })
      },




      async kick_person_group({ commit }, data) {
        await api.post("/group-center/group-person-kich", data,{
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            commit("set_kick_person", e.data);
        }).catch(() => {
            console.log("hahah")
        })
      },




      async update_namegroup({ commit }, data) {
        console.log(data);
        await api.put("/group-center/group-name", data, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
           commit("set_update_namegroup", e.data)
        }).catch(() => {
            console.log("hahah")
        })
      },

      async get_array_connect({ commit }, data) {
            await api.get("/user-center/array-connect", {
                headers: {
                    Authorization: "Bearer " + data
                }
            }).then((e) => {
                commit("set_data_connect", e.data);
            }).catch((err) => {
                console.log(err)
            })
        },

    async get_mygroup({ commit },data) {
        await api.get("/group-center/mygroup", {
            headers: {
                Authorization: "Bearer " + data
            }
        }).then((e) => {
            commit("set_data_mygroup",e.data);
        }).catch((err) => {
            console.log(err)
        })
    },

    
    async unmess({ commit }, data) {
        await api.post("/revoke-center/unmess", data,{
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            commit("set_data_after_update", e.data)
        }).catch((err) => {
            console.log(err)
        })
    },

    async watchmess({ commit }, data) {
        await api.post("/user/watch", data, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            commit("set_data_after_watch", e.data)
            console.log(e.data)
        }).catch((err) => {
            console.log(err)
        })
    },

    async pin({ commit }, data) {
        await api.post("/pin-center/pin", data, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            const x = [];
            x.push(e.data.mess_reponse);
            commit("set_data_after_send", x)
            commit("set_after_pin", e.data.pin_chid)
        }).catch((err) => {
            console.log(err)
        })
    },

    async getpin({ commit }, data) {
        await api.get("/pin-center/pin", {
            headers: {
                Authorization: "Bearer " + data
            }
        }).then((e) => {
            commit("set_array_pin", e.data)
        }).catch((err) => {
            console.log(err)
        })
    },

    async updatenotify({ commit },data) {
        await api.get("/info-center/change-notify/" + data, {
            headers: {
                Authorization: "Bearer " +  localStorage.getItem("token")
            }
        })
    },

    async unpin({ commit }, id) {
        await api.delete("/pin-center/pin-del/" + id, {
            headers: {Authorization: "Bearer " + localStorage.getItem("token")}
        }).then((e) => {
            const x = [];
            x.push(e.data.mess_reponse);
            commit("set_data_after_send", x)
        }).catch((err) => {
            console.log(err)
        })
    },
    
    }
  }
export default module_mess;