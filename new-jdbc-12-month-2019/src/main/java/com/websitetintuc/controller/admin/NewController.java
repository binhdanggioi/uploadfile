package com.websitetintuc.controller.admin;

import com.websitetintuc.constant.SystemConstant;
import com.websitetintuc.model.NewModel;
import com.websitetintuc.paging.PageRequest;
import com.websitetintuc.paging.Pageable;
import com.websitetintuc.service.ICategoryService;
import com.websitetintuc.service.INewService;
import com.websitetintuc.service.impl.CategoryService;
import com.websitetintuc.service.impl.NewService;
import com.websitetintuc.sort.Sorter;
import com.websitetintuc.utils.FormUltil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet {

    private INewService newService;
    private ICategoryService categoryService;

    public NewController() {
        newService = new NewService();
        categoryService = new CategoryService();
    }

    private static final long serialVersionUID = 2686801510274002166L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewModel model = FormUltil.toModel(NewModel.class, request);
        String view = "";
        if (model.getType().equals(SystemConstant.LIST)) {
            Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItem(),
                    new Sorter(model.getSortName(), model.getSortBy()));
            model.setListResult(newService.findAll(pageable));
            model.setTotalItem(newService.getTotalItem());
            model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
            view = "/views/admin/new/list.jsp";
        } else if (model.getType().equals(SystemConstant.EDIT)) {
            if (model.getId() != null) {
                model = newService.findOne(model.getId());
            }
            request.setAttribute("categories",categoryService.findAll());
            view = "/views/admin/new/edit.jsp";
        }
        if(request.getParameter("message") != null){
            String messageResponse = "";
            String alert = "";
            String message = request.getParameter("message");
            if(message.equals("INSERT_SUCCESS")){
                messageResponse = "INSERT_SUCCESS";
                alert = "success";
            }else if(message.equals("UPDATE_SUCCESS")){
                messageResponse="UPDATE_SUCCESS";
                alert = "success";
            }else if(message.equals("DELETE_SUCCESS")){
                messageResponse = "DELETE_SUCCESS";
                alert = "success";
            }else if(message.equals("ERROR_SYSTEM")){
                messageResponse = "ERROR_SYSTEM";
                alert = "danger";
            }
            request.setAttribute("messageResponse", messageResponse);
            request.setAttribute("alert",alert);
        }
        request.setAttribute(SystemConstant.MODEL,model);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
