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
@Table(name = "PRODUCTTYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producttype.findAll", query = "SELECT p FROM Producttype p")
    , @NamedQuery(name = "Producttype.findByProducttypeid", query = "SELECT p FROM Producttype p WHERE p.producttypeid = :producttypeid")
    , @NamedQuery(name = "Producttype.findByProducttypename", query = "SELECT p FROM Producttype p WHERE p.producttypename = :producttypename")})
public class Producttype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PRODUCTTYPEID")
    private String producttypeid;
    @Column(name = "PRODUCTTYPENAME")
    private String producttypename;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producttypeid")
    private List<Product> productList;

    public Producttype() {
    }

    public Producttype(String producttypeid) {
        this.producttypeid = producttypeid;
    }
    public Producttype(String producttypeid,String producttypename) {
        this.producttypeid = producttypeid;
        this.producttypename=producttypename;
    }

    public String getProducttypeid() {
        return producttypeid;
    }

    public void setProducttypeid(String producttypeid) {
        this.producttypeid = producttypeid;
    }

    public String getProducttypename() {
        return producttypename;
    }

    public void setProducttypename(String producttypename) {
        this.producttypename = producttypename;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (producttypeid != null ? producttypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producttype)) {
            return false;
        }
        Producttype other = (Producttype) object;
        if ((this.producttypeid == null && other.producttypeid != null) || (this.producttypeid != null && !this.producttypeid.equals(other.producttypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Producttype[ producttypeid=" + producttypeid + " ]";
    }
    
}
