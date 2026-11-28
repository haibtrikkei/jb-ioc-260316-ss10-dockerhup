package ra.edmo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.w3c.dom.stylesheets.LinkStyle;
import ra.edmo.model.entity.Product;

import java.util.List;

public interface ProductService {
    Page<Product> getAllByName(String searchBy, String searchValue, Integer page, Integer pageSize, String sortBy, String orderBy);
    List<Product> initDataProducts();
}
