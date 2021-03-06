package cn.ftf.productblockchain.centernode.cache;


import cn.ftf.productblockchain.centernode.bean.ProductInfo;
import cn.ftf.productblockchain.centernode.websocket.MyServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据池
 *
 * @Author 房廷飞
 * @Create 2020-12-12 10:39
 */

@Component
public class DataPool {
    private List<ProductInfo> productInfoPool;
    private ObjectMapper mapper=new ObjectMapper();
    private MyServer myServer=null;

    public DataPool() {
        productInfoPool=new ArrayList<>();
    }

    public void addData(ProductInfo productInfo){
        this.productInfoPool.add(productInfo);
        System.out.println("Pool Size:"+this.productInfoPool.size());
        judge();
    }
    private void judge(){
        String listJson=null;
        if(this.productInfoPool.size()==5){
            try {
                listJson=mapper.writeValueAsString(this.productInfoPool);
                myServer.broadcast(listJson);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println(listJson);
        }
    }
}
