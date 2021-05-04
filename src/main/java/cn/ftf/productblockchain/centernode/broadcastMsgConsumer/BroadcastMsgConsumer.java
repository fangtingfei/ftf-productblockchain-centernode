package cn.ftf.productblockchain.centernode.broadcastMsgConsumer;


import cn.ftf.productblockchain.centernode.bean.POJO.BroadcastedProductInfo;
import cn.ftf.productblockchain.centernode.bean.POJO.ProductInfo;
import cn.ftf.productblockchain.centernode.bean.block.Block;
import cn.ftf.productblockchain.centernode.bean.block.Blockchain;
import cn.ftf.productblockchain.centernode.bean.block.MiniBlock;
import cn.ftf.productblockchain.centernode.cache.DataPool;
import cn.ftf.productblockchain.centernode.cache.View;
import cn.ftf.productblockchain.centernode.handle.GenerateBlockHander;
import cn.ftf.productblockchain.centernode.message.BroadcastMsg;
import cn.ftf.productblockchain.centernode.util.JacksonUtils;
import cn.ftf.productblockchain.centernode.util.RSAUtils;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashSet;

/**
 * @author fangtingfei
 * @version 1.0
 * @date 2021-03-28 14:29
 */
public class BroadcastMsgConsumer {

    private static Logger logger = LoggerFactory.getLogger(BroadcastMsgConsumer.class);
    public static void handleProductMsg(String broadcastMsgJson) throws IOException {
        BroadcastedProductInfo broadcastedProductInfo = JacksonUtils.jsonToObj(broadcastMsgJson, BroadcastedProductInfo.class);
        String productJson = null;
        ProductInfo product = null;
        try {
            product = new ProductInfo(broadcastedProductInfo.getCompany(), broadcastedProductInfo.getProduct(),broadcastedProductInfo.getTimeStamp(), broadcastedProductInfo.getOrginPlace(), broadcastedProductInfo.getDescription(), broadcastedProductInfo.getNotes());
            productJson = JacksonUtils.objToJson(product);
            logger.info("[提取商品信息]productJson:" + productJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean boo = RSAUtils.verify("SHA256withRSA", RSAUtils.getPublicKeyFromString("RSA", broadcastedProductInfo.getSenderPublicKey()), productJson, broadcastedProductInfo.getSignaturedData());
        if (boo) {
            DataPool.addData(broadcastedProductInfo);
            logger.info("[数据校验成功，录入成功] productInfo:" + product);
            return;
        }
        logger.info("[数据校验失败，录入失败！] productInfo:" + product);

    }
    public static void handleBlockMsg(String broadcastMsgJson, WebSocketClient client){
        Block block = JacksonUtils.jsonToObj(broadcastMsgJson, Block.class);
        MiniBlock miniBlock = null;
        //区块校验
        boolean boo=Blockchain.verifyBlock(block);
        if(boo){
            logger.info("[区块数据校验成功] block:" + block);
        }
        Boolean matchFlag=true;
        HashSet<String> localHashSet = new HashSet<>();
        for (int i = 0; i < DataPool.getProductInfoPool().size(); i++) {
            localHashSet.add(JacksonUtils.objToJson(DataPool.getProductInfoPool().get(i)));
        }
        for (int i = 0; i < block.getList().length; i++) {
            if (!localHashSet.contains(JacksonUtils.objToJson(block.getList()[i]))) {
                System.out.println("本地数据池不含有该条数据！");
                matchFlag=false;
            } else {
                System.out.println("匹配到本地数据池数据" + JacksonUtils.objToJson(block.getList()[i]));
            }
        }
        if(matchFlag){
            BroadcastMsg msg=new BroadcastMsg(3,"VOTE");
            client.send(JacksonUtils.objToJson(msg));
        }

//        try {
//            miniBlock=new MiniBlock(block.height,block.timeStamp,block.hash,block.preHash);
//            logger.info("[生成MiniBlock] miniBlock={}", miniBlock);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }
    public static void handleViewedBlockMsg(String broadcastMsgJson) throws IOException {
        Block block = JacksonUtils.jsonToObj(broadcastMsgJson, Block.class);
        Blockchain.addBlock(block);
        for (int i = 0; i < 4; i++) {
            DataPool.getProductInfoPool().remove(0);
        }
    }
    public static void handleVote() throws IOException {
        View.voteNum++;
        if(View.canCreateBlock()){
            Block block=View.getCacheBlock();
            GenerateBlockHander hander=new GenerateBlockHander();
            hander.generateBlock(block.getList(),true);
            for (int i = 0; i < 4; i++) {
                DataPool.getProductInfoPool().remove(0);
            }
        }
    }
}
