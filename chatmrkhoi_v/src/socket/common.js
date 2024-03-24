import Stomp from "webstomp-client"
export default () => {
    var stompClient = Stomp.client("ws://localhost:8081/gs-guide-websocket");
    // stompClient.debug = function() {};
    
    const socketx = new WebSocket("ws://localhost:8081/gs-guide-websocket");
    socketx.addEventListener("error" , () => {
       alert("Ngung ket noi")
    })
    return {
        stompClient,
    }
}