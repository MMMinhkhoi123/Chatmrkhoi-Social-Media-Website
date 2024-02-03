import socket from "../socket/common";
const { stompClient } = socket();
export default (datasend, room) => {
    if(stompClient.connected != true) {
        stompClient.connect();
    }
    stompClient.send("/user/"+ room +"/private", JSON.stringify(datasend));
}