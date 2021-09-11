package com.example.casestudy_spring_webbanquanao.repository;


import java.util.List;
import java.util.Optional;

import com.example.casestudy_spring_webbanquanao.models.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;



public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	
	@EntityGraph(attributePaths = { "sizes", "categories", "brands" })
	List<Product> findAllEagerBy();
		
	@EntityGraph(attributePaths = { "sizes", "categories", "brands" })
	Optional<Product> findById(Long id);

	@Query("select DISTINCT b.name from Brand b join b.product p where p.id = ?1")
	List<String> findBrandsByID(Long id);

	@Query("select DISTINCT c.name from Category c join c.product p where p.id = ?1")
	List<String> findCategoryByID(Long id);

	@Query("SELECT DISTINCT s.value FROM Size s")
	List<String> findAllSizes();
	
	@Query("SELECT DISTINCT c.name FROM Category c")
	List<String> findAllCategories();
	
	@Query("SELECT DISTINCT b.name FROM Brand b")
	List<String> findAllBrands();
	
}
