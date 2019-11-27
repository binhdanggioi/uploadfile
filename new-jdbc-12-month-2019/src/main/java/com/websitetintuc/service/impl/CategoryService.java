package com.websitetintuc.service.impl;


import java.util.List;

import javax.inject.Inject;

import com.websitetintuc.dao.ICategoryDAO;
import com.websitetintuc.dao.impl.CategoryDAO;
import com.websitetintuc.model.CategoryModel;
import com.websitetintuc.service.ICategoryService;

public class CategoryService implements ICategoryService {
	@Inject
	private ICategoryDAO categoryDao; // cách gọi tầng DAO theo kiểu thủ công C1
	public CategoryService() {
		categoryDao = new CategoryDAO();
	}

	@Override
	public List<CategoryModel> findAll(){
		return categoryDao.findAll();
	}

	@Override
	public List<CategoryModel> findByCategoryId(long id) {

		return null;
	}

}
