package ra.edmo.init_data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ra.edmo.model.entity.Product;
import ra.edmo.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDataProducts implements CommandLineRunner {
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if(productRepository.findAll().isEmpty()) {
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
            productRepository.saveAll(products);
        }
    }
}
