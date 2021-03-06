package cn.ftf.productblockchain.centernode.cache;

import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * AddressPool
 *
 * @Author 房廷飞
 * @Create 2020-12-12 20:32
 */

@Component
public class AddressPool {
    public static HashSet<String> addressPoll = new HashSet<>();

    public AddressPool(){
        addressPoll.add("ws://127.0.0.1:7000");
        addressPoll.add("ws://127.0.0.1:9000");
    }
}
