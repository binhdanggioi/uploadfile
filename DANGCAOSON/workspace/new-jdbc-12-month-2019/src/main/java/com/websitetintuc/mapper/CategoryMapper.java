package com.websitetintuc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.websitetintuc.model.CategoryModel;

public class CategoryMapper implements IRowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet resultset) {
		try {
			CategoryModel category = new CategoryModel();
			category.setId(resultset.getLong("id"));
			category.setCode(resultset.getString("code"));
			category.setName(resultset.getString("name"));
			return category;
		} catch (SQLException e) {
			return null;
		}
	}

}
