import store from '../store/index.js'
import socket from "../socket/common"
import connects from "../socket/connect_private";

const { stompClient } = socket();
export default () => {
        if(stompClient.connected == false) {
            run();
        }
        function run() {
            stompClient.connect(
                {  },
                () => {
                   stompClient.subscribe("/everyone/public", tick => {
                        const x = JSON.parse(tick.body);
                         // ADD GROUP
                        if(x.person != null) {
                            if (x.person.includes(store.state.auth.authen.id) == true) {
                                store.state.chat.array_mygroup.push(x.mygroup);
                                store.state.chat.array_connect.push(x.object_connect)
                                connects();
                            }
                        }
                        if(Number(x.id_obtan) == store.state.auth.authen.id) {
                            // ADD FRIEND
                            if (x.type =="add-friend") {
                                store.state.everyone.data_find.forEach((e, index) => {
                                    if(e.id ==  Number(x.id_send)) {
                                        store.state.everyone.data_find[index].status = "request"
                                    }
                                })
                                var datax = store.getters["everyone/get_friend_id"]( Number(x.id_send))[0]
                                datax.status = "request"
                                store.state.everyone.array_request_friend.push(datax);
                                store.state.everyone.array_not_friend = handle_pop(store.state.everyone.array_not_friend, Number(x.id_send));
                            }
        
                            //DESTROY FRIEND
                            if (x.type == "destroy-add-friend") {
                                store.state.everyone.data_find.forEach((e, index) => {
                                    if(e.id ==  Number(x.id_send)) {
                                        store.state.everyone.data_find[index].status = "not"
                                    }
                                })
        
                                const datax = store.getters["everyone/get_request_friend_id"]( Number(x.id_send))[0];
                                datax.status = "not"
                                store.state.everyone.array_not_friend.push(datax)
                                store.state.everyone.array_request_friend = 
                                handle_pop(store.state.everyone.array_request_friend, Number(x.id_send))
                            }
        
                           // DISAGREE FRIEND
                            if (x.type == "disagree") {
                                store.state.everyone.data_find.forEach((e, index) => {
                                    if(e.id ==  Number(x.id_send)) {
                                        store.state.everyone.data_find[index].status = "not"
                                    }
                                })
                                const datax = store.getters["everyone/get_friend_request_id"](Number(x.id_send))[0];
                                datax.status = "not";
                                store.state.everyone.array_not_friend.push(datax);
        
                                store.state.everyone.array_send_request_friend =
                                  handle_pop(store.state.everyone.array_send_request_friend ,Number(x.id_send))
                            }
        
                            // AGREE FRIEND
                            if (x.type == "friend") { 
                                 store.state.everyone.data_find.forEach((e, index) => {
                                    if(e.id ==  Number(x.id_send)) {
                                        store.state.everyone.data_find[index].status = "friend"
                                    }
                                })
                                var data = store.getters["everyone/get_friend_request_id"](Number(x.id_send))[0]
                                data.status = "friend"
                                store.state.everyone.array_friend.push(data);
                                x.data.idfriend = x.id_send;
                                store.state.everyone.array_send_request_friend = handle_pop(store.state.everyone.array_send_request_friend ,Number(x.id_send))
                                store.state.chat.array_connect.push(x.data);
                                connects();
                            }
    
                            // UNFRIEND 
                            if (x.type == "unfriend") { 
                                store.state.everyone.data_find.forEach((e, index) => {
                                    if(e.id ==  Number(x.id_send)) {
                                        store.state.everyone.data_find[index].status = "not"
                                    }
                                })
    
                                const datax = store.getters["everyone/get_your_friend_id"](Number(x.id_send))[0];
                                datax.status = "not";
                                var item = null;
                                store.state.chat.array_connect.forEach((e) => {
                                    if(e.idfriend == x.id_send) {
                                        item = e;
                                    }
                                })
                                store.state.everyone.array_not_friend.push(datax);
                                if(localStorage.getItem("data-select") == item.coderoom) {
                                    localStorage.removeItem("data-select");
                                    store.state.avaible_chat.action_group = !store.state.avaible_chat.action_group;
                                    store.state.avaible_chat.open_change_array = !store.state.avaible_chat.open_change_array;
                                    const data = {
                                        img: 'unfriend.png',
                                        title: 'Notification.unfriend.Title',
                                        content: 'Notification.unfriend.Content'
                                    }
                                    store.state.avaible_chat.Notification.data = data;
                                    store.state.avaible_chat.Notification.warning.unfriend = true;
                                }
                                store.state.everyone.array_friend = handle_pop(store.state.everyone.array_friend ,Number(x.id_send))
                                store.state.chat.array_connect = store.state.chat.array_connect.filter((e) => e.idfriend != x.id_send);
                            }
                        }
                    },
                );
                },
                error => {}
            );
        }
       
    function handle_pop(array, id) {
        var indexx = 0;
        array.forEach((element, index ) => {
            if(element.id == id) {
                indexx = index;
            }
        });
    return array.filter((e, index) => index != indexx);
    }

}