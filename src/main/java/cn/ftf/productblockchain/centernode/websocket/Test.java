package cn.ftf.productblockchain.centernode.websocket;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * websocket测试
 *
 * @Author 房廷飞
 * @Create 2020-12-19 8:02
 */

public class Test {
    public static void main(String[] args) {
        MyServer server=new MyServer(7000);
        server.startServer();
        URI uri= null;
        try {
            uri = new URI("ws://localhost:7000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        MyClient client1=new MyClient(uri);
        MyClient client2=new MyClient(uri);
        client1.connect();
        client2.connect();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        server.broadcast("这是来自服务器的消息！ahhhhhhhh");
    }

}
