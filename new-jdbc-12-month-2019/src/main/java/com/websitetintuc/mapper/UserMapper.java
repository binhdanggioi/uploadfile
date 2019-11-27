package com.websitetintuc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.websitetintuc.model.RoleModel;
import com.websitetintuc.model.UserModel;

public class UserMapper implements IRowMapper<UserModel> {

    @Override
    public UserModel mapRow(ResultSet resultset) {
        try {
            UserModel user = new UserModel();
            user.setId(resultset.getLong("id"));
            user.setUserName(resultset.getString("username"));
            user.setFullName(resultset.getString("fullname"));
            user.setPassword(resultset.getString("password"));
            user.setStatus(resultset.getInt("status"));
            try {
                RoleModel role = new RoleModel();
                role.setCode(resultset.getString("code"));
                role.setName(resultset.getString("name"));
                user.setRole(role);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

}
