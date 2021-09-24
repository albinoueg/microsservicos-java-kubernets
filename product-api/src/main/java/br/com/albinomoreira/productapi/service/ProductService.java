package br.com.albinomoreira.productapi.service;

import br.com.albinomoreira.productapi.converter.DTOConverter;
import br.com.albinomoreira.productapi.model.Product;
import br.com.albinomoreira.productapi.repository.CategoryRepository;
import br.com.albinomoreira.productapi.repository.ProductRepository;
import br.com.albinomoreira.shoppingclient.dto.ProductDTO;
import br.com.albinomoreira.shoppingclient.exception.CategoryNotFoundException;
import br.com.albinomoreira.shoppingclient.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(DTOConverter::convert).collect(Collectors.toUnmodifiableList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId){
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return products.stream().map(DTOConverter::convert).collect(Collectors.toUnmodifiableList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier){
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if(product != null){
            return DTOConverter.convert(product);
        }
        throw new ProductNotFoundException();
    }

    public ProductDTO save(ProductDTO productDTO){
        Boolean existsCategory = categoryRepository.existsById(productDTO.getCategory().getId());
        if(!existsCategory){
            throw new CategoryNotFoundException();
        }

        Product product = productRepository.save(Product.convert(productDTO));
        return DTOConverter.convert(product);
    }

    public ProductDTO delete(long ProductId) throws ProductNotFoundException {
        Optional<Product> Product = productRepository.findById(ProductId);
        if (Product.isPresent()) {
            productRepository.delete(Product.get());
        }
        throw new ProductNotFoundException();
    }
}
