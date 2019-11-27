package com.websitetintuc.service;

import java.util.List;

import com.websitetintuc.model.CategoryModel;

public interface ICategoryService {
	
	List<CategoryModel> findAll();
	List<CategoryModel> findByCategoryId(long id);

}
