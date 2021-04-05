package cn.ftf.productblockchain.centernode.websocket;



import cn.ftf.productblockchain.centernode.broadcastMsgConsumer.BroadcastMsgConsumer;
import cn.ftf.productblockchain.centernode.cache.AddressPool;
import cn.ftf.productblockchain.centernode.message.BroadcastMsg;
import cn.ftf.productblockchain.centernode.util.JacksonUtils;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

/**
 * 客户端
 *
 * @Author 房廷飞
 * @Create 2020-11-27 8:06
 */

public class MyClient extends WebSocketClient {
    private String uri;
    Logger logger= LoggerFactory.getLogger(getClass());
    public MyClient(URI serverUri) {
        super(serverUri);
        this.uri = serverUri.toString();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        AddressPool.addressPoll.add(uri);
        logger.info("[添加节点成功] URI={}",uri);
        logger.info("[客户端开启连接] URI={}",uri);
    }

    @Override
    public void onMessage(String message) {
        logger.info("[客户端接收消息] Msg={}", message);
        BroadcastMsg broadcastMsg = JacksonUtils.jsonToObj(message, BroadcastMsg.class);
        switch (broadcastMsg.getType()) {
            case 0: {
                logger.info("[客户端接收商品信息] Msg={}", message);
                try {
                    BroadcastMsgConsumer.handleProductMsg(broadcastMsg.getMsg());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            default:{
                logger.info("[广播消息体检测异常] Msg={}",message);
            }
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        AddressPool.addressPoll.remove(uri);
        logger.info("[客户端关闭连接] URI={}",uri);
    }

    @Override
    public void onError(Exception ex) {
        AddressPool.addressPoll.remove(uri);
        logger.info("[客户端错误] URI={}",uri);
    }
}
