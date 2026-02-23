package com.tiendasara.Tienda.Sara.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="Productos")
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(columnDefinition = "TEXT")
private String description;
@Column(name="Precio")
private double price;
@Column(name="Cantidad")
private int amount;
@ManyToOne()
@JoinColumn(name="IdCategoria")
private Category category;
@ManyToOne()
@JoinColumn(name="IdMarca")
private Mark mark;
private Date createdAt;
private String imageFileName;
public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}
public String getDescription() {
return description;
}
public void setDescription(String description) {
this.description = description;
}
public double getPrice() {
return price;
}
public void setPrice(double price) {
this.price = price;
}
public int getAmount() {
return amount;
}
public void setAmount(int amount) {
this.amount = amount;
}
public Category getCategory() {
return category;
}
public void setCategory(Category category) {
this.category = category;
}
public Mark getMark() {
return mark;
}
public void setMark(Mark mark) {
this.mark = mark;
}
public Date getCreatedAt() {
return createdAt;
}
public void setCreatedAt(Date createdAt) {
this.createdAt = createdAt;
}
public String getImageFileName() {
return imageFileName;
}
public void setImageFileName(String imageFileName) {
this.imageFileName = imageFileName;
}
}
