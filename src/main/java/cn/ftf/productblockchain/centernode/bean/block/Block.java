package cn.ftf.productblockchain.centernode.bean.block;

import cn.ftf.productblockchain.centernode.bean.POJO.BroadcastedProductInfo;
import cn.ftf.productblockchain.centernode.cache.DataPool;
import cn.ftf.productblockchain.centernode.util.ByteUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author fangtingfei
 * @version 1.0
 * @date 2021-03-13 22:33
 */
public class Block {
    public Integer height;
    public long timeStamp;
    public BroadcastedProductInfo[] list;
    public String hash;
    public String preHash;


    private static Logger logger= LoggerFactory.getLogger(DataPool.class);

    /**
     * <p> 创建创世区块 </p>
     *
     * @param coinbase
     * @return
     */
    public static Block newGenesisBlock(BroadcastedProductInfo coinbase) {
        return Block.newBlock(-1,ByteUtils.ZERO_HASH, new BroadcastedProductInfo[]{coinbase});
    }

    /**
     * <p> 创建新区块 </p>
     *
     * @param preHeight,transactions,preHash
     * @param transactions
     * @return
     */
    public static Block newBlock(int preHeight, String preHash,BroadcastedProductInfo[] transactions) {
        Block block = new Block(++preHeight, System.currentTimeMillis(),transactions,"",preHash);
        byte[] data = prepareData(block);
        String shaHex = DigestUtils.sha256Hex(data);
        block.setHash(shaHex);
        logger.info("[创建区块] {}",block);
        return block;
    }


    /**
     * 对区块中的交易信息进行Hash计算
     *
     * @return
     */
    public byte[] hashTransaction() {
        byte[][] txIdArrays = new byte[list.length][];
        for (int i = 0; i < list.length; i++) {
            txIdArrays[i] = list[i].hash();
        }
        return new MerkleTree(txIdArrays).getRoot().getHash();
    }

    /**
     * 准备数据
     * <p>
     * 注意：在准备区块数据时，一定要从原始数据类型转化为byte[]，不能直接从字符串进行转换
     *
     * @param block
     * @return
     */
    private static byte[] prepareData(Block block) {
        byte[] prevBlockHashBytes = {};
        if (StringUtils.isNoneBlank(block.getPreHash())) {
            prevBlockHashBytes = new BigInteger(block.getPreHash(), 16).toByteArray();
        }

        return ByteUtils.merge(
                prevBlockHashBytes,
                block.hashTransaction(),
                ByteUtils.toBytes(block.timeStamp)
        );
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public BroadcastedProductInfo[] getList() {
        return list;
    }

    public void setList(BroadcastedProductInfo[] list) {
        this.list = list;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreHash() {
        return preHash;
    }

    public void setPreHash(String preHash) {
        this.preHash = preHash;
    }

    public Block(Integer height, long timeStamp, BroadcastedProductInfo[] list, String hash, String preHash) {
        this.height = height;
        this.timeStamp = timeStamp;
        this.list = list;
        this.hash = hash;
        this.preHash = preHash;
    }

    public Block() {

    }

    @Override
    public String toString() {
        return "Block{" +
                "height=" + height +
                ", timeStamp=" + timeStamp +
                ", list=" + Arrays.toString(list) +
                ", hash='" + hash + '\'' +
                ", preHash='" + preHash + '\'' +
                '}';
    }
}
