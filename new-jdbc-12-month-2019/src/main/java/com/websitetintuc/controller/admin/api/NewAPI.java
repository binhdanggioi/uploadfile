package com.websitetintuc.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.websitetintuc.model.NewModel;
import com.websitetintuc.model.UserModel;
import com.websitetintuc.service.INewService;
import com.websitetintuc.service.impl.NewService;
import com.websitetintuc.utils.HttpUtil;
import com.websitetintuc.utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {
    private INewService newService;

    public NewAPI() {
        newService = new NewService();
    }

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewModel newModel =  HttpUtil.of(request.getReader()).toModel(NewModel.class);
        newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
        newModel = newService.save(newModel);
        mapper.writeValue(response.getOutputStream(), newModel);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewModel updatenew = HttpUtil.of(request.getReader()).toModel(NewModel.class);
        updatenew.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
        updatenew = newService.update(updatenew);
        mapper.writeValue(response.getOutputStream(), updatenew);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewModel deletenew = HttpUtil.of(request.getReader()).toModel(NewModel.class);
        newService.delete(deletenew.getIds());
        mapper.writeValue(response.getOutputStream(), "{ }");

    }
    protected  void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
    }
}
