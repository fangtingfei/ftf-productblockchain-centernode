package cn.ftf.productblockchain.centernode.message;

/**
 * @author fangitnfei
 * @version 1.0
 * @date 2021-03-27 19:08
 */
public class BroadcastMsg {
    // 0-广播商品信息  1-广播区块
    Integer type;
    String msg;

    public BroadcastMsg(){

    }

    public BroadcastMsg(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BroadcastMsg{" +
                "type=" + type +
                ", msg='" + msg + '\'' +
                '}';
    }
}
