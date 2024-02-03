import socket from "../socket/common";
const { stompClient } = socket();
export default (datasend) => {

    if(stompClient.connected == false) {
        stompClient.connect();   
    }
    const handleSend = () => {
        if (stompClient.connected == true) {
            stompClient.send("/user/typing/run", JSON.stringify(datasend));
        } else {
            setTimeout(() => { handleSend() }, 200)
        }
    }
    handleSend()
}