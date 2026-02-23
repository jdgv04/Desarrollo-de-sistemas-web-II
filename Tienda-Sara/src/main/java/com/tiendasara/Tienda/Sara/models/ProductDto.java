package com.tiendasara.Tienda.Sara.models;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.*;
public class ProductDto {
@Size(min = 10, message = "The description should be at least 10 characters")
@Size(max = 2000, message = "The description cannot 2000 characters")
private String description;
@Min(0)
private double price;
@Min(0)
private int amount;
private Category category;
private Mark mark;
private MultipartFile imageFile;
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
public MultipartFile getImageFile() {
return imageFile;
}
public void setImageFile(MultipartFile imageFile) {
this.imageFile = imageFile;
}
}
