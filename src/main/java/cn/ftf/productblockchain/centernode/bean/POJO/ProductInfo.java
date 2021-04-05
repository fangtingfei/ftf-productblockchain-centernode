package cn.ftf.productblockchain.centernode.bean.POJO;

import java.io.Serializable;
import java.util.Objects;

/**
 * ProductInfo
 *
 * @Author 房廷飞
 * @Create 2020-12-11 19:42
 */

public class ProductInfo implements Serializable {
    private String company;
    private String product;
    private long timeStamp;
    private String orginPlace;
    private String description;
    private String notes;

    public ProductInfo() {
    }

    public ProductInfo(String company, String product, long timeStamp, String orginPlace, String description, String notes) {
        this.company = company;
        this.product = product;
        this.timeStamp = timeStamp;
        this.orginPlace = orginPlace;
        this.description = description;
        this.notes = notes;
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

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
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
}
