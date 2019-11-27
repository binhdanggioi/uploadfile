package com.websitetintuc.dao;

import java.util.List;

import com.websitetintuc.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel> {
	List<CategoryModel> findAll();
	CategoryModel findOne(long id);
	CategoryModel findOneByCode(String code);
	CategoryModel findByCategoryId(long id);
}
