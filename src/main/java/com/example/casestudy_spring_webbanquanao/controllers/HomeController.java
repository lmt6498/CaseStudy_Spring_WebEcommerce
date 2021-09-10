package com.example.casestudy_spring_webbanquanao.controllers;

import java.util.List;

import com.example.casestudy_spring_webbanquanao.models.Product;
import com.example.casestudy_spring_webbanquanao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {
		
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping("/")
	public String index(Model model) {		
		List<Product> products = productService.findFirstProducts();
		model.addAttribute("products", products);
		return "index";
	}

	
}
