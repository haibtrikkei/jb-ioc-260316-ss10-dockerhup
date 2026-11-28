package ra.edmo.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edmo.model.dto.response.ApiResponse;
import ra.edmo.model.entity.Product;
import ra.edmo.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/init-data")
    public ResponseEntity<ApiResponse<List<Product>>> initDataProducts() {
        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Khởi tạo dữ liệu ban đầu thành công",
                productService.initDataProducts(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Product>>> getAllProducts(@RequestParam(name = "searchBy", defaultValue = "") String searchBy,
                                                                     @RequestParam(name = "searchValue", defaultValue = "") String searchValue,
                                                                     @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                                     @RequestParam(name = "sortBy", defaultValue = "proId") String sortBy,
                                                                     @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy) {
        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Lấy danh sách sản phẩm thành công",
                productService.getAllByName(searchBy, searchValue, page - 1, pageSize, sortBy, orderBy),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}
