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
    private String productionDate;
    private String orginPlace;
    private String description;
    private String notes;

    public ProductInfo(String company, String product, String productionDate, String orginPlace, String description, String notes) {
        this.company = company;
        this.product = product;
        this.productionDate = productionDate;
        this.orginPlace = orginPlace;
        this.description = description;
        this.notes = notes;
    }
    public ProductInfo(){

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfo productInfo1 = (ProductInfo) o;
        return Objects.equals(company, productInfo1.company) &&
                Objects.equals(product, productInfo1.product) &&
                Objects.equals(productionDate, productInfo1.productionDate) &&
                Objects.equals(orginPlace, productInfo1.orginPlace) &&
                Objects.equals(description, productInfo1.description) &&
                Objects.equals(notes, productInfo1.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, product, productionDate, orginPlace, description, notes);
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "company='" + company + '\'' +
                ", product='" + product + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", orginPlace='" + orginPlace + '\'' +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
