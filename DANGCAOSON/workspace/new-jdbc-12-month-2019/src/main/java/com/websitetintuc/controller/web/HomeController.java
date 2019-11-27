package com.websitetintuc.controller.web;

import com.websitetintuc.model.UserModel;
import com.websitetintuc.service.ICategoryService;
import com.websitetintuc.service.IUserService;
import com.websitetintuc.service.impl.CategoryService;
import com.websitetintuc.service.impl.UserService;
import com.websitetintuc.utils.FormUltil;
import com.websitetintuc.utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat"})
public class HomeController extends HttpServlet {
    private ICategoryService categoryService;
    private IUserService userService;

    public HomeController() {
        userService = new UserService();
        categoryService = new CategoryService();
    }

    private static final long serialVersionUID = 2686801510274002166L;

    ResourceBundle bundle = ResourceBundle.getBundle("message");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            String message = request.getParameter("message");
            String alert = request.getParameter("alert");
            if (message != null && alert != null) {
                request.setAttribute("message", bundle.getString(message));
                request.setAttribute("alert", alert);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("logout")) {
            SessionUtil.getInstance().removeValue(request, "USERMODEL");
            response.sendRedirect(request.getContextPath() + "/trang-chu");
        } else {
            request.setAttribute("categories", categoryService.findAll());
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            UserModel usermodel = FormUltil.toModel(UserModel.class, request);
            usermodel = userService.findByUserNameAndPasswordAndStatus(usermodel.getUserName(), usermodel.getPassword(), 1);
            if (usermodel != null) {
                SessionUtil.getInstance().putValue(request, "USERMODEL", usermodel);
                if (usermodel.getRole().getCode().equals("USER")) {
                    response.sendRedirect(request.getContextPath() + "/trang-chu");
                } else if (usermodel.getRole().getCode().equals("ADMIN")) {
                    response.sendRedirect(request.getContextPath() + "/admin-home");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
            }
        }
    }
}
