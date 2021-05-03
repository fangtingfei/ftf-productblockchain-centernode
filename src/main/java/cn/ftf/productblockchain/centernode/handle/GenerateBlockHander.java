package cn.ftf.productblockchain.centernode.handle;

import cn.ftf.productblockchain.centernode.bean.WebSocketInit;
import cn.ftf.productblockchain.centernode.bean.block.Block;
import cn.ftf.productblockchain.centernode.bean.POJO.BroadcastedProductInfo;
import cn.ftf.productblockchain.centernode.bean.block.Blockchain;
import cn.ftf.productblockchain.centernode.message.BroadcastMsg;
import cn.ftf.productblockchain.centernode.util.JacksonUtils;
import cn.ftf.productblockchain.centernode.websocket.MyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * @author fangtingfei
 * @version 1.0
 * @date 2021-04-03 11:21
 */
public class GenerateBlockHander {
    private  Logger logger= LoggerFactory.getLogger(getClass());
    private  MyServer server= WebSocketInit.server;
    public  void generateBlock(BroadcastedProductInfo[] toBlockList,Boolean boo) throws IOException {
        Block newBlock = Blockchain.mineBlock((toBlockList));
        if(boo){
            Blockchain.addBlock(newBlock);
        }
        broadcastBlock(newBlock,boo);

    }
    public  void broadcastBlock(Block block,Boolean boo){
        String blockJson = JacksonUtils.objToJson(block);
        logger.info("[新区块转化为JSON] blockJson={}",blockJson);
        BroadcastMsg broadcastMsg=null;
        if(!boo){
            broadcastMsg=new BroadcastMsg(1,blockJson);
        }else {
            broadcastMsg=new BroadcastMsg(2,blockJson);
        }
        String broascastMsgJson = JacksonUtils.objToJson(broadcastMsg);
        logger.info("[生成区块广播体JSON] broascastMsgJson={}",broascastMsgJson);
        server.broadcast(broascastMsgJson);
        logger.info("[区块广播体JSON广播成功] broascastMsgJson={}",broascastMsgJson);

    }
}
