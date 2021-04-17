package cn.ftf.productblockchain.centernode.bean.block;

/**
 * @author fangtingfei
 * @version 1.0
 * @date 2021-04-05 11:59
 */
public class MiniBlock {
    public Integer height;
    public long timeStamp;
    public String hash;
    public String preHash;

    public MiniBlock(Integer height, long timeStamp, String hash, String preHash) {
        this.height = height;
        this.timeStamp = timeStamp;
        this.hash = hash;
        this.preHash = preHash;
    }

    public MiniBlock() {
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreHash() {
        return preHash;
    }

    public void setPreHash(String preHash) {
        this.preHash = preHash;
    }

    @Override
    public String toString() {
        return "MiniBlock{" +
                "height=" + height +
                ", timeStamp=" + timeStamp +
                ", hash='" + hash + '\'' +
                ", preHash='" + preHash + '\'' +
                '}';
    }
}
