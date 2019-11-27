package com.websitetintuc.dao;

import java.util.List;

import com.websitetintuc.model.NewModel;
import com.websitetintuc.paging.Pageable;

public interface INewDAO extends GenericDAO<NewModel> {
	NewModel findOne(Long id);
	List<NewModel> findByCategoryId(Long categoryId);
	List<NewModel> findAll(String sql, Pageable pageable);
	Long save(NewModel newModel);
	void update(NewModel updatenew);
	void delete(long id);
	List<NewModel> findAll(Pageable pageable);
	int getTotalItem();
}
