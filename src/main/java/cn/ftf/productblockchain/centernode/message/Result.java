package cn.ftf.productblockchain.centernode.message;

import java.io.Serializable;

/**
 * 封装响应数据
 *
 * @Author 房廷飞
 * @Create 2020-12-10 17:59
 */

public class Result implements Serializable {
    private boolean flag;
    private String message;
    private Object data;
    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
