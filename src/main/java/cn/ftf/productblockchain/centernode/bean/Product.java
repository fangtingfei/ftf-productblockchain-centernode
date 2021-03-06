package cn.ftf.productblockchain.centernode.bean;

import java.io.Serializable;

/**
 * 商品基本信息封装类
 *
 * @Author 房廷飞
 * @Create 2020-12-12 10:38
 */


public class Product implements Serializable {
    private String company;
    private String product;
    private String productionDate;
    private String orginPlace;
    private String description;

    public Product(String company, String product, String productionDate, String orginPlace, String description) {
        this.company = company;
        this.product = product;
        this.productionDate = productionDate;
        this.orginPlace = orginPlace;
        this.description = description;
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

    @Override
    public String toString() {
        return "Product{" +
                "company='" + company + '\'' +
                ", product='" + product + '\'' +
                ", productionDate=" + productionDate +
                ", orginPlace='" + orginPlace + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
