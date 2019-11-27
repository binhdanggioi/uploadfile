package com.websitetintuc.service.impl;

import com.websitetintuc.dao.ICategoryDAO;
import com.websitetintuc.dao.INewDAO;
import com.websitetintuc.dao.impl.CategoryDAO;
import com.websitetintuc.dao.impl.NewDAO;
import com.websitetintuc.model.CategoryModel;
import com.websitetintuc.model.NewModel;
import com.websitetintuc.paging.Pageable;
import com.websitetintuc.service.INewService;
import com.websitetintuc.utils.UploadFileUtils;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

public class NewService implements INewService {

    private INewDAO newDAO;
    private ICategoryDAO categoryDAO;

    public NewService() {
        newDAO = new NewDAO();
        categoryDAO = new CategoryDAO();
    }

    @Override
    public List<NewModel> findAll() {
        return null;
    }

    @Override
    public List<NewModel> findByCategoryId(Long categoryId) {
        return newDAO.findByCategoryId(categoryId);
    }

    @Override
    public NewModel save(NewModel newModel) {
        newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        CategoryModel category = categoryDAO.findOneByCode(newModel.getCategoryCode());
        saveThumbnail(newModel);
        newModel.setCategoryId(category.getId());
        Long newId = newDAO.save(newModel);
        return newDAO.findOne(newId);
    }
    private String saveThumbnail(NewModel newModel){
        String path = "/News/"+newModel.getThumbnailImageName();
        byte[] bytes = Base64.decodeBase64(newModel.getThumbnailBase64().getBytes());
        UploadFileUtils.writeOrUpdate(path, bytes);
        return path;
    }
    @Override
    public NewModel update(NewModel updateNew) {
        NewModel oldNew = newDAO.findOne(updateNew.getId());
        updateNew.setCreatedDate(oldNew.getCreatedDate());
        updateNew.setCreatedBy(oldNew.getCreatedBy());
        updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        CategoryModel category = categoryDAO.findOneByCode(updateNew.getCategoryCode());
        updateNew.setCategoryId(category.getId());
        newDAO.update(updateNew);
        return newDAO.findOne(updateNew.getId());
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            newDAO.delete(id);
        }
    }

    @Override
    public List<NewModel> findAll(Pageable pageable) {
        return newDAO.findAll(pageable);
    }

    @Override
    public int getTotalItem() {
        return newDAO.getTotalItem();
    }

    @Override
    public NewModel findOne(long id) {
        NewModel newModel = newDAO.findOne(id);
        CategoryModel categoryModel = categoryDAO.findOne(newModel.getCategoryId());
        newModel.setCategoryCode(categoryModel.getCode());
        return newModel;
    }

}
