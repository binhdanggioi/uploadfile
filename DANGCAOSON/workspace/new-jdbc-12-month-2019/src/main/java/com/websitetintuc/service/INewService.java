package com.websitetintuc.service;

import java.util.List;

import com.websitetintuc.model.NewModel;
import com.websitetintuc.paging.Pageable;

public interface INewService {
	List<NewModel> findAll();
	List<NewModel> findByCategoryId(Long categoryId);
	NewModel save(NewModel newModel);
	NewModel update(NewModel updatenew);
	void delete(long[] ids);
	List<NewModel> findAll(Pageable pageable);
	int getTotalItem();
	NewModel findOne(long id);
}
