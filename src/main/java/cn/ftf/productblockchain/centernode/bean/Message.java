package cn.ftf.productblockchain.centernode.bean;

/**
 * MessageBean
 *
 * @Author 房廷飞
 * @Create 2020-11-27 8:02
 */

public class Message {
    public int type;
    public String msg;

    public Message() {
    }

    public Message(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
