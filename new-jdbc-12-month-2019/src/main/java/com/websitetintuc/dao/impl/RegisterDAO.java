package com.websitetintuc.dao.impl;

import com.websitetintuc.model.UserModel;

import java.sql.*;
import java.util.ResourceBundle;

public class RegisterDAO {
    ResourceBundle bundle = ResourceBundle.getBundle("db");

    public Connection getConnection() {
        try {
            Class.forName(bundle.getString("driverName"));
            String url = bundle.getString("url");
            String user = bundle.getString("user");
            String password = bundle.getString("password");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    public static int register(UserModel userModel){
        int status = 1;
        Connection connection = null;
        try{
            PreparedStatement statement=connection.prepareStatement("insert into user values(?,?,?)");
            statement.setString(1,userModel.getUserName());
            statement.setString(2,userModel.getFullName());
            statement.setString(3,userModel.getPassword());

            status = statement.executeUpdate();
        }catch(Exception e){}

        return status;
    }
}
