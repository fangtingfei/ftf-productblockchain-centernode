package cn.ftf.productblockchain.centernode.bean.block;

import cn.ftf.productblockchain.centernode.bean.POJO.BroadcastedProductInfo;
import cn.ftf.productblockchain.centernode.util.JacksonUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated 区块链实体类
 * @author fangtingfei
 * @date 2020/04/04 13:34
 */
@Component
public class Blockchain {

    private static ArrayList<Block> blocks=new ArrayList<>();

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
    }

    /**
     * 从 DB 中恢复区块链数据
     *
     * @return
     */

    private static void initBlockChainFromDB(String blockJson) {
        if(blockJson.isBlank()) return;
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

    public Blockchain() {
    }

    public static ArrayList<Block> getBlocks() {
        return blocks;
    }

    public static void setBlocks(ArrayList<Block> blocks) {
        Blockchain.blocks = blocks;
    }

}