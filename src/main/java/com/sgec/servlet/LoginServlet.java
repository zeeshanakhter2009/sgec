/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgec.servlet;

import com.sgec.dbmanager.DBOperations;
import com.sgec.utility.ReturnStatus;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String userID = "admin";
    private final String password = "admin";

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String userType = request.getParameter("userType");
        DBOperations dBOperations = new DBOperations();
        ReturnStatus returnStatus = dBOperations.getUserAccount(user, pwd);
        if (returnStatus.getErrorCode() > 0) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if (userType.equalsIgnoreCase("1")) {
                session.setAttribute("userType", "hospital");
            }
            if (userType.equalsIgnoreCase("2")) {
                session.setAttribute("userType", "townHall");
            }
            if (userType.equalsIgnoreCase("3")) {
                session.setAttribute("userType", "citizen");
            }

            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            response.sendRedirect("home.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }

    }

}
