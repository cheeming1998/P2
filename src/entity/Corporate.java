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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author waikang
 */
@Entity
@Table(name = "CORPORATE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corporate.findAll", query = "SELECT c FROM Corporate c")
    , @NamedQuery(name = "Corporate.findByCustomerid", query = "SELECT c FROM Corporate c WHERE c.customerid = :customerid")
    , @NamedQuery(name = "Corporate.findByCompanyname", query = "SELECT c FROM Corporate c WHERE c.companyname = :companyname")
    , @NamedQuery(name = "Corporate.findByCompanyaddress", query = "SELECT c FROM Corporate c WHERE c.companyaddress = :companyaddress")
    , @NamedQuery(name = "Corporate.findByMonthlylimit", query = "SELECT c FROM Corporate c WHERE c.monthlylimit = :monthlylimit")})
public class Corporate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CUSTOMERID")
    private String customerid;
    @Basic(optional = false)
    @Column(name = "COMPANYNAME")
    private String companyname;
    @Basic(optional = false)
    @Column(name = "COMPANYADDRESS")
    private String companyaddress;
    @Basic(optional = false)
    @Column(name = "MONTHLYLIMIT")
    private double monthlylimit;
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Customer customer;

    public Corporate() {
    }

    public Corporate(String customerid) {
        this.customerid = customerid;
    }

    public Corporate(String customerid,Customer customer, String companyname, String companyaddress, double monthlylimit) {
        this.customerid = customerid;
        this.companyname = companyname;
        this.companyaddress = companyaddress;
        this.monthlylimit = monthlylimit;
        this.customer=customer;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    public double getMonthlylimit() {
        return monthlylimit;
    }

    public void setMonthlylimit(double monthlylimit) {
        this.monthlylimit = monthlylimit;
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
        if (!(object instanceof Corporate)) {
            return false;
        }
        Corporate other = (Corporate) object;
        if ((this.customerid == null && other.customerid != null) || (this.customerid != null && !this.customerid.equals(other.customerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Corporate[ customerid=" + customerid + " ]";
    }
    
}
