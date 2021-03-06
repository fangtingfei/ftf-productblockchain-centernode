package cn.ftf.productblockchain.centernode.bean;

import java.io.Serializable;

/**
 * 商品录入信息
 *
 * @Author 房廷飞
 * @Create 2020-12-10 17:16
 */

public class ProductInfo implements Serializable {
    private String company;
    private String product;
    private String productionDate;
    private String orginPlace;
    private String description;

    private String senderPublicKey;   //扫描终端公钥
    private String signaturedData;  //数据签名

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
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

    @Override
    public String toString() {
        return "ProductInfo{" +
                "company='" + company + '\'' +
                ", product='" + product + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", orginPlace='" + orginPlace + '\'' +
                ", description='" + description + '\'' +
                ", senderPublicKey='" + senderPublicKey + '\'' +
                ", signaturedData='" + signaturedData + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
