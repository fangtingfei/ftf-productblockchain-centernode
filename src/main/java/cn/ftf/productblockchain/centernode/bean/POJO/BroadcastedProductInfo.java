package cn.ftf.productblockchain.centernode.bean.POJO;

import java.io.Serializable;
import java.util.Objects;

/**
 * 商品录入信息
 *
 * @Author 房廷飞
 * @Create 2020-12-10 17:16
 */

public class BroadcastedProductInfo implements Serializable {
    private String company;
    private String product;
    private String productionDate;
    private String orginPlace;
    private String description;
    private String notes;

    //扫描终端公钥
    private String senderPublicKey;
    //数据签名
    private String signaturedData;

    public BroadcastedProductInfo(){

    }
    public BroadcastedProductInfo(String company, String product, String productionDate, String orginPlace, String description, String notes, String senderPublicKey, String signaturedData) {
        this.company = company;
        this.product = product;
        this.productionDate = productionDate;
        this.orginPlace = orginPlace;
        this.description = description;
        this.notes = notes;
        this.senderPublicKey = senderPublicKey;
        this.signaturedData = signaturedData;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BroadcastedProductInfo that = (BroadcastedProductInfo) o;
        return Objects.equals(company, that.company) &&
                Objects.equals(product, that.product) &&
                Objects.equals(productionDate, that.productionDate) &&
                Objects.equals(orginPlace, that.orginPlace) &&
                Objects.equals(description, that.description) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(senderPublicKey, that.senderPublicKey) &&
                Objects.equals(signaturedData, that.signaturedData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, product, productionDate, orginPlace, description, notes, senderPublicKey, signaturedData);
    }

    @Override
    public String toString() {
        return "BroadcastedProductInfo{" +
                "company='" + company + '\'' +
                ", product='" + product + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", orginPlace='" + orginPlace + '\'' +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                ", senderPublicKey='" + senderPublicKey + '\'' +
                ", signaturedData='" + signaturedData + '\'' +
                '}';
    }
}
