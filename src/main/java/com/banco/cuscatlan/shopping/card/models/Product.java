package com.banco.cuscatlan.shopping.card.models;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Integer idproductscar;
    
    @Basic(optional = false)
    @Column
    private String title;
    
    @Basic(optional = false)
    @Column
    private String category;
    
    @Basic(optional = false)
    @Column
    private int idapi;
    
    @Basic(optional = false)
    @Column
    private Double price;
    
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 800)
    private String description;
    
    @Basic(optional = false)
    @Column
    private String image;
    
    @JoinColumn(name = "iduser", referencedColumnName = "iduser", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User iduser;

    
    public Product() {
    }

    public Product(Integer idproductscar) {
        this.idproductscar = idproductscar;
    }

    public Product(Integer idproductscar, String title, String category, int idapi, Double price, String description, String image) {
        this.idproductscar = idproductscar;
        this.title = title;
        this.category = category;
        this.idapi = idapi;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Integer getIdproductscar() {
        return idproductscar;
    }

    public void setIdproductscar(Integer idproductscar) {
        this.idproductscar = idproductscar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIdapi() {
        return idapi;
    }

    public void setIdapi(int idapi) {
        this.idapi = idapi;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }    
}
