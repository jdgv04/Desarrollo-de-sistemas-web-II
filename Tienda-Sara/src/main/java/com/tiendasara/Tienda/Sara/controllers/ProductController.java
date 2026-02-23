package com.tiendasara.Tienda.Sara.controllers;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.tiendasara.Tienda.Sara.models.Category;
import com.tiendasara.Tienda.Sara.models.Mark;
import com.tiendasara.Tienda.Sara.models.Product;
import com.tiendasara.Tienda.Sara.models.ProductDto;
import com.tiendasara.Tienda.Sara.services.CategoryRepository;
import com.tiendasara.Tienda.Sara.services.MarkRepository;
import com.tiendasara.Tienda.Sara.services.ProductRepository;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/products")
public class ProductController {
@Autowired
private ProductRepository repo;
@Autowired
private CategoryRepository repo2;
@Autowired
private MarkRepository repo3;
@GetMapping({"", "/"})
public String showProductList(Model model) {
List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
model.addAttribute("products", products);
return "products/index";
}
public List<Category> getListCategories(){
List<Category> list = repo2.findAll(Sort.by(Sort.Direction.DESC, "id"));
return list;
}
public List<Mark> getListMarks(){
List<Mark> list = repo3.findAll(Sort.by(Sort.Direction.DESC, "id"));
return list;
}
@GetMapping("/create")
public String showCreatePage(Model model) {
ProductDto productDto = new ProductDto();
model.addAttribute("productDto", productDto);
model.addAttribute("categories", getListCategories());
model.addAttribute("marks", getListMarks());
return "products/CreateProduct";
}
@PostMapping("/create")
public String createProduct(
@Valid @ModelAttribute ProductDto productDto,
BindingResult result,
Model model
) {
if (productDto.getImageFile().isEmpty()) {
result.addError(new FieldError("productDto", "imageFile", "The image file is required"));
}
model.addAttribute("categories", getListCategories());
model.addAttribute("marks", getListMarks());
if (result.hasErrors()) {
return "products/CreateProduct";
}
// save image file
MultipartFile image = productDto.getImageFile();
Date createdAt = new Date();
String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
try {
String uploadDir = "public/images/";
Path uploadPath = Paths.get(uploadDir);
if (!Files.exists(uploadPath)) {
Files.createDirectories(uploadPath);
}
try (InputStream inputStream = image.getInputStream()) {
Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
StandardCopyOption.REPLACE_EXISTING);
}
} catch (Exception ex) {
System.out.println("Exception: " + ex.getMessage());
}
Product product = new Product();
product.setDescription(productDto.getDescription());
product.setPrice(productDto.getPrice());
product.setAmount(productDto.getAmount());
product.setCategory(productDto.getCategory());
product.setMark(productDto.getMark());
product.setCreatedAt(createdAt);
product.setImageFileName(storageFileName);
repo.save(product);
return "redirect:/products";
}
@GetMapping("/edit")
public String showEditPage(
Model model,
@RequestParam int id
) {
try {
Product product = repo.findById(id).get();
model.addAttribute("product", product);
model.addAttribute("categories", getListCategories());
model.addAttribute("marks", getListMarks());
ProductDto productDto = new ProductDto();
productDto.setDescription(product.getDescription());
productDto.setPrice(product.getPrice());
productDto.setAmount(product.getAmount());
productDto.setCategory(product.getCategory());
productDto.setMark(product.getMark());
model.addAttribute("productDto", productDto);
}
catch(Exception ex) {
System.out.println("Exception: " + ex.getMessage());
return "redirect:/products";
}
return "products/EditProduct";
}
@PostMapping("/edit")
public String updateProduct(
Model model,
@RequestParam int id,
@Valid @ModelAttribute ProductDto productDto,
BindingResult result
) {
try {
Product product = repo.findById(id).get();
model.addAttribute("product", product);
if (result.hasErrors()) {
return "products/EditProduct";
}
if (!productDto.getImageFile().isEmpty()) {
// delete old image
String uploadDir = "public/images/";
Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());
try {
Files.delete(oldImagePath);
}
catch(Exception ex) {
System.out.println("Exception: " + ex.getMessage());
}
// save new image file
MultipartFile image = productDto.getImageFile();
Date createdAt = new Date();
String storageFileName = createdAt.getTime() + "_" +
image.getOriginalFilename();
try (InputStream inputStream = image.getInputStream()) {
Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
StandardCopyOption.REPLACE_EXISTING);
}
product.setImageFileName(storageFileName);
}
product.setDescription(productDto.getDescription());
product.setPrice(productDto.getPrice());
product.setAmount(productDto.getAmount());
product.setCategory(productDto.getCategory());
product.setMark(productDto.getMark());
repo.save(product);
}
catch(Exception ex) {
System.out.println("Exception: " + ex.getMessage());
}
return "redirect:/products";
}
@GetMapping("/delete")
public String deleteProduct(
@RequestParam int id
) {
try {
Product product = repo.findById(id).get();
// delete product image
Path imagePath = Paths.get("public/images/" + product.getImageFileName());
try {
Files.delete(imagePath);
}
catch(Exception ex) {
System.out.println("Exception: " + ex.getMessage());
}
// delete the product
repo.delete(product);
}
catch (Exception ex) {
System.out.println("Exception: " + ex.getMessage());
}
return "redirect:/products";
}
}
