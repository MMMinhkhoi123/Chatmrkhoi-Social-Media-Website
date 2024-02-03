import socket from "../socket/common";
export default (datasend) => {  
    const { stompClient }  = socket(); 
    stompClient.connect(
        {},
        frame => {
            stompClient.subscribe("/everyone/public", tick => {});
        },
        error => {
           console.log("ket noi that bai");
        }
    );
    const handleSend = () => {
    if ( stompClient.connected == true) {
        stompClient.send("/everyone/public", JSON.stringify(datasend));
    } else {
        console.log("send mess fail");
        setTimeout(() => { handleSend() }, 1000)
    }
    }
    handleSend()
}