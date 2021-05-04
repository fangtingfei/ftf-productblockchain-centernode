package cn.ftf.productblockchain.centernode.cache;

import cn.ftf.productblockchain.centernode.bean.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fangtingfei
 * @version 1.0
 * @date 2021-05-03 18:51
 */
public class View {
    public static int code=0;
    public static int voteNum=0;
    public static Block cacheBlock;

    private static Logger logger= LoggerFactory.getLogger(DataPool.class);

    public static boolean canCreateBlock(){
        int door=AddressPool.addressPoll.size()/2+1;
        logger.info("节点数："+(AddressPool.addressPoll.size()));
        logger.info("阈值："+door);
        if(voteNum>=door){
            logger.info("canCreateBlock：true");

            return true;
        }
        logger.info("canCreateBlock：false");
        return false;
    }
    public static Block getCacheBlock(){
        return cacheBlock;
    }
    public static void setCacheBlock(Block block){
        cacheBlock=block;
    }
}
