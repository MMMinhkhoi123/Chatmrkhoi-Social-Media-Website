import socket from "../socket/common"
import store from "../store";
const { stompClient } = socket();
export default () => { 
    if(stompClient.connected == false) {
        stompClient.connect({}, (e) => {
            stompClient.subscribe("/everyone/newuser", tick => {
                const x = JSON.parse(tick.body);
                var check = true;
                if(store.state.everyone.array_not_friend == null) {
                    store.state.everyone.array_not_friend = [];
                } 
                store.state.everyone.array_not_friend.forEach(element => {
                    if(element.id == x.id) {
                        check = false;
                    }
                });
                if(check == true) {
                    store.state.everyone.array_not_friend.push(x);
                }
            },{});
        },() => {});
    }
}