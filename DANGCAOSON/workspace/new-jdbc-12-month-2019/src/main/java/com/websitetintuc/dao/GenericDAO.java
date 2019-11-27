package com.websitetintuc.dao;

import java.util.List;

import com.websitetintuc.mapper.IRowMapper;
import com.websitetintuc.model.CategoryModel;

public interface GenericDAO<T> {
	<T> List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters);
	void update(String sql, Object... parameters);
	Long insert(String sql, Object... parameters);
	int count(String sql, Object... parameters);
	List<CategoryModel> findCategoryId(String sql, Object... parameters);
}
