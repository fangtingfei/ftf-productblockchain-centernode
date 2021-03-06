package cn.ftf.productblockchain.centernode.controller;


import cn.ftf.productblockchain.centernode.bean.Product;
import cn.ftf.productblockchain.centernode.bean.ProductInfo;
import cn.ftf.productblockchain.centernode.cache.DataPool;
import cn.ftf.productblockchain.centernode.message.Result;
import cn.ftf.productblockchain.centernode.util.RSAUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductionInfoController
 *
 * @Author 房廷飞
 * @Create 2020-12-10 17:31
 */

@RestController
public class ProductionInfoController {

    @Autowired
    private DataPool dataPool;

    @RequestMapping(value = "/addProduction", method = RequestMethod.POST)
    public Result addProduction(@RequestBody ProductInfo productInfo){
        System.out.println(productInfo);
        ObjectMapper mapper=new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(productInfo));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String json=null;
        try {
            Product product=new Product(productInfo.getCompany(),productInfo.getProduct(),productInfo.getProductionDate(),productInfo.getOrginPlace(),productInfo.getDescription());
            json=mapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        boolean boo= RSAUtils.verify("SHA256withRSA", RSAUtils.getPublicKeyFromString("RSA",productInfo.getSenderPublicKey()), json, productInfo.getSignaturedData());
        if(boo){
            dataPool.addData(productInfo);
            return new Result(true,"录入成功！");
        }
        return new Result(false,"录入失败");
    }

}
