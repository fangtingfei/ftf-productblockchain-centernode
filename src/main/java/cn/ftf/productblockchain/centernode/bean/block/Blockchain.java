package cn.ftf.productblockchain.centernode.bean.block;

import cn.ftf.productblockchain.centernode.bean.POJO.BroadcastedProductInfo;
import cn.ftf.productblockchain.centernode.util.ByteUtils;
import cn.ftf.productblockchain.centernode.util.JacksonUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 区块链实体类
 * @author fangtingfei
 * @date 2020/04/04 13:34
 */
@Component
public class Blockchain {

    private static ArrayList<Block> blocks=new ArrayList<>();

    public Blockchain() {
    }

    public static ArrayList<Block> getBlocks() {
        return blocks;
    }

    public static void setBlocks(ArrayList<Block> blocks) {
        Blockchain.blocks = blocks;
    }
    @PostConstruct
    private void init() throws Exception {
        File blockChainDB = new File("blockchain.db");
        if (!blockChainDB.exists() || blockChainDB.length() == 0 || !blockChainDB.exists() || blockChainDB.length() == 0) {
            blockChainDB.createNewFile();
        }
        List<String> blocks = FileUtils.readLines(blockChainDB, "UTF-8");
        if(blocks.size()==0){
            BroadcastedProductInfo genesisProduct=new BroadcastedProductInfo("创世公司","创世区块",1617529203L,"房廷飞","2021年4月4日生成创世区块","描述：创世区块","创世区块".getBytes(),"","");
            Block genesisBlock = Block.newGenesisBlock(genesisProduct);
            Blockchain.addBlock(genesisBlock);
        }
        blocks.stream().forEach(blockJson->initBlockChainFromDB(blockJson));
        System.out.println("中心节点区块链初始化完成，size="+blocks.size());
    }

    /**
     * 从 DB 中恢复区块链数据
     *
     * @return
     */

    private static void initBlockChainFromDB(String blockJson) {
        if(blockJson.isBlank()) {return;}
        Block block=null;
        try {
            block = JacksonUtils.jsonToObj(blockJson, Block.class);
        }catch (Exception e){
            System.out.println("区块反序列化失败:"+blockJson);
        }

        blocks.add(block);
    }


    /**
     * 添加区块
     *
     * @param block
     */
    public static void addBlock(Block block) throws IOException {
        blocks.add(block);
        String blockJson = JacksonUtils.objToJson(block);
        File blockChainDB = new File("blockchain.db");
        FileUtils.write(blockChainDB,blockJson,"UTF-8",true);
        FileUtils.write(blockChainDB,"\n","UTF-8",true);
    }

    /**
     * 商品信息打包
     *
     * @param broadcastedProductInfos
     */
    public static Block mineBlock(BroadcastedProductInfo[] broadcastedProductInfos) throws IOException {
        //数据进入系统时已经验证合法，这里无需验证
        Block lastBlock=blocks.get(blocks.size()-1);
        if (lastBlock == null) {
            throw new RuntimeException("ERROR: Fail to get last block ! ");
        }
        Block block = Block.newBlock(lastBlock.height,lastBlock.hash,broadcastedProductInfos);
        return block;
    }

    /**
     * 区块合法性验证
     */
    public static boolean verifyBlock(Block block) {
        int currentHeight= block.height;
        if(currentHeight==0){return true;}
        Block preBlock=blocks.get(currentHeight-1);
        String preHash= preBlock.hash;
        if(!preHash.equals(block.getPreHash())){
            System.out.println("prehash验证不合法");
            return false;
        }
        //区块Hash校验
        return verifyBlockData(block);
    }

    /**
     * 区块数据验证
     */
    public static boolean verifyBlockData(Block block){
        byte[] prevBlockHashBytes = {};
        if (StringUtils.isNoneBlank(block.getPreHash())) {
            prevBlockHashBytes = new BigInteger(block.getPreHash(), 16).toByteArray();
        }

        byte[] data =ByteUtils.merge(
                prevBlockHashBytes,
                //所有商品信息的默克尔Hash
                block.hashTransaction(),
                ByteUtils.toBytes(block.timeStamp)
        );
        String shaHex = DigestUtils.sha256Hex(data);
        if(!block.hash.equals(shaHex)){
            System.out.println("区块Hash校验失败");
            return false;
        }
        return true;
    }

    public static Block getBlockByHeight(int height){
        return blocks.get(height);
    }

    /**
     * 全链校验
     * @return
     */
    public static boolean verifyAllBlockChain() throws Exception {
        for (int i = 0; i < blocks.size(); i++) {
            if(!verifyBlock(blocks.get(i))){
                System.out.println("全链校验失败，失败目标block"+blocks.get(i));
                return false;
            }
        }
        return true;
    }

}