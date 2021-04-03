package cn.ftf.productblockchain.centernode.controller;

import cn.ftf.productblockchain.centernode.cache.DataPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * ProductionInfoController
 *
 * @Author 房廷飞
 * @Create 2020-12-10 17:31
 */
@Deprecated
@RestController
public class ProductionInfoController {

    @Autowired
    private DataPool dataPool;
    Logger logger= LoggerFactory.getLogger(getClass());

}
