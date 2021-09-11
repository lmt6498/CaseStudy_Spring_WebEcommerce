package com.example.casestudy_spring_webbanquanao.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.casestudy_spring_webbanquanao.models.Product;
import com.example.casestudy_spring_webbanquanao.repository.ProductRepository;
import com.example.casestudy_spring_webbanquanao.repository.ProductSpecification;
import com.example.casestudy_spring_webbanquanao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Value("${productservice.featured-items-number}")
	private int featuredProductsNumber;

	@Override
	public List<Product> findAllProducts() {
		return (List<Product>) productRepository.findAllEagerBy();
	}
	
	@Override
	public Page<Product> findProductsByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh,
										List<String> sizes, List<String> categories, List<String> brands, String search) {		
		Page<Product> page = productRepository.findAll(ProductSpecification.filterBy(priceLow, priceHigh, sizes, categories, brands, search), pageable);
        return page;		
	}	
	
	@Override
	public List<Product> findFirstProducts() {
		return productRepository.findAll(PageRequest.of(0,featuredProductsNumber)).getContent();
	}

	@Override
	public Product findProductById(Long id) {
		Optional<Product> opt = productRepository.findById(id);
		return opt.get();
	}

	@Override
	@CacheEvict(value = { "sizes", "categories", "brands" }, allEntries = true)
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	@CacheEvict(value = { "sizes", "categories", "brands" }, allEntries = true)
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<String> getBrandByID(Long id) {
		return productRepository.findBrandsByID(id);
	}

	@Override
	public List<String> getCategoryByID(Long id) {
		return productRepository.findCategoryByID(id);
	}


	@Override
	@Cacheable("sizes")
	public List<String> getAllSizes() {
		return productRepository.findAllSizes();
	}

	@Override
	@Cacheable("categories")
	public List<String> getAllCategories() {
		return productRepository.findAllCategories();
	}

	@Override
	@Cacheable("brands")
	public List<String> getAllBrands() {
		return productRepository.findAllBrands();
	}
}
