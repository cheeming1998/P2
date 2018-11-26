/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author waikang
 */
@Entity
@Table(name = "ORDERPRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderproduct.findAll", query = "SELECT o FROM Orderproduct o")
    , @NamedQuery(name = "Orderproduct.findByOrderproductid", query = "SELECT o FROM Orderproduct o WHERE o.orderproductid = :orderproductid")
    , @NamedQuery(name = "Orderproduct.findByQuantity", query = "SELECT o FROM Orderproduct o WHERE o.quantity = :quantity")})
public class Orderproduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ORDERPRODUCTID")
    private String orderproductid;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @JoinColumn(name = "CUSTORDERID", referencedColumnName = "CUSTORDERID")
    @ManyToOne(optional = false)
    private Custorder custorderid;
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    @ManyToOne(optional = false)
    private Product productid;

    public Orderproduct() {
    }

    public Orderproduct(String orderproductid) {
        this.orderproductid = orderproductid;
    }
   public Orderproduct(String orderproductid,Integer quantity,Custorder custorderid,Product productid){
       this.orderproductid=orderproductid;
       this.quantity=quantity;
       this.custorderid=custorderid;
       this.productid=productid;
   }
    public String getOrderproductid() {
        return orderproductid;
    }

    public void setOrderproductid(String orderproductid) {
        this.orderproductid = orderproductid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Custorder getCustorderid() {
        return custorderid;
    }

    public void setCustorderid(Custorder custorderid) {
        this.custorderid = custorderid;
    }

    public Product getProductid() {
        return productid;
    }

    public void setProductid(Product productid) {
        this.productid = productid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderproductid != null ? orderproductid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderproduct)) {
            return false;
        }
        Orderproduct other = (Orderproduct) object;
        if ((this.orderproductid == null && other.orderproductid != null) || (this.orderproductid != null && !this.orderproductid.equals(other.orderproductid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Orderproduct[ orderproductid=" + orderproductid + " ]";
    }
    
}
