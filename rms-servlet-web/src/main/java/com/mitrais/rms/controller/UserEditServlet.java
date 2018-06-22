package com.mitrais.rms.controller;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/users/edit/*")
public class UserEditServlet extends AbstractController{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = getTemplatePath(req.getServletPath());
        Long id = Long.parseLong(req.getPathInfo().substring(1));
        UserDao userDao = UserDaoImpl.getInstance();
        Optional<User> users = userDao.find(id);
        if(users.isPresent()){
            req.setAttribute("users", users);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getContextPath()+"/users/list";
        UserDao userDao = UserDaoImpl.getInstance();
        User updateUser = new User(Long.parseLong(req.getParameter("id")),req.getParameter("username"),req.getParameter("password"));
        boolean update =userDao.update(updateUser);
        if(update){
            //TODO: send response success update
        }
        resp.sendRedirect(path);
    }
}
