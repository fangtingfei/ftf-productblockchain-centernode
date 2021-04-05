package cn.ftf.productblockchain.centernode.broadcastMsgConsumer;


import cn.ftf.productblockchain.centernode.bean.POJO.BroadcastedProductInfo;
import cn.ftf.productblockchain.centernode.bean.POJO.ProductInfo;
import cn.ftf.productblockchain.centernode.cache.DataPool;
import cn.ftf.productblockchain.centernode.util.JacksonUtils;
import cn.ftf.productblockchain.centernode.util.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
}
