package cn.ftf.productblockchain.centernode.controller;


import cn.ftf.productblockchain.centernode.cache.AddressPool;
import cn.ftf.productblockchain.centernode.websocket.MyClient;
import cn.ftf.productblockchain.centernode.websocket.MyServer;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.HashSet;

/**
 * WebSocketController
 *
 * @Author 房廷飞
 * @Create 2020-12-19 8:49
 */
@RestController
public class WebSocketController {
    public static MyServer server;
    private HashSet<String> set = new HashSet<>();

    @PostConstruct
    public void init() throws Exception {
        server=new MyServer(9000);
        server.startServer();

        HashSet<String> addressPoll = AddressPool.addressPoll;
        for (String s : addressPoll) {
            MyClient client = new MyClient(new URI(s));
            client.connect();
        }
    }




}
