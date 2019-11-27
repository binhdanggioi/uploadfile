package com.websitetintuc.dao.impl;

import java.util.List;
import java.lang.StringBuilder;

import com.websitetintuc.dao.INewDAO;
import com.websitetintuc.mapper.NewMapper;
import com.websitetintuc.model.NewModel;
import com.websitetintuc.paging.Pageable;
import org.apache.commons.lang.StringUtils;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

    @Override
    public List<NewModel> findByCategoryId(Long categoryId) {
        String sql = "SELECT * FROM news WHERE categoryid = ?";
        return query(sql, new NewMapper(), categoryId);
    }

    @Override
    public List<NewModel> findAll(String sql, Pageable pageable) {
        StringBuilder sql1 = new StringBuilder("SELECT * FROM news");
        sql1.append(" limit"+pageable.getOffset()+", "+pageable.getLimit()+"");
        return this.findAll(sql, pageable);
    }

    @Override
    public Long save(NewModel newModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO news (title, content, thumbnail,");
        sql.append(" shortdescription, categoryid,createddate, createdby)");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), newModel.getTitle(), newModel.getContent(), newModel.getThumbnailImageName(),
                newModel.getShortdescription(), newModel.getCategoryId(), newModel.getCreatedDate(),
                newModel.getCreatedBy());
    }

    @Override
    public void update(NewModel updatenew) {
        String sql = "UPDATE news SET title = ?, shortdescription = ?, content = ?, categoryid = ?, createddate = ?, modifieddate = ?, createdby = ?, modifiedby = ? WHERE id = ?";
        update(sql, updatenew.getTitle(), updatenew.getShortdescription(),
                updatenew.getContent(), updatenew.getCategoryId(), updatenew.getCreatedDate(),
                updatenew.getModifiedDate(), updatenew.getCreatedBy(), updatenew.getModifiedBy(), updatenew.getId());
    }

    @Override
    public NewModel findOne(Long id) {
        String sql = "SELECT * FROM news WHERE id = ?";
        List<NewModel> news = query(sql, new NewMapper(), id);
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM news WHERE id =?";
        update(sql, id);
    }

    @Override
    public List<NewModel> findAll(Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM news");
        if (pageable.getSorter() != null && StringUtils.isNotBlank(pageable.getSorter().getSortName()) && StringUtils.isNotBlank(pageable.getSorter().getSortBy())) {
            sql.append(" ORDER BY " + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy() + "");
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit() + "");
        }
        return query(sql.toString(), new NewMapper());
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM news";
        return count(sql);
    }
}
