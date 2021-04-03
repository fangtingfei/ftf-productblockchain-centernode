package cn.ftf.productblockchain.centernode.cache;


import cn.ftf.productblockchain.centernode.websocket.MyClient;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;

/**
 * AddressPool
 *
 * @Author 房廷飞
 * @Create 2020-12-12 20:32
 */
@Component
public class AddressPool {
    Logger logger= LoggerFactory.getLogger(getClass());
    public static HashSet<String> addressPoll = new HashSet<>();
    public AddressPool(){
    }

    @PostConstruct
    public void init() throws Exception {
        File addressPool = new File("addressPool.uri");
        if (!addressPool.exists() || addressPool.length() == 0 || !addressPool.exists() || addressPool.length() == 0) {
            addressPool.createNewFile();
        }
        List<String> addressFromFile = FileUtils.readLines(addressPool, "UTF-8");
        addressFromFile.stream().forEach(address->addURI(address));
    }

    public void addURI(String uri){
        if(addressPoll.contains(uri)){
            logger.info("[重复注册，未执行注册] URI={}",uri);
            return;
        }
        MyClient client = null;
        try {
            client = new MyClient(new URI(uri));
        } catch (URISyntaxException e) {
            logger.info("[注册节点失败] URI={}",uri);
            e.printStackTrace();
        }
        client.connect();
    }
    public void removeURI(String uri){
        addressPoll.remove(uri);
        try {
            MyClient client = new MyClient(new URI(uri));
            client.close();
            logger.info("[连接销毁] URI={}",uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public HashSet<String> getAddressPoll() {
        return addressPoll;
    }

    public void setAddressPoll(HashSet<String> addressPoll) {
        AddressPool.addressPoll = addressPoll;
    }
}
