package cn.ftf.productblockchain.centernode.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * 客户端
 *
 * @Author 房廷飞
 * @Create 2020-11-27 8:06
 */

public class MyClient extends WebSocketClient {
    private String name;

    public MyClient(URI serverUri) {
        super(serverUri);
        this.name = serverUri.toString();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("webSocket client_" + name + "_open the connection");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("webSocket client_" + name + "_receive the message:" + message+"");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("webSocket client_" + name + "_close the connection");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("webSocket clinet_" + name + "_ERROR");
    }
}
