package com.websitetintuc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.websitetintuc.model.NewModel;

public class NewMapper implements IRowMapper<NewModel> {

	@Override
	public NewModel mapRow(ResultSet resultset) {
		try {
			NewModel newmodel = new NewModel();
			newmodel.setId(resultset.getLong("id"));
			newmodel.setCategoryId(resultset.getLong("categoryid"));
			newmodel.setTitle(resultset.getString("title"));
			newmodel.setContent(resultset.getString("content"));
			newmodel.setShortdescription(resultset.getString("shortdescription"));
			newmodel.setThumbnail(resultset.getString("thumbnail"));
			newmodel.setCreatedDate(resultset.getTimestamp("createddate"));
			newmodel.setCreatedBy(resultset.getString("createdby"));
			if(resultset.getTimestamp("modifieddate") != null ) {
				newmodel.setModifiedDate(resultset.getTimestamp("modifieddate"));
			}
			if(resultset.getString("modifiedby") != null) {
				newmodel.setModifiedBy(resultset.getString("modifiedby"));
			}
			return newmodel;
		} catch (SQLException e) {
			return null;
		}
	}

}
