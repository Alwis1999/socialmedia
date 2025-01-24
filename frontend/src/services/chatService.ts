import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";

const socketUrl = "http://localhost:8080/chat/websocket";

const client = new Client({
    brokerURL: socketUrl,
    reconnectDelay: 5000,
    debug: function (str) {
        console.log(str);
    },
});

client.activate();

export default client;
