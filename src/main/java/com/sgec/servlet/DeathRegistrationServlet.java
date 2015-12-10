/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgec.servlet;

import com.sgec.dbmanager.DBOperations;
import com.sgec.model.ChildRegistration;
import com.sgec.model.DeathRegistration;
import com.sgec.utility.ReturnStatus;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DeathRegistrationServlet", urlPatterns = {"/DeathRegistrationServlet"})
public class DeathRegistrationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DeathRegistrationServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession(true);
            ReturnStatus returnStatus = new ReturnStatus();
            String createdBy = session.getAttribute("user").toString();
            if (createdBy == null || createdBy.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }

            String action = request.getParameter("action") != null ? request.getParameter("action") : "";

            if (action.equals("delete")) {

                String deathRegistrationId = request.getParameter("id") != null ? request.getParameter("id") : "";
                DBOperations dBOperations = new DBOperations();
                returnStatus = dBOperations.deleteDeathRegistration(deathRegistrationId);

                log.debug("deathRegistrationDelete :: ID = " + deathRegistrationId);
                log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                log.info("ErrorCode : " + returnStatus.getErrorCode());
                log.info("StatusCode : " + returnStatus.getStatusCode());
                log.info("ExceptionMessage : " + returnStatus.getExceptionMessage());
                log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                log.debug("Return Status :: " + returnStatus.toString());

                int errorCode = (returnStatus != null ? returnStatus.getErrorCode() : 0);
                if (errorCode == 1) {
                    request.setAttribute("INFO_MSG", "Successfully Deleted.");
                    request.getRequestDispatcher("deathRegistration.jsp").forward(request, response);
                } else {
                    request.setAttribute("INFO_MSG", returnStatus.getMessage());
                    request.getRequestDispatcher("deathRegistration.jsp").forward(request, response);
                }

            } else {

                String citizenId = request.getParameter("citizenId");
                String hospitalName = request.getParameter("hospitalName");
                String country = request.getParameter("country");
                String city = request.getParameter("city");
                String address = request.getParameter("address");
                String other = request.getParameter("other");
                String dateOfDeath = request.getParameter("dateOfDeath");
                String reasonOfDeath = request.getParameter("reasonOfDeath");
                String diseaseName = request.getParameter("diseaseName");
                String accidentDetails = request.getParameter("accidentDetails");

                String userType = session.getAttribute("userType").toString();

                DeathRegistration deathRegistration = new DeathRegistration();

                deathRegistration.setAccidentDetails(accidentDetails);;
                deathRegistration.setAddress(address);
                deathRegistration.setCitizenId(Integer.parseInt(citizenId));
                deathRegistration.setCity(city);
                deathRegistration.setCountry(country);
                deathRegistration.setCreatedBy(createdBy);
                deathRegistration.setDateOfDeath(dateOfDeath);
                deathRegistration.setDiseaseName(diseaseName);
                deathRegistration.setHospitalName(hospitalName);
                deathRegistration.setModifiedDate(diseaseName);
                deathRegistration.setOther(other);
                deathRegistration.setPlaceOfDeath(dateOfDeath);
                deathRegistration.setReasonOfDeath(reasonOfDeath);
                deathRegistration.setStatus(other);

                DBOperations dBOperations = new DBOperations();
                returnStatus = dBOperations.insertDeathRegistration(deathRegistration);

                System.out.println(deathRegistration.toString());
                log.debug("deathRegistration :: " + deathRegistration.toString());
                log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                log.info("ErrorCode : " + returnStatus.getErrorCode());
                log.info("StatusCode : " + returnStatus.getStatusCode());
                log.info("ExceptionMessage : " + returnStatus.getExceptionMessage());
                log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                log.debug("Return Status :: " + returnStatus.toString());

                int errorCode = (returnStatus != null ? returnStatus.getErrorCode() : 0);
                if (errorCode == 1) {
                    request.setAttribute("INFO_MSG", "Successfully Register.");
                    request.getRequestDispatcher("deathRegistration.jsp").forward(request, response);
                } else {
                    request.setAttribute("INFO_MSG", returnStatus.getMessage());
                    request.getRequestDispatcher("deathRegistration.jsp").forward(request, response);
                }
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
