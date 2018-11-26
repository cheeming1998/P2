/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author waikang
 */
@Entity
@Table(name = "PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findByProductid", query = "SELECT p FROM Product p WHERE p.productid = :productid")
    , @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description")
    , @NamedQuery(name = "Product.findByQuantity", query = "SELECT p FROM Product p WHERE p.quantity = :quantity")
    , @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PRODUCTID")
    private String productid;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "QUANTITY")
    private int quantity;
    @Basic(optional = false)
    @Column(name = "PRICE")
    private double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productid")
    private List<Orderproduct> orderproductList;
    @JoinColumn(name = "PRODUCTTYPEID", referencedColumnName = "PRODUCTTYPEID")
    @ManyToOne(optional = false)
    private Producttype producttypeid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productid")
    private List<Promotion> promotionList;

    public Product() {
    }

    public Product(String productid) {
        this.productid = productid;
    }

    public Product(String productid, String description,Producttype producttypeid, int quantity, double price) {
        this.productid = productid;
        this.description = description;
        this.quantity = quantity;
        this.producttypeid=producttypeid;
        this.price = price;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @XmlTransient
    public List<Orderproduct> getOrderproductList() {
        return orderproductList;
    }

    public void setOrderproductList(List<Orderproduct> orderproductList) {
        this.orderproductList = orderproductList;
    }

    public Producttype getProducttypeid() {
        return producttypeid;
    }

    public void setProducttypeid(Producttype producttypeid) {
        this.producttypeid = producttypeid;
    }

    @XmlTransient
    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productid != null ? productid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productid == null && other.productid != null) || (this.productid != null && !this.productid.equals(other.productid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Product[ productid=" + productid + " ]";
    }
    
}
