import socket from "../socket/common"
import store from "../store";

const { stompClient } = socket();
export default () => { 
        if(stompClient.connected == false) {
            stompClient.connect({}, (e) => {
                stompClient.subscribe("/user/typing/run", tick => {
                    const x = JSON.parse(tick.body);
                    if(x.user_id != store.state.auth.authen.id) {
                        if ( store.state.chat.array_connect != null ) {
                            store.state.chat.array_connect.forEach(element => {
                                if(element.coderoom == x.room) {
                                    var check = true;
                                    store.state.chat.data_typing.forEach((e, index) => {
                                        if(e.room == x.room) {                       
                                            check = false;
                                            store.state.chat.data_typing[index] = x;
                                        }
                                    })
                                    if (check == true) {
                                        store.state.chat.data_typing.push(x)
                                    }
                                }
                            });   
                        }
                    }
                })
            }, () => {});
        }
}