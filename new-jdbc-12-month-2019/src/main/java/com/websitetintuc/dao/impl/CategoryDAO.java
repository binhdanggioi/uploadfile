package com.websitetintuc.dao.impl;

import java.util.List;

import com.websitetintuc.dao.ICategoryDAO;
import com.websitetintuc.mapper.CategoryMapper;
import com.websitetintuc.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

    @Override
    public List<CategoryModel> findAll() {
        String sql = "SELECT * FROM category";
        return query(sql, new CategoryMapper());
    }

    @Override
    public CategoryModel findOne(long id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        List<CategoryModel> categoryModels = query(sql, new CategoryMapper(), id);
        return categoryModels.isEmpty() ? null : categoryModels.get(0);
    }

    @Override
    public CategoryModel findOneByCode(String code) {
        String sql = "SELECT * FROM category WHERE code = ?";
        List<CategoryModel> categoryModels = query(sql, new CategoryMapper(), code);
        return categoryModels.isEmpty() ? null : categoryModels.get(0);
    }

    @Override
    public CategoryModel findByCategoryId(long id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        List<CategoryModel> categoryModels = findCategoryId(sql, id);
        return null;
    }
}
