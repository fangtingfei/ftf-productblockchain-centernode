package cn.ftf.productblockchain.centernode.websocket;


import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * 服务端
 *
 * @Author 房廷飞
 * @Create 2020-11-27 8:08
 */

public class MyServer extends WebSocketServer {
    Logger logger= LoggerFactory.getLogger(getClass());
    private int port;

    public MyServer(int port) {
         super(new InetSocketAddress(port));
        this.port = port;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        logger.info("[服务端开启连接] port={}",port);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        logger.info("[服务端关闭连接连接] port={}",port);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        logger.info("[服务端接收消息] Msg={}",message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        logger.info("[服务端出错] port={}",port);
    }

    @Override
    public void onStart() {
        logger.info("[服务端开启成功] port={}",port);
    }

    public void startServer() {
        new Thread(this).start();
    }
}
