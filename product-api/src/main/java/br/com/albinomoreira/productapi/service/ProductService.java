package br.com.albinomoreira.productapi.service;

import br.com.albinomoreira.productapi.dto.ProductDTO;
import br.com.albinomoreira.productapi.exception.ProductNotFoundException;
import br.com.albinomoreira.productapi.model.Product;
import br.com.albinomoreira.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductDTO::convert).collect(Collectors.toUnmodifiableList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId){
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return products.stream().map(ProductDTO::convert).collect(Collectors.toUnmodifiableList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier){
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if(product != null){
            return ProductDTO.convert(product);
        }
        return null;
    }

    public ProductDTO save(ProductDTO productDTO){
        Product product = productRepository.save(Product.convert(productDTO));
        return ProductDTO.convert(product);
    }

    public ProductDTO delete(long ProductId) throws ProductNotFoundException {
        Optional<Product> Product = productRepository.findById(ProductId);
        if (Product.isPresent()) {
            productRepository.delete(Product.get());
        }
        throw new ProductNotFoundException();
    }
}
