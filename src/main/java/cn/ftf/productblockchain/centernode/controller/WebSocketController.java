package cn.ftf.productblockchain.centernode.controller;

import cn.ftf.productblockchain.centernode.cache.AddressPool;
import cn.ftf.productblockchain.centernode.message.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebSocketController
 *
 * @Author 房廷飞
 * @Create 2021-03-20 21:27
 */
@RestController
@RequestMapping("/node")
public class WebSocketController {
    Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private AddressPool addressPool;
    @GetMapping("/add")
    public Result addNode (String uri)throws Exception{
        logger.info("[注册节点] URI={}",uri);
        addressPool.addURI(uri);
        return new Result(true,"success");
    }
    @PostMapping("/remove")
    public Result removeNode (String uri)throws Exception{
        addressPool.removeURI(uri);
        logger.info("[移除节点成功] URI={}",uri);
        return new Result(true,"success");
    }


}
