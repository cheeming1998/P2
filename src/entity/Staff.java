/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author waikang
 */
@Entity
@Table(name = "STAFF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s")
    , @NamedQuery(name = "Staff.findByStaffid", query = "SELECT s FROM Staff s WHERE s.staffid = :staffid")
    , @NamedQuery(name = "Staff.findByUsername", query = "SELECT s FROM Staff s WHERE s.username = :username")
    , @NamedQuery(name = "Staff.findByPassword", query = "SELECT s FROM Staff s WHERE s.password = :password")
    , @NamedQuery(name = "Staff.findByAddress", query = "SELECT s FROM Staff s WHERE s.address = :address")
    , @NamedQuery(name = "Staff.findByContactno", query = "SELECT s FROM Staff s WHERE s.contactno = :contactno")
    , @NamedQuery(name = "Staff.findByDateofbirth", query = "SELECT s FROM Staff s WHERE s.dateofbirth = :dateofbirth")
    , @NamedQuery(name = "Staff.findByFullname", query = "SELECT s FROM Staff s WHERE s.fullname = :fullname")
    , @NamedQuery(name = "Staff.findByIcno", query = "SELECT s FROM Staff s WHERE s.icno = :icno")
    , @NamedQuery(name = "Staff.findByEmail", query = "SELECT s FROM Staff s WHERE s.email = :email")
    , @NamedQuery(name = "Staff.findByGender", query = "SELECT s FROM Staff s WHERE s.gender = :gender")
    , @NamedQuery(name = "Staff.findByDatejoined", query = "SELECT s FROM Staff s WHERE s.datejoined = :datejoined")
    , @NamedQuery(name = "Staff.findByStatus", query = "SELECT s FROM Staff s WHERE s.status = :status")
    , @NamedQuery(name = "Staff.findByRole", query = "SELECT s FROM Staff s WHERE s.role = :role")})
public class Staff implements Serializable {
@PersistenceContext
   EntityManager em;
static Staff staff = new Staff();
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "STAFFID")
    private String staffid;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "CONTACTNO")
    private String contactno;
    @Column(name = "DATEOFBIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    @Column(name = "FULLNAME")
    private String fullname;
    @Column(name = "ICNO")
    private String icno;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "GENDER")
    private Character gender;
    @Column(name = "DATEJOINED")
    @Temporal(TemporalType.DATE)
    private Date datejoined;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "ROLE")
    private String role;

    public Staff() {
    }
    public Staff(String staffid,String username,String password,String address,String contactno,Date dateofbirth,String fullname,String icno,String email,Character gender,Date datejoined,String status,String role)
    {
       this.staffid = staffid; 
       this.username = username;
       this.password = password;
       this.address = address;
       this.contactno = contactno;
       this.dateofbirth=dateofbirth;
       this.fullname=fullname;
       this.icno = icno;
       this.email = email;
       this.gender = gender;
       this.datejoined=datejoined;
       this.status=status;
       this.role=role;
       
    }
    public Staff(String staffid) {
        this.staffid = staffid;
    }
    public List<Staff> findAll(){
         Query query=em.createNamedQuery("Staff.findAll");
    List<Staff> staffList = query.getResultList();
    return staffList;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIcno() {
        return icno;
    }

    public void setIcno(String icno) {
        this.icno = icno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Date getDatejoined() {
        return datejoined;
    }

    public void setDatejoined(Date datejoined) {
        this.datejoined = datejoined;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffid != null ? staffid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.staffid == null && other.staffid != null) || (this.staffid != null && !this.staffid.equals(other.staffid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Staff[ staffid=" + staffid + " ]";
    }
   
}
