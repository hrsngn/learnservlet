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

@WebServlet("/users/*")
public class UserServlet extends AbstractController
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = getTemplatePath(req.getServletPath()+req.getPathInfo());
        if ("/list".equalsIgnoreCase(req.getPathInfo())){
            UserDao userDao = UserDaoImpl.getInstance();
//            List<User> users = userDao.findAll();
            List<User> users = userDao.findAllEm();
            req.setAttribute("users", users);
        }else if("/delete".equalsIgnoreCase(req.getPathInfo())){
            Long id = Long.parseLong(req.getParameter("id"));
            User user = new User(id,"","");
            UserDao userDao = UserDaoImpl.getInstance();
            boolean delete = userDao.delete(user);
            if(delete){
            }
            resp.sendRedirect(req.getContextPath()+req.getServletPath()+"/list");
            return;
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = req.getContextPath()+req.getServletPath()+"/list";
        if(req.getPathInfo() == null){
            UserDao userDao = UserDaoImpl.getInstance();
            User newUser = new User();
            newUser.setUserName(req.getParameter("username"));
            newUser.setPassword(req.getParameter("password"));
            userDao.saveEm(newUser);
        }
        resp.sendRedirect(path);
//        super.doPost(req, resp);
    }
}
