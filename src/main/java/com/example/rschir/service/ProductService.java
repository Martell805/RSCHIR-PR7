package com.example.rschir.service;

import com.example.rschir.entity.Product;
import com.example.rschir.repository.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public Product create(Product product) {
        return productRepo.save(product);
    }

    public Product read(int id) {
        return productRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("No such product"));
    }

    public Product update(int id, Product product) {
        Product prFromRepo = productRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("No such product"));

        prFromRepo.setName(product.getName());
        prFromRepo.setPrice(product.getPrice());
        prFromRepo.setQty(product.getQty());

        return productRepo.save(prFromRepo);
    }

    public void delete(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> read() {
        return productRepo.findAll();
    }
}
