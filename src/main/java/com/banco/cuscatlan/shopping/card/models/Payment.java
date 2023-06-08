package com.banco.cuscatlan.shopping.card.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType; 

 
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpayment", nullable = false)
    private Integer idpayment;
    
    @Basic(optional = false)
    @Column
    private Double totalsum;
    
    @Basic(optional = false)
    @Column
    private String description;
    
    @Basic(optional = false)
    @Column
    @Temporal(TemporalType.DATE)
    private Date created;  
    
    @Column
    private Integer oderid;
    
    @Column
    private Integer user;
    
   
	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Payment() {
		super();
	}

	public Integer getIdPayment() {
		return idpayment;
	}

	public void setIdPayment(Integer idPayment) {
		this.idpayment = idPayment;
	}

	public Payment(Integer idPayment, Double total, String description, Date created, Integer oderId) {
		super();
		this.idpayment = idPayment;
		this.totalsum = total;
		this.description = description;
		this.created = created;
		this.oderid = oderId;
	}

	public Double getTotal() {
		return totalsum;
	}

	public void setTotal(Double total) {
		this.totalsum = total;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getOderid() {
		return oderid;
	}

	public void setOderid(Integer oderId) {
		this.oderid = oderId;
	}
    
    
}
