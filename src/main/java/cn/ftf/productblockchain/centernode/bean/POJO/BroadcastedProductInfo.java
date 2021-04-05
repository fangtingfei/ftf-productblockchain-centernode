package cn.ftf.productblockchain.centernode.bean.POJO;

import cn.ftf.productblockchain.centernode.util.SerializeUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 商品录入信息
 *
 * @Author 房廷飞
 * @Create 2020-12-10 17:16
 */

public class BroadcastedProductInfo implements Serializable {
    private String company;
    private String product;
    private Long timeStamp;
    private String orginPlace;
    private String description;
    private String notes;
    private byte[] hash;

    //扫描终端公钥
    private String senderPublicKey;
    //数据签名
    private String signaturedData;

    public BroadcastedProductInfo() {

    }
    public BroadcastedProductInfo(String company, String product, Long timeStamp, String orginPlace, String description, String notes, String senderPublicKey, String signaturedData) {
        this.company = company;
        this.product = product;
        this.timeStamp = timeStamp;
        this.orginPlace = orginPlace;
        this.description = description;
        this.notes = notes;
        this.senderPublicKey = senderPublicKey;
        this.signaturedData = signaturedData;
    }

    public byte[] hash() {
        // 使用序列化的方式对Transaction对象进行深度复制
        byte[] serializeBytes = SerializeUtils.serialize(this);
        BroadcastedProductInfo copyTx = (BroadcastedProductInfo) SerializeUtils.deserialize(serializeBytes);
        copyTx.setHash(new byte[]{});
        return DigestUtils.sha256(SerializeUtils.serialize(copyTx));
    }


    public BroadcastedProductInfo(String company, String product, Long timeStamp, String orginPlace, String description, String notes, byte[] hash, String senderPublicKey, String signaturedData) {
        this.company = company;
        this.product = product;
        this.timeStamp = timeStamp;
        this.orginPlace = orginPlace;
        this.description = description;
        this.notes = notes;
        this.hash = hash;
        this.senderPublicKey = senderPublicKey;
        this.signaturedData = signaturedData;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getOrginPlace() {
        return orginPlace;
    }

    public void setOrginPlace(String orginPlace) {
        this.orginPlace = orginPlace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSenderPublicKey() {
        return senderPublicKey;
    }

    public void setSenderPublicKey(String senderPublicKey) {
        this.senderPublicKey = senderPublicKey;
    }

    public String getSignaturedData() {
        return signaturedData;
    }

    public void setSignaturedData(String signaturedData) {
        this.signaturedData = signaturedData;
    }


    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "BroadcastedProductInfo{" +
                "company='" + company + '\'' +
                ", product='" + product + '\'' +
                ", timeStamp=" + timeStamp +
                ", orginPlace='" + orginPlace + '\'' +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                ", hash=" + Arrays.toString(hash) +
                ", senderPublicKey='" + senderPublicKey + '\'' +
                ", signaturedData='" + signaturedData + '\'' +
                '}';
    }
}