package ra.edmo.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.edmo.model.entity.Product;
import ra.edmo.repository.ProductRepository;
import ra.edmo.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getAllByName(String searchBy, String searchValue, Integer page, Integer pageSize, String sortBy, String orderBy) {
        Sort sort;
        Pageable pageable;

        if (sortBy != null && !sortBy.isEmpty()) {
            sort = Sort.by(sortBy);
            switch (orderBy.toLowerCase()) {
                case "asc":
                    sort = sort.ascending();
                    break;
                case "desc":
                    sort = sort.descending();
                    break;
                default:
                    sort = Sort.unsorted();
            }
            pageable = PageRequest.of(page, pageSize, sort);
        } else {
            pageable = PageRequest.of(page, pageSize);
        }

        if (searchBy != null && !searchBy.isEmpty()) {
            switch (searchBy) {
                case "proName":
                    return productRepository.findAllByProNameContaining(searchValue, pageable);
                case "producer":
                    return productRepository.findAllByProducerContaining(searchValue, pageable);
                case "yearMaking":
                    Integer yearMaking;
                    if(searchValue.isEmpty()){
                        yearMaking = 0;
                    }else{
                        yearMaking = Integer.valueOf(searchValue);
                    }
                    return productRepository.findAllByYearMakingGreaterThanEqual(yearMaking, pageable);
                case "price":
                    Double price = 0D;
                    if(!searchValue.isEmpty()){
                        price = Double.valueOf(searchValue);
                    }
                    return productRepository.findAllByPriceGreaterThanEqual(price, pageable);
            }

        }
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> initDataProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Product product = new Product();
            product.setProName("Product " + i);
            product.setProducer("Producer " + i);
            product.setYearMaking(2000 + (i % 20));
            product.setPrice(10000.0 + i);
            product.setEnabled(true);
            products.add(product);
        }
        return productRepository.saveAllAndFlush(products);
    }
}
