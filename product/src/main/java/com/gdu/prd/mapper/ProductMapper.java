package com.gdu.prd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.prd.domain.ProductDTO;

@Mapper
public interface ProductMapper {
	public List<ProductDTO> getProductList(); // id(product.xml)하고, 메소드하고 맞추기.
	public int getProductCount();
	public int addProduct(ProductDTO productDTO);
	
}
