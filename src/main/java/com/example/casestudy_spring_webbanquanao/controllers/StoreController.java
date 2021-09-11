package com.example.casestudy_spring_webbanquanao.controllers;

import javax.websocket.server.PathParam;

import com.example.casestudy_spring_webbanquanao.filter.ProductFilter;
import com.example.casestudy_spring_webbanquanao.filter.SortFilter;
import com.example.casestudy_spring_webbanquanao.models.Product;
import com.example.casestudy_spring_webbanquanao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class StoreController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/store")
	public String store(@ModelAttribute("filters") ProductFilter filters, Model model) {
		Integer page = filters.getPage();			
		int pagenumber = (page == null ||  page <= 0) ? 0 : page-1;
		SortFilter sortFilter = new SortFilter(filters.getSort());
		Page<Product> pageresult = productService.findProductsByCriteria(PageRequest.of(pagenumber,9, sortFilter.getSortType()),
																filters.getPricelow(), filters.getPricehigh(), 
																filters.getSize(), filters.getCategory(), filters.getBrand(), filters.getSearch());	
		model.addAttribute("allCategories", productService.getAllCategories());
		model.addAttribute("allBrands", productService.getAllBrands());
		model.addAttribute("allSizes", productService.getAllSizes());
		model.addAttribute("products", pageresult.getContent());
		model.addAttribute("totalitems", pageresult.getTotalElements());
		model.addAttribute("itemsperpage", 9);
		return "store";
	}
	
	
	@RequestMapping("/product-detail")
	public String productDetail(@PathParam("id") Long id, Model model) {
		Product product = productService.findProductById(id);
		model.addAttribute("product", product);
		model.addAttribute("brand", productService.getBrandByID(id));
		model.addAttribute("cate", productService.getCategoryByID(id));
		model.addAttribute("notEnoughStock", model.asMap().get("notEnoughStock"));
		model.addAttribute("addProductSuccess", model.asMap().get("addProductSuccess"));
		return "productDetail";
	}
	

}
