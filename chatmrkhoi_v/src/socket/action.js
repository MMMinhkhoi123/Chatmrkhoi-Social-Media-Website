import socket from "../socket/common"
import store from "../store";
const { stompClient } = socket();
export default () => {
        stompClient.connect({ login: store.state.auth.authen.id } , (e) => {
            stompClient.subscribe("/user/action/run", tick => {
                const x = JSON.parse(tick.body);
                if(store.state.auth.authen != null) {
                    if(x.id != store.state.auth.authen.id &&  store.state.chat.array_connect != null) {
                        store.state.chat.array_connect.forEach(element => {
                            if(element.idfriend == x.id) {
                                var check = true;
                                store.state.chat.data_online.forEach((e, index) => {
                                    if(e.id == x.id) {                       
                                        check = false;
                                        store.state.chat.data_online[index] = x;
                                    }
                                })
                                if (check == true) {
                                    store.state.chat.data_online.push(x)
                                }
                            }
                        });   
                    }
                }
            },{});
        },() => {})
}