package com.gdu.prd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.prd.domain.ProductDTO;
import com.gdu.prd.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	

	@Override
	public void loadProductList(Model model) {
		// 목록에 저장
		model.addAttribute("productList", productMapper.getProductList());
		model.addAttribute("productCount", productMapper.getProductCount());// 전체제품의 갯수.
	}

	@Override
	public int addProduct(ProductDTO productDTO) {
		/* 2 */ int addResult = productMapper.addProduct(productDTO); // 아직맵퍼만들지 않았지만.일단 똑같은 매소드만들거라 가정하고. 작성 후 맵퍼로가.
		/* 2 */ return addResult;
	}

	@Override
	public void loadProduct(int prodNo, Model model) {
			ProductDTO productDTO = productMapper.getproductByNo(prodNo); // 디비에서 가지고 오는작업.
			model.addAttribute("productDTO", productDTO);
	}
	
	
}
