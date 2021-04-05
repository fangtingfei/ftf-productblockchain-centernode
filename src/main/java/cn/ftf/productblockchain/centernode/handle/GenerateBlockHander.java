package cn.ftf.productblockchain.centernode.handle;

import cn.ftf.productblockchain.centernode.bean.block.Block;
import cn.ftf.productblockchain.centernode.bean.POJO.BroadcastedProductInfo;
import cn.ftf.productblockchain.centernode.bean.block.Blockchain;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author fangtingfei
 * @version 1.0
 * @date 2021-04-03 11:21
 */
public class GenerateBlockHander {
    public static Block generateBlock(BroadcastedProductInfo[] toBlockList) throws IOException {
        Block newBlock = Blockchain.mineBlock((toBlockList));
        Blockchain.addBlock(newBlock);
        return null;
    }
}
