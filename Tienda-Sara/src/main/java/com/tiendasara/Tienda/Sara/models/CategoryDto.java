package com.tiendasara.Tienda.Sara.models;

import jakarta.validation.constraints.*;
public class CategoryDto {
@Size(min = 10, message = "The description should be at least 10 characters")
@Size(max = 2000, message = "The description cannot 2000 characters")
private String description;
public String getDescription() {
return description;
}
public void setDescription(String description) {
this.description = description;
}
}