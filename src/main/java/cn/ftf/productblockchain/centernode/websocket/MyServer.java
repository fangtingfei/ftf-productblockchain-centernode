package cn.ftf.productblockchain.centernode.websocket;


import cn.ftf.productblockchain.centernode.controller.WebSocketController;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

/**
 * 服务端
 *
 * @Author 房廷飞
 * @Create 2020-11-27 8:08
 */

public class MyServer extends WebSocketServer {
    private int port;

    public MyServer(int port) {
        super(new InetSocketAddress(port));
        this.port = port;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("webSocket server_" + port + "_open the connection");
//        System.out.println(conn.getRemoteSocketAddress().toString());
//        String strURI="ws:/"+conn.getRemoteSocketAddress().toString();
//        URI uri=null;
//        try {
//            uri=new URI(strURI);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        MyClient client=new MyClient(uri);
//        client.connect();

    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("webSocket server_" + port + "_close the connection");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("webSocket server_" + port + "_receive the message:" + message);
        WebSocketController.server.broadcast("反馈！！！");
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println("webSocket server_" + port + "_ ERROR");
    }

    @Override
    public void onStart() {
        System.out.println("webSocket server_" + port + "_open successfully");
    }

    public void startServer() {
        new Thread(this).start();
    }
}
