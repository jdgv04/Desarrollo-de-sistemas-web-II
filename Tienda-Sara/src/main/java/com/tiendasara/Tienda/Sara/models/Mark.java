package com.tiendasara.Tienda.Sara.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="Marcas")
public class Mark {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(columnDefinition = "TEXT")
private String description;
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
}
