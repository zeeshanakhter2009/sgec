/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgec.servlet;

import com.google.gson.Gson;
import com.sgec.dbmanager.DBOperations;
import com.sgec.model.ChildRegistration;
import com.sgec.utility.ReturnStatus;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ZA
 */
@WebServlet(name = "ChildRegistrationServlet", urlPatterns = {"/ChildRegistrationServlet"})
public class ChildRegistrationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ChildRegistrationServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession(true);
            ReturnStatus returnStatus = new ReturnStatus();
            String motherName = request.getParameter("motherName");
            String fatherName = request.getParameter("fatherName");
            String motherNationality = request.getParameter("motherNationality");
            String fatherNationality = request.getParameter("fatherNationality");
            String grandMotherName = request.getParameter("grandMotherName");
            String grandFatherName = request.getParameter("grandFatherName");
            String dateofBirth = request.getParameter("dateofBirth");
            String weight = request.getParameter("weight");
            String height = request.getParameter("height");
            String skinColor = request.getParameter("skinColor");
            String eyeColor = request.getParameter("eyeColor");
            String country = request.getParameter("country");
            String city = request.getParameter("city");
            String hospitalName = request.getParameter("hospitalName");
            String createdBy = session.getAttribute("user").toString();
            String userType = session.getAttribute("userType").toString();

            ChildRegistration childRegistration = new ChildRegistration();
            childRegistration.setMotherName(motherName);
            childRegistration.setFatherName(fatherName);
            childRegistration.setDateofBirth(dateofBirth);
            childRegistration.setCity(city);
            childRegistration.setCountry(country);
            childRegistration.setCreatedBy(createdBy);
            childRegistration.setEyeColor(eyeColor);
            childRegistration.setSkinColor(skinColor);
            childRegistration.setFatherNationality(fatherNationality);
            childRegistration.setMotherNationality(motherNationality);
            childRegistration.setGrandFatherName(grandFatherName);
            childRegistration.setGrandMotherName(grandMotherName);
            childRegistration.setHeight(Double.parseDouble(height));
            childRegistration.setWeight(Double.parseDouble(weight));
            childRegistration.setHospital(hospitalName);
            DBOperations dBOperations = new DBOperations();
            returnStatus = dBOperations.insertChildRegistration(childRegistration);
            System.out.println(childRegistration.toString());
            log.debug("childRegistration :: " + childRegistration.toString());
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            log.info("ErrorCode : " + returnStatus.getErrorCode());
            log.info("StatusCode : " + returnStatus.getStatusCode());
            log.info("ExceptionMessage : " + returnStatus.getExceptionMessage());
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            log.debug("Return Status :: " + returnStatus.toString());

            int errorCode = (returnStatus != null ? returnStatus.getErrorCode() : 0);
            if (errorCode == 1) {
                request.setAttribute("INFO_MSG", "Successfully Register.");
                request.getRequestDispatcher("childRegistration.jsp").forward(request, response);

// return;
            } else {

                request.setAttribute("INFO_MSG", returnStatus.getMessage());
                request.getRequestDispatcher("childRegistration.jsp").forward(request, response);
                //  return;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
