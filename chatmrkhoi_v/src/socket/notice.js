import socket from "../socket/common"
import store from "../store";
import notice from "../handle/Notice/index";
const { stompClient } = socket();
const { run } = notice();
export default () => {
    if(stompClient.connected == false) {
        stompClient.connect();   
    }
    const handleSend = () => {
        if (stompClient.connected == true) {
            stompClient.subscribe("/user/notice/" + store.state.auth.authen.id , tick => {
                const x = JSON.parse(tick.body);
                store.state.notice.arrayNotice.push(x);
                run(x.title);
            },{});
        } else {
            setTimeout(() => { handleSend() }, 200)
        }
    }
    handleSend()
}