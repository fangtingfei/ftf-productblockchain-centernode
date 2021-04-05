package cn.ftf.productblockchain.centernode.cache;

import cn.ftf.productblockchain.centernode.bean.POJO.BroadcastedProductInfo;
import cn.ftf.productblockchain.centernode.bean.POJO.ProductInfo;
import cn.ftf.productblockchain.centernode.handle.GenerateBlockHander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 接收到的商品数据池
 *
 * @Author 房廷飞
 * @Create 2020-12-10 18:04
 */
@Component
public class DataPool {
    private static List<BroadcastedProductInfo> productInfoPool;
    private static Logger logger= LoggerFactory.getLogger(DataPool.class);
    public DataPool(){
        productInfoPool=new ArrayList<>();
    }

    public static void addData(BroadcastedProductInfo broadcastedProductInfo) throws IOException {
        productInfoPool.add(broadcastedProductInfo);
        int size=productInfoPool.size();
        logger.info("[DataPool数据量]={}",size);
        if(productInfoPool.size()==4){
            BroadcastedProductInfo[] toBlockList=new BroadcastedProductInfo[4];
            for (int i = 0; i < 4; i++) {
                toBlockList[i]=productInfoPool.get(0);
                productInfoPool.remove(0);
            }
            GenerateBlockHander.generateBlock(toBlockList);
        }
    }

}
