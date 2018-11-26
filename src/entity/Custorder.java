/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author waikang
 */
@Entity
@Table(name = "CUSTORDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Custorder.findAll", query = "SELECT c FROM Custorder c")
    , @NamedQuery(name = "Custorder.findByCustorderid", query = "SELECT c FROM Custorder c WHERE c.custorderid = :custorderid")
    , @NamedQuery(name = "Custorder.findByOrderdate", query = "SELECT c FROM Custorder c WHERE c.orderdate = :orderdate")
    , @NamedQuery(name = "Custorder.findByDatedelivery", query = "SELECT c FROM Custorder c WHERE c.datedelivery = :datedelivery")
    , @NamedQuery(name = "Custorder.findByStatus", query = "SELECT c FROM Custorder c WHERE c.status = :status")
    , @NamedQuery(name = "Custorder.findByTotal", query = "SELECT c FROM Custorder c WHERE c.total = :total")
    , @NamedQuery(name = "Custorder.findByDeliveryaddress", query = "SELECT c FROM Custorder c WHERE c.deliveryaddress = :deliveryaddress")
    , @NamedQuery(name = "Custorder.findByDistance", query = "SELECT c FROM Custorder c WHERE c.distance = :distance")})
public class Custorder implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custorderid")
    private List<Payment> paymentList;

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    @Column(name = "PICKUPTIME")
    @Temporal(TemporalType.DATE)
    private Date pickuptime;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CUSTORDERID")
    private String custorderid;
    @Basic(optional = false)
    @Column(name = "ORDERDATE")
    @Temporal(TemporalType.DATE)
    private Date orderdate;
    @Column(name = "DATEDELIVERY")
    @Temporal(TemporalType.DATE)
    private Date datedelivery;
    @Column(name = "STATUS")
    private String status;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL")
    private Double total;
    @Column(name = "DELIVERYADDRESS")
    private String deliveryaddress;
    @Column(name = "DISTANCE")
    private Double distance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custorderid")
    private List<Orderproduct> orderproductList;
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID")
    @ManyToOne(optional = false)
    private Customer customerid;

    public Custorder() {
    }

    public Custorder(String custorderid) {
        this.custorderid = custorderid;
    }

    public Custorder(String custorderid, Date orderdate) {
        this.custorderid = custorderid;
        this.orderdate = orderdate;
    }
    public Custorder(String custoderid, Date orderdate,Date datedelivery,Customer customer,String status,Double total,String deliveryaddress,Double distance,Date pickuptime){
        this.custorderid=custoderid;
                this.orderdate=orderdate;
                this.datedelivery=datedelivery;
                this.customerid=customer;
                this.status=status;
                this.total=total;
                this.deliveryaddress=deliveryaddress;
                this.distance=distance;
                this.pickuptime=pickuptime;
               
    }

    public String getCustorderid() {
        return custorderid;
    }

    public void setCustorderid(String custorderid) {
        String oldCustorderid = this.custorderid;
        this.custorderid = custorderid;
        changeSupport.firePropertyChange("custorderid", oldCustorderid, custorderid);
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        Date oldOrderdate = this.orderdate;
        this.orderdate = orderdate;
        changeSupport.firePropertyChange("orderdate", oldOrderdate, orderdate);
    }

    public Date getDatedelivery() {
        return datedelivery;
    }

    public void setDatedelivery(Date datedelivery) {
        Date oldDatedelivery = this.datedelivery;
        this.datedelivery = datedelivery;
        changeSupport.firePropertyChange("datedelivery", oldDatedelivery, datedelivery);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        String oldStatus = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", oldStatus, status);
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        Double oldTotal = this.total;
        this.total = total;
        changeSupport.firePropertyChange("total", oldTotal, total);
    }

    public String getDeliveryaddress() {
        return deliveryaddress;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        String oldDeliveryaddress = this.deliveryaddress;
        this.deliveryaddress = deliveryaddress;
        changeSupport.firePropertyChange("deliveryaddress", oldDeliveryaddress, deliveryaddress);
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        Double oldDistance = this.distance;
        this.distance = distance;
        changeSupport.firePropertyChange("distance", oldDistance, distance);
    }

    @XmlTransient
    public List<Orderproduct> getOrderproductList() {
        return orderproductList;
    }

    public void setOrderproductList(List<Orderproduct> orderproductList) {
        this.orderproductList = orderproductList;
    }

    public Customer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Customer customerid) {
        Customer oldCustomerid = this.customerid;
        this.customerid = customerid;
        changeSupport.firePropertyChange("customerid", oldCustomerid, customerid);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custorderid != null ? custorderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Custorder)) {
            return false;
        }
        Custorder other = (Custorder) object;
        if ((this.custorderid == null && other.custorderid != null) || (this.custorderid != null && !this.custorderid.equals(other.custorderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Custorder[ custorderid=" + custorderid + " ]";
    }

    public Date getPickuptime() {
        return pickuptime;
    }

    public void setPickuptime(Date pickuptime) {
        Date oldPickuptime = this.pickuptime;
        this.pickuptime = pickuptime;
        changeSupport.firePropertyChange("pickuptime", oldPickuptime, pickuptime);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    @XmlTransient
    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }
    
}
