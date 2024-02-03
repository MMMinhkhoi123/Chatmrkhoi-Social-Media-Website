import { computed, ref } from 'vue'
import store from '../../store/index'
import connect_public from "../../socket/handle_everyone"
import datas from "../Screen_chat/index";

import connects from "../../socket/connect_private";
import { useRouter } from 'vue-router';
export default () => {
    const router = useRouter();
    const { url, authen } = datas();
    const check_send_request = (value) => {
        var satus = false;
        store.state.everyone.array_send_request_friend.forEach((e) => {
            if(e.id == value) {
                satus = true;
            }
        } )
      return satus;
    }

    const check_request = (value) => {
        var satus = false;
        store.state.everyone.array_request_friend.forEach((e) => {
            if(e.id == value) {
                satus = true;
            }
        } )
      return satus;
    }

    const check_friend = (value) => {
        var satus = false;
        store.state.everyone.array_friend.forEach((e) => {
            if(e.id == value) {
                satus = true;
            }
        } )
      return satus;
    }

    // disagree request
    const destroy_agree = (id) => {
        store.state.everyone.data_find.forEach((e, index) => {
            if(e.id == id) {
                store.state.everyone.data_find[index].status = "not";
            }
        })
        const datax = store.getters["everyone/get_request_friend_id"](id)[0];
        datax.status = "not"
        store.state.everyone.array_not_friend.push(datax);

        store.state.everyone.array_request_friend =
        handle_pop(store.state.everyone.array_request_friend, id)
        
        const data = {
            idfriend: id,
            status: "destroy"
        }
        store.dispatch("everyone/action_request", data);
        const datasend =  {
            id_send: store.state.auth.authen.id,
            id_obtan: id,
            type: "disagree",
        } 
        connect_public(datasend);
    } 



    // agree request
    const agree = (id) => {
        store.state.everyone.data_after_action = null;
        store.state.everyone.data_find.forEach((e, index) => {
            if(e.id == id) {
                store.state.everyone.data_find[index].status = "friend";
            }
        })
        var datax = store.getters["everyone/get_request_friend_id"](Number(id))[0];
        datax.satus = "friend";
        store.state.everyone.array_friend.push(datax);
 

        store.state.everyone.array_request_friend = 
        handle_pop(store.state.everyone.array_request_friend ,id)
        const data = {
            idfriend:  id,
            status: "friend"
        }
        store.dispatch("everyone/action_request", data).then((e) => {
            const x = setInterval(() => {
                if(store.state.everyone.data_after_action != null) {
                    clearInterval(x);
                    // add my connect 
                    connects();
                    const datasend =  {
                        id_send: store.state.auth.authen.id,
                        id_obtan: id,
                        type: "friend",
                        data: store.state.everyone.data_after_action,
                    } 
                    connect_public(datasend);
                }
            }, 1000);
        })
    }




    // unfriend
    const unfriend = (id) => {
        store.state.everyone.data_find.forEach((e, index) => {
            if(e.id == id) {
                store.state.everyone.data_find[index].status = "not";
            }
        })
        const datax = store.getters["everyone/get_your_friend_id"](id)[0];
        datax.status = "not"
        store.state.everyone.array_not_friend.push(datax);
        store.state.everyone.array_friend = handle_pop(store.state.everyone.array_friend, id);
        store.dispatch("everyone/unfriend",id).then((e) => {
            store.state.chat.array_connect = store.state.chat.array_connect.filter((ex) => ex.idfriend != id );
            connects();
        })
        const datasend =  {
            id_send: store.state.auth.authen.id,
            id_obtan: id,
            type: "unfriend",
        } 
        connect_public(datasend);
    }

    const user = ref(null);


    const showalert = (value) => {
        const data = {
            img: 'delete.png',
            title: 'Notification.DeleteObj.Title',
            content: 'Notification.DeleteObj.Content'
        }
        store.state.avaible_chat.Notification.data = data;
        store.state.avaible_chat.data_select_user = value;
        store.state.avaible_chat.Notification.warning.unfriend = true;
    }


    const unfriend_fake = () => {
        unfriend(store.state.avaible_chat.data_select_user.id);
        store.state.avaible_chat.Notification.warning.unfriend = false;
    }
    

// destroy request
    const destroy = (id) => {
        store.state.everyone.data_find.forEach((e, index) => {
            if(e.id == id) {
                store.state.everyone.data_find[index].status = "not"
                store.state.everyone.array_not_friend.push(store.state.everyone.data_find[index]);
            }
        })
        const datax = store.getters["everyone/get_friend_request_id"](id)[0];
        datax.status = "not";
        store.state.everyone.array_not_friend.push(datax);
        store.state.everyone.array_send_request_friend =
             handle_pop(store.state.everyone.array_send_request_friend, id);  
        const data = {
            idfriend: id,
            status: "destroy"
        }
        store.dispatch("everyone/action_request",data);
        const datasend =  {
            id_send: store.state.auth.authen.id,
            id_obtan: id,
            type: "destroy-add-friend",
        } 
        connect_public(datasend);
    };




    // add request
    const addfriend = (id) => {
    store.state.everyone.data_find.forEach((e, index) => {
        if(e.id == id) {
            store.state.everyone.data_find[index].status = "request"
            store.state.everyone.data_find[index].coderoom = store.state.auth.authen.id.toString() + id.toString()
        }
    });

    var datax = store.getters["everyone/get_friend_id"](Number(id))[0]
    datax.status = "request"
    store.state.everyone.array_send_request_friend.push(datax);
    store.state.everyone.array_not_friend = handle_pop(store.state.everyone.array_not_friend, id);
    
    store.dispatch("everyone/addfriend", id);
        const datasend =  {
            id_send: store.state.auth.authen.id,
            id_obtan: id,
            type: "add-friend",
        } 
        connect_public(datasend);
    }


    
    // pop 
    function handle_pop(array, id) {
            var indexx = 0;
            array.forEach((element, index ) => {
                if(element.id == id) {
                    indexx = index;
                }
            });
            
        return array.filter((e, index) => index != indexx);
        }
    // list 
    function upper(text) {
        return text.toUpperCase();
    }

    function runselect(arrays) {
        const array = [];
        if(store.state.everyone.optionselect == true) {
            arrays.forEach((e) => {
                if(upper(e.fullname).search(upper(store.state.everyone.keysearch)) != -1) {
                    array.push(e);
                }
            })
        }
        if(store.state.everyone.optionselect == false) {
            arrays.forEach((e) => {
            if(upper(e.gmail).search(upper(store.state.everyone.keysearch)) != -1) {
                array.push(e);
              }
            });
        }
      return array;
    }

    const data = computed(() => {
        if (store.state.everyone.chose == 2) {
            if(store.state.everyone.keysearch != '') {
                const array = runselect(store.state.everyone.array_send_request_friend);
                store.state.avaible_chat.count_user =  array.length;
                return array;
            }
            store.state.avaible_chat.count_user = store.state.everyone.array_send_request_friend.length;
            return store.state.everyone.array_send_request_friend;
        } 
        if (store.state.everyone.chose == 3) {
            if(store.state.everyone.keysearch != '') {
                const array = runselect(store.state.everyone.array_request_friend);
                store.state.avaible_chat.count_user =  array.length;
                return array;
            }
            store.state.avaible_chat.count_user =  store.state.everyone.array_request_friend.length;
            return store.state.everyone.array_request_friend;
        }
        if (store.state.everyone.chose == 4) {
            if(store.state.everyone.keysearch != '') {
                const array = runselect(store.state.everyone.array_friend);
                store.state.avaible_chat.count_user =  array.length;
                return array;
            }
            store.state.avaible_chat.count_user =  store.state.everyone.array_friend.length;
            return store.state.everyone.array_friend;
        }
        if (store.state.everyone.resetrom == true) {
            store.state.everyone.resetrom = false;
            store.state.avaible_chat.count_user =   store.state.everyone.data_find.length;
            return store.state.everyone.data_find;
        }
        store.state.avaible_chat.count_user =  store.state.everyone.data_find.length;
        return store.state.everyone.data_find;
    })


    const hover_count =  computed(() => {
      return data.value.length;
    })
 
    const search = () => {
        store.state.everyone.keysearch == store.state.auth.authen.gmail 
        ? null 
        : store.state.everyone.optionselect == false 
                ?  store.dispatch("everyone/finduser", store.state.everyone.keysearch) 
                :  store.dispatch("everyone/findusername", store.state.everyone.keysearch);
    }




    function Goprofile(value) {
        router.push({ name: 'profile', query: { id: value } })
    }

    function CheckAddFriend(item) {
        if(item.status == 'not') {
            return true;
        }
        return false;
    }

    function CheckUnFriend(item) {    
        if( check_send_request(item.id) == true
        && item.status != 'not'
        && check_friend(item.id) == false) {
            return true;
        }
        return false;
    }

    function CheckDisagree(item)
    {
        if(check_request(item.id) == true && check_friend(item.id) == false) {
            return true;
        }
        return false;
    }


    function CheckAgree(item) {
        if(check_request(item.id) == true && check_friend(item.id) == false) {
            return true;
        }
        return false;
    }

    function DeleteFriend(item) {
        if(check_friend(item.id) == true) {
            return true;
        }
        return false;
    }



    return { data, addfriend, destroy,url,user, showalert, unfriend_fake, destroy_agree, agree, check_send_request, hover_count,check_request, check_friend,unfriend,search ,DeleteFriend,CheckAgree,CheckDisagree,CheckUnFriend, CheckAddFriend,Goprofile }
}