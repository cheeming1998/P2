/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author waikang
 */
@Entity
@Table(name = "CONSUMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumer.findAll", query = "SELECT c FROM Consumer c")
    , @NamedQuery(name = "Consumer.findByCustomerid", query = "SELECT c FROM Consumer c WHERE c.customerid = :customerid")
    , @NamedQuery(name = "Consumer.findByFullname", query = "SELECT c FROM Consumer c WHERE c.fullname = :fullname")
    , @NamedQuery(name = "Consumer.findByDateofbirth", query = "SELECT c FROM Consumer c WHERE c.dateofbirth = :dateofbirth")})
public class Consumer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CUSTOMERID")
    private String customerid;
    @Basic(optional = false)
    @Column(name = "FULLNAME")
    private String fullname;
    @Basic(optional = false)
    @Column(name = "DATEOFBIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Customer customer;

    public Consumer() {
    }

    public Consumer(String customerid) {
        this.customerid = customerid;
    }

    public Consumer(String customerid,Customer customer, String fullname, Date dateofbirth) {
        this.customerid = customerid;
        this.fullname = fullname;
        this.dateofbirth = dateofbirth;
        this.customer = customer;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerid != null ? customerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumer)) {
            return false;
        }
        Consumer other = (Consumer) object;
        if ((this.customerid == null && other.customerid != null) || (this.customerid != null && !this.customerid.equals(other.customerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Consumer[ customerid=" + customerid + " ]";
    }
    
}
