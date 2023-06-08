package com.banco.cuscatlan.shopping.card.models;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table; 

 
@Entity
@Table(name = "orders")
public class Orders implements Serializable {


	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Integer idorders;
    
    @JoinColumn(name = "idstatus", referencedColumnName = "idstatus")
    @ManyToOne(optional = false)
    private OrderStatus idstatus;
    
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User userIduser;

    public Orders(Integer idorders, OrderStatus idstatus, User userIduser) {
		super();
		this.idorders = idorders;
		this.idstatus = idstatus;
		this.userIduser = userIduser;
	}
    public Orders() {
    }

    public Orders(Integer idorders) {
        this.idorders = idorders;
    }

    public Integer getIdorders() {
        return idorders;
    }

    public void setIdorders(Integer idorders) {
        this.idorders = idorders;
    }

    public OrderStatus getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(OrderStatus idstatus) {
        this.idstatus = idstatus;
    }

    public User getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(User userIduser) {
        this.userIduser = userIduser;
    }
    
}
